package com.mimecast.model;

import java.util.List;

public class World {
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof World)) return false;

        World world = (World) o;

        if (!cities.equals(world.cities)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cities.hashCode();
    }
}
