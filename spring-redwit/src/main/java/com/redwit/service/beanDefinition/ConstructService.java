package com.redwit.service.beanDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-03-01 11:20
 **/
@Service
public class ConstructService {

	private LubanService lubanService;

	public ConstructService() {
		System.out.println("service create by no args constructor");
	}

	// 通过Autowired指定使用这个构造函数，否则默认会使用无参
	@Autowired
	public ConstructService(LubanService lubanService) {
		System.out.println("注入luBanService by constructor with arg");
		this.lubanService = lubanService;
		System.out.println("service create by constructor with arg");
	}

	public void test(){
		System.out.println(lubanService);
	}

}
