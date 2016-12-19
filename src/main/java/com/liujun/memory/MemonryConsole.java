package com.liujun.memory;

public enum MemonryConsole {

    /**
     * 标识当前内存块已经用满
    * @字段说明 FULL
    */
    FULL(2),

    /**
     * 当前内存块可使用
    * @字段说明 CANUSE
    */
    CANUSE(1),

    /**
     * 可使用内存块不够
    * @字段说明 NOT_ENOUGH
    */
    NOT_ENOUGH(0),

    ;

    /**
     * 当前的标识
    * @字段说明 flag
    */
    private int flag;

    private MemonryConsole(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
