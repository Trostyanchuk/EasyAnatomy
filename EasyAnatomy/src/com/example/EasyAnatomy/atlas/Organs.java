package com.example.EasyAnatomy.atlas;


import com.example.EasyAnatomy.R;

public enum Organs {

    /**
     * figure types:
     * 1 - rectangle
     * 2 - circle
     * 3 - square
     */

    BRAIN_PART_1(1, new int[]{140, 17, 13, 35}, R.drawable.brain, R.string.brain_text, R.string.brain_title),
    BRAIN_PART_2(1, new int[]{165, 30, 6, 20}, R.drawable.brain, R.string.brain_text, R.string.brain_title),
    EAR(2, new int[]{178, 43, 6}, R.drawable.ear, R.string.ear_text, R.string.ear_title),
    EYE_1(2, new int[]{158, 36, 4}, R.drawable.eye, R.string.eye_text, R.string.eye_title),
    EYE_2(2, new int[]{142, 37, 3}, R.drawable.eye, R.string.eye_text, R.string.eye_title),
    THYROID(3, new int[]{0,0,0}, R.drawable.thyroid, R.string.thyroid_text, R.string.thyroid_title), //щитовидка
    HEART(2, new int[]{162, 118, 20}, R.drawable.heart, R.string.heart_text, R.string.heart_title),
    LUNGS(1, new int[]{138, 98, 45, 50}, R.drawable.lungs, R.string.lungs_text, R.string.lungs_title),
    LIVER_1(2, new int[]{149, 150, 9}, R.drawable.liver, R.string.liver_text, R.string.liver_title),
    LIVER_2(1, new int[]{153, 144, 6, 10}, R.drawable.liver, R.string.liver_text, R.string.liver_title),
    GALLBLADDER(2, new int[]{144, 165, 3}, R.drawable.gallbladder, R.string.gallbladder_text, R.string.gallbladder_title),
    STOMACH_1(0, new int[]{}, R.drawable.stomach, R.string.stomach_text, R.string.stomach_title),  //TODO
    STOMACH_2(0, new int[]{}, R.drawable.stomach, R.string.stomach_text, R.string.stomach_title),  //TODO
    COLON_1(1, new int[]{138, 169, 23, 30}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    COLON_2(1, new int[]{178, 184, 31, 10}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    COLON_3(3, new int[]{174, 212, 8}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    SMALL_INTESTINE(1, new int[]{136, 182, 28, 38}, R.drawable.small_intensine, R.string.small_intestine_text, R.string.small_intestine_title),
    ;

    private int type;
    private int[] coordinates;
    private int imageId;
    private int textId;
    private int titleId;

    Organs(int type, int[] coordinates, int imageId, int textId, int titleId) {
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
