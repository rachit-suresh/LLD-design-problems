package com.bookmyshow;

import java.util.HashMap;
import java.util.Map;

public class BasicPricingStrategy implements IPricingStrategy {
    @Override
    public Map<SeatType, Integer> calcPrices(Show show) {
        // Boilerplate implementation
        Map<SeatType, Integer> prices = new HashMap<>();
        prices.put(SeatType.GOLD, 200);
        prices.put(SeatType.SILVER, 150);
        prices.put(SeatType.RECLINER, 300);
        return prices;
    }
}
