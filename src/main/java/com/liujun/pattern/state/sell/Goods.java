package com.liujun.pattern.state.sell;

/**
 * 商品信息
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class Goods {

	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品价格
	 */
	private float price;
	
	
	/**
	 * 商品数量
	 */
	private int num;

	
	/**
	 * 商品售价，需要几个硬币
	 */
	private int needCoin;
	
	
	

	public Goods(String name, float price, int num, int needCoin) {
		this.name = name;
		this.price = price;
		this.num = num;
		this.needCoin = needCoin;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getNeedCoin() {
		return needCoin;
	}


	public void setNeedCoin(int needCoin) {
		this.needCoin = needCoin;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goods [name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", num=");
		builder.append(num);
		builder.append(", needCoin=");
		builder.append(needCoin);
		builder.append("]");
		return builder.toString();
	}


}
