package com.rzd.dafeiji;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;


public class DafeijiView extends SurfaceView implements SurfaceHolder.Callback,Runnable,android.view.View.OnTouchListener{
	private Bitmap my1,my2,my3,my4;
	private Bitmap diji1,diji2,diji3,diji4;
	private Bitmap bg;
	private Bitmap baozha1,baozha2,baozha4,baozha5,baozha6;
	private Bitmap zidan;
	private Bitmap erjihuancun;
	
	private WindowManager windowManager;
	private int display_w;
	private int display_h;
	private int youxijieshu = 0;
	private ArrayList<GameImage> gameImages = new ArrayList<GameImage>();
	private ArrayList<Zidan> zidans = new ArrayList<Zidan>();
	private ArrayList<DirenImage> direnImages = new ArrayList<DirenImage>();//敌人飞机的列表，
	private FeijiImage feijiImage = null;
	public  DafeijiView(Context context) {
		super(context);
		getHolder().addCallback(this);
		this.setOnTouchListener(this);//事件注册
	}
	private void init(){
		my1 = BitmapFactory.decodeResource(getResources(), R.drawable.ziji1);
		my2 = BitmapFactory.decodeResource(getResources(), R.drawable.ziji2);
		my3 = BitmapFactory.decodeResource(getResources(), R.drawable.ziji3);
		my4 = BitmapFactory.decodeResource(getResources(), R.drawable.ziji4);
		diji1 = BitmapFactory.decodeResource(getResources(), R.drawable.diji1);
		diji2 = BitmapFactory.decodeResource(getResources(), R.drawable.diji2);
		diji3 = BitmapFactory.decodeResource(getResources(), R.drawable.diji3);
		diji4 = BitmapFactory.decodeResource(getResources(), R.drawable.diji4);
		baozha1 = BitmapFactory.decodeResource(getResources(), R.drawable.baozha1);
		baozha2 = BitmapFactory.decodeResource(getResources(), R.drawable.baozha2);
		baozha4 = BitmapFactory.decodeResource(getResources(), R.drawable.baozha4);
		baozha5 = BitmapFactory.decodeResource(getResources(), R.drawable.baozha5);
		baozha6 = BitmapFactory.decodeResource(getResources(), R.drawable.baozha6);
		bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		zidan = BitmapFactory.decodeResource(getResources(), R.drawable.zidan);	
	
		erjihuancun = Bitmap.createBitmap(display_w, display_h, Config.ARGB_8888);//生产二级缓存的照片
		feijiImage = new FeijiImage();
		gameImages.add(new BeiJingImage(bg));
		gameImages.add(feijiImage);
		gameImages.add(new DirenImage());
	}
	
	private interface GameImage{
		public Bitmap getBitmap();
		public int getX();
		public int getY();
	}
	private class Zidan implements GameImage{//子弹
		@SuppressWarnings("unused")
		private Bitmap zidan1;
		@SuppressWarnings("unused")
		private FeijiImage feiji;
		private int x;
		private int y;
		public Zidan(FeijiImage feiji,Bitmap zidan){
			this.feiji = feiji;
			this.zidan1 = zidan;
			
			x = (feiji.getX()+(feiji.getWidth()/2)-17);
			y =(feiji.getY()-zidan.getHeight()+80); 
		}
		public Bitmap getBitmap() {
			y-=45;
			if (y<=-100) {
				zidans.remove(this);
			}
			return zidan;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			
			return y;
		}
		
	}
	
	private class FeijiImage implements GameImage {//自己的飞机
		private int x;
		private int y;
		private int width;
		private int height;
		private ArrayList<Bitmap> zijis = new ArrayList<Bitmap>();
		private ArrayList<Bitmap> bitmapbz = new ArrayList<Bitmap>();
		private FeijiImage(){
			zijis.add(my1);
			zijis.add(my2);
			zijis.add(my3);
			zijis.add(my4);
			bitmapbz.add(baozha1);//敌人飞机爆炸集合添加
			bitmapbz.add(baozha2);
			bitmapbz.add(baozha4);
			bitmapbz.add(baozha5);
			bitmapbz.add(baozha6);
			x = (display_w-my1.getWidth())/2;//飞机的坐标
			y = display_h-my1.getHeight()-30;
			width = my1.getWidth();//自己飞机的高和宽
			height = my1.getHeight();	
		};
		
