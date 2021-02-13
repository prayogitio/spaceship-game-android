## Spaceship Game on Android

The objective is to exercise creating spaceship game on Android.  
We have a player's spaceship which will shoot yellow square bullet.  
We have enemy's spaceship which will spawn from the left and moves to the right with random speed.  
The initial lives is 3. The lives will be decreased whenever the player's spaceship is collided with the enemy's spaceship or enemy's spaceship flew towards the left most without getting shot. Game over screen will be shown when we no longer have any lives.  
Score will be increased if we take down enemy's spaceship.

Here is the screenshot of the game:  
![1613210848415 (1)](https://user-images.githubusercontent.com/33726233/107853063-8864b700-6e46-11eb-903e-ec45cf7d7cd1.jpg)


### Explanation of the source code
There are 4 activities in this game:
1. **MainActivity**  
This is the main action, as specified in `AndroidManifest.xml`. The view consist of 2 buttons, those are the `Play` and `Rule` button.
2. **GameActivity**  
This activity will take action when we select `Play` button.
Here is where the `GameView` is created and where we play the Spaceship game.
3. **RuleActivity**  
This activity will take action when we select `Rule` button.
Here we display the rules of the Spaceship game.
4. **GameOverActivity**  
This activity will take action when the spaceship is running out of lives.
From here, we can choose `Replay` to return to `GameActivity`, and we can choose `Main menu` to return to the main menu.

There are 6 classes that are orchestrated by the `GameView`:
1. **Player**  
The player's spaceship that will keep shooting `Bullet`
The default movement of this spaceship is down with defined gravity. When you tap and hold the screen, some boost point will be added to make the spaceship moving in up direction.
2. **Enemy**  
The enemy's spaceship. When enemy's spaceship is constructed, we generate two random numbers for the spaceship speed and for the initial `y` position. It will move from right to left.
3. **Bullet**  
The player's spaceship's bullet. The bullet will always be shooted from the "mouth" of the player's spaceship.
4. **Boom**  
This is an object to make a collision effect.
5. **Star**  
We draw `Star` objects, which are circles with random radiuses, to the canvas to make space nuance.
