package com.bibodha.magnetfactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MagnetFactory extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_magnet_factory);

		Button newGame = (Button) findViewById(R.id.newGame);
		//Button stats = (Button) findViewById(R.id.stats);
		Button howToPlay = (Button) findViewById(R.id.howToPlay);
		newGame.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Intent intent = new Intent();
				intent.setClass(MagnetFactory.this, NewGame.class);
				startActivity(intent);
			}
		});
		
//		stats.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View arg0)
//			{
//				// TODO Auto-generated method stub
//			}
//		});
		
		howToPlay.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MagnetFactory.this, HowToPlay.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_magnet_factory, menu);
		return true;
	}
}
