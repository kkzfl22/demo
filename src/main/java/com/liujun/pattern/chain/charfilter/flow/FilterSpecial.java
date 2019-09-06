package com.liujun.pattern.chain.charfilter.flow;

import com.liujun.pattern.chain.charfilter.base.SeqLinkedList;
import com.liujun.pattern.chain.charfilter.base.ServiceExecInf;
import com.liujun.pattern.chain.charfilter.constant.FLowKeyEnum;

/**
 * 进行特殊字符的检查
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public class FilterSpecial implements ServiceExecInf {

  private static final char[] SPECIAL_CHAR =
      new char[] {'!', '@', '#', '$', '%', '^', '&', '*', '(', '"', '\''};

  public static final FilterSpecial INSTANCE = new FilterSpecial();

  @Override
  public boolean invoke(SeqLinkedList seqList) throws Exception {
    String input = (String) seqList.getParam().get(FLowKeyEnum.INPUT_PARAM_KEY.getKey());

    char[] valus = input.toCharArray();

    for (char items : valus) {
      for (char item : SPECIAL_CHAR) {
        if (item == items) {
          return false;
        }
      }
    }

    return seqList.nextExec();
  }
}
