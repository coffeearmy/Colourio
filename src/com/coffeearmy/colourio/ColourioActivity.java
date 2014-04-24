package com.coffeearmy.colourio;

import com.coffeearmy.colourio.fragment.CustomCameraFragment;
import com.coffeearmy.colourio.fragment.StaggeredGridFragment;
import com.etsy.android.grid.StaggeredGridView;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class ColourioActivity extends ActionBarActivity {

	// UI elements
	private DrawerLayout mDrawerLayout = null;
	private ActionBarDrawerToggle mDrawerToggle = null;
	private RelativeLayout mLayoutDrawer;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_colourio_main);
		mTitle = "holaa";
		createDrawerMenu();

		// Set Fragment
		createFragments();
		// Display icons in action bar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void createFragments() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment =StaggeredGridFragment.newInstance(this, null);// new CustomCameraFragment();
		fm.beginTransaction()
				.replace(R.id.content_frame, fragment, "StaggeredGridView")
				.commit();

	}

	private void createDrawerMenu() {
		// Get layout drawer
		mLayoutDrawer = (RelativeLayout) findViewById(R.id.drawerContainer);

		// Set drawer Layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Configurate Drawer toggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, android.R.string.ok,
				android.R.string.cancel) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(mTitle);
				ActivityCompat.invalidateOptionsMenu(ColourioActivity.this);

			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mTitle);
				ActivityCompat.invalidateOptionsMenu(ColourioActivity.this);

			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.colourio_main, menu);
		return true;
	}

}
