package com.liujun.jvm.four.visualVM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ctc.wstx.io.BufferRecycler;

/**
 * 
 * 使用BTrace日志跟踪
 * 
 * 
 * BTrace限制 为了保证trace语句只读, BTrace对trace脚本有一些限制(比如不能改变被trace代码中的状态) BTrace
 * class不能新建类, 新建数组, 抛异常, 捕获异常, 
 * 不能调用实例方法以及静态方法(com.sun.btrace.BTraceUtils除外) 
 * 不能将目标程序和对象赋值给BTrace的实例和静态field 
 * 不能定义外部, 内部, 匿名, 本地类 
 * 不能有同步块和方法 
 * 不能有循环
 *  不能实现接口,
 * 不能扩展类 不能使用assert语句, 不能使用class字面值
 * 
 * 
 * 
 * 未被注解的方法参数 
未使用注解的方法参数一般都是用来做方法签名匹配用的, 他们一般和被trace方法中参数出现的顺序一致. 不过他们也可以与注解方法交错使用, 如果一个参数类型声明为*AnyType[]*, 则表明它按顺序"通吃"方法所有参数. 未注解方法需要与*Location*结合使用: 
Kind.ENTRY, Kind.RETURN- 被trace方法参数
Kind.THROW - 抛异常
Kind.ARRAY_SET, Kind.ARRAY_GET - 数组索引
Kind.CATCH - 捕获异常
Kind.FIELD_SET - 属性值
Kind.LINE - 行号
Kind.NEW - 类名
Kind.ERROR - 抛异常
 * 
 * @author liujun
 * @date 2015年8月14日
 * @verion 0.0.1
 */
public class BtraceTest {

	/**
	 * 日志信息
	 */
	private Logger log = Logger.getLogger(BtraceTest.class);
	
	
	private long value = 0;

	public synchronized int add(int a, int b) {
		log.info("a:+b" + a + b);
		
		int v = 2013 ;
		value = 100;
		return a + b;
	}

	public static void main(String[] args) throws IOException {

		String classPath = BtraceTest.class.getResource("../../../../../log4j.xml").getPath();

		System.out.println(classPath);

		PropertyConfigurator.configure(classPath);

		BtraceTest test = new BtraceTest();

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			read.readLine();

			int a = (int) Math.round(Math.random() * 10000);
			int b = (int) Math.round(Math.random() * 10000);

			System.out.println(test.add(a, b));
		}
	}

}
