package com.coffeearmy.colourio.fragment;



import java.util.ArrayList;
import java.util.List;

import com.coffeearmy.colourio.R;
import com.coffeearmy.colourio.customviews.ColorSwitcher;
import com.coffeearmy.colourio.data.ChooseColor;
import com.coffeearmy.colourio.data.ChooseColor.ColorState;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class SaveColorFragment extends Fragment {

	public static final String FRAGMENT_TAG = "save_color_dialog";

	
	private Animation animFlipInForeward;
	private Animation animFlipOutForeward;
	private Animation animFlipInBackward;
	private Animation animFlipOutBackward;
	
	public SaveColorFragment() {}
	
	
	public static Fragment newInstance() {
		SaveColorFragment f = new SaveColorFragment();
		
		//Pass arguments to the fragment
//		Bundle args = new Bundle();
//			args.putLong();
//			args.putString();
//			f.setArguments(args);
	

		return f;
	}

	private int mIndexColor;

	private ColorSwitcher mColorSwitcher;
	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, final Bundle savedInstanceState) {
		animFlipInForeward = AnimationUtils.loadAnimation(getActivity(),
				R.anim.flipin);
		animFlipOutForeward = AnimationUtils.loadAnimation(getActivity(),
				R.anim.flipout);
		animFlipInBackward = AnimationUtils.loadAnimation(getActivity(),
				R.anim.flipin_reverse);
		animFlipOutBackward = AnimationUtils.loadAnimation(getActivity(),
				R.anim.flipout_reverse);

		
		 mColorSwitcher= new ColorSwitcher(getActivity(),inflater, populateViewSwitcher() );
		mColorSwitcher.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return true;
			}
		});
		//Fill the dialog fields if is needed
//		if(savedInstanceState!=null){
//			restoreState(savedInstanceState);
//		}
	
		return mColorSwitcher;
	}

	private List<ChooseColor> populateViewSwitcher() {
		
		List<ChooseColor> mockList = new ArrayList<ChooseColor>();
		mockList.add(new ChooseColor(0x00FFFF,ColorState.INDEF));
		mockList.add(new ChooseColor(0xFF00FF,ColorState.INDEF));
		mockList.add(new ChooseColor(0xFFFF00,ColorState.INDEF));
		mockList.add(new ChooseColor(0xFFFFFF,ColorState.INDEF));
		mockList.add(new ChooseColor(0x0000FF,ColorState.INDEF));
		mockList.add(new ChooseColor(0xFF0000,ColorState.INDEF));
		mockList.add(new ChooseColor(0x00FF00,ColorState.INDEF));
		mockList.add(new ChooseColor(0x000000,ColorState.INDEF));
		return mockList;
		
		
	}
	
	private void SwipeRight() {
		mColorSwitcher.setInAnimation(animFlipInBackward);
		mColorSwitcher.setOutAnimation(animFlipOutBackward);
		if (mIndexColor < 7)
			mIndexColor = mIndexColor + 1;
		mColorSwitcher.prepareView(mIndexColor);
		// showPrevious();
	}

	private void SwipeLeft() {
		mColorSwitcher.setInAnimation(animFlipInForeward);
		mColorSwitcher.setOutAnimation(animFlipOutForeward);
		if (mIndexColor > 0)
			mIndexColor = mIndexColor - 1;
		mColorSwitcher.prepareView(mIndexColor);
		// showNext();
	}

	

	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float sensitvity = 50;
			if ((e1.getX() - e2.getX()) > sensitvity) {
				SwipeLeft();
			} else if ((e2.getX() - e1.getX()) > sensitvity) {
				SwipeRight();
			}

			return true;
		}

	};

	GestureDetector gestureDetector = new GestureDetector(
			simpleOnGestureListener);

	
}
