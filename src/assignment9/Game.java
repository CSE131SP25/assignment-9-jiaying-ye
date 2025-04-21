package assignment9;

import java.awt.event.KeyEvent;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class Game extends Snake{
 
 private Snake snake;
 private Food food;
 private final int WIDTH = 1;
    private final int HEIGHT = 1;
    private final Random random = new Random();
 
 public Game() {
  StdDraw.enableDoubleBuffering();
  snake = new Snake();
  food = new Food();
 }
 
 public void play() {
  while (snake.isInbounds()) {
   int dir = getKeypress();
   snake.changeDirection(dir);
   snake.move();
   if (snake.eatFood(food)) {
    food = new Food(); // Move the food to a new random position
   }
   updateDrawing();
   try {
    Thread.sleep(50); // Pause for 50 ms to control the game's speed
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
  System.out.println("Game Over!");
   
   /*
    * 1. Pass direction to your snake
    * 2. Tell the snake to move
    * 3. If the food has been eaten, make a new one
    * 4. Update the drawing
    */
 }
 
 private int getKeypress() {
  if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
   return 1;
  } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
   return 2;
  } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
   return 3;
  } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
   return 4;
  } else {
   return -1;
  }
 }
 
 /**
  * Clears the screen, draws the snake and food, pauses, and shows the content
  */
 private void updateDrawing() {
  StdDraw.clear();
  snake.draw();
  food.draw();
  StdDraw.show();
  
  /*
   * 1. Clear screen
   * 2. Draw snake and food
   * 3. Pause (50 ms is good)
   * 4. Show
   */
 }
 
 public static void main(String[] args) {
  Game g = new Game();
  g.play();
 }
}
