package com.mimecast.model;

import java.util.Objects;

public class Monster {
    private int id;
    private String name;
    private City currentCity;
    private boolean isAlive;
    private int moves;

    public Monster(int id, String name, City currentCity, boolean isAlive, int moves) {
        this.id = id;
        this.name = name;
        this.currentCity = currentCity;
        this.isAlive = isAlive;
        this.moves = moves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    public int getMoves() {
        return moves;
    }

    public void nextMove() {
        this.moves++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return id == monster.id &&
                isAlive == monster.isAlive &&
                moves == monster.moves &&
                Objects.equals(name, monster.name) &&
                Objects.equals(currentCity, monster.currentCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currentCity, isAlive, moves);
    }
}
