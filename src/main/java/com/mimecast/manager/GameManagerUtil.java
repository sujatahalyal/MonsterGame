package com.mimecast.manager;

import com.mimecast.model.City;
import com.mimecast.model.Direction;
import com.mimecast.model.Monster;
import com.mimecast.model.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameManagerUtil {
    List<City> cities;

    /**
     * get remaining cities list
     * @param world
     * @return
     */
    public static List<City> getRemainingCities(World world){
        List<City> remainingCityList= new ArrayList<>();
        for(City rCity:world.getCities()){
            if(!rCity.isDestroyed()){
                remainingCityList.add(rCity);
            }
        }
        return remainingCityList;
    }

    /**
     * Get destoyed cities list
     * @param world
     * @return
     */
    public static List<City> getDestroyedCities(World world){
        List<City> destroyedCityList= new ArrayList<>();
        for(City rCity:world.getCities()){
            if(rCity.isDestroyed()){
                destroyedCityList.add(rCity);
            }
        }
        return destroyedCityList;
    }

    /**
     * Get all alive monsters
     * @param world
     * @return
     */
    public static List<Monster> getAliveMonsterList(World world){
        List<Monster> aMonsterList = new ArrayList<>();
        for(City cCity:world.getCities()){
            for(Monster mMonster:cCity.getMonsterList()){
                if(mMonster.isAlive()){
                    aMonsterList.add(mMonster);
                }
            }
        }
        return aMonsterList;
    }

    /**
     * Check if any monster is alive
     * @param world
     * @return
     */
    static boolean isAnyMonstersAlive(World world){
        if(GameManagerUtil.getAliveMonsterList(world).size()>0){
            return true;
        }
        return false;
    }

    /**
     * print all cities and its directions
     * @param world
     */
    public void printWorld(World world){
        List<City> cities = world.getCities();
        System.out.println("Total cities: "+cities.size());
        for(City city: cities){
            System.out.print(city.getName()+" ");
            Map<Direction,City> direction =  city.getDirection();
            direction.entrySet().stream().forEach((k)->System.out.print(k.getKey().getName()+"="+k.getValue().getName()+" "));
            System.out.println(" ");
        }
    }
}
