package com.example.basicappfragmentmission10;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements View.OnTouchListener {
	private static final String TAG = "Fragment1";
	FragmentCallback callback;
	TextView textView;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		if (context instanceof FragmentCallback) {
			callback = (FragmentCallback) context;
		} else {
			Log.d(TAG, "Activity is not FragmentCallback.");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

		Button button1 = rootView.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callback.onFragmentSelected(1, null);
			}
		});
		ViewPager pager = rootView.findViewById(R.id.pager);
		pager.setOffscreenPageLimit(3);

		CustomerPagerAdapter adapter = new CustomerPagerAdapter(getFragmentManager());

		for (int i = 0; i < 3; i++) {
			PageFragment page = createPage(i);
			adapter.addItem(page);
		}
//		pager.setAdapter(adapter);
		return rootView;
	}

	public void setAdapter(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

		View view = rootView.findViewById(R.id.textView);
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				int action = motionEvent.getAction();
				float curX = motionEvent.getX();
				float curY = motionEvent.getY();

				if (action == MotionEvent.ACTION_DOWN) {
					setText("손가락 눌림 : " + curX + "," + curY);
				} else if (action == MotionEvent.ACTION_MOVE) {
					setText("손가락 움직 : " + curX + "," + curY);
				} else if (action == MotionEvent.ACTION_UP) {
					setText("손가락 뗌 : " + curX + "," + curY);
				}
				return true;
			}
		});
	}

	public void setText(String data) {
		textView.append(data + "\n");
	}

	public PageFragment createPage(int index) {
		String name = "화면 " + index;
		PageFragment fragment = PageFragment.newInstance(name);

		return fragment;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	class CustomerPagerAdapter extends FragmentStatePagerAdapter {
		ArrayList<Fragment> items = new ArrayList<Fragment>();

		public CustomerPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public void addItem(Fragment item) {
			items.add(item);
		}

		@Override
		public Fragment getItem(int position) {
			return items.get(position);
		}

		@Override
		public int getCount() {
			return items.size();
		}
	}
}