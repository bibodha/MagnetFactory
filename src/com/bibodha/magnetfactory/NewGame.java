package com.bibodha.magnetfactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

import com.bibodha.magnetfactory.models.MagnetFactoryModel;
import com.bibodha.magnetfactory.models.Piece;
import com.bibodha.magnetfactory.views.GamePanel;

public class NewGame extends Activity implements GamePanel.OnTouchedListner, GamePanel.SurfaceListener,
		MagnetFactoryModel.OnGameStatusListener
{
	private GamePanel _panel = null;
	private MagnetFactoryModel _mfModel = null;
	private GamePanelThread _thread = null;
	private Piece[][] _board = null;
	private boolean _gameOver = false;

	final int FRAMES_PER_SECOND = 60;
	final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	boolean surfaceCreated = false;
	final int LEFT = 1;
	final int RIGHT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		_panel = new GamePanel(this);
		_mfModel = new MagnetFactoryModel();
		SurfaceHolder holder = _panel.getMyHolder();
		_thread = new GamePanelThread(_panel, holder);
		_panel.setOnTouchedListener(this);
		_panel.setOnSurfaceListener(this);
		_mfModel.setOnGameStatusListener(this);
		setContentView(_panel);
	}

	@Override
	public void onTouched(int state)
	{
		if (!_gameOver)
		{
			_thread.setTouched(state);
		}
		else
		{
			if (state == 1)
			{
				Intent intent = new Intent();
				intent.setClass(this, MagnetFactory.class);
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(this, NewGame.class);
				startActivity(intent);
			}
		}
	}

	@Override
	public void onSurfaceCreated(boolean state)
	{
		if (state)
		{
			_panel.setWillNotDraw(false);
			_thread.setRunning(true);
			_thread.start();
		}

	}

	@Override
	public void onSurfaceDestroyed(boolean state)
	{
		if (state)
		{
			try
			{
				_thread.setRunning(false);
				_thread.join();
			}
			catch (InterruptedException e)
			{

			}
		}
	}

	@Override
	public void onGameOver(boolean status, int score)
	{
		if (status)
		{
			_gameOver = true;
			_thread.gameOver(true);
			_panel.setGameOver(true);
		}
	}

	public class GamePanelThread extends Thread
	{

		private SurfaceHolder _surfaceHolder = null;
		private GamePanel _gamePanel = null;
		private boolean _run = false;
		private boolean _pause = false;
		private boolean _gameOver = false;
		private boolean _touched = false;
		private int _touchLocation = 0;
		
		final int FRAMES_PER_SECOND = 60;
		final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

		public GamePanelThread(GamePanel gamePanel, SurfaceHolder holder)
		{
			_gamePanel = gamePanel;
			_surfaceHolder = holder;
		}

		public void setRunning(boolean run)
		{
			_run = run;
		}

		public void pause(boolean state)
		{
			_pause = state;
		}

		public void gameOver(boolean state)
		{
			_gameOver = state;
		}
		
		public void setTouched(int location)
		{
			_touched = true;
			_touchLocation = location;
		}
		
		@Override
		public void run()
		{
			long nextDrawTick = SystemClock.uptimeMillis();

			while (_run)
			{
				nextDrawTick += SKIP_TICKS;
				long sleepTime = nextDrawTick - SystemClock.uptimeMillis();
				if (sleepTime >= 0)
					try
					{
						Thread.sleep(sleepTime);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				if(_touched)
				{
					if(_touchLocation == 1)
						_mfModel.shiftRight();
					else
						_mfModel.shiftLeft();
					_touched = false;
				}
				if (!_gameOver)
					_mfModel.update();

				_board = _mfModel.getBoard();
				int score = _mfModel.getScore();
				_panel.setScore(score);
				_panel.setBoard(_board);

				while (_pause)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				Canvas canvas = null;
				try
				{
					canvas = _surfaceHolder.lockCanvas(null);
					synchronized (_surfaceHolder)
					{
						_gamePanel.doDraw(canvas);
					}
				}
				finally
				{
					if (canvas != null)
					{
						_surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
	}
}
