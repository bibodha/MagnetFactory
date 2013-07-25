package com.bibodha.magnetfactory.models;

public class Piece
{
	// Vec2 _pos = null;
	int _height = 0;
	int _width = 0;
	
	private Vec2 _pos = null;
	private boolean _complete = false;
	private int _value = 0;
	private boolean _falling = false;
	private Piece _nextPiece = null;
	private Piece _prevPiece = null;
	
	final int LEFT = 1;
	final int MIDDLE = 2;
	final int RIGHT = 3;

	public Piece()
	{

	}

	public boolean getComplete()
	{
		return _complete;
	}

	public void setComplete(boolean _complete)
	{
		this._complete = _complete;
	}

	public int getValue()
	{
		return _value;
	}

	public void setValue(int _value)
	{
		this._value = _value;
	}

	public boolean getFalling()
	{
		return _falling;
	}

	public void setFalling(boolean _falling)
	{
		this._falling = _falling;
	}
	
	public void setPos(int x, int y)
	{
		_pos = new Vec2(x, y);
	}
	
	public Vec2 getPos()
	{
		return _pos;
	}
	
	public void setWidth(int width)
	{
		_width = width;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public void setHeight(int height)
	{
		_height = height;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	@Override
	public String toString()
	{
		return "Value: " + _value + ", Falling: " + _falling + ".";
	}

	public Piece getNextPiece()
	{
		return _nextPiece;
	}

	public void setNextPiece(Piece nextPiece)
	{
		_nextPiece = nextPiece;
	}

	public Piece getPrevPiece()
	{
		return _prevPiece;
	}

	public void setPrevPiece(Piece prevPiece)
	{
		_prevPiece = prevPiece;
	}
}
