package com.redwit.service.beanDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-03-01 11:16
 **/
@Service
public class SetterService {

	@Autowired
	private LubanService lubanService;

	public SetterService() {
		System.out.println("SetterService create");
	}

	public void test(){
		System.out.println(lubanService);
	}
	// 通过autowired指定使用set方法完成注入
	@Autowired
	public void setLuBanService(LubanService luBanService) {
		System.out.println("注入luBanService by setter");
		this.lubanService = luBanService;
	}

}
