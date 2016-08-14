package com.liujun.io.nio.trans.console;

public enum Config {

    /**
     * 前端端口标识 
    * 方法描述
     * @return 
    * @创建日期 2016年8月14日
    */
    CONFIG_TYPE_FIRST(1),

    /**
     * 后端端口标识 
    * @字段说明 CONFIG_TYPE_END
    */
    CONFIG_TYPE_END(2);

    private Config(int key) {
        this.key = key;
    }

    /**
     * 配制key信息
    * @字段说明 key
    */
    private int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}
