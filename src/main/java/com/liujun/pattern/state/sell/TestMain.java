package com.liujun.pattern.state.sell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class TestMain {

	public static void main(String[] args) throws IOException {
		SellMachineContext context = new SellMachineContext();

		Goods goods1 = new Goods("雪碧", 3, 5, 3);
		context.addGoods("0001", goods1);
		Goods goods2 = new Goods("可口可乐", 3, 5, 3);
		context.addGoods("0002", goods2);

		// 投入硬币
		context.insertCoin(100);

		// 选择商品
		context.selectfix();

		// 售卖
		sellMsg(context);

		// 投入硬币
		context.insertCoin(100);

		// 选择商品
		context.selectfix();

		// 售卖
		sellMsg(context);

		// 售卖
		sellMsg(context);

		// 售卖
		sellMsg(context);
		// 退币
		int rollCoin = context.rollBackCoin();

		System.out.println("最后退币:" + rollCoin);

	}

	/**
	 * 售卖
	 * 
	 * @param context
	 * @throws IOException
	 */
	public static void sellMsg(SellMachineContext context) throws IOException {
		// 购买商品
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		if ((line = read.readLine()) != null) {
			Goods goods22 = context.workOff(line, 2);
			System.out.println("卖出的东西信息:" + goods22);
		}
	}
}
