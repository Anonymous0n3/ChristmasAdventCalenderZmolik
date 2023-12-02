package com.example.christmasadventcalenderzmolik;


public class Present {
    private final String presentName;
    private final String presentDescription;

    public Present(String presentName, String presentDescription) {
        this.presentName = presentName;
        this.presentDescription = presentDescription;
    }

    public String getPresentName() {
        return presentName;
    }

    public String getPresentDescription() {
        return presentDescription;
    }
}
