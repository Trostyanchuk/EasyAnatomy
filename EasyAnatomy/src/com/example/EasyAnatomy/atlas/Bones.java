package com.example.EasyAnatomy.atlas;


import com.example.EasyAnatomy.R;

public enum Bones {

    /**
     * figure types:
     * 1 - rectangle
     * 2 - circle
     * 3 - square
     */

    SKULL(2, new int[]{138, 80, 28}, R.drawable.brain, R.string.skull_text, R.string.skull_title),
    CHIN(2, new int[]{138, 80, 28}, R.drawable.brain, R.string.chin_text, R.string.chin_title),   //подбородок
    SPINE(2, new int[]{153, 123, 14}, R.drawable.brain, R.string.spine_text, R.string.spine_title),    //позвоночник
    THORAX(2, new int[]{167, 178, 41}, R.drawable.brain, R.string.thorax_text, R.string.thorax_title),   //грудная клетка
    JOINT_1(2, new int[]{254, 162, 16}, R.drawable.brain, R.string.joint_text, R.string.joint_title),    //сустав
    //BRUSH()   //кисть
    PELVIS_1(1, new int[]{148, 245, 36, 28}, R.drawable.brain, R.string.pelvis_text, R.string.pelvis_title),   //таз
    PELVIS_2(1, new int[]{177, 231, 49, 28}, R.drawable.brain, R.string.pelvis_text, R.string.pelvis_title);

    private int type;
    private int[] coordinates;
    private int imageId;
    private int textId;
    private int titleId;

    Bones(int type, int[] coordinates, int imageId, int textId, int titleId) {
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
