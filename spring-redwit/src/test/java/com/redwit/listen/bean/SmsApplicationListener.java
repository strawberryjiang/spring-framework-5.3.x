package com.redwit.listen.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-06-02 14:41
 **/
@Slf4j
@Component
public class SmsApplicationListener implements ApplicationListener<MyEvent> {

	@Override
	public void onApplicationEvent(MyEvent event) {
		log.debug("发短信");
	}
}
