package com.liujun.jvm.four.jconsole;



public class SynAddRunalbeInfo {

	static class SynAddRunnalbe implements Runnable
	{
		int a,b;
		
		public SynAddRunnalbe(int a,int b)
		{
			this.a = a;
			this.b = b;
		}
		
		public void run() {
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a + b);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100 ; i++) {
			new Thread(new SynAddRunnalbe(1, 2)).start();
			new Thread(new SynAddRunnalbe(2, 1)).start();
		}
	}
	
}
