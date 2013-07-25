package com.bibodha.magnetfactory.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.bibodha.magnetfactory.R;
import com.bibodha.magnetfactory.models.MagnetFactoryModel;
import com.bibodha.magnetfactory.models.Piece;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
	Paint bgPaint = null;
	Paint paint = null;
	float top = 0;
	float left = 0;
	float right = 70;
	float bottom = 50;
	public int _width = 0;
	public int _height = 0;
	public float xTouch = 0;
	public boolean touched = false;
	MagnetFactoryModel _mfModel = null;
	public int touchLocation = 0;
	Piece[][] _board = null;
	SurfaceHolder _holder = null;
	boolean surface = false;
	private int _score = 0;
	boolean once = false;
	private boolean _gameOver = false;

	private Bitmap leftMagnet = null;
	private Bitmap middleMagnet = null;
	private Bitmap rightMagnet = null;
	private Bitmap background = null;

	OnTouchedListner _onTouchedListener = null;
	SurfaceListener _onSurfaceListener = null;

	public GamePanel(Context context)
	{
		this(context, null);
	}

	public GamePanel(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public GamePanel(Context context, AttributeSet attrs, int def)
	{
		super(context, attrs, def);
		paint = new Paint();
		paint.setColor(Color.BLUE);
		bgPaint = new Paint();
		bgPaint.setColor(Color.WHITE);
		_holder = getHolder();
		_holder.addCallback(this);
		leftMagnet = BitmapFactory.decodeResource(context.getResources(), R.drawable.magnet_left);
		middleMagnet = BitmapFactory.decodeResource(context.getResources(), R.drawable.magnet_center);
		rightMagnet = BitmapFactory.decodeResource(context.getResources(), R.drawable.magnet_right);
		background = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipes);
	}

	public SurfaceHolder getMyHolder()
	{
		return _holder;
	}

	public void setGameOver(boolean status)
	{
		_gameOver = status;
	}

	public void doDraw(Canvas canvas)
	{
		if (!_gameOver)
		{
			if (!once)
			{
				once = true;
				_width = canvas.getWidth();
				_height = canvas.getHeight();
			}
			//canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(background, 0, 0, null);
			paint.setColor(Color.BLUE);
			paint.setTextSize(48);
			paint.setAntiAlias(true);
			canvas.drawText("" + _score, 20, 50, paint);
			for (int i = 0; i < _board.length; i++)
				for (int j = 0; j < _board[0].length; j++)
				{
					if (_board[i][j] != null)
					{
						Piece p = _board[i][j];
						left = leftMagnet.getWidth() * j;
						top = _height - (leftMagnet.getHeight() * i);
						int iconHeight = leftMagnet.getHeight();
						if (p.getValue() == 1)
							canvas.drawBitmap(leftMagnet, left, top - iconHeight, null);
						else if (p.getValue() == 2)
							canvas.drawBitmap(middleMagnet, left, top - iconHeight, null);
						else
							canvas.drawBitmap(rightMagnet, left, top - iconHeight, null);
					}
				}
		}
		else
		{
			canvas.drawColor(Color.argb(1, 0, 0, 0));
			paint.setColor(Color.RED);
			paint.setAntiAlias(true);
			paint.setTextSize(72);
			paint.setTextAlign(Align.CENTER);
			canvas.drawText("Game Over", _width/2, _height/2, paint);
			paint.setTextSize(24);
			paint.setColor(Color.WHITE);
			canvas.drawText("Your Score: " + _score, _width/2, _height/2+36, paint);
			canvas.drawText("Play Again", _width/2 - 72, _height/2 + 144, paint);
			canvas.drawText("Main Menu", _width/2+72, _height/2 + 144, paint);
		}
		

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
	{
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		if (_onSurfaceListener != null)
		{
			_onSurfaceListener.onSurfaceCreated(true);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		if (_onSurfaceListener != null)
			_onSurfaceListener.onSurfaceDestroyed(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			xTouch = event.getX();
			if (_onTouchedListener != null)
			{
				if (xTouch > _width / 2)
					_onTouchedListener.onTouched(1);
				else
					_onTouchedListener.onTouched(2);
			}
		}
		return true;
	}

	public void setBoard(Piece[][] board)
	{
		_board = board;
	}

	public void setScore(int score)
	{
		_score = score;
	}

	public interface OnTouchedListner
	{
		public void onTouched(int state);
	}

	public void setOnTouchedListener(OnTouchedListner listener)
	{
		_onTouchedListener = listener;
	}

	public interface SurfaceListener
	{
		public void onSurfaceCreated(boolean state);

		public void onSurfaceDestroyed(boolean state);
	}

	public void setOnSurfaceListener(SurfaceListener listener)
	{
		_onSurfaceListener = listener;
	}
}
