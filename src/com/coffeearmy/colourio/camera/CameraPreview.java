package com.coffeearmy.colourio.camera;

import java.io.IOException;
import java.util.List;

import com.coffeearmy.colourio.fragment.CustomCameraFragment;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

//Code from http://www.41post.com/3470/programming/android-retrieving-the-camera-preview-as-a-pixel-array
public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback, PreviewCallback {

	private SurfaceHolder mHolder;
    private Camera mCamera;
    private Camera.Parameters mParameters;
    private byte[] mBuffer;
 
    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
 
    public CameraPreview(Context context) {
        super(context);
        init();
    }
 
    public void init() {
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//Log.i(TAG, "Preview: surfaceChanged() - size now " + w + "x" + h);
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        try {
            mParameters = mCamera.getParameters();
            mParameters.set("orientation","landscape");
            for (Integer i : mParameters.getSupportedPreviewFormats()) {
                //Log.i(TAG, "supported preview format: " + i);
            } 
 
            List<Size> sizes = mParameters.getSupportedPreviewSizes();
            for (Size size : sizes) {
                //Log.i(TAG, "supported preview size: " + size.width + "x" + size.height);
            }
            mCamera.setParameters(mParameters); // apply the changes
        } catch (Exception e) {
            // older phone - doesn't support these calls
        }
 
        //updateBufferSize(); // then use them to calculate
 
        Size p = mCamera.getParameters().getPreviewSize();
        //Log.i(TAG, "Preview: checking it was set: " + p.width + "x" + p.height); // DEBUG
        mCamera.startPreview();
    }
 
    public Parameters getCameraParameters(){
        return mCamera.getParameters();
    }
		
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		 // The Surface has been created, acquire the camera and tell it where to draw.
	    try {
	        mCamera = Camera.open(); // WARNING: without permission in Manifest.xml, crashes
	    }
	    catch (RuntimeException exception) {
	        //Log.i(TAG, "Exception on Camera.open(): " + exception.toString());
	        Toast.makeText(getContext(), "Camera broken, quitting " ,Toast.LENGTH_LONG).show();
	        // TODO: exit program
	    }
	 
	    try {
	        mCamera.setPreviewDisplay(holder);
	        //updateBufferSize();
	        mCamera.addCallbackBuffer(mBuffer); // where we'll store the image data
	        mCamera.setPreviewCallbackWithBuffer(new PreviewCallback() {
	            public synchronized void onPreviewFrame(byte[] data, Camera c) {
	 
	                if (mCamera != null) { // there was a race condition when onStop() was called..
	                    mCamera.addCallbackBuffer(mBuffer); // it was consumed by the call, add it back
	                }
	            }
	        });
	    } catch (Exception exception) {
	        //Log.e(TAG, "Exception trying to set preview");
	        if (mCamera != null){
	            mCamera.release();
	            mCamera = null;
	        }
	        // TODO: add more exception handling logic here
	    }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		 // Surface will be destroyed when we return, so stop the preview.
	    // Because the CameraDevice object is not a shared resource, it's very
	    // important to release it when the activity is paused.
	    //Log.i(TAG,"SurfaceDestroyed being called");
	    mCamera.stopPreview();
	    mCamera.release();
	    mCamera = null;
		
	}
}
