package com.mimecast.monster;

import com.mimecast.model.City;
import com.mimecast.model.World;

public interface MonsterMoveStrategy {
    Integer getNextDirection(World world);
}
