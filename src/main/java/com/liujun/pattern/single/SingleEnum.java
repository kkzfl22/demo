package com.liujun.pattern.single;

/**
 * 使用枚兴来进实现单例的方式
 * @author liujun
 *
 * @date 2014年12月16日
 * @vsersion 0.0.1
 */
public enum SingleEnum {

	INSTANCE();

	public void getname() {
		System.out.println("名称信息");
	}
	
	public void print()
	{
		System.out.println("111");
	}

	public static void main(String[] args) {
		SingleEnum.INSTANCE.getname();
		SingleEnum.INSTANCE.print();
	}

}
