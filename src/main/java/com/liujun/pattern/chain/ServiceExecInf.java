package com.liujun.pattern.chain;

/**
 * 进行代码执行流程接口
 * @author liujun
 *
 * @date 2014年4月8日
 * @vsersion 0.0.1
 */
public interface ServiceExecInf {

	/**
	 * 进行正常流程执行的代码
	 * 
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public boolean invoke(SeqLinkedList seqList) throws Exception;

	/**
	 * 进行回退流程操作
	 * 
	 * @param seqlist
	 * @return
	 * @throws Exception
	 */
	public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception;

}
