package com.mimecast.monster;

import com.mimecast.model.World;

import java.util.Random;

public class MonsterRandomMoveStrategy implements MonsterMoveStrategy {
    @Override
    public Integer getNextDirection(World world) {
        Random randomGenerator = new Random();
        Integer position = randomGenerator.nextInt(world.getCities().size() - 1);
        return position;
    }
}
