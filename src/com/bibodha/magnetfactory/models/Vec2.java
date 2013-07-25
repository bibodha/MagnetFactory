package com.bibodha.magnetfactory.models;

public class Vec2
{
	float x = 0;
	float y = 0;

	public Vec2()
	{

	}

	public Vec2(float inX, float inY)
	{
		x = inX;
		y = inY;
	}

	public Vec2(Vec2 v)
	{
		x = v.x;
		y = v.y;
	}

	public Vec2 clone()
	{
		return new Vec2(x, y);
	}

	public Vec2 add(float inX, float inY)
	{
		x += inX;
		y += inY;

		return this;
	}

	public Vec2 add(Vec2 v)
	{
		x += v.x;
		y += v.y;

		return this;
	}

	public Vec2 sub(float inX, float inY)
	{
		x -= inX;
		y -= inY;
		return this;
	}

	public Vec2 sub(Vec2 v)
	{
		x -= v.x;
		y -= v.y;
		return this;
	}

	public Vec2 mul(float n)
	{
		x *= n;
		y *= n;
		return this;
	}

	public Vec2 div(float n)
	{
		x /= n;
		y /= n;
		return this;
	}

	public Vec2 set(float inX, float inY)
	{
		x = inX;
		y = inY;

		return this;
	}

	public Vec2 set(Vec2 v)
	{
		x = v.x;
		y = v.y;

		return this;
	}

	public float square(float n)
	{
		return n * n;
	}

	public float sqLength()
	{
		return square(x) + square(y);
	}

	public float length()
	{
		return (float) Math.sqrt(sqLength());
	}

	public float angle()
	{
		return (float) Math.atan2(y, x);
	}

	public Vec2 rotate(float r)
	{
		double angle = Math.atan2(y, x);
		float length = length();

		x = (float) Math.cos(angle + r) * length;
		y = (float) Math.sin(angle + r) * length;

		return this;
	}

	public boolean equals(Vec2 v)
	{
		return (v.x == this.x) && (v.y == this.y);
	}
}
