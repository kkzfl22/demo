package com.liujun.io;

public class TestTime {
	
	
	public static void main(String[] args) {
		long ttime = System.nanoTime();
		System.out.println(ttime);
		long ttime2 = System.nanoTime();
		System.out.println(ttime2);
		
		for (int i = 0; i < 1000; i++) {
			long ttime3 = System.nanoTime();
			System.out.println(ttime3);
		}
	}

}
