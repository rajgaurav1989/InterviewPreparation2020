package com.raju.factory;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.raju.enums.SLOT_TYPE;
import com.raju.strategy.PricingStrategy;
import com.raju.strategy.impl.FlatPricing;
import com.raju.strategy.impl.ProRatedPricing;

public class PriceStrategyFactory {
	public static PricingStrategy getPricingStrategy(String param,Map<SLOT_TYPE,Integer> slotPricingMap) {
		if (StringUtils.equalsIgnoreCase(param,"FLAT")) {
			return new FlatPricing(slotPricingMap);
		}
		else {
			return new ProRatedPricing(slotPricingMap);
		}
	}
}
