package com.liujun.jvm.dataguru.ten.bean;

import java.util.ArrayList;
import java.util.List;

public class ClassBeanInfo {

    /**
     * 所有的字段
    * @字段说明 fieldlist
    */
    private List<String> fieldlist = new ArrayList<String>();

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

    /**
     * 类名
    * @字段说明 className
    */
    private String className;

    public List<String> getFieldylist() {
        return fieldlist;
    }

    public void addFieldlist(String proproty) {
        this.fieldlist.add(proproty);
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClassBeanInfo [proprotylist=");
        builder.append(fieldlist);
        builder.append(", methodList=");
        builder.append(methodList);
        builder.append(", superClassName=");
        builder.append(superClassName);
        builder.append(", interClassName=");
        builder.append(interClassName);
        builder.append(", className=");
        builder.append(className);
        builder.append("]");
        return builder.toString();
    }

}
