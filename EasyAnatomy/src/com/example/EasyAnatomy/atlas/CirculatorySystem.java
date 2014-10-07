package com.example.EasyAnatomy.atlas;


import com.example.EasyAnatomy.R;

public enum CirculatorySystem {
    /**
     * figure types:
     * 1 - rectangle
     * 2 - circle
     * 3 - square
     */

    BRAIN(2, new int[]{156, 39, 18}, R.drawable.brain, R.string.brain_blood_text, R.string.brain_blood_title),
    HEART(2, new int[]{153, 137, 17}, R.drawable.heart, R.string.heart_text, R.string.heart_title);

    private int type;
    private int[] coordinates;
    private int imageId;
    private int textId;
    private int titleId;

    CirculatorySystem(int type, int[] coordinates, int imageId, int textId, int titleId) {
        this.type = type;
        this.coordinates = coordinates;
        this.imageId = imageId;
        this.textId = textId;
        this.titleId = titleId;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTextId() {
        return textId;
    }

    public int getType() {
        return type;
    }

    public int getTitleId() {
        return titleId;
    }
}
