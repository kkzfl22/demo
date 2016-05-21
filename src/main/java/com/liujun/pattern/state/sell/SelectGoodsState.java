package com.liujun.pattern.state.sell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 选择商品模式
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class SelectGoodsState implements SellMachineInf {

	

	/**
	 * 当前的售货机对象
	 */
	private SellMachineContext context;
	
	
	public SelectGoodsState(SellMachineContext context) {
		this.context = context;
	}

	
	@Override
	public void insertCoin(int coin) {
		System.out.println("当前在售卖商品模式，可以切换到投币模式");
		this.context.setCurrsellState(this.context.getInsertCoin());
		this.context.getCurrsellState().insertCoin(coin);
	}

	@Override
	public int rollBackCoin() {
		
		System.out.println("当前在售卖商品模式，可以切换到退币模式");
		this.context.setCurrsellState(this.context.getRollBackCoin());
		return this.context.rollBackCoin();
	}

	@SuppressWarnings("static-access")
	@Override
	public void selectfix() {
		System.out.println("当前在售卖模式，进行选择商品");
		Map<String,Goods> goodsMap = this.context.getSellmap();
		
		Iterator<Entry<String, Goods>> iterGoods = goodsMap.entrySet().iterator();
		
		while(iterGoods.hasNext())
		{
			Entry<String, Goods> iterEntry = iterGoods.next();
			Goods selectGoods = iterEntry.getValue();
			
			if(selectGoods.getNeedCoin() <= this.context.getCoinNum())
			{
				System.out.println("可以选择:" + iterEntry.getKey() + ";商品信息:"+selectGoods);
			}
		}
	}

	@Override
	public Goods workOff(String key, int sellNum) {
		System.out.println("当前在售卖模式，可以进行直接售卖");
		this.context.setCurrsellState(this.context.getSellGoods());
		return this.context.getCurrsellState().workOff(key, sellNum);
	}

}
 