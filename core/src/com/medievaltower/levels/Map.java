package com.medievaltower.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Map {

    private int[][][] map; // map[x][y][z], where z = 0 for the base layer, z = 1 for the first decoration layer, and z = 2 for the second decoration layer

    public Map() {
        this.map = null;
    }

    public int[][][] getMap() {
        return this.map;
    }

    public void setMap(String mapNumber) {

        // get name of different map type
        String[] paths = {"block", "decoration", "item"};

        // reset map
        this.map = null;

        // load map each per each path
        int z = 0;
        for (String path : paths) {
            String path_block = "Maps/" + mapNumber + "/" + path + ".csv";

            // read map file
            FileHandle handle = Gdx.files.internal(path_block);
            String text = handle.readString();

            // split map file into lines and elements
            String[] lines = text.split("\\n");
            String[] elements = lines[0].split(",");

            // init map
            if (this.map == null) {
                this.map = new int[lines.length][elements.length][3];
            }

            int x = 0;
            int y = 0;

            // set map
            for (String line : lines) {
                for (String element : elements) {
                    map[y][x][z] = Integer.parseInt(element);
                    x++;
                }
                x = 0;
                y++;
            }
            z++;
        }
    }

    public int getElement(int x, int y, int z) {
        return map[x][y][z];
    }

    public void setElement(int x, int y, int z, int elementId) {
        this.map[x][y][z] = elementId;
    }

    public void test() {

        // Load the map data
        setMap("1");

        System.out.println(getElement(0, 0, 0));
        System.out.println(getElement(0, 0, 1));
        System.out.println(getElement(0, 0, 2));

        setElement(0,0,0,1);
        System.out.println(getMap()[0][0][0]);
    }
}