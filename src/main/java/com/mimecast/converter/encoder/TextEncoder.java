package com.mimecast.converter.encoder;

import com.mimecast.converter.Encoder;
import com.mimecast.converter.encoder.exception.WriteException;
import com.mimecast.model.City;
import com.mimecast.model.Direction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextEncoder implements Encoder {
    private String filePath;

    public TextEncoder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void encode(List<City> cities, List<City> citiesConflict) throws WriteException, IOException {
        List<String> strConflict= new ArrayList<>();
        for(City city1:citiesConflict){
            strConflict.add(city1.getName());
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            String strLine;
            for (City city : cities) {
                strLine = "";
                strLine += city.getName();
                if(city.getDirection() != null) {
                    for (Map.Entry<Direction, City> neighbor : city.getDirection().entrySet()) {
                        if(!strConflict.equals(neighbor.getValue().getName())) {
                            strLine += " " + neighbor.getKey().getName() + "=" + neighbor.getValue().getName();
                        }
                    }
                }
                // bufferedWriter.write(columns.toArray(new String[columns.size()]));
                bufferedWriter.write(strLine);
                bufferedWriter.newLine();
            }
        }
    }
}