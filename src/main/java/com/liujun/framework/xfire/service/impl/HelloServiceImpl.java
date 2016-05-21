package com.liujun.framework.xfire.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.liujun.framework.xfire.bean.Boday;
import com.liujun.framework.xfire.bean.ClientBoday;
import com.liujun.framework.xfire.bean.User;
import com.liujun.framework.xfire.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String helloword(String name) throws RemoteException {
		return "您好：" + name + "！欢迎您使用webservice";
	}

	@Override
	public User getUserById(int id) throws RemoteException {

		if (1 == id) {
			User us = new User();

			us.setId(1);
			us.setName("刘军");
			us.setPwd("113322");

			Boday bo = new Boday();
			bo.setMsg(11);
			bo.setRsc("rsc");

			ArrayList<Boday> list = new ArrayList<Boday>();
			list.add(bo);
			list.add(bo);
			list.add(bo);

			Boday[] bos = new Boday[list.size() - 1];
			us.setRsp(list.toArray(bos));

			return us;
		}

		return null;
	}

}
