package com.rzd.dafeiji;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

public class MainActivity extends Activity implements DialogInterface.OnClickListener {

	DafeijiView view = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view= new DafeijiView(this);
		setContentView(view);//新建视图
	}
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			view.stop();
			AlertDialog.Builder alert =new AlertDialog.Builder(this);
			alert.setTitle("你要退出吗？");
			alert.setNegativeButton("退出", this);
			alert.setPositiveButton("继续", this);
			alert.create().show();
		}
		return false;
	
	}
		
	public void onClick(DialogInterface arg0, int arg1) {
		if (arg1 == -2) {
			android.os.Process.killProcess(android.os.Process.myPid());
		}else{
			view.start();
		}
		
	}
}
