package com.coffeearmy.colourio.customviews;

import java.util.List;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.coffeearmy.colourio.R;
import com.coffeearmy.colourio.data.ChooseColor;

public class ColorSwitcher extends ViewFlipper {

	

	private View mViewColor;

	private List<ChooseColor> mColorList;

	private TextView mTextView;

	private RadioButton mRadioBSave;

	private RadioButton mRadioBDiscard;

	private int mIndexColor;

	public ColorSwitcher(Context context, LayoutInflater inflater,
			List<ChooseColor> colorList) {
		super(context);
		mIndexColor = 0;
		mColorList = colorList;
		mViewColor = inflater.inflate(R.layout.select_color, null);
		mTextView = (TextView) mViewColor.findViewById(R.id.txtColorCode);
		mRadioBSave = (RadioButton) mViewColor.findViewById(R.id.rdbSaveColor);
		mRadioBDiscard = (RadioButton) mViewColor
				.findViewById(R.id.rdbDiscardColor);

		
		prepareView(mIndexColor);
	}
	
	

	public void prepareView(int color) {
		if (color <= mColorList.size()) {
			ChooseColor aux = mColorList.get(color);
			if (aux != null) {
				mTextView.setBackgroundColor(aux.color);
				mTextView.setText(aux.color + "");

				switch (aux.state) {
				case CHOOSE:
					mRadioBSave.setChecked(true);
					break;
				case NOT_CHOOSE:
					mRadioBDiscard.setChecked(true);
					break;
				default:
					mRadioBSave.setChecked(false);
					mRadioBDiscard.setChecked(false);
					break;
				}
				removeView(mViewColor);
				addView(mViewColor);
			}
		}
	}

	
}
