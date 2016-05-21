package com.liujun.pattern.dynamicproxy.jdk;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * 动态代理类的核心实现
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class Proxy {
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(Proxy.class);
	
	
	/**
	 * 获得代理类实实例对象
	 * @param infce 类信息
	 * @param h 实现对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception { 
		
		//JDK6 Complier API, CGLib ---- ASM	
		String src = getClassMsg(infce);
		
		String namePath = infce.getClassLoader().getResource("com/liujun/pattern/dynamicproxy/jdk/").getPath();
		
		String className= "$Proxy1";
		String fileName = 
				namePath+"/"+className+".java";
		//生成java文件
		FileWriter fw = null;
		try {
			File f = new File(fileName);
			fw = new FileWriter(f);
			fw.write(src);
			fw.flush();
			
		} catch (Exception e) {
			log.error("Proxy newProxyInstance Exception",e);
		}
		finally
		{
			IOUtils.closeQuietly(fw);
		}
		
		//进行动态编译操作,此使用jdk自动的Complier
		StandardJavaFileManager fileMgr = null;
		try {
			//compile进行java的动态编译
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			fileMgr = compiler.getStandardFileManager(null, null, null);
			Iterable units = fileMgr.getJavaFileObjects(fileName);
			CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
			t.call();
		} catch (Exception e) {
			log.error("Proxy newProxyInstance compile Exception",e);
		}
		finally
		{
			IOUtils.closeQuietly(fileMgr);
		}
		
		
		
		//load into memory and create an instance
		URL[] urls = new URL[] {new URL("file:/" + namePath)};
		URLClassLoader ul = new URLClassLoader(urls);
		
		Class c = ul.loadClass("com.liujun.pattern.dynamicproxy.jdk."+className);
		
		//通过构造方法加载实例对象
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		Object m = ctr.newInstance(h);

		return m;
	}
	
	/**
	 * 得到类的信息
	 * @param infce
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String getClassMsg(Class infce)
	{
		String methodStr = "";
		String rt = "\r\n";
		
		Method[] methods = infce.getMethods();
		
		for(Method m : methods) {
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 "    try {" + rt +
						 "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
						 "    h.invoke(this, md);" + rt +
						 "    }catch(Exception e) {e.printStackTrace();}" + rt +
						
						 "}";
		}
		
		String src = 
			"package com.liujun.pattern.dynamicproxy.jdk;" +  rt +
			"import java.lang.reflect.Method;" + rt +
			"public class $Proxy1 implements " + infce.getName() + "{" + rt +
			"    public $Proxy1(InvocationHandler h) {" + rt +
			"        this.h = h;" + rt +
			"    }" + rt +
			
			
			"    com.liujun.pattern.dynamicproxy.jdk.InvocationHandler h;" + rt +
							
			methodStr +
			"}";
		
		return src;
	}
}
