package com.mimecast;

import com.mimecast.converter.decoder.TextDecoder;
import com.mimecast.converter.encoder.TextEncoder;
import com.mimecast.manager.GameManager;
import com.mimecast.monster.MonsterRandomMoveStrategy;
import com.mimecast.util.ApplicationProperties;

public class Main {

        public static final int MAX_MONSTER_MOVES=10000;

        public static void main(String args[]) throws Exception{
         if(args.length==0) {
            System.out.println("Please enter an integer as command line argument");
            return;
        }
            String strTotalMosters = args[0];
            int totalMonsters = 0;
            try {
                totalMonsters = Integer.parseInt(strTotalMosters);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer as command line argument");
            }
            Main mainP= new Main();
            mainP.startGame(totalMonsters);
        }

        public void startGame(int totalMonsters) throws Exception{
            String inputFilePath = ApplicationProperties.INSTANCE.getInputFile();
            String outputFilePath = ApplicationProperties.INSTANCE.getOutputFile();
            GameManager gManager = new GameManager(new TextDecoder(inputFilePath),new TextEncoder(outputFilePath));
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
          /*  GameManager gManager = new GameManager(new TextDecoder(getClass().getClassLoader().getResource("map.txt").getFile()),
                    new TextEncoder(getClass().getClassLoader().getResource("output.txt").getFile()));*/
            gManager.createWorld();
            gManager.startGame(totalMonsters, MAX_MONSTER_MOVES, new MonsterRandomMoveStrategy()); //Select the appropriate strategy
            //Find Check if all city destroyed, if not try to move alive monsters to cities and continue playing
            gManager.encodeRemainingCities();
        }
}
