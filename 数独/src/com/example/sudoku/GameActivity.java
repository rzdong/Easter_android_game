package com.example.sudoku;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class GameActivity extends Activity {
	private int mwidth,mheight;
	private DisplayMetrics dMetrics;
	private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,
	imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView20,
	imageView21,imageView22,imageView23,imageView24,imageView25,imageView26,imageView27,imageView28,imageView29,imageView30,
	imageView31,imageView32,imageView33,imageView34,imageView35,imageView36,imageView37,imageView38,imageView39,imageView40,
	imageView41,imageView42,imageView43,imageView44,imageView45,imageView46,imageView47,imageView48,imageView49,imageView50,
	imageView51,imageView52,imageView53,imageView54,imageView55,imageView56,imageView57,imageView58,imageView59,imageView60,
	imageView61,imageView62,imageView63,imageView64,imageView65,imageView66,imageView67,imageView68,imageView69,imageView70,
	imageView71,imageView72,imageView73,imageView74,imageView75,imageView76,imageView77,imageView78,imageView79,imageView80,imageView81,
	ImageView101,imageview102,imageView103,imageView104,imageView105,imageView106,imageView107,imageView108,imageView109;
	private TableRow tableRow1,tableRow2,tableRow3,tableRow4,tableRow5,tableRow6,tableRow7,tableRow8,tableRow9,tableRow10;
	private TableRow tableRows[] = new TableRow[10];//存放tablerow的数组，方便设置布局
	private ImageView imageViews[] = new ImageView[81];//存放九宫格的view
	private Game game;
	private ImageView imageV_9[] = new ImageView[9];
	private boolean clickState = false;//选择栏点击是否有效
	private Bitmap bitmaps[] = new Bitmap[11];//定义一个存十个bitmap的图片数组
	private boolean border_state = false; //当前是否有激活状态的输入
	private int border_index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game);
		
		dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		mwidth = dMetrics.widthPixels;
		mheight= dMetrics.heightPixels;
		game = new Game();
		initbitmap(); // 读取数字1-9图
		initrow(); //初始化每个tablerow View的高度
		init(); //初始化九宫格和待选数字，并添加监听事件
