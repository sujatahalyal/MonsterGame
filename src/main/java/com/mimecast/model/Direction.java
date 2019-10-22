package com.mimecast.model;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private String name;

    private Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Direction direction) {
        return name.equals(direction.getName());
    }
}
