package com.liujun.pattern.chain.charfilter;

import com.liujun.pattern.chain.charfilter.base.SeqLinkedList;
import com.liujun.pattern.chain.charfilter.constant.FLowKeyEnum;
import com.liujun.pattern.chain.charfilter.flow.AddUrl;
import com.liujun.pattern.chain.charfilter.flow.FilterSpecial;
import com.liujun.pattern.chain.charfilter.flow.FilterWord;
import com.liujun.pattern.chain.charfilter.flow.ReplaceOper;

/**
 * 进行字符串的过滤操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public class CharFileter {

  public static final CharFileter INSTANCE = new CharFileter();

  public String filter(String inputContext) {
    SeqLinkedList seqLinkedList = new SeqLinkedList();

    seqLinkedList.addExec(FilterSpecial.INSTANCE);
    seqLinkedList.addExec(FilterWord.INSTANCE);
    seqLinkedList.addExec(AddUrl.INSTANCE);
    seqLinkedList.addExec(ReplaceOper.INSTANCE);

    seqLinkedList.putParam(FLowKeyEnum.INPUT_PARAM_KEY.getKey(), inputContext);

    boolean rsp = false;
    try {
      rsp = seqLinkedList.nextExec();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (rsp) {
      return (String) seqLinkedList.getParam().get(FLowKeyEnum.OUTPUT_PARAM_KEY.getKey());
    }
    return null;
  }

  public static void main(String[] args) {

    System.out.println(INSTANCE.filter("@"));
    System.out.println(INSTANCE.filter("共产党是。。"));
    System.out.println(INSTANCE.filter("\t这是什么 "));
  }
}
