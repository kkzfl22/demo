package com.liujun.pattern.chain.charfilter.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 序列存放流程执行信息
 * 
 * @author liujun
 * 
 * @date 2014年4月3日
 * 
 */
public class SeqLinkedList {

	/**
	 * 用来存放流程的容器
	 */
	private List<ServiceExecInf> linkedServ = new LinkedList<ServiceExecInf>();



	/**
	 * 用来存放参数的集合
	 */
	private Map<String, Object> param = new HashMap<String, Object>();

	/**
	 * 添加流程代码
	 *
	 * @param serviceExec
	 */
	public void addExec(ServiceExecInf serviceExec) {
		this.linkedServ.add(serviceExec);
	}

	/**
	 * 添加流程代码
	 *
	 * @param serviceExec
	 *            [] 流程执行数组
	 */
	public void addExec(ServiceExecInf[] serviceExec) {
		if (null != serviceExec) {
			for (int i = 0; i < serviceExec.length; i++) {
				this.linkedServ.add(serviceExec[i]);
			}
		}
	}

	public void putParam(String key, Object value) {
		this.param.put(key, value);
	}

	public Object getValue(String key) {
		return param.get(key);
	}


	public Map<String, Object> getParam() {
		return param;
	}

	/**
	 * 执行下一个流程代码
	 *
	 * @return
	 * @throws Exception
	 */
	public boolean nextExec() throws Exception {

		if (null != linkedServ && linkedServ.size() > 0) {

			ServiceExecInf servExec = linkedServ.remove(0);


			return servExec.invoke(this);
		} else {
			return true;
		}
	}


}
