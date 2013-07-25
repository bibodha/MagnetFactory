package com.bibodha.magnetfactory.models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;
import android.provider.OpenableColumns;
import android.text.format.Time;

public class StatsReadWrite
{
	private String FILENAME = "stats";
	private int _lastScore = 0;
	private int _highScore = 0;
	private Time _longestPlayTime = null;
	private Time _lastPlayTime = null;
	private Time _totalPlayTime = null;
	private int _bricksCleared = 0;
	
	private String _toWrite = "";
	FileOutputStream fos = null;
	
	public StatsReadWrite(Context context)
	{
		try
		{
			fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLastScore()
	{
		return _lastScore;
	}

	public void setLastScore(int lastScore)
	{
		_lastScore = lastScore;
	}
	
	public int getHighScore()
	{
		return _highScore;
	}
	
	public Time getLongestPlayTime()
	{
		return _longestPlayTime;
	}
	
	public Time getLastPlayTime()
	{
		return _lastPlayTime;
	}
	
	public Time getTotalPlayTime()
	{
		return _totalPlayTime;
	}
	
	public int getTotalBricksCleared()
	{
		return _bricksCleared;
	}
	
	public void setBricksCleared(int bricks)
	{
		_bricksCleared += bricks;
	}

}
