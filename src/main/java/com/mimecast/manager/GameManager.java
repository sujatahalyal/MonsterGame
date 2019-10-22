package com.mimecast.manager;

import com.mimecast.converter.Decoder;
import com.mimecast.converter.Encoder;
import com.mimecast.converter.decoder.exception.ReadException;
import com.mimecast.model.City;
import com.mimecast.model.Direction;
import com.mimecast.model.Monster;
import com.mimecast.model.World;
import com.mimecast.monster.MonsterMoveStrategy;

import java.io.IOException;
import java.util.*;

public class GameManager {
    Decoder decoder;  //convert data from file to java object
    Encoder encoder; ////convert data to file from java object
    World world;

    /**
     * Constructor to initialize reader and writer file paths.
     * @param decoder
     * @param encoder
     */
    public GameManager(Decoder decoder,Encoder encoder){
        this.encoder = encoder;
        this.decoder = decoder;
    }

    /*
     *  create class world with all cities *
     * @return
     * @throws ReadException
     */
    public World createWorld() throws ReadException {
        this.world = decoder.decode();
        return world;
    }

    /**
     * Start the game
     * @param totalMonsters
     * @param maxMonsterMove
     * @param strategy
     */
    public void startGame(int totalMonsters,int maxMonsterMove,MonsterMoveStrategy strategy){
        for (Integer i = 0; i < totalMonsters; i++) {
            if(GameManagerUtil.getRemainingCities(world).size() <1){
                System.out.println("All cities destroyed, finishing the game.");
                return;
            }
            createMonster(i,strategy);
        }

        //Continue the game  with remaining cities and remaining monsters.
        while(!GameManagerUtil.getRemainingCities(world).isEmpty()){
            moveMonsters(maxMonsterMove);
            if(!GameManagerUtil.isAnyMonstersAlive(world)){
                System.out.println("All Monsters have been either killed or finished "+maxMonsterMove+" moves, so finishing the game.");
                return;
            }
        }
        //If all the cities destroyed then print the message and finish the game
        if(GameManagerUtil.getRemainingCities((world)).isEmpty()){
            System.out.println("All cities have been destroyed, so finishing the game.");
        }
    }


    /**
     * Start moving the monsters to adjecent cities randomly
     * @param maxMonsterMove
     */
    public void moveMonsters(int maxMonsterMove){
        List<City> remainingCities = GameManagerUtil.getRemainingCities(world);
        for(City rCity: remainingCities){
            City nextCity = getNextCity(rCity);
            //If all the adjacent cities are destroyed then Monster is trapped in the current city.
            if(nextCity != null){
                // System.out.println("next city:"+nextCity.getName());
                moveMonstersCities(rCity,nextCity,maxMonsterMove);
                //  continue;
            }else{
                if(!rCity.getMonsterList().isEmpty()) {
                    System.out.println(rCity.getMonsterList().get(0).getName()+" trapped in City: " + rCity.getName());
                    rCity.getMonsterList().get(0).kill();
                }
            }
        }
    }

    /**
     * Move monster to one city to different adjacent cities.
     * @param currentCity
     * @param nextCity
     * @param maxMonsterMove
     */
    public void moveMonstersCities(City currentCity, City nextCity,int maxMonsterMove){
        int cIndex = world.getCities().indexOf(currentCity);
        int nIndex = world.getCities().indexOf(nextCity);
    if( world.getCities().get(cIndex).getMonsterList().isEmpty()) return;
        Monster cMonster = world.getCities().get(cIndex).getMonsterList().get(0);
        cMonster.nextMove();
        if(cMonster.getMoves()>=maxMonsterMove){
            System.out.println(cMonster.getName()+" has finished "+maxMonsterMove+" moves.");
            cMonster.kill();
            return;
        }
        cMonster.setCurrentCity(nextCity);
        world.getCities().get(cIndex).removeMonster(cMonster);
        world.getCities().get(nIndex).addMonster(cMonster);
        if(nextCity.getMonsterList().size()>1){
            //City destroyed and monsters will get killed.
            nextCity.setDestroyed(true);
           // String monster1=nextCity.getMonsterList().get(0).getName();
            //String monster2=nextCity.getMonsterList().get(1).getName();
            Iterator<Monster> mIterator = nextCity.getMonsterList().iterator();
            while(mIterator.hasNext()){
                mIterator.next().kill();
            }
            System.out.println(nextCity.getName()+" has been destroyed by "+nextCity.getMonsterList().get(0).getName()
                    +" and "+nextCity.getMonsterList().get(1).getName()+"!");

        }
    }

    /**
     * Get adjacent city randomly
     * @param city
     * @return
     */
    public City getNextCity(City city){
        Map<Direction,City> cityDirection = city.getDirection();
        Optional<Map.Entry<Direction,City>> opCity= cityDirection.entrySet().stream().filter(x->!(x.getValue().isDestroyed())).findAny();
        if(opCity.isPresent()){
            return opCity.get().getValue();
        }
       // City nextCity
        return null;
    }

    /**
     * Create new Monster and add it to city randomly
     * @param id
     * @param strategy
     */
    private void createMonster(int id, MonsterMoveStrategy strategy){
        City randomCity;
       // System.out.println("CityManager.getRemainingCities(world)::"+ GameManagerUtil.getRemainingCities(world).size());
        if(GameManagerUtil.getRemainingCities(world).size()<1){
            System.out.println("All cities destroyed, finishing the game.");
            return;
        }
        do {
            Integer position = strategy.getNextDirection(world);
            randomCity = world.getCities().get(position);
            if(GameManagerUtil.getRemainingCities(world).size()==1){
                randomCity = GameManagerUtil.getRemainingCities(world).get(0);
            }
        }while(randomCity.isDestroyed());
        //assuming moves=1 when first created and assigned to city
        Monster monster = new Monster(id, "Monster "+id, randomCity, true,1);
        monster.setCurrentCity(randomCity);
        randomCity.addMonster(monster);
        if(randomCity.getMonsterList().size()>1){
            //City destroyed and monsters will get killed.
            randomCity.setDestroyed(true);
            Iterator<Monster> mIterator = randomCity.getMonsterList().iterator();
            while(mIterator.hasNext()){
                mIterator.next().kill();
            }
            System.out.println(randomCity.getName()+" has been killed by "+randomCity.getMonsterList().get(0).getName()
                    +" and "+randomCity.getMonsterList().get(1).getName()+"!");
        }
    }

    /**
     * Encode remaining cities to output file
     * @throws IOException
     */
    public void encodeRemainingCities() throws IOException {
        encoder.encode(GameManagerUtil.getRemainingCities(world), GameManagerUtil.getDestroyedCities(world));
    }
}
