package com.mimecast.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class City {
    private String name;
    private Map<Direction,City> direction;
    private List<Monster> monsterList;
    private boolean isDestroyed=false;

    public City(String name, boolean isDestroyed) {
        this.name = name;
        this.isDestroyed = isDestroyed;
        monsterList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Direction, City> getDirection() {
        return direction;
    }

    public void setDirection(Map<Direction, City> direction) {
        this.direction = direction;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void addMonster(Monster monster){
        this.monsterList.add(monster);
    }
    public void removeMonster(Monster monster){
        this.monsterList.remove(monster);
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

}
