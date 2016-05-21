package com.liujun.pattern.state.sell;

public class SellGoodsState implements SellMachineInf {

	/**
	 * 当前的售货机对象
	 */
	private SellMachineContext context;
	
	
	public SellGoodsState(SellMachineContext context) {
		this.context = context;
	}

	
	@Override
	public void insertCoin(int coin) {
		System.out.println("当前在卖出模式，可以投入硬币,当前投入:"+coin);
		this.context.setCurrsellState(this.context.getInsertCoin());
		this.context.getCurrsellState().insertCoin(coin);
	}

	@Override
	public int rollBackCoin() {
		System.out.println("当前在卖出模式，可以退币");
		this.context.setCurrsellState(this.context.getRollBackCoin());
		return this.context.getCurrsellState().rollBackCoin();
	}

	@Override
	public void selectfix() {
		System.out.println("当前在卖出模式，可以选择");
		this.context.setCurrsellState(this.context.getSelectGoods());
		this.context.getCurrsellState().selectfix();
	}

	@SuppressWarnings("static-access")
	@Override
	public Goods workOff(String key, int sellNum) {
		System.out.println("当前在卖出模式，可以进行卖出");
		Goods goods = this.context.getSellmap().get(key);
		
		//1，检查用户的数据，以及所需在硬币是否足够
		int needvalue =(int) (goods.getPrice()*sellNum);
		
		if(needvalue < this.context.getCoinNum())
		{
			goods.setNum(goods.getNum() - sellNum);
			
			//减去商品数量
			this.context.getSellmap().put(key, goods);
			
			//减去金额
			this.context.setCoinNum(this.context.getCoinNum() - needvalue);
			
			Goods sellGoods = new Goods(goods.getName(),goods.getPrice(),sellNum,goods.getNeedCoin());
			
			sellGoods.setName(goods.getName());
			sellGoods.setNeedCoin(goods.getNeedCoin());
			sellGoods.setPrice(goods.getPrice());
			sellGoods.setNum(sellNum);
			
			return sellGoods;
		}
		
		return null;
	}

}
