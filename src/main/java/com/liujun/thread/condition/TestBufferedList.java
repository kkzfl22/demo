package com.liujun.thread.condition;

public class TestBufferedList {

	public static void main(String[] args) {

		final BufferedList<Integer> testCondition = new BufferedList<Integer>();

		final int size = 100000;

		Runnable run1 = new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < size; i++) {
						System.out.println("放入的值："+i);
						testCondition.put(i);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		
		Runnable run2 = new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < size; i++) {
						int value = testCondition.get();
						System.out.println("取出的值："+value);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		
		Thread thr1 = new Thread(run1); 
		Thread thr2 = new Thread(run2); 
		thr1.start();
		thr2.start();
	}
}
