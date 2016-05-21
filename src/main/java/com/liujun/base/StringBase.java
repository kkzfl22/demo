package com.liujun.base;

public class StringBase
{

	public static void test1()
	{

		String a = "a" + "b" + 1;

		System.out.println(a.hashCode());
		String b = "ab1";

		System.out.println(b.hashCode());

		System.out.println(a == b);
		
		String c = new String("ab1");
		
		System.out.println(c==a);

		System.out.println("----------sf");

	}

	private static String getA()
	{
		return "a";
	}

	public static void test2()
	{
		String a = "a";

		final String c = "a";

		String b = a + "b";

		String d = c + "b";

		String e = getA() + "b";

		String compare = "ab";

		System.out.println(b == compare);

		System.out.println(d == compare);

		System.out.println(e == compare);

	}

	public static void main(String[] args)
	{
		test1();
		test2();
	}
}