//		System.out.println(mwidth/9 + "----------------------------------");
	}
	public void initrow(){  
		tableRow1 = (TableRow)findViewById(R.id.tableRow1);
		tableRow2 = (TableRow)findViewById(R.id.tableRow2);
		tableRow3 = (TableRow)findViewById(R.id.tableRow3);
		tableRow4 = (TableRow)findViewById(R.id.tableRow4);
		tableRow5 = (TableRow)findViewById(R.id.tableRow5);
		tableRow6 = (TableRow)findViewById(R.id.tableRow6);
		tableRow7 = (TableRow)findViewById(R.id.tableRow7);
		tableRow8 = (TableRow)findViewById(R.id.tableRow8);
		tableRow9 = (TableRow)findViewById(R.id.tableRow9);
		tableRow10 = (TableRow)findViewById(R.id.tableRow10);
		tableRows[0] = tableRow1;
		tableRows[1] = tableRow2;
		tableRows[2] = tableRow3;
		tableRows[3] = tableRow4;
		tableRows[4] = tableRow5;
		tableRows[5] = tableRow6;
		tableRows[6] = tableRow7;
		tableRows[7] = tableRow8;
		tableRows[8] = tableRow9;
		tableRows[9] = tableRow10;
		for(int i=0;i<10;i++){
			android.view.ViewGroup.LayoutParams params1 = tableRows[i].getLayoutParams();
			params1.height = mwidth/9;
			tableRows[i].setLayoutParams(params1);
		}
	}
	public void initbitmap(){  //加载十个数字的图片
		bitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt0);
		bitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt1);
		bitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt2);
		bitmaps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt3);
		bitmaps[4] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt4);
		bitmaps[5] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt5);
		bitmaps[6] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt6);
		bitmaps[7] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt7);
		bitmaps[8] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt8);
		bitmaps[9] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt9);
		bitmaps[10] = BitmapFactory.decodeResource(getResources(), R.drawable.sqrt_border);
	}
	public void init(){ //初始化九宫格和九宫格内的数据
		imageV_9[0] = (ImageView)findViewById(R.id.imageView101);
		imageV_9[1] = (ImageView)findViewById(R.id.imageView102);
		imageV_9[2] = (ImageView)findViewById(R.id.imageView103);
		imageV_9[3] = (ImageView)findViewById(R.id.imageView104);
		imageV_9[4] = (ImageView)findViewById(R.id.imageView105);
		imageV_9[5] = (ImageView)findViewById(R.id.imageView106);
		imageV_9[6] = (ImageView)findViewById(R.id.imageView107);
		imageV_9[7] = (ImageView)findViewById(R.id.imageView108);
		imageV_9[8] = (ImageView)findViewById(R.id.imageView109);
		
		imageViews[0] = (ImageView)findViewById(R.id.imageView1);
		imageViews[1] = (ImageView)findViewById(R.id.imageView2);
		imageViews[2] = (ImageView)findViewById(R.id.imageView3);
		imageViews[3] = (ImageView)findViewById(R.id.imageView4);
		imageViews[4] = (ImageView)findViewById(R.id.imageView5);
		imageViews[5] = (ImageView)findViewById(R.id.imageView6);
		imageViews[6] = (ImageView)findViewById(R.id.imageView7);
		imageViews[7] = (ImageView)findViewById(R.id.imageView8);
		imageViews[8] = (ImageView)findViewById(R.id.imageView9);
		imageViews[9] = (ImageView)findViewById(R.id.imageView10);
		imageViews[10] = (ImageView)findViewById(R.id.imageView11);
		imageViews[11] = (ImageView)findViewById(R.id.imageView12);
		imageViews[12] = (ImageView)findViewById(R.id.imageView13);
		imageViews[13] = (ImageView)findViewById(R.id.imageView14);
		imageViews[14] = (ImageView)findViewById(R.id.imageView15);
		imageViews[15] = (ImageView)findViewById(R.id.imageView16);
		imageViews[16] = (ImageView)findViewById(R.id.imageView17);
		imageViews[17] = (ImageView)findViewById(R.id.imageView18);
		imageViews[18] = (ImageView)findViewById(R.id.imageView19);
		imageViews[19] = (ImageView)findViewById(R.id.imageView20);
		imageViews[20] = (ImageView)findViewById(R.id.imageView21);
		imageViews[21] = (ImageView)findViewById(R.id.imageView22);
		imageViews[22] = (ImageView)findViewById(R.id.imageView23);
		imageViews[23] = (ImageView)findViewById(R.id.imageView24);
		imageViews[24] = (ImageView)findViewById(R.id.imageView25);
		imageViews[25] = (ImageView)findViewById(R.id.imageView26);
		imageViews[26] = (ImageView)findViewById(R.id.imageView27);
		imageViews[27] = (ImageView)findViewById(R.id.imageView28);
		imageViews[28] = (ImageView)findViewById(R.id.imageView29);
		imageViews[29] = (ImageView)findViewById(R.id.imageView30);
		imageViews[30] = (ImageView)findViewById(R.id.imageView31);
		imageViews[31] = (ImageView)findViewById(R.id.imageView32);
		imageViews[32] = (ImageView)findViewById(R.id.imageView33);
		imageViews[33] = (ImageView)findViewById(R.id.imageView34);
		imageViews[34] = (ImageView)findViewById(R.id.imageView35);
		imageViews[35] = (ImageView)findViewById(R.id.imageView36);
		imageViews[36] = (ImageView)findViewById(R.id.imageView37);
		imageViews[37] = (ImageView)findViewById(R.id.imageView38);
		imageViews[38] = (ImageView)findViewById(R.id.imageView39);
		imageViews[39] = (ImageView)findViewById(R.id.imageView40);
		imageViews[40] = (ImageView)findViewById(R.id.imageView41);
		imageViews[41] = (ImageView)findViewById(R.id.imageView42);
		imageViews[42] = (ImageView)findViewById(R.id.imageView43);
		imageViews[43] = (ImageView)findViewById(R.id.imageView44);
		imageViews[44] = (ImageView)findViewById(R.id.imageView45);
		imageViews[45] = (ImageView)findViewById(R.id.imageView46);
		imageViews[46] = (ImageView)findViewById(R.id.imageView47);
		imageViews[47] = (ImageView)findViewById(R.id.imageView48);
		imageViews[48] = (ImageView)findViewById(R.id.imageView49);
		imageViews[49] = (ImageView)findViewById(R.id.imageView50);
		imageViews[50] = (ImageView)findViewById(R.id.imageView51);
		imageViews[51] = (ImageView)findViewById(R.id.imageView52);
		imageViews[52] = (ImageView)findViewById(R.id.imageView53);
		imageViews[53] = (ImageView)findViewById(R.id.imageView54);
		imageViews[54] = (ImageView)findViewById(R.id.imageView55);
		imageViews[55] = (ImageView)findViewById(R.id.imageView56);
		imageViews[56] = (ImageView)findViewById(R.id.imageView57);
		imageViews[57] = (ImageView)findViewById(R.id.imageView58);
		imageViews[58] = (ImageView)findViewById(R.id.imageView59);
		imageViews[59] = (ImageView)findViewById(R.id.imageView60);
		imageViews[60] = (ImageView)findViewById(R.id.imageView61);
		imageViews[61] = (ImageView)findViewById(R.id.imageView62);
		imageViews[62] = (ImageView)findViewById(R.id.imageView63);
		imageViews[63] = (ImageView)findViewById(R.id.imageView64);
		imageViews[64] = (ImageView)findViewById(R.id.imageView65);
		imageViews[65] = (ImageView)findViewById(R.id.imageView66);
		imageViews[66] = (ImageView)findViewById(R.id.imageView67);
		imageViews[67] = (ImageView)findViewById(R.id.imageView68);
		imageViews[68] = (ImageView)findViewById(R.id.imageView69);
		imageViews[69] = (ImageView)findViewById(R.id.imageView70);
		imageViews[70] = (ImageView)findViewById(R.id.imageView71);
		imageViews[71] = (ImageView)findViewById(R.id.imageView72);
		imageViews[72] = (ImageView)findViewById(R.id.imageView73);
		imageViews[73] = (ImageView)findViewById(R.id.imageView74);
		imageViews[74] = (ImageView)findViewById(R.id.imageView75);
		imageViews[75] = (ImageView)findViewById(R.id.imageView76);
		imageViews[76] = (ImageView)findViewById(R.id.imageView77);
		imageViews[77] = (ImageView)findViewById(R.id.imageView78);
		imageViews[78] = (ImageView)findViewById(R.id.imageView79);
		imageViews[79] = (ImageView)findViewById(R.id.imageView80);
		imageViews[80] = (ImageView)findViewById(R.id.imageView81);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				switch(game.getANumber(i,j)){
					case 0:
						imageViews[j*9+i].setImageBitmap(bitmaps[0]);
						break;
					case 1:
						imageViews[j*9+i].setImageBitmap(bitmaps[1]);
						break;
					case 2:
						imageViews[j*9+i].setImageBitmap(bitmaps[2]);
						break;
					case 3:
						imageViews[j*9+i].setImageBitmap(bitmaps[3]);
						break;
					case 4:
						imageViews[j*9+i].setImageBitmap(bitmaps[4]);
						break;
					case 5:
						imageViews[j*9+i].setImageBitmap(bitmaps[5]);
						break;
					case 6:
						imageViews[j*9+i].setImageBitmap(bitmaps[6]);
						break;
					case 7:
						imageViews[j*9+i].setImageBitmap(bitmaps[7]);
						break;
					case 8:
						imageViews[j*9+i].setImageBitmap(bitmaps[8]);
						break;
					case 9:
						imageViews[j*9+i].setImageBitmap(bitmaps[9]);
						break;
				}
			}
		}
		for(int i=0;i<81;i++){
			final int a = i;
			imageViews[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (game.canChange(a)) {
						Toast.makeText(getApplicationContext(), "这个数字不能更改哦~", Toast.LENGTH_SHORT).show();
						return;
					}
					if (!border_state) {
						imageViews[a].setImageBitmap(bitmaps[10]);
						border_state = true;
						border_index = a;
					}else {
						imageViews[border_index].setImageBitmap(bitmaps[0]);
						imageViews[a].setImageBitmap(bitmaps[10]);
						border_index = a;
					}
					imageViews[a].setImageBitmap(bitmaps[10]);
					// TODO 自动生成的方法存根
					int arr[] = game.getAIntArray(a); //此处返回一个数组

//					Toast toast = Toast.makeText(getApplicationContext(),String.valueOf(arr.toString()) ,Toast.LENGTH_SHORT);			
//					toast.show();
					for(int i=1;i<10;i++){
						switch(i){
						case 1:
							imageV_9[0].setImageBitmap(bitmaps[1]);
							break;
						case 2:
							imageV_9[1].setImageBitmap(bitmaps[2]);
							break;
						case 3:
							imageV_9[2].setImageBitmap(bitmaps[3]);
							break;
						case 4:
							imageV_9[3].setImageBitmap(bitmaps[4]);
							break;
						case 5:
							imageV_9[4].setImageBitmap(bitmaps[5]);
							break;
						case 6:
							imageV_9[5].setImageBitmap(bitmaps[6]);
							break;
						case 7:
							imageV_9[6].setImageBitmap(bitmaps[7]);
							break;
						case 8:
							imageV_9[7].setImageBitmap(bitmaps[8]);
							break;
						case 9:
							imageV_9[8].setImageBitmap(bitmaps[9]);
							break;
					}
					}
					for(int i = 0;i < arr.length;i++){
						if (arr[i] == 0) {
							continue;
						}
						switch(arr[i]){
						case 1:
							imageV_9[0].setImageBitmap(bitmaps[0]);
							break;
						case 2:
							imageV_9[1].setImageBitmap(bitmaps[0]);
							break;
						case 3:
							imageV_9[2].setImageBitmap(bitmaps[0]);
							break;
						case 4:
							imageV_9[3].setImageBitmap(bitmaps[0]);
							break;
						case 5:
							imageV_9[4].setImageBitmap(bitmaps[0]);
							break;
						case 6:
							imageV_9[5].setImageBitmap(bitmaps[0]);
							break;
						case 7:
							imageV_9[6].setImageBitmap(bitmaps[0]);
							break;
						case 8:
							imageV_9[7].setImageBitmap(bitmaps[0]);
							break;
						case 9:
							imageV_9[8].setImageBitmap(bitmaps[0]);
							break;
					}
			        }
					//已知arr[5] = 2356，求array[4] = 14789,123456789
					int aaa[] = new int[9];
					int count=0;
					for(int i=0;i<9;i++){
						aaa[i] = i+1;
					}
					for(int i=0;i<aaa.length;i++){
						for(int j=0;j<arr.length;j++){
							if (aaa[i] == arr[j]) {
								aaa[i]=0;
							}
						}
					}
//					for(int t:aaa){
//						if (t!=0) {
//							array[count++]=t;
//						}
//					}
					for(int i=0;i<9;i++){
						final int aa = i+1;
						if (aaa[i] != 0) {
							imageV_9[i].setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO 自动生成的方法存根
//									Toast.makeText(getApplicationContext(), String.valueOf(aa), Toast.LENGTH_SHORT).show();
									game.setANumber(a,aa); //修改初始化数组中的值
									Index indexs = new Index(a);
									game.setToArrayList(indexs);
									imageViews[a].setImageBitmap(bitmaps[aa]);
									border_state = false;
								}
							});
						}else {
							imageV_9[i].setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO 自动生成的方法存根
									Toast.makeText(getApplicationContext(), "点击无效", Toast.LENGTH_SHORT).show();
								}
							});
						}
					}
					
				}
			});
		}
		
	}
}
