package com.mimecast;

import com.mimecast.model.City;
import com.mimecast.model.Direction;
import com.mimecast.model.Monster;
import com.mimecast.model.World;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {
    public static String getTestDir() {
        String testFolder = System.getProperty("user.dir");
        return testFolder;
    }

    public static World generateTestWorld(){
        World expectedWorld = new World();

        List<City> cities = new ArrayList<>();
        City city1 = new City("City1",false);
        City city2 = new City("City2",false);
        City city3 = new City("City3",false);
        City city4 = new City("City4",false);

        Map<Direction, City> city1Directions = new LinkedHashMap<>();
        city1Directions.put(Direction.NORTH, city2);
        city1Directions.put(Direction.EAST, city3);
        city1.setDirection(city1Directions);

        int id=1;
        Monster monster1 = new Monster(id,"Monster "+id,city1,true,1);
        city1.addMonster(monster1);



        Map<Direction, City> city2Directions = new LinkedHashMap<>();
        city2Directions.put(Direction.SOUTH, city1);
        city2.setDirection(city2Directions);
        id=2;
        Monster monster2 = new Monster(id,"Monster "+id,city2,true,1);
        city2.addMonster(monster2);

        Map<Direction, City> city3Directions = new LinkedHashMap<>();
        city3Directions.put(Direction.WEST, city1);
        city3.setDirection(city2Directions);


        Map<Direction, City> city4Directions = new LinkedHashMap<>();
        city4.setDirection(city4Directions);

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        expectedWorld.setCities(cities);
        return expectedWorld;
    }
}
