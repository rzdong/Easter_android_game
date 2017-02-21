package com.example.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class StartActivity extends Activity {
	
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	private ImageButton button4;
	private Intent intent;
	private ImageButton imageButton;
	private Bitmap bitmap;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		init();
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.about_a);
		
	}
	public void init(){
		button1 = (ImageButton)findViewById(R.id.button1);
		button2 = (ImageButton)findViewById(R.id.button2);
		button3 = (ImageButton)findViewById(R.id.button3);
		button4 = (ImageButton)findViewById(R.id.button4);
		button1.setScaleType(ScaleType.CENTER_INSIDE);
		button2.setScaleType(ScaleType.CENTER_INSIDE);
		button3.setScaleType(ScaleType.CENTER_INSIDE);
		button4.setScaleType(ScaleType.CENTER_INSIDE);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				intent = new Intent();
				intent.setClass(StartActivity.this, GameActivity.class);
				StartActivity.this.startActivity(intent);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				intent = new Intent();
				intent.setClass(StartActivity.this, SettingActivity.class);
				StartActivity.this.startActivity(intent);
			}
		});
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				intent = new Intent();
				intent.setClass(StartActivity.this, HistoryActivity.class);
				StartActivity.this.startActivity(intent);
			}
		});
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				intent = new Intent();
				intent.setClass(StartActivity.this, AboutActivity.class);
				StartActivity.this.startActivity(intent);
			}
		});
	} 
}
