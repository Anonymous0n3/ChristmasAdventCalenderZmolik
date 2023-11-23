package com.example.christmasadventcalenderzmolik;

public enum Gifts {
    GIFT_1("socks"),
    GIFT_2("t-shirt"),
    GIFT_3("sweater"),
    GIFT_4("jacket"),
    GIFT_5("scarf"),
    GIFT_6("gloves"),
    GIFT_7("hat"),
    GIFT_8("shoes"),
    GIFT_9("boots"),
    GIFT_10("sneakers"),
    GIFT_11("jeans"),
    GIFT_12("shorts"),
    GIFT_13("skirt"),
    GIFT_14("dress"),
    GIFT_15("suit"),
    GIFT_16("tie"),
    GIFT_17("shirt");

    private final String giftName;
    Gifts(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftName() {
        return giftName;
    }
}
