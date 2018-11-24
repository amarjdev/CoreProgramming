package com.java.alg.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * SUDUKO SOLVER USING BACKTRACKING ALGORITHEM
 * Input {{0,4,2,1},{1,0,0,3},{4,0,3,0},{0,3,0,4}}
 * Input {{3,4,2,1},{1,2,4,3},{4,1,3,2},{2,3,1,4}}
 */
public class Sudoko {
	public static int MAX =4;
	public static int RC=2;
	public static void main(String args[]) {
		int[][] inputArray = {{0,0,0,1},{1,0,0,3},{4,0,0,0},{0,3,0,0}};
		solver(inputArray, 0, 0, 1);
	}
	public static boolean solver(int[][] inputArray,int i, int j, int input) {
		for(int r=i;r<MAX;r++) {
			for(int c=j;c<MAX;c++) {
				if(inputArray[r][c] == 0) {
					List valsToFit = availableValToFit(inputArray, r, c);
					if(valsToFit == null || valsToFit.size() == 0) {
						return false;
					} else {
						int z=0;int sr =r; int sc =c;
						if(c == MAX -1) {
							sr = r+1;
							sc =0;
						} else {
							sr = r;
							sc =c +1;
						}
						boolean isSolved = false;
						while(!isSolved) {
							if(z >= valsToFit.size()) return false;
							inputArray = addVal(inputArray, (Integer)valsToFit.get(z), r, c);
							isSolved = solver(inputArray, sr, sc, 0);
							z++;
						}
						c =MAX;
						r=MAX;
					}
				}
			}
			j=0;
		}
		if(input == 1) {
			for(int r =0; r<MAX;r++) {
				for(int c =0; c<MAX;c++) {
					System.out.print(inputArray[r][c]+",");
				}
				System.out.println();
			}
		}
		return true;
		
	}
	public static int[][] addVal(int[][] inputArray, int value, int r, int c) {
		inputArray[r][c] = value;
		return inputArray;
	}
	public static List availableValToFit(int[][] inputArray,int r, int c) {
		int rIdx = getStartIndex(r);
		int cIdx = getStartIndex(c);
		int[] subMatAvailableVals = getSubMatAvlValues(inputArray, rIdx, cIdx);
		int[] output = filterRowColValues(subMatAvailableVals, inputArray, r, c);
		List<Integer> ls = new ArrayList<>();
		for(int i=0;i<MAX;i++) {
			if(output[i] == 0) {
				ls.add(i+1);
			}
		}
		return ls;
	}
	public static int getStartIndex(int i) {
		int count =0;
		while(i >= count+RC) {
			count = count +RC;
		}
		return count;
	}
	public static int[] getSubMatAvlValues(int[][] inputArray, int rIdx, int cIdx) {
		int out[] = {0,0,0,0};
		for( int i=rIdx; i <rIdx + RC;i++) {
			for(int j=cIdx;j<cIdx+RC;j++) {
				if(inputArray[i][j] > 0) {
					out[inputArray[i][j] - 1] = 1; 
				}
			}
		}
		return out;
	}
	
	public static int[] filterRowColValues(int[] input,int[][] inputArray,int r,int c) {
		int out[] =input;
		for(int i =0;i<MAX;i++ ) {
			if(inputArray[i][c] >0) {
				out[inputArray[i][c] -1] = 1;
			}
		}
		for(int i =0;i<MAX;i++ ) {
			if(inputArray[r][i] >0) {
				out[inputArray[r][i] -1] = 1;
			}
		}
		return out;
	}
	
}