		private int index = 0;
		private int Num = 0;
		
		public Bitmap getBitmap() {
			Bitmap bitmap = zijis.get(index);
			if (Num == 9) {
				index++;
				if(index == 5 && state){
					gameImages.remove(this);//移除爆炸图像
				}
				if (index == zijis.size()) {
					index = 0;
				}
				Num = 0;
			}
			Num++;
				
			return bitmap;
		}
		
		public void zijibaozha (ArrayList<DirenImage> direnImages){
			for (DirenImage direnImage : (ArrayList<DirenImage>)direnImages.clone()){
				if (
					  /*(((x+width)> direnImage.getX()) && ((y+height)>direnImage.getY())) || 
					  ((x<(direnImage.getX()+direnImage.getWidth())) && ((y+height)>direnImage.getY())) || 
					  ((x+width>direnImage.getX())&&(y<(direnImage.getY()+direnImage.getHeight()))) ||
					  ((y<(direnImage.getX()+direnImage.getWidth()))&&(y<(direnImage.getY()+direnImage.getHeight())))*/
						(x>direnImage.getX()-width+55)&&
						(x<direnImage.getX()+direnImage.getWidth()-55)&&
						(y>direnImage.getHeight()-height+66)&&
						(y<direnImage.getY()+direnImage.getHeight()-65)
				    )//撞机条件
				{
				feijiImage.zijis = bitmapbz;
				
				feijiImage.getBitmap();
				Paint paint = new Paint();
				paint.setColor(Color.rgb(255, 255, 0));
				youxijieshu = 1;
				//stop();
				
				//gameImages.remove(feijiImage);
			}
			}
		}
		public int getWidth() {
			return width;	
		}
		public int getHeight(){
			return height;
		}

		public int getX() {
			
			return x;
		}

		public int getY() {
			
			return y;
		}
		
		public void setX(int x){//拖动时赋值
			this.x = x;
		}
		
		public void setY(int y){//拖动时赋值
			this.y = y;
		}
		
	}
	
	private class DirenImage implements GameImage {
		
		private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
		private ArrayList<Bitmap> bitmapbz = new ArrayList<Bitmap>();
		private int x;
		private int y;
		private int width;
		private int height;
		public DirenImage() {
			bitmaps.add(diji1);//敌人飞机集合添加
			bitmaps.add(diji2);
			bitmaps.add(diji3);
			bitmaps.add(diji4);
			bitmapbz.add(baozha1);//敌人飞机爆炸集合添加
			bitmapbz.add(baozha2);
			bitmapbz.add(baozha4);
			bitmapbz.add(baozha5);
			bitmapbz.add(baozha6);
			
			y = -diji1.getHeight();//敌机出现的位置y
			Random random = new Random();
			x = random.nextInt(display_w-(diji1.getWidth()));//敌机出现的位置x
			width = diji1.getWidth();//敌机图片宽
			height = diji1.getHeight();//敌机图片高
		}
		
		private int index = 0;
		private int Num = 0;
		private int count = 0;
		private boolean state = false;
		Random random = new Random();
		
		public void shoudaogongji(ArrayList<Zidan> zidans){//敌人收到攻击时的方法，包括子弹消失敌机爆炸
			if (!state) {
				
				for (GameImage zidan : (ArrayList<Zidan>)zidans.clone()){//遍历子弹，每颗子弹是否击中敌机，击中则这个子弹消失，
					if (zidan.getX()>x 
							&& zidan.getY()>y 
							&& zidan.getX()<width+x 
							&& zidan.getY()<height+y) {
						zidans.remove(zidan);//击中敌机的子弹消失
						count++;
						if (count == random.nextInt(4)+1) {//随机数1.2.3.4
							//四以内的整数，如果击中数和击毁敌机数一样多，
							//那么才爆炸，其中，有些敌机时永远不能爆炸的，
							//因为random.nextInt(4)+1)可能等于
							state = true;
							bitmaps = bitmapbz;//击中指定次数的敌机将会被替换成爆炸
							fengshu=fengshu+count*10;
							count = 0;
						}
						break;
					} 
					
				}
			}
		}
		public Bitmap getBitmap() {
			Bitmap bitmap = bitmaps.get(index);
			if (Num == 5) {//敌机图片切换速度
				index++;
				if(index == 5 && state){
					gameImages.remove(this);//移除爆炸图像
					direnImages.remove(this);
				}
				if (index == bitmaps.size()) {
					index = 0;
				}
				Num = 0;
			}
			y+=direnfeijiyidong;
			Num++;
			if (y>display_h) {
				gameImages.remove(this);
			}
			return bitmap;
		}
		public int getWidth() {
			return width;	
		}
		
