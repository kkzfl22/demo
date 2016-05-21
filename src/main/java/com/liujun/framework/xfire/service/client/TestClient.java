package com.liujun.framework.xfire.service.client;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.Arrays;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.liujun.framework.xfire.bean.User;
import com.liujun.framework.xfire.service.HelloService;

public class TestClient {

	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			Service serviceModel = new ObjectServiceFactory()
					.create(HelloService.class);
			XFireProxyFactory xfire = new XFireProxyFactory();

			HelloService service = (HelloService) xfire.create(serviceModel,
					"http://localhost:8080/demo/webservice/HelloService");

			// xfire.create(client);

			// 方法一 测试
			String str = service.helloword("测试");
			System.out.println(str);
			// 方法二 测试
			User user = service.getUserById(1);
			System.out.println("id:" + user.getId());
			System.out.println("username:" + user.getName());
			System.out.println("sex:" + user.getPwd());
			System.out.println("数据:"+Arrays.toString(user.getRsp()));
			
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
