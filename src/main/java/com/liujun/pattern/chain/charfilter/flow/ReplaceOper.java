package com.liujun.pattern.chain.charfilter.flow;

import com.liujun.pattern.chain.charfilter.base.SeqLinkedList;
import com.liujun.pattern.chain.charfilter.base.ServiceExecInf;
import com.liujun.pattern.chain.charfilter.constant.FLowKeyEnum;

/**
 * 进行特殊符号的替换操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public class ReplaceOper implements ServiceExecInf {

  private static final String[] SPECIAL_CHAR = new String[] {" ", "\\t", "\\s"};

  public static final ReplaceOper INSTANCE = new ReplaceOper();

  @Override
  public boolean invoke(SeqLinkedList seqList) throws Exception {
    String input = (String) seqList.getParam().get(FLowKeyEnum.INPUT_PARAM_KEY.getKey());

    for (String item : SPECIAL_CHAR) {
      input = input.replaceAll(item, "");
    }

    seqList.getParam().put(FLowKeyEnum.OUTPUT_PARAM_KEY.getKey(), input);

    return seqList.nextExec();
  }
}
