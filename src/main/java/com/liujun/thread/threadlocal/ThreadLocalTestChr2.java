package com.liujun.thread.threadlocal;

import com.ctc.wstx.util.DataUtil;
import com.liujun.thread.threadlocal.util.ThreadUtils;

/**
 * 验证多线程间ThreadLocal的数据是互不干扰的
 * @author liujun
 *
 */
public class ThreadLocalTestChr2
{
	
	public static void main(String[] args)
	{
		ThreadLocalTestChr2 thr2 = new ThreadLocalTestChr2();
		thr2.testchr2();
	}
	
	public void testchr2()
	{
		Thread thr1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				setData1();
				getData1();
			}
			
			
			private void setData1()
			{
				ThreadUtils.setData("这是方法1设置的值");			
			}
			
			private void getData1()
			{
				System.out.println(ThreadUtils.getData());
			}
		});	
		
		thr1.start();
	}
	
	
}
