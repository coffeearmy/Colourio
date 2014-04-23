package com.coffeearmy.colourio.fragment;

import com.coffeearmy.colourio.R;
import com.coffeearmy.colourio.camera.CameraPreview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/** Fragment use the camera and store the selected pixel color */
public class CustomCameraFragment extends Fragment {

	private static View rootView;
	private CameraPreview mPreview;

	public CustomCameraFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.camera_layout, container, false);
		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(getActivity());
		FrameLayout preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);
		preview.addView(mPreview);
		return rootView;
	}

}
