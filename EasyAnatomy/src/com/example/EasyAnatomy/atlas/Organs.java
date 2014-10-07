package com.example.EasyAnatomy.atlas;


import com.example.EasyAnatomy.R;

public enum Organs {

    /**
     * figure types:
     * 1 - rectangle
     * 2 - circle
     * 3 - square
     */

    BRAIN_PART_1(1, new int[]{141, 16, 17, 43}, R.drawable.brain, R.string.brain_text, R.string.brain_title),
    BRAIN_PART_2(1, new int[]{175, 18, 20, 15}, R.drawable.brain, R.string.brain_text, R.string.brain_title),
    EAR(2, new int[]{179, 45, 6}, R.drawable.ear, R.string.ear_text, R.string.ear_title), //TODO
    EYE_1(2, new int[]{158, 39, 6}, R.drawable.eye, R.string.eye_text, R.string.eye_title),
    EYE_2(2, new int[]{141, 38, 6}, R.drawable.eye, R.string.eye_text, R.string.eye_title),
    THYROID(3, new int[]{153, 75, 17}, R.drawable.thyroid, R.string.thyroid_text, R.string.thyroid_title), //щитовидка
    LUNGS(1, new int[]{136, 101, 50, 58}, R.drawable.lungs, R.string.lungs_text, R.string.lungs_title),
    LIVER_1(2, new int[]{152, 161, 8}, R.drawable.liver, R.string.liver_text, R.string.liver_title),
    LIVER_2(1, new int[]{156, 152, 2, 10}, R.drawable.liver, R.string.liver_text, R.string.liver_title),
    GALLBLADDER(2, new int[]{142, 173, 4}, R.drawable.gallbladder, R.string.gallbladder_text, R.string.gallbladder_title),
    STOMACH_1(1, new int[]{150, 169, 8, 17}, R.drawable.stomach, R.string.stomach_text, R.string.stomach_title),
    STOMACH_2(2, new int[]{169, 163, 7}, R.drawable.stomach, R.string.stomach_text, R.string.stomach_title),
    COLON_1(1, new int[]{136, 179, 11, 37}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    COLON_2(1, new int[]{179, 197, 36, 11}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    //COLON_3(3, new int[]{174, 212, 8}, R.drawable.colon, R.string.colon_text, R.string.colon_title),
    SMALL_INTESTINE(1, new int[]{135, 192, 30, 43}, R.drawable.small_intensine, R.string.small_intestine_text, R.string.small_intestine_title),
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
