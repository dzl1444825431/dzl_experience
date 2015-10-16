package com.dzl.test.viewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerFragment extends Fragment {

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		System.out.println("resp1onse ViewPagerFragment: public void setUserVisibleHint(boolean isVisibleToUser) { start void " + isVisibleToUser);
		
		super.setUserVisibleHint(isVisibleToUser);
		System.out.println("resp1onse ViewPagerFragment: public void setUserVisibleHint(boolean isVisibleToUser) { end void ");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment: public void onCreate(Bundle savedInstanceState) { start void ");
		
		super.onCreate(savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment: public void onCreate(Bundle savedInstanceState) { end void ");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		System.out.println("resp1onse ViewPagerFragment: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { start return ");
		System.out.println("resp1onse ViewPagerFragment: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { end return ");
		TextView tv = new TextView(getActivity());
		tv.setText("fragment tv 0");
		return tv;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment: public void onViewCreated(View view, Bundle savedInstanceState) { start void ");
		
		super.onViewCreated(view, savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment: public void onViewCreated(View view, Bundle savedInstanceState) { end void ");
	}

	@Override
	public void onStart() {
		System.out.println("resp1onse ViewPagerFragment: public void onStart() { start void ");
		
		super.onStart();
		System.out.println("resp1onse ViewPagerFragment: public void onStart() { end void ");
	}

	@Override
	public void onResume() {
		System.out.println("resp1onse ViewPagerFragment: public void onResume() { start void ");
		
		super.onResume();
		System.out.println("resp1onse ViewPagerFragment: public void onResume() { end void ");
	}

	@Override
	public void onPause() {
		System.out.println("resp1onse ViewPagerFragment: public void onPause() { start void ");
		
		super.onPause();
		System.out.println("resp1onse ViewPagerFragment: public void onPause() { end void ");
	}

	@Override
	public void onStop() {
		System.out.println("resp1onse ViewPagerFragment: public void onStop() { start void ");
		
		super.onStop();
		System.out.println("resp1onse ViewPagerFragment: public void onStop() { end void ");
	}

	@Override
	public void onDestroyView() {
		System.out.println("resp1onse ViewPagerFragment: public void onDestroyView() { start void ");
		
		super.onDestroyView();
		System.out.println("resp1onse ViewPagerFragment: public void onDestroyView() { end void ");
	}

	@Override
	public void onDestroy() {
		System.out.println("resp1onse ViewPagerFragment: public void onDestroy() { start void ");
		
		super.onDestroy();
		System.out.println("resp1onse ViewPagerFragment: public void onDestroy() { end void ");
	}

	

}
