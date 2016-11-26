package com.stx.xhb.dmgameapp.view;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

public class DialogFactory {

	public static Dialog createCenterDialog(Context context, View view, int style, int height, int y, float dimAmount, boolean cancelable) {
		Dialog dialog = new Dialog(context, style);// 创建对话框，使用自定义样式
		dialog.setContentView(view);// 添加布局到对话框
		Window window = dialog.getWindow();// 设置对话框显示属性
		WindowManager.LayoutParams params = window.getAttributes();
		window.setGravity(Gravity.CENTER); // 对话框显示方位
		window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 背景变暗
		params.x = 0; // 基于显示方位的水平偏移量
		params.y = y; // 基于显示方位的垂直偏移量
		params.width = LayoutParams.FILL_PARENT; // 水平填充效果
		params.height = height; // 垂直填充效果
		params.dimAmount = dimAmount; // 背景黑暗度
		window.setAttributes(params);
		dialog.setCancelable(cancelable); 
		return dialog;
	}
	
	public static Dialog createCenterDialog(Context context, View view, int style, int height, float dimAmount, boolean cancelable) {
		return createCenterDialog(context, view, style, height, 0, dimAmount, cancelable);
	}
	
	public static Dialog createCenterDialog(Context context, View view, int style, int height) {
		return createCenterDialog(context, view, style, height, 0.7f, false);
	}

	public static Dialog creatWrapWidthDialog(Context context, View view, int style, int height, float dimAmount, boolean cancelable) {
		return creatWrapWidthDialog(context, view, style, height, 0, dimAmount, cancelable);
	}

	public static Dialog creatWrapWidthDialog(Context context, View view, int style, int height, int y, float dimAmount, boolean cancelable) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int screenWidth = wm.getDefaultDisplay().getWidth();
		Dialog dialog = new Dialog(context, style);// 创建对话框，使用自定义样式
		dialog.setContentView(view);// 添加布局到对话框
		Window window = dialog.getWindow();// 设置对话框显示属性
		WindowManager.LayoutParams params = window.getAttributes();
		window.setGravity(Gravity.CENTER); // 对话框显示方位
		window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 背景变暗
		params.x = 0; // 基于显示方位的水平偏移量
		params.y = y; // 基于显示方位的垂直偏移量
		params.width = screenWidth/3; // 水平填充效果
		params.height = height; // 垂直填充效果
		params.dimAmount = dimAmount; // 背景黑暗度
		window.setAttributes(params);
		dialog.setCancelable(cancelable);
		return dialog;
	}
}
