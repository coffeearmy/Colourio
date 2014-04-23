package com.coffeearmy.colourio.fragment;



import com.coffeearmy.colourio.customviews.ColorSwitcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class SaveColorDialog extends DialogFragment {

	public static SaveColorDialog newInstance() {
		SaveColorDialog f = new SaveColorDialog();

		//Pass arguments to the fragment
//		Bundle args = new Bundle();
//			args.putLong();
//			args.putString();
//			f.setArguments(args);
	

		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = getActivity().getLayoutInflater();
		ViewFlipper colorSwitcher= new ColorSwitcher(getActivity());
		//populateViewSwitcher(colorSwitcher,getArguments());
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				getActivity());
		
		// Set buttons to the dialog
			alertBuilder
			.setPositiveButton(android.R.string.ok,null)
			.setNegativeButton(android.R.string.cancel, null);
		
		//Fill the dialog fields if is needed
//		if(savedInstanceState!=null){
//			restoreState(savedInstanceState);
//		}

		alertBuilder.setView(colorSwitcher);
		Dialog customDialog = alertBuilder.create();
		customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return customDialog;
	}

	private void populateViewSwitcher(ViewSwitcher colorSwitcher, Bundle bundle) {
		
		
	}
}
