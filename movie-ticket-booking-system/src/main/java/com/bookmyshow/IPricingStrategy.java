package com.bookmyshow;

import java.util.Map;

public interface IPricingStrategy {
    Map<SeatType, Integer> calcPrices(Show show);
}
