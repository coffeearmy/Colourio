package com.coffeearmy.colourio.fragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.coffeearmy.colourio.R;
import com.coffeearmy.colourio.adapter.SampleAdapter;
import com.coffeearmy.colourio.data.SampleData;
import com.etsy.android.grid.StaggeredGridView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class StaggeredGridFragment extends Fragment implements
		AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

	public static final String TAG = "staggeredgrid";
	@InjectView(R.id.grid_view)
	StaggeredGridView mGridView;

	private LayoutInflater mLayoutInflater;

	private static StaggeredGridFragment staggeredFragment;
	private boolean mHasRequestedMore;
	private SampleAdapter mAdapter;
	private ArrayList<String> mData;

	public static Fragment newInstance(Context c, Bundle b) {
		if (staggeredFragment == null) {
			staggeredFragment = new StaggeredGridFragment();
		}
		return staggeredFragment;
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, final Bundle savedInstanceState) {
		View staggeredView = inflater.inflate(R.layout.staggered_layout,
				container, false);
		ButterKnife.inject(this, staggeredView);
		return staggeredView;
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		 mGridView = (StaggeredGridView)
		 getView().findViewById(R.id.grid_view);

		if (savedInstanceState == null) {

		}

		if (mAdapter == null) {
			mAdapter = new SampleAdapter(getActivity(), R.id.txt_line1);
		}

		if (mData == null) {
			mData = SampleData.generateSampleData();
		}

		for (String data : mData) {
			mAdapter.add(data);
		}

		mGridView.setAdapter(mAdapter);
		mGridView.setOnScrollListener(this);
		mGridView.setOnItemClickListener(this);
	}

	@Override
	public void onScrollStateChanged(final AbsListView view,
			final int scrollState) {
		Log.d(TAG, "onScrollStateChanged:" + scrollState);
	}

	@Override
	public void onScroll(final AbsListView view, final int firstVisibleItem,
			final int visibleItemCount, final int totalItemCount) {
		Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem
				+ " visibleItemCount:" + visibleItemCount + " totalItemCount:"
				+ totalItemCount);
		// our handling
		if (!mHasRequestedMore) {
			int lastInScreen = firstVisibleItem + visibleItemCount;
			if (lastInScreen >= totalItemCount) {
				Log.d(TAG, "onScroll lastInScreen - so load more");
				mHasRequestedMore = true;
				onLoadMoreItems();
			}
		}
	}

	private void onLoadMoreItems() {
		final ArrayList<String> sampleData = SampleData.generateSampleData();
		for (String data : sampleData) {
			mAdapter.add(data);
		}
		// stash all the data in our backing store
		mData.addAll(sampleData);
		// notify the adapter that we can update now
		mAdapter.notifyDataSetChanged();
		mHasRequestedMore = false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		Toast.makeText(getActivity(), "Item Clicked: " + position,
				Toast.LENGTH_SHORT).show();
	}
}
