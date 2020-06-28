package com.raju.strategy;

import java.util.Date;

import com.raju.enums.SLOT_TYPE;

public interface PricingStrategy {
	public float getPrice(SLOT_TYPE type,Date startTime,Date endTime);
}
