package com.liujun.pattern.builder.builder;

/**
 * 创建多个复杂的对象
 * 与工程模式的区别就是：工厂模式关注的是创建单个产品，
 * 而建造者模式则关注创建符合对象，多个部分
 * @author liujun
 *
 * @date 2015年5月7日
 * @vsersion 0.0.1
 */
public class House {
	
	public static void main(String[] args) {
		
		//设计师
		Designer des = new Designer();
		
		//民工建造房子
		Builder build = new Worker();
		
		//设计师指挥民工建造房子
		des.order(build);
		
		//房主交房
		build.getRoom();
		
	}

}
