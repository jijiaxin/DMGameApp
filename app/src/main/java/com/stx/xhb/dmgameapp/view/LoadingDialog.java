package com.stx.xhb.dmgameapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.stx.xhb.dmgameapp.R;
import com.wang.avi.AVLoadingIndicatorView;


public class LoadingDialog {

	private static boolean _isStart = false; // 标记提示框是否启动
	
	private static Dialog	_dialog	= null;
	private static Activity	_lastActivity	= null;
	
//	static AnimationDrawable _animationDrawable;
	static AVLoadingIndicatorView indicatorView;

	public static synchronized void create(Activity activity) {
		init(activity, null);
	}
	
	public static synchronized void create(Activity activity, String msg) {
		init(activity, msg);
	}
	
	public static synchronized void create(Activity activity, int msgResId) {
		String msg = activity.getString(msgResId);
		init(activity, msg);
	}
	
	public static synchronized void createWithAnim(Activity activity, int animResId) {
		init(activity, null);
	}
	
	
	private static void init(Activity activity, String msg) {
		cancel();
		LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view	= inflater.inflate(R.layout.custom_loading_view, null);
		indicatorView = (AVLoadingIndicatorView)view.findViewById(R.id.indicatorView);
		indicatorView.setVisibility(View.VISIBLE);
//		ImageView img = (ImageView)view.findViewById(R.id.dialog_loading_img);

//		AnimationsContainer.getInstance().createProgressDialogAnim(img).start();
//		img.setImageResource(R.anim.anim_loading);
//        _animationDrawable = (AnimationDrawable) img.getDrawable();
//        _animationDrawable.start();
		
		_dialog	= DialogFactory.creatWrapWidthDialog(activity, view, R.style.custom_dialog, LayoutParams.WRAP_CONTENT, 0.4f, true);
		_dialog.setCancelable(false);
		_dialog.show();
		_isStart = true;
		
		_lastActivity	= activity;
	}
	
	public static synchronized void cancel(){
		if (_isStart) {
			_isStart = false;
			
			if(null != _lastActivity && _lastActivity.isFinishing()) {
				/*
				 *  dialog 所在的activity已经正在销毁，无需再去销毁dialog
				 *  如果再主动去销毁dialog会导致异常
				 */
				
				_lastActivity	= null;
				return ;

			}
			
			if(_dialog.isShowing()) {

				indicatorView.setVisibility(View.GONE);
				_dialog.cancel();
			}
		}
	}
	
	public static synchronized void setCanCancel(){
		_dialog.setCancelable(true);
	}
}
