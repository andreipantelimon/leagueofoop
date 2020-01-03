
# League of OOP - Final Stage

Minimal implementation for a classic 2D MMO-style game.

League of OOP is a game where 4 types of players move on a 2D board of different types of terrain and fight if 2 players meet in the same cell of the board. 
The players can fight based on 2 strategies: a healing one and an empowerment one.
Angels start to show up on the map. 
There is an observer of the game called The Great Magician.

The types of players are as follows:
1. Knight - Expert in body fighting, has 2 abilities: Execute and Slam
2. Pyromancer - Fire master, has 2 abilities: Fireblast and Ignite
3. Wizard - Superior mental capacity, has 2 abilities: Drain and Deflect
4. Rogue - Sneak-attacker, has 2 abilities: Backstab and Paralysis

The types of terrain are as follows:
1. Land - It gives the Knight a 15% bonus damage
2. Volcanic - It gives the Pyromancer a 25% bonus damage
3. Desert - It gives the Wizard a 10% damage increase
4. Woods - It gives the Rogue the ability to Crit and a 15% bonus damage

There are 10 types of Angels:
1. Damage Angel - Increases damage
2. Dark Angel - Decreases HP
3. Dracula - Decreases HP and Decreases Damage
4. Good Boy - Increases HP and Increases Damage
5. Level Up Angel - Gives the player necessary XP to level up
6. Life Giver - Increases HP
7. Small Angel - Increases HP and Increases Damage by a small amount
8. Spawner Angel - Respawns the player with a specified HP
9. The Doomer - Kills the player
10. XP Angel - Gives the player an amount of XP

Implemented by: Andrei Pantelimon 325CA

## Demo / How it works
This is how the input looks like:

You give the program the dimensions of the board, N strings of M length with letters corresponding to the name of the terrains.
Next up you the to input the number of players and on the next P lines, a letter corresponding to the player type along with the coordinates that he will spawn in.
Next up you input the number of rounds. Every round is described in P letters that indicate the direction that every player will move ('U' for up, 'D' for down, 'L' for left, 'R' for right, '_' for no movement).
Finally you input the number of angels for every round and their type and position on the board.

![Input](https://imgur.com/GM6X7FG.png)          ![Output](https://imgur.com/acVeaSb.png)

And it will magically print the events seen by the Great Magician and the final results as shown in the second photo.

## Compile and usage
Compiling the program: 

`unzip FileIO.jar` 
`javac -g main/Main.java`
> Note: You need to have the .jar file of the API inside the src folder.

Running it:
`java main.Main "INPUT_FILE" "OUTPUT_FILE"`

## Tech framework and API

Project is made with IntelliJ Idea Community Edition in Java 12 with using only the [FileIO API](http://elf.cs.pub.ro/poo/laboratoare/tutorial-io) from the OOP team at the university.

## Code example

Below is showed how a Pyromancer calculates the Fireblast abilty damage and applies it.

![Code example](https://i.imgur.com/BVMewnJ.png)

- It begins by calculating the ground modifier, if the player is located in a Volcano terrain
- The base damage is calculated
- Ground modifier is applied
- The damage is added to a variable that keeps the damage dealt to a potential Wizard if is present
- The race modifier is calculated and applied
- Final damage is applied and it declares the player as dead if he dies

## Flow and Design

Explaining in short terms: 
1. Input is read using the GameIOLoader class.
2. Every player is instantiated in a list of players. They also get their own class based on their type.
3. The game is played:
3.1. Board and players are initialized
3.2. Angels are added on the map
3.3. Every player takes Damage over Time and is declared dead if his hp drops below 0 (used in the next rounds)
3.4. Players are moved (only their coordinates are modified)
3.5. Board is reinitialized (players are cleared from their last round terrain and are reinitialized in the new board with the new coordinates). The actual objects of the players and the board do not actually modify (only some of their parameters).
3.6. Where there are 2 players in the same cell, they fight.
3.7. Angels visit the players
3.8. Players level up if necessary.
4. Output seen by the Great Magician is written in the output file.

## Issues
- In the tests there is a Float Error issue with Rogue and Pyromancer when calculating the damage, therefore needing to subtract the error to get the correct damage output.
- In the dense test, a player moves out of the map and comes back in with nothing happening to him, although there is specified that this could not happen.

## References
- [First Stage of the Project](http://elf.cs.pub.ro/poo/teme/proiect/etapa1) - Project description with necessary information 
- [Second Stage of the Project](http://elf.cs.pub.ro/poo/teme/proiect/etapa2) - Project description with necessary information 
- [FileIO API](http://elf.cs.pub.ro/poo/laboratoare/tutorial-io) - Download and tutorial on how to use it
- [Github](https://github.com/andreipantelimon) - Andrei Pantelimon

