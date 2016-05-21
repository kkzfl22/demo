package com.liujun.jvm.four.hsdis;

/**
 * 进行hsdis测试
 * java -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+PrintAssembly -Xcomp -XX:CompileCommand=dontinline,*Bar.sum -XX:CompileCommand=compileonly,*Bar.sum -jar jvm.jar  
 * 
 * 参数-XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading 不可缺少
 * 
 * 环境说明：
1.      CPU：Intel i7
2.      操作系统：Ubuntu 12.04-amd64
3.      JDK：openjdkversion "1.7.0-internal-fastdebug"，我使用的是fastdebug版本，可以直接使用openjdk。
4.      hsdis  下载地址：https://kenai.com/projects/base-hsdis/downloads，也可自行下载源代码编译，将hsdis-*.so放在目录$JAVA_HOME/jre/lib/amd64/server下.
 * 
 * 
 * @author liujun
 * @date 2015年8月9日
 * @verion 0.0.1
 */
public class Bar {

	
	int a = 1;
	
	static int b = 2;
	
	public int sum(int c)
	{
		return a+b+c;
	}
	
	public static void main(String[] args) {
		int value = new Bar().sum(3);
		System.out.println(value);
	}
}
