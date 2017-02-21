package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;


public class MainActivity extends Activity {
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		new Handler().postDelayed(new Runnable(){ //这里相当于Handler的第一个参数

            @Override
            public void run() { 
                Intent intent = new Intent(); 
                intent.setClass(MainActivity.this, StartActivity.class);  
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            } 
                
           }, 2000); //这里的1000指1000毫秒即1秒
	}
	public void init(){
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setScaleType(ImageView.ScaleType. CENTER_CROP);
	}
	

}
