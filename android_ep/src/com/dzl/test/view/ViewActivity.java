package com.dzl.test.view;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;

public class ViewActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewActivity: protected void onCreate(Bundle savedInstanceState) { start void ");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		MotionEvent motionEvent;
		System.out.println("resp1onse ViewActivity: protected void onCreate(Bundle savedInstanceState) { end void ");
	}

	@Override
	public Intent getIntent() {

		System.out.println("resp1onse ViewActivity: public Intent getIntent() { start return ");
		System.out.println("resp1onse ViewActivity: public Intent getIntent() { end return ");
		return super.getIntent();
	}

	@Override
	public void setIntent(Intent newIntent) {
		System.out.println("resp1onse ViewActivity: public void setIntent(Intent newIntent) { start void ");

		super.setIntent(newIntent);
		System.out.println("resp1onse ViewActivity: public void setIntent(Intent newIntent) { end void ");
	}

	@Override
	public WindowManager getWindowManager() {

//		System.out.println("resp1onse ViewActivity: public WindowManager getWindowManager() { start return ");
//		System.out.println("resp1onse ViewActivity: public WindowManager getWindowManager() { end return ");
		return super.getWindowManager();
	}

	@Override
	public Window getWindow() {

//		System.out.println("resp1onse ViewActivity: public Window getWindow() { start return ");
//		System.out.println("resp1onse ViewActivity: public Window getWindow() { end return ");
		return super.getWindow();
	}

	@Override
	public LoaderManager getLoaderManager() {

		System.out.println("resp1onse ViewActivity: public LoaderManager getLoaderManager() { start return ");
		System.out.println("resp1onse ViewActivity: public LoaderManager getLoaderManager() { end return ");
		return super.getLoaderManager();
	}

	@Override
	public View getCurrentFocus() {

		System.out.println("resp1onse ViewActivity: public View getCurrentFocus() { start return ");
		System.out.println("resp1onse ViewActivity: public View getCurrentFocus() { end return ");
		return super.getCurrentFocus();
	}

	

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewActivity: protected void onRestoreInstanceState(Bundle savedInstanceState) { start void ");

		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("resp1onse ViewActivity: protected void onRestoreInstanceState(Bundle savedInstanceState) { end void ");
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		System.out.println("resp1onse ViewActivity: protected void onPostCreate(Bundle savedInstanceState) { start void ");

		super.onPostCreate(savedInstanceState);
		System.out.println("resp1onse ViewActivity: protected void onPostCreate(Bundle savedInstanceState) { end void ");
	}

	@Override
	protected void onStart() {
		System.out.println("resp1onse ViewActivity: protected void onStart() { start void ");

		super.onStart();
		System.out.println("resp1onse ViewActivity: protected void onStart() { end void ");
	}

	@Override
	protected void onRestart() {
		System.out.println("resp1onse ViewActivity: protected void onRestart() { start void ");

		super.onRestart();
		System.out.println("resp1onse ViewActivity: protected void onRestart() { end void ");
	}

	@Override
	protected void onResume() {
		System.out.println("resp1onse ViewActivity: protected void onResume() { start void ");

		super.onResume();
		System.out.println("resp1onse ViewActivity: protected void onResume() { end void ");
	}

	@Override
	protected void onPostResume() {
		System.out.println("resp1onse ViewActivity: protected void onPostResume() { start void ");

		super.onPostResume();
		System.out.println("resp1onse ViewActivity: protected void onPostResume() { end void ");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		System.out.println("resp1onse ViewActivity: protected void onNewIntent(Intent intent) { start void ");

		super.onNewIntent(intent);
		System.out.println("resp1onse ViewActivity: protected void onNewIntent(Intent intent) { end void ");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		System.out.println("resp1onse ViewActivity: protected void onSaveInstanceState(Bundle outState) { start void ");

		super.onSaveInstanceState(outState);
		System.out.println("resp1onse ViewActivity: protected void onSaveInstanceState(Bundle outState) { end void ");
	}

	@Override
	protected void onPause() {
		System.out.println("resp1onse ViewActivity: protected void onPause() { start void ");

		super.onPause();
		System.out.println("resp1onse ViewActivity: protected void onPause() { end void ");
	}

	@Override
	protected void onUserLeaveHint() {
		System.out.println("resp1onse ViewActivity: protected void onUserLeaveHint() { start void ");

		super.onUserLeaveHint();
		System.out.println("resp1onse ViewActivity: protected void onUserLeaveHint() { end void ");
	}

	@Override
	public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {

		System.out.println("resp1onse ViewActivity: public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) { end return ");
		return super.onCreateThumbnail(outBitmap, canvas);
	}

	@Override
	public CharSequence onCreateDescription() {

		System.out.println("resp1onse ViewActivity: public CharSequence onCreateDescription() { start return ");
		System.out.println("resp1onse ViewActivity: public CharSequence onCreateDescription() { end return ");
		return super.onCreateDescription();
	}

	@Override
	protected void onStop() {
		System.out.println("resp1onse ViewActivity: protected void onStop() { start void ");

		super.onStop();
		System.out.println("resp1onse ViewActivity: protected void onStop() { end void ");
	}

	@Override
	protected void onDestroy() {
		System.out.println("resp1onse ViewActivity: protected void onDestroy() { start void ");

		super.onDestroy();
		System.out.println("resp1onse ViewActivity: protected void onDestroy() { end void ");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		System.out.println("resp1onse ViewActivity: public void onConfigurationChanged(Configuration newConfig) { start void ");

		super.onConfigurationChanged(newConfig);
		System.out.println("resp1onse ViewActivity: public void onConfigurationChanged(Configuration newConfig) { end void ");
	}

	@Override
	public int getChangingConfigurations() {

		System.out.println("resp1onse ViewActivity: public int getChangingConfigurations() { start return ");
		System.out.println("resp1onse ViewActivity: public int getChangingConfigurations() { end return ");
		return super.getChangingConfigurations();
	}

	@Override
	public void onLowMemory() {
		System.out.println("resp1onse ViewActivity: public void onLowMemory() { start void ");

		super.onLowMemory();
		System.out.println("resp1onse ViewActivity: public void onLowMemory() { end void ");
	}

	@Override
	public FragmentManager getFragmentManager() {

		System.out.println("resp1onse ViewActivity: public FragmentManager getFragmentManager() { start return ");
		System.out.println("resp1onse ViewActivity: public FragmentManager getFragmentManager() { end return ");
		return super.getFragmentManager();
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		System.out.println("resp1onse ViewActivity: public void onAttachFragment(Fragment fragment) { start void ");

		super.onAttachFragment(fragment);
		System.out.println("resp1onse ViewActivity: public void onAttachFragment(Fragment fragment) { end void ");
	}

	@Override
	public View findViewById(int id) {

		System.out.println("resp1onse ViewActivity: public View findViewById(int id) { start return ");
		System.out.println("resp1onse ViewActivity: public View findViewById(int id) { end return ");
		return super.findViewById(id);
	}

	@Override
	public ActionBar getActionBar() {

		System.out.println("resp1onse ViewActivity: public ActionBar getActionBar() { start return ");
		System.out.println("resp1onse ViewActivity: public ActionBar getActionBar() { end return ");
		return super.getActionBar();
	}

	@Override
	public void setContentView(int layoutResID) {
		System.out.println("resp1onse ViewActivity: public void setContentView(int layoutResID) { start void ");

		super.setContentView(layoutResID);
		System.out.println("resp1onse ViewActivity: public void setContentView(int layoutResID) { end void ");
	}

	@Override
	public void setContentView(View view) {
		System.out.println("resp1onse ViewActivity: public void setContentView(View view) { start void ");

		super.setContentView(view);
		System.out.println("resp1onse ViewActivity: public void setContentView(View view) { end void ");
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		System.out.println("resp1onse ViewActivity: public void setContentView(View view, LayoutParams params) { start void ");

		super.setContentView(view, params);
		System.out.println("resp1onse ViewActivity: public void setContentView(View view, LayoutParams params) { end void ");
	}

	@Override
	public void addContentView(View view, LayoutParams params) {
		System.out.println("resp1onse ViewActivity: public void addContentView(View view, LayoutParams params) { start void ");

		super.addContentView(view, params);
		System.out.println("resp1onse ViewActivity: public void addContentView(View view, LayoutParams params) { end void ");
	}

	@Override
	public void setFinishOnTouchOutside(boolean finish) {
		System.out.println("resp1onse ViewActivity: public void setFinishOnTouchOutside(boolean finish) { start void ");

		super.setFinishOnTouchOutside(finish);
		System.out.println("resp1onse ViewActivity: public void setFinishOnTouchOutside(boolean finish) { end void ");
	}

	InputEvent o;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onKeyDown(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onKeyDown(int keyCode, KeyEvent event) { end return ");
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onKeyLongPress(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onKeyLongPress(int keyCode, KeyEvent event) { end return ");
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onKeyUp(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onKeyUp(int keyCode, KeyEvent event) { end return ");
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { end return ");
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public void onBackPressed() {
		System.out.println("resp1onse ViewActivity: public void onBackPressed() { start void ");

		super.onBackPressed();
		System.out.println("resp1onse ViewActivity: public void onBackPressed() { end void ");
	}

	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onKeyShortcut(int keyCode, KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onKeyShortcut(int keyCode, KeyEvent event) { end return ");
		return super.onKeyShortcut(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onTouchEvent(MotionEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onTouchEvent(MotionEvent event) { end return ");
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onTrackballEvent(MotionEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean onTrackballEvent(MotionEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onTrackballEvent(MotionEvent event) { end return ");
		return super.onTrackballEvent(event);
	}

	@Override
	public void onUserInteraction() {
		System.out.println("resp1onse ViewActivity: public void onUserInteraction() { start void ");

		super.onUserInteraction();
		System.out.println("resp1onse ViewActivity: public void onUserInteraction() { end void ");
	}

	@Override
	public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params) {
//		System.out.println("resp1onse ViewActivity: public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params) { start void ");

		super.onWindowAttributesChanged(params);
//		System.out.println("resp1onse ViewActivity: public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params) { end void ");
	}

	@Override
	public void onContentChanged() {
		System.out.println("resp1onse ViewActivity: public void onContentChanged() { start void ");

		super.onContentChanged();
		System.out.println("resp1onse ViewActivity: public void onContentChanged() { end void ");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		System.out.println("resp1onse ViewActivity: public void onWindowFocusChanged(boolean hasFocus) { start void ");

		super.onWindowFocusChanged(hasFocus);
		System.out.println("resp1onse ViewActivity: public void onWindowFocusChanged(boolean hasFocus) { end void ");
	}

	@Override
	public void onAttachedToWindow() {
		System.out.println("resp1onse ViewActivity: public void onAttachedToWindow() { start void ");

		super.onAttachedToWindow();
		System.out.println("resp1onse ViewActivity: public void onAttachedToWindow() { end void ");
	}

	@Override
	public void onDetachedFromWindow() {
		System.out.println("resp1onse ViewActivity: public void onDetachedFromWindow() { start void ");

		super.onDetachedFromWindow();
		System.out.println("resp1onse ViewActivity: public void onDetachedFromWindow() { end void ");
	}

	@Override
	public boolean hasWindowFocus() {

		System.out.println("resp1onse ViewActivity: public boolean hasWindowFocus() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean hasWindowFocus() { end return ");
		return super.hasWindowFocus();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean dispatchKeyEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean dispatchKeyEvent(KeyEvent event) { end return ");
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean dispatchKeyShortcutEvent(KeyEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean dispatchKeyShortcutEvent(KeyEvent event) { end return ");
		return super.dispatchKeyShortcutEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		System.out.println("resp1onse ViewActivity: public boolean dispatchTouchEvent(MotionEvent ev) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean dispatchTouchEvent(MotionEvent ev) { end return ");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean dispatchTrackballEvent(MotionEvent ev) {

		System.out.println("resp1onse ViewActivity: public boolean dispatchTrackballEvent(MotionEvent ev) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean dispatchTrackballEvent(MotionEvent ev) { end return ");
		return super.dispatchTrackballEvent(ev);
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {

		System.out.println("resp1onse ViewActivity: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) { end return ");
		return super.dispatchPopulateAccessibilityEvent(event);
	}

	@Override
	public View onCreatePanelView(int featureId) {

		System.out.println("resp1onse ViewActivity: public View onCreatePanelView(int featureId) { start return ");
		System.out.println("resp1onse ViewActivity: public View onCreatePanelView(int featureId) { end return ");
		return super.onCreatePanelView(featureId);
	}

	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {

		System.out.println("resp1onse ViewActivity: public boolean onCreatePanelMenu(int featureId, Menu menu) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onCreatePanelMenu(int featureId, Menu menu) { end return ");
		return super.onCreatePanelMenu(featureId, menu);
	}

	@Override
	public boolean onPreparePanel(int featureId, View view, Menu menu) {

		System.out.println("resp1onse ViewActivity: public boolean onPreparePanel(int featureId, View view, Menu menu) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onPreparePanel(int featureId, View view, Menu menu) { end return ");
		return super.onPreparePanel(featureId, view, menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {

		System.out.println("resp1onse ViewActivity: public boolean onMenuOpened(int featureId, Menu menu) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onMenuOpened(int featureId, Menu menu) { end return ");
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		System.out.println("resp1onse ViewActivity: public boolean onMenuItemSelected(int featureId, MenuItem item) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onMenuItemSelected(int featureId, MenuItem item) { end return ");
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onPanelClosed(int featureId, Menu menu) {
		System.out.println("resp1onse ViewActivity: public void onPanelClosed(int featureId, Menu menu) { start void ");

		super.onPanelClosed(featureId, menu);
		System.out.println("resp1onse ViewActivity: public void onPanelClosed(int featureId, Menu menu) { end void ");
	}

	@Override
	public void invalidateOptionsMenu() {
		System.out.println("resp1onse ViewActivity: public void invalidateOptionsMenu() { start void ");

		super.invalidateOptionsMenu();
		System.out.println("resp1onse ViewActivity: public void invalidateOptionsMenu() { end void ");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		System.out.println("resp1onse ViewActivity: public boolean onCreateOptionsMenu(Menu menu) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onCreateOptionsMenu(Menu menu) { end return ");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		System.out.println("resp1onse ViewActivity: public boolean onPrepareOptionsMenu(Menu menu) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onPrepareOptionsMenu(Menu menu) { end return ");
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		System.out.println("resp1onse ViewActivity: public boolean onOptionsItemSelected(MenuItem item) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onOptionsItemSelected(MenuItem item) { end return ");
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		System.out.println("resp1onse ViewActivity: public void onOptionsMenuClosed(Menu menu) { start void ");

		super.onOptionsMenuClosed(menu);
		System.out.println("resp1onse ViewActivity: public void onOptionsMenuClosed(Menu menu) { end void ");
	}

	@Override
	public void openOptionsMenu() {
		System.out.println("resp1onse ViewActivity: public void openOptionsMenu() { start void ");

		super.openOptionsMenu();
		System.out.println("resp1onse ViewActivity: public void openOptionsMenu() { end void ");
	}

	@Override
	public void closeOptionsMenu() {
		System.out.println("resp1onse ViewActivity: public void closeOptionsMenu() { start void ");

		super.closeOptionsMenu();
		System.out.println("resp1onse ViewActivity: public void closeOptionsMenu() { end void ");
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		System.out.println("resp1onse ViewActivity: public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) { start void ");

		super.onCreateContextMenu(menu, v, menuInfo);
		System.out.println("resp1onse ViewActivity: public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) { end void ");
	}

	@Override
	public void registerForContextMenu(View view) {
		System.out.println("resp1onse ViewActivity: public void registerForContextMenu(View view) { start void ");

		super.registerForContextMenu(view);
		System.out.println("resp1onse ViewActivity: public void registerForContextMenu(View view) { end void ");
	}

	@Override
	public void unregisterForContextMenu(View view) {
		System.out.println("resp1onse ViewActivity: public void unregisterForContextMenu(View view) { start void ");

		super.unregisterForContextMenu(view);
		System.out.println("resp1onse ViewActivity: public void unregisterForContextMenu(View view) { end void ");
	}

	@Override
	public void openContextMenu(View view) {
		System.out.println("resp1onse ViewActivity: public void openContextMenu(View view) { start void ");

		super.openContextMenu(view);
		System.out.println("resp1onse ViewActivity: public void openContextMenu(View view) { end void ");
	}

	@Override
	public void closeContextMenu() {
		System.out.println("resp1onse ViewActivity: public void closeContextMenu() { start void ");

		super.closeContextMenu();
		System.out.println("resp1onse ViewActivity: public void closeContextMenu() { end void ");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		System.out.println("resp1onse ViewActivity: public boolean onContextItemSelected(MenuItem item) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onContextItemSelected(MenuItem item) { end return ");
		return super.onContextItemSelected(item);
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		System.out.println("resp1onse ViewActivity: public void onContextMenuClosed(Menu menu) { start void ");

		super.onContextMenuClosed(menu);
		System.out.println("resp1onse ViewActivity: public void onContextMenuClosed(Menu menu) { end void ");
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		System.out.println("resp1onse ViewActivity: protected Dialog onCreateDialog(int id) { start return ");
		System.out.println("resp1onse ViewActivity: protected Dialog onCreateDialog(int id) { end return ");
		return super.onCreateDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {

		System.out.println("resp1onse ViewActivity: protected Dialog onCreateDialog(int id, Bundle args) { start return ");
		System.out.println("resp1onse ViewActivity: protected Dialog onCreateDialog(int id, Bundle args) { end return ");
		return super.onCreateDialog(id, args);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		System.out.println("resp1onse ViewActivity: protected void onPrepareDialog(int id, Dialog dialog) { start void ");

		super.onPrepareDialog(id, dialog);
		System.out.println("resp1onse ViewActivity: protected void onPrepareDialog(int id, Dialog dialog) { end void ");
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		System.out.println("resp1onse ViewActivity: protected void onPrepareDialog(int id, Dialog dialog, Bundle args) { start void ");

		super.onPrepareDialog(id, dialog, args);
		System.out.println("resp1onse ViewActivity: protected void onPrepareDialog(int id, Dialog dialog, Bundle args) { end void ");
	}

	@Override
	public boolean onSearchRequested() {

		System.out.println("resp1onse ViewActivity: public boolean onSearchRequested() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean onSearchRequested() { end return ");
		return super.onSearchRequested();
	}

	@Override
	public void startSearch(String initialQuery, boolean selectInitialQuery,
			Bundle appSearchData, boolean globalSearch) {

		super.startSearch(initialQuery, selectInitialQuery, appSearchData,
				globalSearch);
	}

	@Override
	public void triggerSearch(String query, Bundle appSearchData) {
		System.out.println("resp1onse ViewActivity: public void triggerSearch(String query, Bundle appSearchData) { start void ");

		super.triggerSearch(query, appSearchData);
		System.out.println("resp1onse ViewActivity: public void triggerSearch(String query, Bundle appSearchData) { end void ");
	}

	@Override
	public void takeKeyEvents(boolean get) {
		System.out.println("resp1onse ViewActivity: public void takeKeyEvents(boolean get) { start void ");

		super.takeKeyEvents(get);
		System.out.println("resp1onse ViewActivity: public void takeKeyEvents(boolean get) { end void ");
	}

	@Override
	public LayoutInflater getLayoutInflater() {

		System.out.println("resp1onse ViewActivity: public LayoutInflater getLayoutInflater() { start return ");
		System.out.println("resp1onse ViewActivity: public LayoutInflater getLayoutInflater() { end return ");
		return super.getLayoutInflater();
	}

	@Override
	public MenuInflater getMenuInflater() {

		System.out.println("resp1onse ViewActivity: public MenuInflater getMenuInflater() { start return ");
		System.out.println("resp1onse ViewActivity: public MenuInflater getMenuInflater() { end return ");
		return super.getMenuInflater();
	}

	@Override
	protected void onApplyThemeResource(Theme theme, int resid, boolean first) {
//		System.out.println("resp1onse ViewActivity: protected void onApplyThemeResource(Theme theme, int resid, boolean first) { start void ");

		super.onApplyThemeResource(theme, resid, first);
//		System.out.println("resp1onse ViewActivity: protected void onApplyThemeResource(Theme theme, int resid, boolean first) { end void ");
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		System.out.println("resp1onse ViewActivity: public void startActivityForResult(Intent intent, int requestCode) { start void ");

		super.startActivityForResult(intent, requestCode);
		System.out.println("resp1onse ViewActivity: public void startActivityForResult(Intent intent, int requestCode) { end void ");
	}

	@Override
	public void startIntentSenderForResult(IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags) throws SendIntentException {

		super.startIntentSenderForResult(intent, requestCode, fillInIntent,
				flagsMask, flagsValues, extraFlags);
	}

	@Override
	public void startActivity(Intent intent) {
		System.out.println("resp1onse ViewActivity: public void startActivity(Intent intent) { start void ");

		super.startActivity(intent);
		System.out.println("resp1onse ViewActivity: public void startActivity(Intent intent) { end void ");
	}

	@Override
	public void startActivities(Intent[] intents) {
		System.out.println("resp1onse ViewActivity: public void startActivities(Intent[] intents) { start void ");

		super.startActivities(intents);
		System.out.println("resp1onse ViewActivity: public void startActivities(Intent[] intents) { end void ");
	}

	@Override
	public void startIntentSender(IntentSender intent, Intent fillInIntent,
			int flagsMask, int flagsValues, int extraFlags)
			throws SendIntentException {

		super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
				extraFlags);
	}

	@Override
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {

		System.out.println("resp1onse ViewActivity: public boolean startActivityIfNeeded(Intent intent, int requestCode) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean startActivityIfNeeded(Intent intent, int requestCode) { end return ");
		return super.startActivityIfNeeded(intent, requestCode);
	}

	@Override
	public boolean startNextMatchingActivity(Intent intent) {

		System.out.println("resp1onse ViewActivity: public boolean startNextMatchingActivity(Intent intent) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean startNextMatchingActivity(Intent intent) { end return ");
		return super.startNextMatchingActivity(intent);
	}

	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {

		super.startActivityFromChild(child, intent, requestCode);
	}

	@Override
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {

		super.startActivityFromFragment(fragment, intent, requestCode);
	}

	@Override
	public void startIntentSenderFromChild(Activity child, IntentSender intent,
			int requestCode, Intent fillInIntent, int flagsMask,
			int flagsValues, int extraFlags) throws SendIntentException {

		super.startIntentSenderFromChild(child, intent, requestCode,
				fillInIntent, flagsMask, flagsValues, extraFlags);
	}

	@Override
	public void overridePendingTransition(int enterAnim, int exitAnim) {
		System.out.println("resp1onse ViewActivity: public void overridePendingTransition(int enterAnim, int exitAnim) { start void ");

		super.overridePendingTransition(enterAnim, exitAnim);
		System.out.println("resp1onse ViewActivity: public void overridePendingTransition(int enterAnim, int exitAnim) { end void ");
	}

	@Override
	public String getCallingPackage() {

		System.out.println("resp1onse ViewActivity: public String getCallingPackage() { start return ");
		System.out.println("resp1onse ViewActivity: public String getCallingPackage() { end return ");
		return super.getCallingPackage();
	}

	@Override
	public ComponentName getCallingActivity() {

		System.out.println("resp1onse ViewActivity: public ComponentName getCallingActivity() { start return ");
		System.out.println("resp1onse ViewActivity: public ComponentName getCallingActivity() { end return ");
		return super.getCallingActivity();
	}

	@Override
	public void setVisible(boolean visible) {
		System.out.println("resp1onse ViewActivity: public void setVisible(boolean visible) { start void ");

		super.setVisible(visible);
		System.out.println("resp1onse ViewActivity: public void setVisible(boolean visible) { end void ");
	}

	@Override
	public boolean isFinishing() {

		System.out.println("resp1onse ViewActivity: public boolean isFinishing() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean isFinishing() { end return ");
		return super.isFinishing();
	}

	@Override
	public boolean isChangingConfigurations() {

		System.out.println("resp1onse ViewActivity: public boolean isChangingConfigurations() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean isChangingConfigurations() { end return ");
		return super.isChangingConfigurations();
	}

	@Override
	public void recreate() {
		System.out.println("resp1onse ViewActivity: public void recreate() { start void ");

		super.recreate();
		System.out.println("resp1onse ViewActivity: public void recreate() { end void ");
	}

	@Override
	public void finish() {
		System.out.println("resp1onse ViewActivity: public void finish() { start void ");

		super.finish();
		System.out.println("resp1onse ViewActivity: public void finish() { end void ");
	}

	@Override
	public void finishFromChild(Activity child) {
		System.out.println("resp1onse ViewActivity: public void finishFromChild(Activity child) { start void ");

		super.finishFromChild(child);
		System.out.println("resp1onse ViewActivity: public void finishFromChild(Activity child) { end void ");
	}

	@Override
	public void finishActivity(int requestCode) {
		System.out.println("resp1onse ViewActivity: public void finishActivity(int requestCode) { start void ");

		super.finishActivity(requestCode);
		System.out.println("resp1onse ViewActivity: public void finishActivity(int requestCode) { end void ");
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		System.out.println("resp1onse ViewActivity: public void finishActivityFromChild(Activity child, int requestCode) { start void ");

		super.finishActivityFromChild(child, requestCode);
		System.out.println("resp1onse ViewActivity: public void finishActivityFromChild(Activity child, int requestCode) { end void ");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("resp1onse ViewActivity: protected void onActivityResult(int requestCode, int resultCode, Intent data) { start void ");

		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("resp1onse ViewActivity: protected void onActivityResult(int requestCode, int resultCode, Intent data) { end void ");
	}

	@Override
	public PendingIntent createPendingResult(int requestCode, Intent data,int flags) {

		System.out.println("resp1onse ViewActivity: public PendingIntent createPendingResult(int requestCode, Intent data,int flags) { start return ");
		System.out.println("resp1onse ViewActivity: public PendingIntent createPendingResult(int requestCode, Intent data,int flags) { end return ");
		return super.createPendingResult(requestCode, data, flags);
	}

	@Override
	public void setRequestedOrientation(int requestedOrientation) {
		System.out.println("resp1onse ViewActivity: public void setRequestedOrientation(int requestedOrientation) { start void ");

		super.setRequestedOrientation(requestedOrientation);
		System.out.println("resp1onse ViewActivity: public void setRequestedOrientation(int requestedOrientation) { end void ");
	}

	@Override
	public int getRequestedOrientation() {

		System.out.println("resp1onse ViewActivity: public int getRequestedOrientation() { start return ");
		System.out.println("resp1onse ViewActivity: public int getRequestedOrientation() { end return ");
		return super.getRequestedOrientation();
	}

	@Override
	public int getTaskId() {

		System.out.println("resp1onse ViewActivity: public int getTaskId() { start return ");
		System.out.println("resp1onse ViewActivity: public int getTaskId() { end return ");
		return super.getTaskId();
	}

	@Override
	public boolean isTaskRoot() {

		System.out.println("resp1onse ViewActivity: public boolean isTaskRoot() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean isTaskRoot() { end return ");
		return super.isTaskRoot();
	}

	@Override
	public boolean moveTaskToBack(boolean nonRoot) {

		System.out.println("resp1onse ViewActivity: public boolean moveTaskToBack(boolean nonRoot) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean moveTaskToBack(boolean nonRoot) { end return ");
		return super.moveTaskToBack(nonRoot);
	}

	@Override
	public String getLocalClassName() {

		System.out.println("resp1onse ViewActivity: public String getLocalClassName() { start return ");
		System.out.println("resp1onse ViewActivity: public String getLocalClassName() { end return ");
		return super.getLocalClassName();
	}

	@Override
	public ComponentName getComponentName() {

		System.out.println("resp1onse ViewActivity: public ComponentName getComponentName() { start return ");
		System.out.println("resp1onse ViewActivity: public ComponentName getComponentName() { end return ");
		return super.getComponentName();
	}

	@Override
	public SharedPreferences getPreferences(int mode) {

		System.out.println("resp1onse ViewActivity: public SharedPreferences getPreferences(int mode) { start return ");
		System.out.println("resp1onse ViewActivity: public SharedPreferences getPreferences(int mode) { end return ");
		return super.getPreferences(mode);
	}

	@Override
	public Object getSystemService(String name) {

//		System.out.println("resp1onse ViewActivity: public Object getSystemService(String name) { start return ");
//		System.out.println("resp1onse ViewActivity: public Object getSystemService(String name) { end return ");
		return super.getSystemService(name);
	}

	@Override
	public void setTitle(CharSequence title) {
		System.out.println("resp1onse ViewActivity: public void setTitle(CharSequence title) { start void ");

		super.setTitle(title);
		System.out.println("resp1onse ViewActivity: public void setTitle(CharSequence title) { end void ");
	}

	@Override
	public void setTitle(int titleId) {
		System.out.println("resp1onse ViewActivity: public void setTitle(int titleId) { start void ");

		super.setTitle(titleId);
		System.out.println("resp1onse ViewActivity: public void setTitle(int titleId) { end void ");
	}

	@Override
	public void setTitleColor(int textColor) {
		System.out.println("resp1onse ViewActivity: public void setTitleColor(int textColor) { start void ");

		super.setTitleColor(textColor);
		System.out.println("resp1onse ViewActivity: public void setTitleColor(int textColor) { end void ");
	}

	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		System.out.println("resp1onse ViewActivity: protected void onTitleChanged(CharSequence title, int color) { start void ");

		super.onTitleChanged(title, color);
		System.out.println("resp1onse ViewActivity: protected void onTitleChanged(CharSequence title, int color) { end void ");
	}

	@Override
	protected void onChildTitleChanged(Activity childActivity,CharSequence title) {
		System.out.println("resp1onse ViewActivity: protected void onChildTitleChanged(Activity childActivity,CharSequence title) { start void ");

		super.onChildTitleChanged(childActivity, title);
		System.out.println("resp1onse ViewActivity: protected void onChildTitleChanged(Activity childActivity,CharSequence title) { end void ");
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {

//		System.out.println("resp1onse ViewActivity: public View onCreateView(String name, Context context, AttributeSet attrs) { start return ");
//		System.out.println("resp1onse ViewActivity: public View onCreateView(String name, Context context, AttributeSet attrs) { end return ");
		return super.onCreateView(name, context, attrs);
	}

	@Override
	public View onCreateView(View parent, String name, Context context,AttributeSet attrs) {

//		System.out.println("resp1onse ViewActivity: public View onCreateView(View parent, String name, Context context,AttributeSet attrs) { start return ");
//		System.out.println("resp1onse ViewActivity: public View onCreateView(View parent, String name, Context context,AttributeSet attrs) { end return ");
		return super.onCreateView(parent, name, context, attrs);
	}

	@Override
	public void dump(String prefix, FileDescriptor fd, PrintWriter writer,
			String[] args) {

		super.dump(prefix, fd, writer, args);
	}

	@Override
	public ActionMode startActionMode(Callback callback) {

		System.out.println("resp1onse ViewActivity: public ActionMode startActionMode(Callback callback) { start return ");
		System.out.println("resp1onse ViewActivity: public ActionMode startActionMode(Callback callback) { end return ");
		return super.startActionMode(callback);
	}

	@Override
	public ActionMode onWindowStartingActionMode(Callback callback) {

		System.out.println("resp1onse ViewActivity: public ActionMode onWindowStartingActionMode(Callback callback) { start return ");
		System.out.println("resp1onse ViewActivity: public ActionMode onWindowStartingActionMode(Callback callback) { end return ");
		return super.onWindowStartingActionMode(callback);
	}

	@Override
	public void onActionModeStarted(ActionMode mode) {
		System.out.println("resp1onse ViewActivity: public void onActionModeStarted(ActionMode mode) { start void ");

		super.onActionModeStarted(mode);
		System.out.println("resp1onse ViewActivity: public void onActionModeStarted(ActionMode mode) { end void ");
	}

	@Override
	public void onActionModeFinished(ActionMode mode) {
		System.out.println("resp1onse ViewActivity: public void onActionModeFinished(ActionMode mode) { start void ");

		super.onActionModeFinished(mode);
		System.out.println("resp1onse ViewActivity: public void onActionModeFinished(ActionMode mode) { end void ");
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		System.out.println("resp1onse ViewActivity: protected void attachBaseContext(Context newBase) { start void ");

		super.attachBaseContext(newBase);
		System.out.println("resp1onse ViewActivity: protected void attachBaseContext(Context newBase) { end void ");
	}

	@Override
	public Resources getResources() {

//		System.out.println("resp1onse ViewActivity: public Resources getResources() { start return ");
//		System.out.println("resp1onse ViewActivity: public Resources getResources() { end return ");
		return super.getResources();
	}

	@Override
	public void setTheme(int resid) {
//		System.out.println("resp1onse ViewActivity: public void setTheme(int resid) { start void ");

		super.setTheme(resid);
//		System.out.println("resp1onse ViewActivity: public void setTheme(int resid) { end void ");
	}

	@Override
	public Theme getTheme() {

//		System.out.println("resp1onse ViewActivity: public Theme getTheme() { start return ");
//		System.out.println("resp1onse ViewActivity: public Theme getTheme() { end return ");
		return super.getTheme();
	}

	@Override
	public Context getBaseContext() {

//		System.out.println("resp1onse ViewActivity: public Context getBaseContext() { start return ");
//		System.out.println("resp1onse ViewActivity: public Context getBaseContext() { end return ");
		return super.getBaseContext();
	}

	@Override
	public AssetManager getAssets() {

		System.out.println("resp1onse ViewActivity: public AssetManager getAssets() { start return ");
		System.out.println("resp1onse ViewActivity: public AssetManager getAssets() { end return ");
		return super.getAssets();
	}

	@Override
	public PackageManager getPackageManager() {

		System.out.println("resp1onse ViewActivity: public PackageManager getPackageManager() { start return ");
		System.out.println("resp1onse ViewActivity: public PackageManager getPackageManager() { end return ");
		return super.getPackageManager();
	}

	@Override
	public ContentResolver getContentResolver() {

		System.out.println("resp1onse ViewActivity: public ContentResolver getContentResolver() { start return ");
		System.out.println("resp1onse ViewActivity: public ContentResolver getContentResolver() { end return ");
		return super.getContentResolver();
	}

	@Override
	public Looper getMainLooper() {

		System.out.println("resp1onse ViewActivity: public Looper getMainLooper() { start return ");
		System.out.println("resp1onse ViewActivity: public Looper getMainLooper() { end return ");
		return super.getMainLooper();
	}

	@Override
	public Context getApplicationContext() {

		System.out.println("resp1onse ViewActivity: public Context getApplicationContext() { start return ");
		System.out.println("resp1onse ViewActivity: public Context getApplicationContext() { end return ");
		return super.getApplicationContext();
	}

	@Override
	public ClassLoader getClassLoader() {

		System.out.println("resp1onse ViewActivity: public ClassLoader getClassLoader() { start return ");
		System.out.println("resp1onse ViewActivity: public ClassLoader getClassLoader() { end return ");
		return super.getClassLoader();
	}

	@Override
	public String getPackageName() {

//		System.out.println("resp1onse ViewActivity: public String getPackageName() { start return ");
//		System.out.println("resp1onse ViewActivity: public String getPackageName() { end return ");
		return super.getPackageName();
	}

	@Override
	public ApplicationInfo getApplicationInfo() {

//		System.out.println("resp1onse ViewActivity: public ApplicationInfo getApplicationInfo() { start return ");
//		System.out.println("resp1onse ViewActivity: public ApplicationInfo getApplicationInfo() { end return ");
		return super.getApplicationInfo();
	}

	@Override
	public String getPackageResourcePath() {

		System.out.println("resp1onse ViewActivity: public String getPackageResourcePath() { start return ");
		System.out.println("resp1onse ViewActivity: public String getPackageResourcePath() { end return ");
		return super.getPackageResourcePath();
	}

	@Override
	public String getPackageCodePath() {

		System.out.println("resp1onse ViewActivity: public String getPackageCodePath() { start return ");
		System.out.println("resp1onse ViewActivity: public String getPackageCodePath() { end return ");
		return super.getPackageCodePath();
	}

	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {

		System.out.println("resp1onse ViewActivity: public SharedPreferences getSharedPreferences(String name, int mode) { start return ");
		System.out.println("resp1onse ViewActivity: public SharedPreferences getSharedPreferences(String name, int mode) { end return ");
		return super.getSharedPreferences(name, mode);
	}

	@Override
	public FileInputStream openFileInput(String name)throws FileNotFoundException {

		return super.openFileInput(name);
	}

	@Override
	public FileOutputStream openFileOutput(String name, int mode)throws FileNotFoundException {

		return super.openFileOutput(name, mode);
	}

	@Override
	public boolean deleteFile(String name) {

		System.out.println("resp1onse ViewActivity: public boolean deleteFile(String name) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean deleteFile(String name) { end return ");
		return super.deleteFile(name);
	}

	@Override
	public File getFileStreamPath(String name) {

		System.out.println("resp1onse ViewActivity: public File getFileStreamPath(String name) { start return ");
		System.out.println("resp1onse ViewActivity: public File getFileStreamPath(String name) { end return ");
		return super.getFileStreamPath(name);
	}

	@Override
	public String[] fileList() {

		System.out.println("resp1onse ViewActivity: public String[] fileList() { start return ");
		System.out.println("resp1onse ViewActivity: public String[] fileList() { end return ");
		return super.fileList();
	}

	@Override
	public File getFilesDir() {

		System.out.println("resp1onse ViewActivity: public File getFilesDir() { start return ");
		System.out.println("resp1onse ViewActivity: public File getFilesDir() { end return ");
		return super.getFilesDir();
	}

	@Override
	public File getExternalFilesDir(String type) {

		System.out.println("resp1onse ViewActivity: public File getExternalFilesDir(String type) { start return ");
		System.out.println("resp1onse ViewActivity: public File getExternalFilesDir(String type) { end return ");
		return super.getExternalFilesDir(type);
	}

	@Override
	public File getObbDir() {

		System.out.println("resp1onse ViewActivity: public File getObbDir() { start return ");
		System.out.println("resp1onse ViewActivity: public File getObbDir() { end return ");
		return super.getObbDir();
	}

	@Override
	public File getCacheDir() {

		System.out.println("resp1onse ViewActivity: public File getCacheDir() { start return ");
		System.out.println("resp1onse ViewActivity: public File getCacheDir() { end return ");
		return super.getCacheDir();
	}

	@Override
	public File getExternalCacheDir() {

		System.out.println("resp1onse ViewActivity: public File getExternalCacheDir() { start return ");
		System.out.println("resp1onse ViewActivity: public File getExternalCacheDir() { end return ");
		return super.getExternalCacheDir();
	}

	@Override
	public File getDir(String name, int mode) {

		System.out.println("resp1onse ViewActivity: public File getDir(String name, int mode) { start return ");
		System.out.println("resp1onse ViewActivity: public File getDir(String name, int mode) { end return ");
		return super.getDir(name, mode);
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory) {

		System.out.println("resp1onse ViewActivity: public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory) { start return ");
		System.out.println("resp1onse ViewActivity: public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory) { end return ");
		return super.openOrCreateDatabase(name, mode, factory);
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory, DatabaseErrorHandler errorHandler) {

		System.out.println("resp1onse ViewActivity: public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory, DatabaseErrorHandler errorHandler) { start return ");
		System.out.println("resp1onse ViewActivity: public SQLiteDatabase openOrCreateDatabase(String name, int mode,CursorFactory factory, DatabaseErrorHandler errorHandler) { end return ");
		return super.openOrCreateDatabase(name, mode, factory, errorHandler);
	}

	@Override
	public boolean deleteDatabase(String name) {

		System.out.println("resp1onse ViewActivity: public boolean deleteDatabase(String name) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean deleteDatabase(String name) { end return ");
		return super.deleteDatabase(name);
	}

	@Override
	public File getDatabasePath(String name) {

		System.out.println("resp1onse ViewActivity: public File getDatabasePath(String name) { start return ");
		System.out.println("resp1onse ViewActivity: public File getDatabasePath(String name) { end return ");
		return super.getDatabasePath(name);
	}

	@Override
	public String[] databaseList() {

		System.out.println("resp1onse ViewActivity: public String[] databaseList() { start return ");
		System.out.println("resp1onse ViewActivity: public String[] databaseList() { end return ");
		return super.databaseList();
	}

	@Override
	public Drawable getWallpaper() {

		System.out.println("resp1onse ViewActivity: public Drawable getWallpaper() { start return ");
		System.out.println("resp1onse ViewActivity: public Drawable getWallpaper() { end return ");
		return super.getWallpaper();
	}

	@Override
	public Drawable peekWallpaper() {

		System.out.println("resp1onse ViewActivity: public Drawable peekWallpaper() { start return ");
		System.out.println("resp1onse ViewActivity: public Drawable peekWallpaper() { end return ");
		return super.peekWallpaper();
	}

	@Override
	public int getWallpaperDesiredMinimumWidth() {

		System.out.println("resp1onse ViewActivity: public int getWallpaperDesiredMinimumWidth() { start return ");
		System.out.println("resp1onse ViewActivity: public int getWallpaperDesiredMinimumWidth() { end return ");
		return super.getWallpaperDesiredMinimumWidth();
	}

	@Override
	public int getWallpaperDesiredMinimumHeight() {

		System.out.println("resp1onse ViewActivity: public int getWallpaperDesiredMinimumHeight() { start return ");
		System.out.println("resp1onse ViewActivity: public int getWallpaperDesiredMinimumHeight() { end return ");
		return super.getWallpaperDesiredMinimumHeight();
	}

	@Override
	public void setWallpaper(Bitmap bitmap) throws IOException {

		super.setWallpaper(bitmap);
	}

	@Override
	public void setWallpaper(InputStream data) throws IOException {

		super.setWallpaper(data);
	}

	@Override
	public void clearWallpaper() throws IOException {

		super.clearWallpaper();
	}

	@Override
	public void sendBroadcast(Intent intent) {
		System.out.println("resp1onse ViewActivity: public void sendBroadcast(Intent intent) { start void ");

		super.sendBroadcast(intent);
		System.out.println("resp1onse ViewActivity: public void sendBroadcast(Intent intent) { end void ");
	}

	@Override
	public void sendBroadcast(Intent intent, String receiverPermission) {
		System.out.println("resp1onse ViewActivity: public void sendBroadcast(Intent intent, String receiverPermission) { start void ");

		super.sendBroadcast(intent, receiverPermission);
		System.out.println("resp1onse ViewActivity: public void sendBroadcast(Intent intent, String receiverPermission) { end void ");
	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
		System.out.println("resp1onse ViewActivity: public void sendOrderedBroadcast(Intent intent, String receiverPermission) { start void ");

		super.sendOrderedBroadcast(intent, receiverPermission);
		System.out.println("resp1onse ViewActivity: public void sendOrderedBroadcast(Intent intent, String receiverPermission) { end void ");
	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission,BroadcastReceiver resultReceiver, Handler scheduler,int initialCode, String initialData, Bundle initialExtras) {
		System.out.println("resp1onse ViewActivity: public void sendOrderedBroadcast(Intent intent, String receiverPermission,BroadcastReceiver resultReceiver, Handler scheduler,int initialCode, String initialData, Bundle initialExtras) { start void ");

		super.sendOrderedBroadcast(intent, receiverPermission, resultReceiver,scheduler, initialCode, initialData, initialExtras);
		System.out.println("resp1onse ViewActivity: public void sendOrderedBroadcast(Intent intent, String receiverPermission,BroadcastReceiver resultReceiver, Handler scheduler,int initialCode, String initialData, Bundle initialExtras) { end void ");
	}

	@Override
	public void sendStickyBroadcast(Intent intent) {
		System.out.println("resp1onse ViewActivity: public void sendStickyBroadcast(Intent intent) { start void ");

		super.sendStickyBroadcast(intent);
		System.out.println("resp1onse ViewActivity: public void sendStickyBroadcast(Intent intent) { end void ");
	}

	@Override
	public void sendStickyOrderedBroadcast(Intent intent,
			BroadcastReceiver resultReceiver, Handler scheduler,
			int initialCode, String initialData, Bundle initialExtras) {

		super.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler,initialCode, initialData, initialExtras);
	}

	@Override
	public void removeStickyBroadcast(Intent intent) {
		System.out.println("resp1onse ViewActivity: public void removeStickyBroadcast(Intent intent) { start void ");

		super.removeStickyBroadcast(intent);
		System.out.println("resp1onse ViewActivity: public void removeStickyBroadcast(Intent intent) { end void ");
	}

	@Override
	public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter) {

		System.out.println("resp1onse ViewActivity: public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter) { start return ");
		System.out.println("resp1onse ViewActivity: public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter) { end return ");
		return super.registerReceiver(receiver, filter);
	}

	@Override
	public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter, String broadcastPermission, Handler scheduler) {

		System.out.println("resp1onse ViewActivity: public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter, String broadcastPermission, Handler scheduler) { start return ");
		System.out.println("resp1onse ViewActivity: public Intent registerReceiver(BroadcastReceiver receiver,IntentFilter filter, String broadcastPermission, Handler scheduler) { end return ");
		return super.registerReceiver(receiver, filter, broadcastPermission,
				scheduler);
	}

	@Override
	public void unregisterReceiver(BroadcastReceiver receiver) {
		System.out.println("resp1onse ViewActivity: public void unregisterReceiver(BroadcastReceiver receiver) { start void ");

		super.unregisterReceiver(receiver);
		System.out.println("resp1onse ViewActivity: public void unregisterReceiver(BroadcastReceiver receiver) { end void ");
	}

	@Override
	public ComponentName startService(Intent service) {

		System.out.println("resp1onse ViewActivity: public ComponentName startService(Intent service) { start return ");
		System.out.println("resp1onse ViewActivity: public ComponentName startService(Intent service) { end return ");
		return super.startService(service);
	}

	@Override
	public boolean stopService(Intent name) {

		System.out.println("resp1onse ViewActivity: public boolean stopService(Intent name) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean stopService(Intent name) { end return ");
		return super.stopService(name);
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {

		System.out.println("resp1onse ViewActivity: public boolean bindService(Intent service, ServiceConnection conn, int flags) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean bindService(Intent service, ServiceConnection conn, int flags) { end return ");
		return super.bindService(service, conn, flags);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		System.out.println("resp1onse ViewActivity: public void unbindService(ServiceConnection conn) { start void ");

		super.unbindService(conn);
		System.out.println("resp1onse ViewActivity: public void unbindService(ServiceConnection conn) { end void ");
	}

	@Override
	public boolean startInstrumentation(ComponentName className,String profileFile, Bundle arguments) {

		System.out.println("resp1onse ViewActivity: public boolean startInstrumentation(ComponentName className,String profileFile, Bundle arguments) { start return ");
		System.out.println("resp1onse ViewActivity: public boolean startInstrumentation(ComponentName className,String profileFile, Bundle arguments) { end return ");
		return super.startInstrumentation(className, profileFile, arguments);
	}

	@Override
	public int checkPermission(String permission, int pid, int uid) {

		System.out.println("resp1onse ViewActivity: public int checkPermission(String permission, int pid, int uid) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkPermission(String permission, int pid, int uid) { end return ");
		return super.checkPermission(permission, pid, uid);
	}

	@Override
	public int checkCallingPermission(String permission) {

		System.out.println("resp1onse ViewActivity: public int checkCallingPermission(String permission) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkCallingPermission(String permission) { end return ");
		return super.checkCallingPermission(permission);
	}

	@Override
	public int checkCallingOrSelfPermission(String permission) {

		System.out.println("resp1onse ViewActivity: public int checkCallingOrSelfPermission(String permission) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkCallingOrSelfPermission(String permission) { end return ");
		return super.checkCallingOrSelfPermission(permission);
	}

	@Override
	public void enforcePermission(String permission, int pid, int uid,
			String message) {

		super.enforcePermission(permission, pid, uid, message);
	}

	@Override
	public void enforceCallingPermission(String permission, String message) {
		System.out.println("resp1onse ViewActivity: public void enforceCallingPermission(String permission, String message) { start void ");

		super.enforceCallingPermission(permission, message);
		System.out.println("resp1onse ViewActivity: public void enforceCallingPermission(String permission, String message) { end void ");
	}

	@Override
	public void enforceCallingOrSelfPermission(String permission, String message) {
		System.out.println("resp1onse ViewActivity: public void enforceCallingOrSelfPermission(String permission, String message) { start void ");

		super.enforceCallingOrSelfPermission(permission, message);
		System.out.println("resp1onse ViewActivity: public void enforceCallingOrSelfPermission(String permission, String message) { end void ");
	}

	@Override
	public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
		System.out.println("resp1onse ViewActivity: public void grantUriPermission(String toPackage, Uri uri, int modeFlags) { start void ");

		super.grantUriPermission(toPackage, uri, modeFlags);
		System.out.println("resp1onse ViewActivity: public void grantUriPermission(String toPackage, Uri uri, int modeFlags) { end void ");
	}

	@Override
	public void revokeUriPermission(Uri uri, int modeFlags) {
		System.out.println("resp1onse ViewActivity: public void revokeUriPermission(Uri uri, int modeFlags) { start void ");

		super.revokeUriPermission(uri, modeFlags);
		System.out.println("resp1onse ViewActivity: public void revokeUriPermission(Uri uri, int modeFlags) { end void ");
	}

	@Override
	public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {

		System.out.println("resp1onse ViewActivity: public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) { end return ");
		return super.checkUriPermission(uri, pid, uid, modeFlags);
	}

	@Override
	public int checkCallingUriPermission(Uri uri, int modeFlags) {

		System.out.println("resp1onse ViewActivity: public int checkCallingUriPermission(Uri uri, int modeFlags) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkCallingUriPermission(Uri uri, int modeFlags) { end return ");
		return super.checkCallingUriPermission(uri, modeFlags);
	}

	@Override
	public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {

		System.out.println("resp1onse ViewActivity: public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) { end return ");
		return super.checkCallingOrSelfUriPermission(uri, modeFlags);
	}

	@Override
	public int checkUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags) {

		System.out.println("resp1onse ViewActivity: public int checkUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags) { start return ");
		System.out.println("resp1onse ViewActivity: public int checkUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags) { end return ");
		return super.checkUriPermission(uri, readPermission, writePermission,pid, uid, modeFlags);
	}

	@Override
	public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags,String message) {
		System.out.println("resp1onse ViewActivity: public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags,String message) { start void ");

		super.enforceUriPermission(uri, pid, uid, modeFlags, message);
		System.out.println("resp1onse ViewActivity: public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags,String message) { end void ");
	}

	@Override
	public void enforceCallingUriPermission(Uri uri, int modeFlags,String message) {
		System.out.println("resp1onse ViewActivity: public void enforceCallingUriPermission(Uri uri, int modeFlags,String message) { start void ");

		super.enforceCallingUriPermission(uri, modeFlags, message);
		System.out.println("resp1onse ViewActivity: public void enforceCallingUriPermission(Uri uri, int modeFlags,String message) { end void ");
	}

	@Override
	public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags,String message) {
		System.out.println("resp1onse ViewActivity: public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags,String message) { start void ");

		super.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
		System.out.println("resp1onse ViewActivity: public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags,String message) { end void ");
	}

	@Override
	public void enforceUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags,String message) {
		System.out.println("resp1onse ViewActivity: public void enforceUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags,String message) { start void ");

		super.enforceUriPermission(uri, readPermission, writePermission, pid,uid, modeFlags, message);
		System.out.println("resp1onse ViewActivity: public void enforceUriPermission(Uri uri, String readPermission,String writePermission, int pid, int uid, int modeFlags,String message) { end void ");
	}

	@Override
	public Context createPackageContext(String packageName, int flags)throws NameNotFoundException {

		return super.createPackageContext(packageName, flags);
	}

	@Override
	public boolean isRestricted() {

		System.out.println("resp1onse ViewActivity: public boolean isRestricted() { start return ");
		System.out.println("resp1onse ViewActivity: public boolean isRestricted() { end return ");
		return super.isRestricted();
	}

}
