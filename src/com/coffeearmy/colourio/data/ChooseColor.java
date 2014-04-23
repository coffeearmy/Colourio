package com.coffeearmy.colourio.data;

import java.io.Serializable;

import android.graphics.Color;

public class ChooseColor implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	public enum ColorState { NOT_CHOOSE, INDEF, CHOOSE};
	ColorState state;
	Color color;
}
