package com.liujun.pattern.chain.charfilter.constant;

/**
 * 过滤器链的枚举对象
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/09/06
 */
public enum FLowKeyEnum {

  /** 输入的内容 */
  INPUT_PARAM_KEY("input_param_context"),

  /** 输出文本 */
  OUTPUT_PARAM_KEY("output_parap_context"),
  ;

  private String key;

  FLowKeyEnum(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
