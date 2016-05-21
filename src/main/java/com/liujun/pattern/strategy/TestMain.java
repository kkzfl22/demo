package com.liujun.pattern.strategy;

public class TestMain
{
	public static void main(String[] args)
	{
		InVokeService invoke = new InVokeService();
		invoke.invoke("1");
		invoke.invoke("2");
		invoke.invoke("3");
		
		test();
	}
	

	public static void test()
	{
		int start = 2400;
		
		int more = 300;
		
		int end = 0;
		
		for (int i = 0; i < 30; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				if(i != 0)
				{
					end +=start+more;
				}
				else
				{
					end +=start;
				}
				
			}
			
			System.out.println("第"+(i+1)+"年:总价："+end+";上涨："+more);
			more = more +300;
			
			
		}
		
		System.out.println(end);
	}
}
