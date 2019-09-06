package com.liujun.pattern.chain.charfilter.base;

/**
 * 进行代码执行流程接口
 *
 * @author liujun
 * @date 2014年4月8日
 * @vsersion 0.0.1
 */
public interface ServiceExecInf {

  /**
   * 进行正常流程执行的代码
   *
   * @param seqList
   * @return
   * @throws Exception
   */
  boolean invoke(SeqLinkedList seqList) throws Exception;
}
