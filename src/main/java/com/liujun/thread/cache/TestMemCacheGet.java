package com.liujun.thread.cache;

public class TestMemCacheGet {

	public static void main(String[] args) throws InterruptedException {
		Computable<Integer,Integer> comp1= 	new Computable<Integer,Integer>(){

			@Override
			public Integer compute(Integer arg) throws InterruptedException {
				Thread.currentThread().sleep(1000);
				System.out.println(arg/0);
				return arg*2;
			}
			
		};
		
		MemCache<Integer,Integer> cache = new MemCache<Integer,Integer>(comp1);
		
		System.out.println(cache.compute(1000));
		System.out.println(cache.compute(2000));
	}
}
