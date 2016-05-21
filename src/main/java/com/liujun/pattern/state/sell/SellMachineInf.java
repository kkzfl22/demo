package com.liujun.pattern.state.sell;

/**
 * 售货机接口
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public interface SellMachineInf {
	
	/**
	 * 投入硬币
	 */
	public void insertCoin(int coin);
	
	/**
	 * 退回硬币
	 */
	public int rollBackCoin();
	
	/**
	 * 选择饮料
	 */
	public void selectfix();
	
	
	/**
	 * 售出饮料
	 */
	public Goods workOff(String key,int sellNum);
	
}
