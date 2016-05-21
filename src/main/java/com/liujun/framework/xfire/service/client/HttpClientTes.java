package com.liujun.framework.xfire.service.client;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.codehaus.xfire.client.Client;

public class HttpClientTes extends Client {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String value = "http://localhost:8080/demo/webservice/HelloService?wsdl&&test";

		String encode = URLEncoder.encode(value, "GBK");
		System.out.println(encode);
		String value22 = URLDecoder.decode(encode, "GBK");
		System.out.println(value22);
	}

}
