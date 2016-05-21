package com.liujun.pattern.state.sell;

import java.util.HashMap;
import java.util.Map;

/**
 * 焦货机对象
 * 
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class SellMachineContext {

	/**
	 * 商品信息
	 */
	private static final Map<String, Goods> SELLMAP = new HashMap<String, Goods>();

	/**
	 * 投入硬币的数量
	 */
	private int coinNum = 0;

	/**
	 * 没有硬币状态
	 */
	private SellMachineInf notCoin = new NotCoinState(this);

	/**
	 * 投入硬币状态
	 */
	private SellMachineInf insertCoin = new InsertCoinState(this);

	/**
	 * 选择商品状态
	 */
	private SellMachineInf selectGoods = new SelectGoodsState(this);

	/**
	 * 售出商品状态
	 */
	private SellMachineInf sellGoods = new SellGoodsState(this);

	/**
	 * 退币状态
	 */
	private SellMachineInf rollBackCoin = new RollCoinState(this);

	/**
	 * 当前售货机状态
	 */
	private SellMachineInf currsellState;

	public SellMachineContext() {
		this.currsellState = this.notCoin;
	}

	/**
	 * 添加商品
	 * 
	 * @param key
	 *            商品编号
	 * @param value
	 *            添加商品的数量
	 */
	public void addGoods(String key, Goods value) {
		SELLMAP.put(key, value);
	}

	public static Map<String, Goods> getSellmap() {
		return SELLMAP;
	}

	protected int getCoinNum() {
		return coinNum;
	}

	protected void setCoinNum(int coinNum) {
		this.coinNum = coinNum;
	}

	protected SellMachineInf getCurrsellState() {
		return currsellState;
	}

	protected void setCurrsellState(SellMachineInf currsellState) {
		this.currsellState = currsellState;
	}

	protected SellMachineInf getNotCoin() {
		return notCoin;
	}

	protected SellMachineInf getInsertCoin() {
		return insertCoin;
	}

	protected SellMachineInf getSelectGoods() {
		return selectGoods;
	}

	protected SellMachineInf getSellGoods() {
		return sellGoods;
	}

	protected SellMachineInf getRollBackCoin() {
		return rollBackCoin;
	}

	public void insertCoin(int coin) {
		this.currsellState.insertCoin(coin);
	}

	public int rollBackCoin() {
		return this.currsellState.rollBackCoin();
	}

	public void selectfix() {
		this.currsellState.selectfix();
	}

	public Goods workOff(String key, int sellNum) {
		return this.currsellState.workOff(key, sellNum);
	}

}
