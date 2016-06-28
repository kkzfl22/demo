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
    public static void main(String[] args) {
        // 发送 GET 请求
        // String s =
        // HttpRequest.sendGet("http://localhost:6144/Home/RequestString",
        // "key=123&v=456");
        // System.out.println(s);

        // 发送 POST 请求
        // String list =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.listCookBookPraise",
        // "params={\"cookbookId\":\"262361218960855040\"}");
        // System.out.println(list);

        // 发送 POST 请求
        String sr = HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.dtl",
                "params={\"cookbookId\":\"262361218960855040\"}");
        System.out.println(jsonFormatter(sr));

        // 发送 POST 请求
        String addfalse = HttpRequest.sendPost(
                "http://msg.51jxh.com:2223/api?method=OaCookBookService.addCookBookPraise",
                "params={\"cookbookId\":\"262361218960855040\",\"praiseFlag\":\"false\",\"praiseId\":\"265296765165506560\"}");
        System.out.println(jsonFormatter(addfalse));

        // // 发送 POST 请求
        // String add =
        // HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.addCookBookPraise",
        // "params={\"cookbookId\":\"262361218960855040\",\"praiseFlag\":\"true\"}");
        // System.out.println(add);

        String sr2 = HttpRequest.sendPost("http://msg.51jxh.com:2223/api?method=OaCookBookService.dtl",
                "params={\"cookbookId\":\"262361218960855040\"}");
        System.out.println(jsonFormatter(sr2));
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
