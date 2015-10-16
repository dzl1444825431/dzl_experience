package com.dzl.test.viewPager;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SharedElementCallback;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

public class ViewPagerFragment2 extends Fragment {

	@Override
	public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
		System.out.println("resp1onse ViewPagerFragment2: public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) { start void ");

		super.dump(prefix, fd, writer, args);
		System.out.println("resp1onse ViewPagerFragment2: public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) { end void ");
	}

	@Override
	public boolean getAllowEnterTransitionOverlap() {

		System.out.println("resp1onse ViewPagerFragment2: public boolean getAllowEnterTransitionOverlap() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public boolean getAllowEnterTransitionOverlap() { end return ");
		return super.getAllowEnterTransitionOverlap();
	}

	@Override
	public boolean getAllowReturnTransitionOverlap() {

		System.out.println("resp1onse ViewPagerFragment2: public boolean getAllowReturnTransitionOverlap() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public boolean getAllowReturnTransitionOverlap() { end return ");
		return super.getAllowReturnTransitionOverlap();
	}

	@Override
	public Object getEnterTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getEnterTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getEnterTransition() { end return ");
		return super.getEnterTransition();
	}

	@Override
	public Object getExitTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getExitTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getExitTransition() { end return ");
		return super.getExitTransition();
	}

	@Override
	public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {

		System.out.println("resp1onse ViewPagerFragment2: public LayoutInflater getLayoutInflater(Bundle savedInstanceState) { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public LayoutInflater getLayoutInflater(Bundle savedInstanceState) { end return ");
		return super.getLayoutInflater(savedInstanceState);
	}

	@Override
	public LoaderManager getLoaderManager() {

		System.out.println("resp1onse ViewPagerFragment2: public LoaderManager getLoaderManager() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public LoaderManager getLoaderManager() { end return ");
		return super.getLoaderManager();
	}

	@Override
	public Object getReenterTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getReenterTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getReenterTransition() { end return ");
		return super.getReenterTransition();
	}

	@Override
	public Object getReturnTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getReturnTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getReturnTransition() { end return ");
		return super.getReturnTransition();
	}

	@Override
	public Object getSharedElementEnterTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getSharedElementEnterTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getSharedElementEnterTransition() { end return ");
		return super.getSharedElementEnterTransition();
	}

	@Override
	public Object getSharedElementReturnTransition() {

		System.out.println("resp1onse ViewPagerFragment2: public Object getSharedElementReturnTransition() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Object getSharedElementReturnTransition() { end return ");
		return super.getSharedElementReturnTransition();
	}

	@Override
	public boolean getUserVisibleHint() {

		System.out.println("resp1onse ViewPagerFragment2: public boolean getUserVisibleHint() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public boolean getUserVisibleHint() { end return ");
		return super.getUserVisibleHint();
	}

	@Override
	public View getView() {

		System.out.println("resp1onse ViewPagerFragment2: public View getView() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public View getView() { end return ");
		return super.getView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onActivityCreated(Bundle savedInstanceState) { start void ");

		super.onActivityCreated(savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment2: public void onActivityCreated(Bundle savedInstanceState) { end void ");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("resp1onse ViewPagerFragment2: public void onActivityResult(int requestCode, int resultCode, Intent data) { start void ");

		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("resp1onse ViewPagerFragment2: public void onActivityResult(int requestCode, int resultCode, Intent data) { end void ");
	}

	@Override
	public void onAttach(Activity activity) {
		System.out.println("resp1onse ViewPagerFragment2: public void onAttach(Activity activity) { start void ");

		super.onAttach(activity);
		System.out.println("resp1onse ViewPagerFragment2: public void onAttach(Activity activity) { end void ");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		System.out.println("resp1onse ViewPagerFragment2: public void onConfigurationChanged(Configuration newConfig) { start void ");

		super.onConfigurationChanged(newConfig);
		System.out.println("resp1onse ViewPagerFragment2: public void onConfigurationChanged(Configuration newConfig) { end void ");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		System.out.println("resp1onse ViewPagerFragment2: public boolean onContextItemSelected(MenuItem item) { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public boolean onContextItemSelected(MenuItem item) { end return ");
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onCreate(Bundle savedInstanceState) { start void ");

		super.onCreate(savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment2: public void onCreate(Bundle savedInstanceState) { end void ");
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {

		System.out.println("resp1onse ViewPagerFragment2: public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) { end return ");
		return super.onCreateAnimation(transit, enter, nextAnim);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		System.out.println("resp1onse ViewPagerFragment2: public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) { start void ");

		super.onCreateContextMenu(menu, v, menuInfo);
		System.out.println("resp1onse ViewPagerFragment2: public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) { end void ");
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		System.out.println("resp1onse ViewPagerFragment2: public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { start void ");

		super.onCreateOptionsMenu(menu, inflater);
		System.out.println("resp1onse ViewPagerFragment2: public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { end void ");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { start return ");
		TextView tv = new TextView(getActivity());
		tv.setText("fragment tv");

		System.out.println("resp1onse ViewPagerFragment2: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { end return  =1");
		return tv;
	}

	@Override
	public void onDestroy() {
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroy() { start void ");

		super.onDestroy();
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroy() { end void ");
	}

	@Override
	public void onDestroyOptionsMenu() {
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroyOptionsMenu() { start void ");

		super.onDestroyOptionsMenu();
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroyOptionsMenu() { end void ");
	}

	@Override
	public void onDestroyView() {
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroyView() { start void ");

		super.onDestroyView();
		System.out.println("resp1onse ViewPagerFragment2: public void onDestroyView() { end void ");
	}

	@Override
	public void onDetach() {
		System.out.println("resp1onse ViewPagerFragment2: public void onDetach() { start void ");

		super.onDetach();
		System.out.println("resp1onse ViewPagerFragment2: public void onDetach() { end void ");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		System.out.println("resp1onse ViewPagerFragment2: public void onHiddenChanged(boolean hidden) { start void ");

		super.onHiddenChanged(hidden);
		System.out.println("resp1onse ViewPagerFragment2: public void onHiddenChanged(boolean hidden) { end void ");
	}

	@Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) { start void ");

		super.onInflate(activity, attrs, savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment2: public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) { end void ");
	}

	@Override
	public void onLowMemory() {
		System.out.println("resp1onse ViewPagerFragment2: public void onLowMemory() { start void ");

		super.onLowMemory();
		System.out.println("resp1onse ViewPagerFragment2: public void onLowMemory() { end void ");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		System.out.println("resp1onse ViewPagerFragment2: public boolean onOptionsItemSelected(MenuItem item) { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public boolean onOptionsItemSelected(MenuItem item) { end return ");
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		System.out.println("resp1onse ViewPagerFragment2: public void onOptionsMenuClosed(Menu menu) { start void ");

		super.onOptionsMenuClosed(menu);
		System.out.println("resp1onse ViewPagerFragment2: public void onOptionsMenuClosed(Menu menu) { end void ");
	}

	@Override
	public void onPause() {
		System.out.println("resp1onse ViewPagerFragment2: public void onPause() { start void ");

		super.onPause();
		System.out.println("resp1onse ViewPagerFragment2: public void onPause() { end void ");
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		System.out.println("resp1onse ViewPagerFragment2: public void onPrepareOptionsMenu(Menu menu) { start void ");

		super.onPrepareOptionsMenu(menu);
		System.out.println("resp1onse ViewPagerFragment2: public void onPrepareOptionsMenu(Menu menu) { end void ");
	}

	@Override
	public void onResume() {
		System.out.println("resp1onse ViewPagerFragment2: public void onResume() { start void ");

		super.onResume();
		System.out.println("resp1onse ViewPagerFragment2: public void onResume() { end void ");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onSaveInstanceState(Bundle outState) { start void ");

		super.onSaveInstanceState(outState);
		System.out.println("resp1onse ViewPagerFragment2: public void onSaveInstanceState(Bundle outState) { end void ");
	}

	@Override
	public void onStart() {
		System.out.println("resp1onse ViewPagerFragment2: public void onStart() { start void ");

		super.onStart();
		System.out.println("resp1onse ViewPagerFragment2: public void onStart() { end void ");
	}

	@Override
	public void onStop() {
		System.out.println("resp1onse ViewPagerFragment2: public void onStop() { start void ");

		super.onStop();
		System.out.println("resp1onse ViewPagerFragment2: public void onStop() { end void ");
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onViewCreated(View view, Bundle savedInstanceState) { start void ");

		super.onViewCreated(view, savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment2: public void onViewCreated(View view, Bundle savedInstanceState) { end void ");
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewPagerFragment2: public void onViewStateRestored(Bundle savedInstanceState) { start void ");

		super.onViewStateRestored(savedInstanceState);
		System.out.println("resp1onse ViewPagerFragment2: public void onViewStateRestored(Bundle savedInstanceState) { end void ");
	}

	@Override
	public void registerForContextMenu(View view) {
		System.out.println("resp1onse ViewPagerFragment2: public void registerForContextMenu(View view) { start void ");

		super.registerForContextMenu(view);
		System.out.println("resp1onse ViewPagerFragment2: public void registerForContextMenu(View view) { end void ");
	}

	@Override
	public void setAllowEnterTransitionOverlap(boolean allow) {
		System.out.println("resp1onse ViewPagerFragment2: public void setAllowEnterTransitionOverlap(boolean allow) { start void ");

		super.setAllowEnterTransitionOverlap(allow);
		System.out.println("resp1onse ViewPagerFragment2: public void setAllowEnterTransitionOverlap(boolean allow) { end void ");
	}

	@Override
	public void setAllowReturnTransitionOverlap(boolean allow) {
		System.out.println("resp1onse ViewPagerFragment2: public void setAllowReturnTransitionOverlap(boolean allow) { start void ");

		super.setAllowReturnTransitionOverlap(allow);
		System.out.println("resp1onse ViewPagerFragment2: public void setAllowReturnTransitionOverlap(boolean allow) { end void ");
	}

	@Override
	public void setArguments(Bundle args) {
		System.out.println("resp1onse ViewPagerFragment2: public void setArguments(Bundle args) { start void ");

		super.setArguments(args);
		System.out.println("resp1onse ViewPagerFragment2: public void setArguments(Bundle args) { end void ");
	}

	@Override
	public void setEnterSharedElementCallback(SharedElementCallback callback) {
		System.out.println("resp1onse ViewPagerFragment2: public void setEnterSharedElementCallback(SharedElementCallback callback) { start void ");

		super.setEnterSharedElementCallback(callback);
		System.out.println("resp1onse ViewPagerFragment2: public void setEnterSharedElementCallback(SharedElementCallback callback) { end void ");
	}

	@Override
	public void setEnterTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setEnterTransition(Object transition) { start void ");

		super.setEnterTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setEnterTransition(Object transition) { end void ");
	}

	@Override
	public void setExitSharedElementCallback(SharedElementCallback callback) {
		System.out.println("resp1onse ViewPagerFragment2: public void setExitSharedElementCallback(SharedElementCallback callback) { start void ");

		super.setExitSharedElementCallback(callback);
		System.out.println("resp1onse ViewPagerFragment2: public void setExitSharedElementCallback(SharedElementCallback callback) { end void ");
	}

	@Override
	public void setExitTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setExitTransition(Object transition) { start void ");

		super.setExitTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setExitTransition(Object transition) { end void ");
	}

	@Override
	public void setHasOptionsMenu(boolean hasMenu) {
		System.out.println("resp1onse ViewPagerFragment2: public void setHasOptionsMenu(boolean hasMenu) { start void ");

		super.setHasOptionsMenu(hasMenu);
		System.out.println("resp1onse ViewPagerFragment2: public void setHasOptionsMenu(boolean hasMenu) { end void ");
	}

	@Override
	public void setInitialSavedState(SavedState state) {
		System.out.println("resp1onse ViewPagerFragment2: public void setInitialSavedState(SavedState state) { start void ");

		super.setInitialSavedState(state);
		System.out.println("resp1onse ViewPagerFragment2: public void setInitialSavedState(SavedState state) { end void ");
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		System.out.println("resp1onse ViewPagerFragment2: public void setMenuVisibility(boolean menuVisible) { start void ");

		super.setMenuVisibility(menuVisible);
		System.out.println("resp1onse ViewPagerFragment2: public void setMenuVisibility(boolean menuVisible) { end void ");
	}

	@Override
	public void setReenterTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setReenterTransition(Object transition) { start void ");

		super.setReenterTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setReenterTransition(Object transition) { end void ");
	}

	@Override
	public void setRetainInstance(boolean retain) {
		System.out.println("resp1onse ViewPagerFragment2: public void setRetainInstance(boolean retain) { start void ");

		super.setRetainInstance(retain);
		System.out.println("resp1onse ViewPagerFragment2: public void setRetainInstance(boolean retain) { end void ");
	}

	@Override
	public void setReturnTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setReturnTransition(Object transition) { start void ");

		super.setReturnTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setReturnTransition(Object transition) { end void ");
	}

	@Override
	public void setSharedElementEnterTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setSharedElementEnterTransition(Object transition) { start void ");

		super.setSharedElementEnterTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setSharedElementEnterTransition(Object transition) { end void ");
	}

	@Override
	public void setSharedElementReturnTransition(Object transition) {
		System.out.println("resp1onse ViewPagerFragment2: public void setSharedElementReturnTransition(Object transition) { start void ");

		super.setSharedElementReturnTransition(transition);
		System.out.println("resp1onse ViewPagerFragment2: public void setSharedElementReturnTransition(Object transition) { end void ");
	}

	@Override
	public void setTargetFragment(Fragment fragment, int requestCode) {
		System.out.println("resp1onse ViewPagerFragment2: public void setTargetFragment(Fragment fragment, int requestCode) { start void ");

		super.setTargetFragment(fragment, requestCode);
		System.out.println("resp1onse ViewPagerFragment2: public void setTargetFragment(Fragment fragment, int requestCode) { end void ");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		System.out.println("resp1onse ViewPagerFragment2: public void setUserVisibleHint(boolean isVisibleToUser) { start void ");

		super.setUserVisibleHint(isVisibleToUser);
		System.out.println("resp1onse ViewPagerFragment2: public void setUserVisibleHint(boolean isVisibleToUser) { end void ");
	}

	@Override
	public void startActivity(Intent intent) {
		System.out.println("resp1onse ViewPagerFragment2: public void startActivity(Intent intent) { start void ");

		super.startActivity(intent);
		System.out.println("resp1onse ViewPagerFragment2: public void startActivity(Intent intent) { end void ");
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		System.out.println("resp1onse ViewPagerFragment2: public void startActivityForResult(Intent intent, int requestCode) { start void ");

		super.startActivityForResult(intent, requestCode);
		System.out.println("resp1onse ViewPagerFragment2: public void startActivityForResult(Intent intent, int requestCode) { end void ");
	}

	@Override
	public String toString() {

		System.out.println("resp1onse ViewPagerFragment2: public String toString() { start return ");
		System.out.println("resp1onse ViewPagerFragment2: public String toString() { end return ");
		return super.toString();
	}

	@Override
	public void unregisterForContextMenu(View view) {
		System.out.println("resp1onse ViewPagerFragment2: public void unregisterForContextMenu(View view) { start void ");

		super.unregisterForContextMenu(view);
		System.out.println("resp1onse ViewPagerFragment2: public void unregisterForContextMenu(View view) { end void ");
	}

}
