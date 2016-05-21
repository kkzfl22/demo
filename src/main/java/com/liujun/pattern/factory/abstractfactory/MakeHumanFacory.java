package com.liujun.pattern.factory.abstractfactory;

/**
 * 制造人类
 * 
 * @author liujun
 * 
 */
public class MakeHumanFacory extends AbsMakeHumanFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends HumanInf> T makeHuman(Class<T> className) {
		T result = null;
		try {
			result = (T) Class.forName(className.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.out.println("制造人类出现问题");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
