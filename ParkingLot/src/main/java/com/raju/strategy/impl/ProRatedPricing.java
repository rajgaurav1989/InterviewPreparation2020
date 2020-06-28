package com.raju.strategy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.raju.enums.SLOT_TYPE;
import com.raju.strategy.PricingStrategy;
import com.raju.utils.ProjectConstants;

public class ProRatedPricing implements PricingStrategy {
	Map<SLOT_TYPE,Integer> slotPricingMap = new HashMap<>();
	
	private final long DUMMY_TIME = 1800000l ;
	
	
	public ProRatedPricing(Map<SLOT_TYPE,Integer> slotPricingMap) {
		this.slotPricingMap = slotPricingMap ;
	}
	
	@Override
	public float getPrice(SLOT_TYPE type, Date startTime, Date endTime) {
		startTime = new Date(startTime.getTime() - DUMMY_TIME);
		endTime = new Date(endTime.getTime() + DUMMY_TIME ) ;
		float hour = (endTime.getTime() - startTime.getTime())/ProjectConstants.HOUR_MILLIS;
		return slotPricingMap.get(type) * hour ;
	}

}
