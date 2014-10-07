package com.example.EasyAnatomy.util;


import com.example.EasyAnatomy.atlas.Bones;
import com.example.EasyAnatomy.atlas.CirculatorySystem;
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
        boolean matches = false;
        for(Organs organ : Organs.values()) {
            if(organ.getType() == 1 || organ.getType() == 3) {
                matches = checkIfRectangleOrSquare(eventX, eventY, organ.getCoordinates());
            } else if (organ.getType() == 2 ) {
                matches =  checkIfCircle(eventX, eventY, organ.getCoordinates());
            }
            if(matches) {
                resources[0] = organ.getImageId();
                resources[1] = organ.getTextId();
                resources[2] = organ.getTitleId();
                return resources;
            }
        }
        return resources;
    }

    public static int[] getCustomResourcesFromCirculatorySystemMap(float eventX, float eventY) {
        int[] resources = new int[]{0, 0, 0};
        boolean matches = false;
        for(CirculatorySystem part : CirculatorySystem.values()) {
            if(part.getType() == 1 || part.getType() == 3) {
                matches = checkIfRectangleOrSquare(eventX, eventY, part.getCoordinates());
            } else if (part.getType() == 2 ) {
                matches =  checkIfCircle(eventX, eventY, part.getCoordinates());
            }
            if(matches) {
                resources[0] = part.getImageId();
                resources[1] = part.getTextId();
                resources[2] = part.getTitleId();
                return resources;
            }
        }
        return resources;
    }

    public static int[] getCustomResourcesFromBonesMap(float eventX, float eventY) {
        int[] resources = new int[]{0, 0, 0};
        boolean matches = false;
        for(Bones bone : Bones.values()) {
            if(bone.getType() == 1 || bone.getType() == 3) {
                matches = checkIfRectangleOrSquare(eventX, eventY, bone.getCoordinates());
            } else if (bone.getType() == 2 ) {
                matches =  checkIfCircle(eventX, eventY, bone.getCoordinates());
            }
            if(matches) {
                resources[0] = bone.getImageId();
                resources[1] = bone.getTextId();
                resources[2] = bone.getTitleId();
                return resources;
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
                    && y > coordinates[1] && y < coordinates[1] + coordinates[2]) {
                return true;      //square is detected
            }
        } else if(coordinates.length == 4) {
            //we are looking for rectangle
            //coordinates[0] - x
            //coordinates[1] - y
            //coordinates[2] - h (увеличение по оси у)
            //coordiates[3] - l (увеличение по оси х)
            if(x > coordinates[0] && x < coordinates[0] + coordinates[3] &&
                    y > coordinates[1] && y < coordinates[1] + coordinates[2]) {
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
            double actual = Math.sqrt(Math.pow((coordinates[0] - x), 2) + Math.pow((coordinates[1] - y), 2));
            if(actual < coordinates[2]) {
                return true;    //circle is detected
            }
        }
        return false;
    }
}
