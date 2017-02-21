package com.example.sudoku;

import java.util.ArrayList;

import android.R.integer;

public class Game {
	private final String str = "360000000004230800000004200"+"070460003820000014500013020"+"001900000007048300000000045";
	private int sudoku[] = new int[81];
	private int sudoku1[]= new int[81];//副本，不会改变里面的值
	private int array[] = new int[9];
	private ArrayList List = new ArrayList();
	public Game(){
		sudoku = fromPuzzleString(str);
//		calculateAllUsedTile();
		initstr1();
	}
	public void initstr1(){ //初始化一个数组副本
		for(int i=0;i<81;i++){
			sudoku1[i] = sudoku[i];
		}
	}
	public int getANumber(int x, int y){//根据二维数组的位置，返回该点实际的值
		
		return sudoku[y*9+x];
		
	}
	protected int[] fromPuzzleString(String src){ //把
        int []sudo = new int[src.length()];
        for(int i = 0;i < sudo.length;i++)
        {
            sudo[i] = src.charAt(i)-'0';
        }
        return sudo;
    }
	private int indexX,indexY;
	public int[] calculator(int x, int y){
		int c[] = new int[9];
		for(int i=0;i<9;i++){
			int t=getANumber(x, i);
			if (i == y) {
				continue;
			}
			if (t != 0) {
				c[t-1] = t;
			}
		}
		for(int i=0;i<9;i++){
			int t=getANumber(i, y);
			if (i == x) {
				continue;
			}
			if (t != 0) {
				c[t-1] = t;
			}
		}
		int startx = (x / 3) * 3;
        int starty = (y / 3) * 3;
        for(int i = startx; i < startx + 3; i++){
            for(int j = starty;j < starty + 3; j++){
                if(i == x && j == y)
                    continue;
                int t = getANumber(i, j);
                if(t!=0)
                    c[t-1] = t;
            }
        }
        int nused = 0;
        for(int t:c){
            if(t!=0)
                nused++;
        }
        int c1[] = new int[nused];
        nused = 0;
        for(int t:c){
            if(t!=0)
                c1[nused++] = t;
        }
		return c1;
	}
	public int[] getAIntArray(int a) { //点击某个view，传入索引
		// TODO 自动生成的方法存根
		indexX = (int)(a % 9);  //点击的imageview在二维数组中的坐标
		indexY = (int)(a / 9);
		return calculator(indexX, indexY);
	}
	public void setANumber(int index, int val){//根据点击选择的值，修改原始数组中的值。
		sudoku[index] = val;
	}
	public boolean canChange(int index){
		if(sudoku1[index] == 0){
			return false;
		}else {
			return true;
		}	
	}
	public void setToArrayList(Index a){
		List.add(a); //把一个对象放入arraylist中
	}
	public int getIndexFromArrayList(){
		int a = List.get(List.size()-1).index;
		return a;
	}
}
