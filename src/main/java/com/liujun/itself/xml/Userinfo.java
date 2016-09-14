package com.liujun.itself.xml;

import javax.xml.bind.annotation.*;

/**
 * 用于生成xml文件的实体信息
* 源文件名：Userinfo.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年9月14日
* 修改作者：liujun
* 修改日期：2016年9月14日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://com.liujun/", name = "user") 
public class Userinfo {
    
    /**
     * 用户名
    * @字段说明 name
    */
    @XmlElement(required = true)
    private String name;
    
    /**
     * 密码
    * @字段说明 passwd
    */
    @XmlElement(required = true)
    private String passwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Userinfo [name=" + name + ", passwd=" + passwd + "]";
    }
    

}
