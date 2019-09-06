package com.liujun.pattern.chain.charfilter.flow;

import com.liujun.pattern.chain.charfilter.base.SeqLinkedList;
import com.liujun.pattern.chain.charfilter.base.ServiceExecInf;
import com.liujun.pattern.chain.charfilter.constant.FLowKeyEnum;

/**
 * 添加指定的url
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public class AddUrl implements ServiceExecInf {

  private static final String url = "http://www.para.com";

  public static final AddUrl INSTANCE = new AddUrl();

  @Override
  public boolean invoke(SeqLinkedList seqList) throws Exception {

    String input = (String) seqList.getParam().get(FLowKeyEnum.INPUT_PARAM_KEY.getKey());

    String outValue = input + url;

    seqList.getParam().put(FLowKeyEnum.INPUT_PARAM_KEY.getKey(), outValue);

    return seqList.nextExec();
  }
}
