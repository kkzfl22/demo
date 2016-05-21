package com.liujun.framework.junit4;


/**
 * 
 * 使用参数化运行器运行
 * 
 * 1,@RunWith(Parameterized.class)注解，
 * 表示该类将不使用Junit内建的运行器运行，
 * 而使用参数化运行器运行
 * 
 * 2,在参数化运行类中提供参数的方法上要使用@Parameters注解来修饰，
 * 同时在测试类的构造方法中为各个参数赋值（构造方法是有JUnit调用的），
 * 最后编写测试类，它会根据参数的组数来运行测试多次。
 * 
 * @author liujun
 *
 */
//@RunWith(Parameterized.class)
public class TestParamten {
	
	// private int a;
	//
	// private int b;
	//
	// public TestParamten(int a, int b) {
	// this.a = a;
	// this.b = b;
	// }
	//
	// //@Parameters
	// public static Collection<Object[]> getIntList()
	// {
	// return Arrays.asList(new Object[]{1,2},new Object[]{3,4});
	// }
	//
	//
	// //@Test
	// public void testadd1()
	// {
	// System.out.println(a+b);
	// Assert.assertThat((a+b), anything());
	// }
	//
	// //@Test
	// public void testMock()
	// {
	// ArrayList list = mock(ArrayList.class,withSettings().serializable());
	//
	// when(list.get(0)).thenReturn(new Integer(1111));
	//
	// System.out.println(list.get(0));
	//
	//
	// }

}
