package com.bookmyshow;

public class Screen {
    private String theatre_id;
    private ScreenType screenType;
    private int basePrice;

    public Screen(String theatre_id, ScreenType screenType, int basePrice) {
        this.theatre_id = theatre_id;
        this.screenType = screenType;
        this.basePrice = basePrice;
    }

    public String getTheatre_id() {
        return theatre_id;
    }

    public void setTheatre_id(String theatre_id) {
        this.theatre_id = theatre_id;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
