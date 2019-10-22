package com.mimecast.Direction;

import com.mimecast.Direction.Exception.DirectionNotFoundException;
import com.mimecast.model.Direction;

public class DirectionFactory {

    protected DirectionFactory() {
    }

    public static Direction create(String direction) throws DirectionNotFoundException {
        for (Direction curDirection : Direction.values()) {
            if (curDirection.getName().equals(direction)) {
                return curDirection;
            }
        }

        throw new DirectionNotFoundException("Invalid direction " + direction);
    }
}
