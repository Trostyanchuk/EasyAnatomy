package com.example.EasyAnatomy.util;


import com.example.EasyAnatomy.atlas.Organs;

public class AtlasUtil {

    /**
     * figure types:
     * 1 - rectangle
     * 2 - circle
     * 3 - square
     */
    /**
     *
     * @param eventX
     * @param eventY
     * @return
     */
    public static int[] getCustomResourcesFromOrgansMap(float eventX, float eventY) {
        int[] resources = new int[]{0, 0, 0};
        for(Organs organ : Organs.values()) {
            if((organ.getType() == 1 || organ.getType() == 3)
                    && checkIfRectangleOrSquare(eventX, eventY, organ.getCoordinates())) {
                resources[0] = organ.getImageId();
                resources[1] = organ.getTextId();
                resources[2] = organ.getTitleId();
            } else if(organ.getType() == 2
                    && checkIfCircle(eventX, eventY, organ.getCoordinates())) {
                resources[0] = organ.getImageId();
                resources[1] = organ.getTextId();
                resources[2] = organ.getTitleId();
            }
        }
        return resources;
    }

    /**
     *
     * @param x
     * @param y
     * @param coordinates  (0, 1 - coordinates of top left corner, 2 - hight, 3 - length)
     * @return
     */
    private static boolean checkIfRectangleOrSquare(float x, float y, int[] coordinates) {

        if(coordinates.length == 3) {
            //we are looking square
            if(x > coordinates[0] && x < coordinates[0] + coordinates[2]
                    && y > coordinates[1] && y < coordinates[0] + coordinates[2]) {
                return true;      //square is detected
            }
        } else if(coordinates.length == 4) {
            //we are looking for rectangle
            if(x > coordinates[0] && x < coordinates[0] + coordinates[3] &&
                    y > coordinates[1] && y < coordinates[0] + coordinates[2]) {
                return true;    //rectangle is detected
            }
        }
        return false;
    }

    /**
     *
     * @param x
     * @param y
     * @param coordinates
     * @return
     */
    private static boolean checkIfCircle(float x, float y, int[] coordinates) {
        if(coordinates.length == 3) {
            if(Math.sqrt(Math.pow((coordinates[0] - x), 2) + Math.pow((coordinates[1] - y), 2)) < coordinates[2]) {
                return true;    //circle is detected
            }
        }
        return false;
    }
}
