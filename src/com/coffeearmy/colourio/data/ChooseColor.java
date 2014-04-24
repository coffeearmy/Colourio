package com.coffeearmy.colourio.data;

import java.io.Serializable;

import android.graphics.Color;

public class ChooseColor implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	public static enum ColorState { NOT_CHOOSE, INDEF, CHOOSE};
	public ColorState state=ColorState.INDEF;
	public int color;
	
	public ChooseColor(int c,ColorState s) {
		color=c;
		state=s;
	}
}
