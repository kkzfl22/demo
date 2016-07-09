package com.liujun.jvm.dataguru.ten.bean;

import java.util.ArrayList;
import java.util.List;

public class ClassBeanInfo {

    /**
     * 所有的字段
    * @字段说明 proprotylist
    */
    private List<String> proprotylist = new ArrayList<String>();

    /**
     * 所有方法
    * @字段说明 methodList
    */
    private List<String> methodList = new ArrayList<String>();

    /**
     * 超类的名称
    * @字段说明 superClassName
    */
    private String superClassName;

    /**
     * 实现的接口
    * @字段说明 interClassName
    */
    private String interClassName;

    public List<String> getProprotylist() {
        return proprotylist;
    }

    public void addProprotylist(String proproty) {
        this.proprotylist.add(proproty);
    }

    public List<String> getMethodList() {
        return methodList;
    }

    public void addMethodList(String method) {
        this.methodList.add(method);
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public String getInterClassName() {
        return interClassName;
    }

    public void setInterClassName(String interClassName) {
        this.interClassName = interClassName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClassBeanInfo [proprotylist=");
        builder.append(proprotylist);
        builder.append(", methodList=");
        builder.append(methodList);
        builder.append(", superClassName=");
        builder.append(superClassName);
        builder.append(", interClassName=");
        builder.append(interClassName);
        builder.append("]");
        return builder.toString();
    }

}
