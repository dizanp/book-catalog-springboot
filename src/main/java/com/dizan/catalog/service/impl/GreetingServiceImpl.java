package com.dizan.catalog.service.impl;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dizan.catalog.config.ApplicationProperties;
import com.dizan.catalog.config.CloudProperties;
import com.dizan.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	private ApplicationProperties appProperties;
	
	private CloudProperties cloudProperties;
	
	
	@Override
	public String sayGreeting() {
		System.out.println(cloudProperties.getApiKey());
		TimeZone timezone = TimeZone.getTimeZone(appProperties.getTimezone());
		return appProperties.getWelcomeText()+", our timezone :"+timezone.getDisplayName() +
				", our curremcy :"+appProperties.getCurrency();
	}

	public GreetingServiceImpl(ApplicationProperties appProperties, CloudProperties cloudProperties) {
		super();
		this.appProperties = appProperties;
		this.cloudProperties = cloudProperties;
	}

}
