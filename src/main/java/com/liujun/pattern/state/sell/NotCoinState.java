package com.liujun.pattern.state.sell;

/**
 * 尚未投入硬币状态
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class NotCoinState implements SellMachineInf {

	/**
	 * 当前的售货机对象
	 */
	private SellMachineContext context;
	
	
	public NotCoinState(SellMachineContext context) {
		this.context = context;
	}

	@Override
	public void insertCoin(int coinnum) {
		System.out.println("当前在没有硬币投入的状态下,投入硬币:"+coinnum);
		//当前已经投入硬币，则可以进行其他的操作，则设置状状为已经投入硬币状态
		this.context.setCurrsellState(this.context.getInsertCoin());
		this.context.getCurrsellState().insertCoin(coinnum);
	}

	@Override
	public int rollBackCoin() {
		
		System.out.println("当前未投入硬币，不能如币");
		return 0;
	}

	@Override
	public void selectfix() {
		System.out.println("当前没有投入硬币，不能选择商品");
	}

	@Override
	public Goods workOff(String key, int sellNum) {
		System.out.println("当前没有硬币，不能售商品");
		return null;
	}
}
