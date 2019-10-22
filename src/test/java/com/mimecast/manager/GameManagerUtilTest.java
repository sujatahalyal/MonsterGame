package com.mimecast.manager;

import com.mimecast.TestUtil;
import com.mimecast.model.World;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerUtilTest {

    World worldExpected;

    @Before
    public void setUp(){
        worldExpected = TestUtil.generateTestWorld();
    }

    @Test
    public void getRemainingCities() {
        worldExpected.getCities().get(1).setDestroyed(true);
        assertEquals(3,GameManagerUtil.getRemainingCities(worldExpected).size());
    }

    @Test
    public void getDestroyedCities() {
        worldExpected.getCities().get(1).setDestroyed(true);
        worldExpected.getCities().get(0).setDestroyed(true);
        assertEquals(2,GameManagerUtil.getDestroyedCities(worldExpected).size());
    }

    @Test
    public void getAliveMonsterList() {
        assertEquals(2,GameManagerUtil.getAliveMonsterList(worldExpected).size());
    }

    @Test
    public void getAliveMonsterList_when_killed_one() {
        worldExpected.getCities().get(0).getMonsterList().get(0).kill();
        assertEquals(1,GameManagerUtil.getAliveMonsterList(worldExpected).size());
    }

    @Test
    public void isAnyMonstersAlive() {
    }
}