		public int getHeight(){
			return height;
		}
		
		public int getX() {
			
			return x;
		}

		
		public int getY() {
			return y;
		}
		
	}
	//负责背景的处理
	private class BeiJingImage implements GameImage{
		private Bitmap bg;
		
		private BeiJingImage(Bitmap bg){
			this .bg = bg;
			newBitmap = Bitmap.createBitmap(display_w, display_h, Config.ARGB_8888);
		}
		private int height = 0;
		private Bitmap newBitmap = null;

		public Bitmap getBitmap() {
			
			Paint paint = new Paint();
			Canvas canvas = new Canvas(newBitmap);
			
			canvas.drawBitmap(bg, 
					new Rect(0, 0, bg.getWidth(), bg.getHeight()), 
					new Rect(0, height, display_w, display_h+height), 
					paint);
			canvas.drawBitmap(bg, 
					new Rect(0, 0, bg.getWidth(), bg.getHeight()), 
					new Rect(0, -display_h+height, display_w, height), 
					paint);
			height+=2;
			if (height >= display_h) {
				height = 0;
			}
			return newBitmap;	
		}
		public int getX() {
			
			return 0;
		}

		public int getY() {
			
			return 0;
		}
	} 
	private boolean state = false;
	private SurfaceHolder holder;
	private long fengshu = 0;
	private int guanqia = 1; 
	private int direnfeijiyidong = 6;
	private int chujishuliang = 25;
	private int zidanfashesudu = 8;
	private boolean stopstate = false;
	public void stop(){
		stopstate = true;
	}
	public void start(){
		stopstate = false;
		thread.interrupt();
	}
	//绘画中心
	public void run() {
		Paint paint = new Paint();
		Paint paint2 = new Paint();
		Paint paint3 = new Paint();
		paint2.setColor(Color.rgb(200, 200, 200));
		paint2.setAntiAlias(true);
		paint2.setShadowLayer(20, 10, 10, Color.BLACK);
		paint2.setTypeface(Typeface.SANS_SERIF);
		paint2.setTextSize(70);
		paint3.setColor(Color.rgb(225, 225, 225));
		paint3.setAntiAlias(true);//抗锯齿
		paint3.setShadowLayer(20, 10, 10, Color.BLACK);
		paint3.setTypeface(Typeface.SANS_SERIF);
		paint3.setTextSize(140);
		int diren_number = 0;
		int zidan_number = 0;
		try {
			while (state){
				while (stopstate) {
					try{
						Thread.sleep(100000);
					
					}catch (Exception e) {
						
					}
				}
				if (selectfeiji!=null) {
					if (zidan_number==zidanfashesudu) {
						zidans.add(new Zidan(selectfeiji, zidan));//新添加一颗子弹，并把子弹放入子弹列表中发射子弹，速度
						zidan_number=0;
					}
					zidan_number++;
				}
				Canvas newCanvas = new Canvas(erjihuancun);
				for (GameImage image : (ArrayList<GameImage>)gameImages.clone()){//遍历gameImage列表
					if (image instanceof DirenImage) {//如果image是DirenImage的实例，那么image转换成DirenImage，
						//然后调用它的方法
						((DirenImage)image).shoudaogongji(zidans);//把子弹告诉敌机
						//((DirenImage)image).zijibaozha(feijiImage);//调用DirenImage中的,我方飞机，爆炸的方法
					}
					//feijiImage.zijibaozha(direnImages);
					
					
					newCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(), paint);
				}
				/*for (GameImage image : (ArrayList<GameImage>) gameImages.clone()){
					if (image instanceof FeijiImage) {
						((FeijiImage)image).zijibaozha(direnImages);
					}
					newCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(), paint);
				}*/
				
				
				for (Zidan image : (ArrayList<Zidan>) zidans.clone()){
					newCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(), paint);
				}
				//分数
				newCanvas.drawText("分 : "+fengshu, 50, 100, paint2);
				
				newCanvas.drawText("关 : "+guanqia+"/13", 50, 191, paint2);
				if (youxijieshu == 1) {
					newCanvas.drawText("游戏结束啦", (display_w-145*5)/2, (display_h-155)/2, paint3);
					stop();
				}
				

				
				if (fengshu>=100&&fengshu<200) {
					guanqia=2;
					direnfeijiyidong=7;
					chujishuliang=22;
					zidanfashesudu = 8;
				}else if (fengshu>=200&&fengshu<400) {
					guanqia=3;
					direnfeijiyidong=8;
					chujishuliang=19;
					zidanfashesudu = 8;
				}else if (fengshu>=400&&fengshu<700) {
					guanqia=4;
					direnfeijiyidong=9;
					chujishuliang=16;
					zidanfashesudu = 7;
				}else if (fengshu>=700&&fengshu<1100) {
					guanqia=5;
					direnfeijiyidong=11;
					chujishuliang=13;
					zidanfashesudu = 6;
				}else if (fengshu>=1100&&fengshu<1600) {
					guanqia=6;
					direnfeijiyidong=12;
					chujishuliang=12;
					zidanfashesudu = 5;
				}else if (fengshu>=1600&&fengshu<2200) {
					guanqia=7;
					direnfeijiyidong=13;
					chujishuliang=11;
					zidanfashesudu = 5;
				}else if(fengshu>=2200&&fengshu<2800){
					guanqia =8;
					direnfeijiyidong=14;
					chujishuliang=10;
					zidanfashesudu = 4;
				}
				else if(fengshu>=2800&&fengshu<3400){
					guanqia =9;
					direnfeijiyidong=15;
					chujishuliang=9;
					zidanfashesudu = 4;
				}
				else if(fengshu>=3400&&fengshu<4000){
					guanqia =10;
					direnfeijiyidong=16;
					chujishuliang=8;
					zidanfashesudu = 3;
				}
				else if(fengshu>=4000&&fengshu<4400){
					guanqia =11;
					direnfeijiyidong=17;
					chujishuliang=7;
					zidanfashesudu = 2;
				}
				else if(fengshu>=4400&&fengshu<4800){
					guanqia =12;
					direnfeijiyidong=18;
					chujishuliang=7;
					zidanfashesudu = 2;
				}else if(fengshu>=4800){
					guanqia =13;
					direnfeijiyidong=19;
					chujishuliang=6;
					zidanfashesudu = 1;
				}
				if (diren_number==chujishuliang) {
					DirenImage diRen = new DirenImage();
					gameImages.add(diRen);
					direnImages.add(diRen);
					diren_number = 0;
				}
				diren_number++;
				
				Canvas canvas = holder.lockCanvas();
				canvas.drawBitmap(erjihuancun, 0, 0, paint);
				holder.unlockCanvasAndPost(canvas);
				Thread.sleep(10);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
			
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		state = false;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int fprmat, int width, int height) {
		display_w = width;
		display_h = height;
		init();
		this.holder = holder;
		state = true;
		thread = new Thread(this);
		thread.start();
	}
	Thread thread = null;
	FeijiImage selectfeiji;
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (GameImage game : gameImages){
				if (game instanceof FeijiImage) {
					FeijiImage feijiImage = (FeijiImage)game;
					if (feijiImage.getX()<event.getX()
							&& feijiImage.getY()<event.getY()
							&& feijiImage.getX() + feijiImage.getWidth() > event.getX()
							&& feijiImage.getY() + feijiImage.getHeight() > event.getY()) {
						selectfeiji = feijiImage;
					}
					else {
						selectfeiji = null;
					}
					break;
				}
			}
		}else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (selectfeiji!=null) {
				selectfeiji.setX((int)event.getX()-selectfeiji.getWidth()/2);
				selectfeiji.setY((int)event.getY()-selectfeiji.getHeight()/2);
			}
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
			selectfeiji = null;
		}
		return true;
	}

	
	
	

	
}
