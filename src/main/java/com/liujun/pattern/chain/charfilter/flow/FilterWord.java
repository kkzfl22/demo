package com.liujun.pattern.chain.charfilter.flow;

import com.liujun.pattern.chain.charfilter.base.SeqLinkedList;
import com.liujun.pattern.chain.charfilter.base.ServiceExecInf;
import com.liujun.pattern.chain.charfilter.constant.FLowKeyEnum;

/**
 * 敏感词过滤
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public class FilterWord implements ServiceExecInf {

  private static final String[] SPECIAL_CHAR = new String[] {"共产党", "共党"};

  public static final FilterWord INSTANCE = new FilterWord();

  @Override
  public boolean invoke(SeqLinkedList seqList) throws Exception {
    String input = (String) seqList.getParam().get(FLowKeyEnum.INPUT_PARAM_KEY.getKey());

    for (String values : SPECIAL_CHAR) {
      if (input.indexOf(values) != -1) {
        return false;
      }
    }

    return seqList.nextExec();
  }
}
