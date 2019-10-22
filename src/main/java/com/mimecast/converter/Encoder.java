package com.mimecast.converter;

import com.mimecast.converter.encoder.exception.WriteException;
import com.mimecast.model.City;

import java.io.IOException;
import java.util.List;

public interface Encoder {
    public void encode(List<City> cities, List<City> citiesConflict) throws WriteException, IOException;
}
