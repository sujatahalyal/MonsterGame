package com.mimecast.manager;

import com.mimecast.TestUtil;
import com.mimecast.converter.decoder.TextDecoder;
import com.mimecast.converter.encoder.TextEncoder;
import com.mimecast.model.City;
import com.mimecast.model.Monster;
import com.mimecast.model.World;
import org.junit.Before;
import org.junit.Test;
import java.io.File;


import static org.junit.Assert.*;

public class GameManagerTest {
    private GameManager gManager;
    private TextEncoder encoder;
    private TextDecoder decoder;
    ClassLoader classLoader = getClass().getClassLoader();
    World worldExpected;
    World worldActual;

    @Before
    public void setUp() throws Exception {
        File file = new File(classLoader.getResource("expected_output.txt").getFile());
        encoder = new TextEncoder(file.getParent()+"/output.txt");
        decoder = new TextDecoder(classLoader.getResource("rightMap.txt").getFile());
        gManager = new GameManager(decoder,encoder);
        worldExpected = TestUtil.generateTestWorld();
        worldActual = gManager.createWorld();
    }

    @Test
    public void createWorldTest() throws Exception{
        assertEquals(worldExpected.getCities().size(),worldActual.getCities().size());
        assertEquals(worldExpected.getCities().get(0).getName(),worldActual.getCities().get(0).getName());
    }

    @Test
    public void addMonstersTest() {
        int id=1;
        Monster monster1 = new Monster(id,"Monster "+id,worldActual.getCities().get(0),true,1);
        worldActual.getCities().get(0).addMonster(monster1);

        assertEquals(worldExpected.getCities().get(0).getMonsterList().size(),worldActual.getCities().get(0).getMonsterList().size());
    }

    @Test
    public void moveMonstersCitiesTest() {
        int id=1;
        Monster monster1 = new Monster(id,"Monster "+id,worldActual.getCities().get(0),true,1);
        worldActual.getCities().get(0).addMonster(monster1);
        gManager.moveMonstersCities(worldActual.getCities().get(0),worldActual.getCities().get(1),10000);
        assertEquals(worldExpected.getCities().get(1).getMonsterList().size(),worldActual.getCities().get(1).getMonsterList().size());
    }

    @Test
    public void getNextCityTest() {
        City nCity = gManager.getNextCity(worldActual.getCities().get(0));
        assertEquals(worldExpected.getCities().get(1).getName(),nCity.getName());
    }

    @Test
    public void getNextCityTest_when_destroyed_one_City() {
        worldActual.getCities().get(1).setDestroyed(true);
        City nCity = gManager.getNextCity(worldActual.getCities().get(0));
        assertEquals(worldExpected.getCities().get(2).getName(),nCity.getName());
    }

    @Test
    public void getNextCityTest_when_no_adjacent_cities() {
        worldActual.getCities().get(1).setDestroyed(true);
        worldActual.getCities().get(2).setDestroyed(true);
        City nCity = gManager.getNextCity(worldActual.getCities().get(0));
        assertEquals(null,nCity);
    }

}