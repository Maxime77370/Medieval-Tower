package com.medievaltower.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.medievaltower.entities.Constant;
import com.medievaltower.entities.bloc.Bloc;

public class Map extends Level{

    private String[] paths = {"block", "decoration", "item"}; // get name of different mapId type
    private int[][][] mapId; // mapId[x][y][z], where z = 0 for the base layer, z = 1 for the first decoration layer, and z = 2 for the second decoration layer
    private Bloc[][][] map; // mapId[x][y][z], where z = 0 for the base layer, z = 1 for the first decoration layer, and z = 2 for the second decoration layer
    private Constant constantElements = new Constant();
    public Map() {
        this.mapId = null;
    }

    public int[][][] getIdMap() {
        return this.mapId;
    }

    public void setIdMap(String mapIdNumber) {

        // reset mapId
        this.mapId = null;

        // load mapId each per each path
        int z = 0;
        for (String path : paths) {
            String path_block = "mapIds/" + mapIdNumber + "/" + path + ".csv";

            // read mapId file
            FileHandle handle = Gdx.files.internal(path_block);
            String text = handle.readString();

            // split mapId file into lines and elements
            String[] lines = text.split("\\n");
            String[] elements = lines[0].split(",");

            // init mapId
            if (this.mapId == null) {
                this.mapId = new int[lines.length][elements.length][3];
            }

            int x = 0;
            int y = 0;

            // set mapId
            for (String line : lines) {
                for (String element : elements) {
                    mapId[y][x][z] = Integer.parseInt(element);
                    x++;
                }
                x = 0;
                y++;
            }
            z++;
        }
    }

    public int getIdElement(int x, int y, int z) {
        return mapId[y][x][z];
    }

    public void setIdElement(int x, int y, int z, int elementId) {
        this.mapId[y][x][z] = elementId;
    }

    public void setMap(){
        this.map = null;
        this.map = new Bloc[mapId.length][mapId[0].length][3];
        for(int y = 0; y < mapId.length; y++){
            for(int x = 0; x < mapId[0].length; x++){
                for(int z = 0; z < mapId[0][0].length; z++){
                    this.map[y][x][0] = constantElements.newBloc(mapId[y][x][z], x, y);
                }
            }
        }
    }

    public Bloc[][][] getMap(){
        return this.map;
    }

    public void setElements(int x, int y, int z, int id){
        this.map[y][x][z] = constantElements.newBloc(id, x, y);
    }

    public void setElements(int x, int y, int z, Bloc element){
        this.map[y][x][z] = element;
    }

    public Bloc getElements(int x, int y, int z){
        return this.map[y][x][z];
    }

    public void update(){
        for(int y = 0; y < mapId.length; y++){
            for(int x = 0; x < mapId[0].length; x++){
                for(int z = 0; z < mapId[0][0].length; z++){
                    this.map[y][x][0].update();
                }
            }
        }
    }
    public void test() {

        // Load the mapId data
        setIdMap("1");

        System.out.println(getIdElement(0, 0, 0));
        System.out.println(getIdElement(0, 0, 1));
        System.out.println(getIdElement(0, 0, 2));

        setIdElement(0,0,0,1);
        System.out.println(getIdMap()[0][0][0]);
    }
}