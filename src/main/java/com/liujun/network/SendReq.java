package com.liujun.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
* 源文件名：SendReq.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月28日
* 修改作者：liujun
* 修改日期：2016年6月28日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class SendReq {

    /**
     * 进行登陆
    * 方法描述
    * @创建日期 2016年7月25日
    */
    public void login() {
        // 发送 POST 请求
        String list = HttpRequest.sendPost("http://msg.51jxh.com/api?method=OrgAuthService.login",
                "params={'userName':'14511111123','pwd':'123456','os':'web'}");
        System.out.println(jsonFormatter(list));
    }

    /**
     * 流程的查询
    * 方法描述
    * @创建日期 2016年7月25日
    */
    public void oAHoliday() {
        // // 发送 POST 请求
        String list = HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OAHolidayService.approveList",
                "params={'startTime':'2016-07-27 11:02:24','endTime':'2016-07-27 11:06:51'}");
        System.out.println(jsonFormatter(list));
        // // 进行详情查询
        // String list =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OAHolidayService.holidayDtl",
        // "params={'oid':'275709709623758848'}");
        // System.out.println(jsonFormatter(list));
        // 进行请假列表查询
        // String list =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OAHolidayService.holidayList",
        // "params={'startTime':'2016-07-27 11:02:24','endTime':'2016-07-27
        // 11:06:51'}");
        // System.out.println(jsonFormatter(list));
    }

    /**
     * 报修流程的查询
     * 方法描述
     * @创建日期 2016年7月25日
     */
    public void repair() {
        // 发送 POST 请求
        // String list =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=SchoolRepairService.repairList",
        // "params={'startTime':'2016-07-25 09:16:46','endTime':'2016-07-25
        // 09:18:40'}");
        // System.out.println(jsonFormatter(list));

        // 发送 POST 请求
        String list = HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=SchoolRepairService.approveDtlFlow",
                "params={'repairId':'276856324967370752'}");
        System.out.println(jsonFormatter(list));
    }

    public static void main(String[] args) {

        SendReq send = new SendReq();

        send.login();
        // send.approve();
        // send.oAHoliday();
        // send.repair();

        // 发送 GET 请求
        // String s =
        // HttpRequest.sendGet("http://localhost:6144/Home/RequestString",
        // "key=123&v=456");
        // System.out.println(s);

        // 发送 POST 请求
        // String sr1 =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OAHolidayService.add",
        // "params={\"startTime\":\"2016-07-11\",\"endTime\":\"2016-07-11\",\"ema\":\"2\",\"sma\":\"1\",\"type\":\"1\",\"summary\":\"12345\",\"applyer\":\"2\",\"applyerTime\":\"2016-07-11\"}");
        // System.out.println(jsonFormatter(sr1));
        // String sr =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OAHolidayService.holidayList",
        // "params={}");
        // System.out.println(jsonFormatter(sr));

        // 加载入口
        // String sr =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=SchoolRepairService.approveCheckEntry",
        // "params={}");
        // System.out.println(jsonFormatter(sr));

        // // 发送 POST 请求
        // String addfalse = HttpRequest.sendPost(
        // "http://msg.51jxh.com:2223/api?method=OaCookBookService.addCookBookPraise",
        // "params={\"cookbookId\":\"262361218960855040\",\"praiseFlag\":\"false\",\"praiseId\":\"265296765165506560\"}");
        // System.out.println(jsonFormatter(addfalse));
        //
        // // // 发送 POST 请求
        // // String add =
        // //
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.addCookBookPraise",
        // //
        // "params={\"cookbookId\":\"262361218960855040\",\"praiseFlag\":\"true\"}");
        // // System.out.println(add);
        //
        // String sr2 =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.dtl",
        // "params={\"cookbookId\":\"262361218960855040\"}");
        // System.out.println(jsonFormatter(sr2));
    }

    /**
     * 进行字符串格式化
    * 方法描述
    * @param uglyJSONString
    * @return
    * @创建日期 2016年6月28日
    */
    public static String jsonFormatter(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
