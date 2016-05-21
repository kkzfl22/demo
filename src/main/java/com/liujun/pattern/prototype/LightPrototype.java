package com.liujun.pattern.prototype;

/**
 * 原型模式 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
 * 
 * final 关键字不能用在原型模式中
 * 
 * @author liujun
 * 
 * @date 2015年5月7日
 * @vsersion 0.0.1
 */
public class LightPrototype implements Cloneable {

	/**
	 * 基本信息
	 */
	private BaseItem item;

	/**
	 * 身高
	 */
	private int hight;

	public BaseItem getItem() {
		return item;
	}

	public void setItem(BaseItem item) {
		this.item = item;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	@Override
	public String toString() {
		return "LightPrototype [item=" + item + ", hight=" + hight + "]";
	}

	public LightPrototype clone() throws CloneNotSupportedException {
		LightPrototype proto = (LightPrototype) super.clone();
		return proto;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		LightPrototype li1 = new LightPrototype();

		BaseItem item = new BaseItem();

		item.setBaseAge(24);
		item.setBaseName("basenameA");

		li1.setItem(item);
		li1.setHight(170);

		System.out.println("li1:" + li1);

		// 进行浅拷贝的问题，如果一个对象中还引用另外一个对象时，进行浅拷贝后，改变拷贝的对象中的引用其他对象的信息，原来对象也会受到影响
		
		//浅拷贝仅能改变当前对象中变量的信息，不能改变引用对象中的信息

		LightPrototype li2 = li1.clone();
		li2.getItem().setBaseName("baseNamB");
		li2.getItem().setBaseAge(22);
		li2.setHight(180);

		System.out.println("li2:" + li2);
		System.out.println("li1:" + li1);

	}

}
