package com.example.ismailamassi.bmi.models;

public class TipsItem {
    private int tips_img ;
    private String tips_title;
    private String tips_descriptions;

    public TipsItem(int tips_img, String tips_title, String tips_descriptions) {
        this.tips_img = tips_img;
        this.tips_title = tips_title;
        this.tips_descriptions = tips_descriptions;
    }

    public int getTips_img() {
        return tips_img;
    }

    public String getTips_title() {
        return tips_title;
    }

    public String getTips_descriptions() {
        return tips_descriptions;
    }
}
