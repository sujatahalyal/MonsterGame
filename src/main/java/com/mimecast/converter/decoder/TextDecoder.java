package com.mimecast.converter.decoder;

import com.mimecast.Direction.DirectionFactory;
import com.mimecast.Direction.Exception.DirectionNotFoundException;
import com.mimecast.converter.Decoder;
import com.mimecast.converter.decoder.exception.ReadException;
import com.mimecast.model.City;
import com.mimecast.model.Direction;
import com.mimecast.model.World;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TextDecoder implements Decoder {
    private TextDecoder reader;
    private String filePath;

    public TextDecoder(String filePath) {
        this.filePath = filePath;
    }
    public World decode() throws ReadException{
        World world = new World();
        List<City> cities = new ArrayList();
        String thisLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((thisLine = bufferedReader.readLine()) != null) {
                String strCity="";
                if(thisLine.indexOf(" ") != -1) {
                    strCity = thisLine.substring(0, thisLine.indexOf(" "));
                    City city = getCityByName(strCity, cities);
                    String strSubStr = thisLine.substring(thisLine.indexOf(" ") + 1);
                    String[] str1 = strSubStr.split("\\s");
                    Map<Direction, City> neighbors = new LinkedHashMap();
                    for (String str2 : str1) {
                        String position = str2.substring(0, str2.indexOf('='));
                        String city1 = str2.substring(str2.indexOf('=') + 1);
                        Direction neighborLocation = DirectionFactory.create(position);
                        City neighborCity = getCityByName(city1, cities);
                        neighbors.put(neighborLocation, neighborCity);
                        city.setDirection(neighbors);

                    }
                } else {
                    strCity = thisLine;
                    City city = getCityByName(strCity, cities);

                }
            }
        }catch (IOException | DirectionNotFoundException e) {
            throw new ReadException(e);
        }
        world.setCities(cities);
        return world;
    }

    private City getCityByName(String cityName, List<City> cities) {
        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                return city;
            }
        }
        City city = new City(cityName,false);
        cities.add(city);
        return city;
    }
}
