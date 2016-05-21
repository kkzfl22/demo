package com.liujun.pattern.state.sell;

/**
 * 当前为投入硬币状态
 * @author liujun
 * @date 2015年11月28日
 * @verion 0.0.1
 */
public class InsertCoinState implements SellMachineInf {

	
	/**
	 * 当前的售货机对象
	 */
	private SellMachineContext context;
	
	
	public InsertCoinState(SellMachineContext context) {
		this.context = context;
	}

	
	@Override
	public void insertCoin(int coin) {
		System.out.println("当前用户已经在投币状态下，可以继续投入硬币");
		this.context.setCoinNum((this.context.getCoinNum() + coin));
	}

	@Override
	public int rollBackCoin() {
		System.out.println("用户已经投入了硬币，不买商品，可以退还,切换到退币模式");
		this.context.setCurrsellState(this.context.getRollBackCoin());
		return this.context.rollBackCoin();
	}

	@Override
	public void selectfix() {
		System.out.println("用户已经投入了硬币，可以切换到选择商品模式");
		this.context.setCurrsellState(this.context.getSelectGoods());
		this.context.getCurrsellState().selectfix();
	}

	@Override
	public Goods workOff(String key, int sellNum) {
		System.out.println("用户已经投入了硬币，可以售卖商品");
		this.context.setCurrsellState(this.context.getSellGoods());
		
		return this.context.getCurrsellState().workOff(key, sellNum);
	}

}
