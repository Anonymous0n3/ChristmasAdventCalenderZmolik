package com.example.christmasadventcalenderzmolik;

public enum Gifts {
    GIFT_1(new Present("Gift 1", "Gift 1 description")),
    GIFT_2(new Present("Gift 2", "Gift 2 description")),
    GIFT_3(new Present("Gift 3", "Gift 3 description")),
    GIFT_4(new Present("Gift 4", "Gift 4 description")),
    GIFT_5(new Present("Gift 5", "Gift 5 description")),
    GIFT_6(new Present("Gift 6", "Gift 6 description")),
    GIFT_7(new Present("Gift 7", "Gift 7 description")),
    GIFT_8(new Present("Gift 8", "Gift 8 description")),
    ;

    private final Present present;

    Gifts(Present present) {
        this.present = present;
    }

    public Present getPresent() {
        return present;
    }
}
