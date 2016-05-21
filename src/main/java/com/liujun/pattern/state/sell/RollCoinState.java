package com.liujun.pattern.state.sell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 退货模式
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class RollCoinState implements SellMachineInf {


	/**
	 * 当前的售货机对象
	 */
	private SellMachineContext context;
	
	
	public RollCoinState(SellMachineContext context) {
		this.context = context;
	}
	
	@Override
	public void insertCoin(int coin) {
		System.out.println("当前在退货模式，可以进行投币操作");
		this.context.setCurrsellState(this.context.getInsertCoin());
		this.context.getCurrsellState().insertCoin(coin);
	}

	@Override
	public int rollBackCoin() {
		System.out.println("当前在退币模式，进行退币操作");
		return this.context.getCoinNum();
	}

	@Override
	public void selectfix() {
		System.out.println("当前在退币模式，不能进行选择商品");
	}

	@Override
	public Goods workOff(String key, int sellNum) {
		System.out.println("当前在退币模式，不能售卖商品");
		return null;
	}
}
