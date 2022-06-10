package com.redwit.listen.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-06-02 14:40
 **/
@Slf4j
@Service
public class MyService {

	@Autowired
	private ApplicationEventPublisher publisher;

	public void doBusiness(){
		log.debug("主体业务");
		publisher.publishEvent(new MyEvent("MyService.doBusiness()"));
	}
}
