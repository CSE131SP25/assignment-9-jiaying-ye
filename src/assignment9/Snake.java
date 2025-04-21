package assignment9;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

 private static final double SEGMENT_SIZE = 0.02;
 public static final double FOOD_SIZE = 0.02;
 private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
 private LinkedList<BodySegment> segments;
 private double deltaX;
 private double deltaY;
 private final int WIDTH = 1;
    private final int HEIGHT = 1;
    private BodySegment lastHeadPosition;
    private int score;
 
 public Snake() {
  segments = new LinkedList<>();
        segments.add(new BodySegment(Math.random(), Math.random(), SEGMENT_SIZE));
  deltaX = 0;
  deltaY = 0;
 }
 
 public void changeDirection(int direction) {
  if(direction == 1) { //up
   deltaY = MOVEMENT_SIZE;
   deltaX = 0;
  } else if (direction == 2) { //down
   deltaY = -MOVEMENT_SIZE;
   deltaX = 0;
  } else if (direction == 3) { //left
   deltaY = 0;
   deltaX = -MOVEMENT_SIZE;
  } else if (direction == 4) { //right
   deltaY = 0;
   deltaX = MOVEMENT_SIZE;
  }
 }
 
 /**
  * Moves the snake by updating the position of each of the segments
  * based on the current direction of travel
  */
 public void move() {
  double headX = segments.getFirst().getX();
     double headY = segments.getFirst().getY();
  for (int i = segments.size() - 1; i > 0; i--) {
   BodySegment segment = segments.get(i);
         BodySegment previousSegment = segments.get(i - 1);
         segment.setX(previousSegment.getX());
         segment.setY(previousSegment.getY());
        }
  BodySegment head = segments.getFirst();
     head.setX(head.getX() + deltaX);
     head.setY(head.getY() + deltaY);
     lastHeadPosition = new BodySegment(headX, headY, head.getSize());
 }

 // Method to grow the snake
 public void grow() {
     // Add a new segment at the last head position
     if (lastHeadPosition != null) {
         segments.addFirst(new BodySegment(lastHeadPosition.getX(), lastHeadPosition.getY(), lastHeadPosition.getSize()));
     }
 }
 
 /**
  * Draws the snake by drawing each segment
  */
 public void draw() {
  for (int i = 0; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            segment.draw();
        }
  StdDraw.setFont();
  StdDraw.textLeft(0.05, 0.95, "Score: " + score);
 }
 
 /**
  * The snake attempts to eat the given food, growing if it does so successfully
  * @param f the food to be eaten
  * @return true if the snake successfully ate the food
  */
 public boolean eatFood(Food f) {
  BodySegment head = segments.getLast();
     double headX = head.getX();
     double headY = head.getY();
     double foodX = f.getX();
     double foodY = f.getY();
  double distance = Math.sqrt(Math.pow(headX - foodX, 2) + Math.pow(headY - foodY, 2));
   if (distance <= MOVEMENT_SIZE) {
          segments.addFirst(new BodySegment(headX, headY, SEGMENT_SIZE));
          score ++;
          return true;
      }
      return false;
 }
 
 /**
  * Returns true if the head of the snake is in bounds
  * @return whether or not the head is in the bounds of the window
  */
 public boolean isInbounds() {
   BodySegment head = segments.getFirst();
   return head.getX() >= 0 && head.getX() <= WIDTH && head.getY() >= 0 && head.getY() <= HEIGHT;
 }
}
