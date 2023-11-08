package com.medievaltower.game;

import com.badlogic.gdx.Gdx;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapManager {

    private int[][][] map; // map[x][y][z], z for drawing decoration in another block

    public MapManager() {
        int xSize = 10; // Specify the size you need
        int ySize = 10; // Specify the size you need
        int zSize = 3; // Specify the size you need
        map = new int[xSize][ySize][zSize];
    }


    public int[][][] getMap() {
        return this.map;
    }

    public void setMap(String mapNumber) throws IOException{
        String path = "Map/" + mapNumber + ".csv";
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Gdx.files.internal(path));
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String name = csvRecord.get(0);
                String email = csvRecord.get(1);
                String phone = csvRecord.get(2);
                String country = csvRecord.get(3);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + name);
                System.out.println("Email : " + email);
                System.out.println("Phone : " + phone);
                System.out.println("Country : " + country);
                System.out.println("---------------\n\n");
            }
        }
    }

    public int getElement(int x, int y, int z) {
        return map[x][y][z];
    }

    public void setElement(int x, int y, int z, int elementId) {
        this.map[x][y][z] = elementId;
    }

    public static void main(String[] args) throws IOException {
        AssetManager assetManager = new AssetManager();
        MapManager mapManager = new MapManager();
        mapManager.setMap("1");
    }
}
