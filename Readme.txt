This project MonsterGame reads all the details of cities and its adjecent direction cities from input file and creates all the cities and adjecent cities.
The total no of monsters are input from command line argument and run will start the game of assigning monsters randomly to different cities.
Following messages displayed:
1. When 2 monsters end up at same city
ex:City1 has been destroyed by Monster 1 and Monster 30!

2. When no adjacent city is available for monsters to move, then monster will be trapped.
ex:Monster 1 is trapped in city City2

3. When all cities are destroyed
All cities destroyed, finishing the game.

4. When all monsters have been finished maximum no of moves or killed.
All Monsters have been either killed or finished, so finishing the game.

Instructions to setup and to follow.
1. Download project from git repository https://github.com/sujatahalyal/MonsterGame.git
2. Edit resources/application.properties file to give the correct location of input file and output file.
3. Run Main.java with argument as no_of_monsters.
4. The output will display different messages as explained above.
5. The program will wait either all the cities destroyed or all the monsters will be either dead or done maximum moves.
6. The output file will print all the remaining cities if there is any left.


