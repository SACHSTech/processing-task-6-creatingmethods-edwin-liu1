import processing.core.PApplet;

public class Sketch extends PApplet {
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */

  public void settings() {
	// put your size call here
    size(1200, 750);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */

  // variables
  int centerSize;
  
  int screenSize;

  int randFlowerSize;

  int randFlowerDistance;

  public void setup() {
    background(137, 196, 118);

    // variable calculations

    if (width > height){
      screenSize = height;
    } else {
      screenSize = width;
    }

    randFlowerSize = screenSize / 20;

    centerSize = (screenSize / 15);

    randFlowerDistance = screenSize / 5;

    // draws the randomized flowers
    for (int y = (randFlowerDistance / 2); y <= height - (randFlowerDistance / 2); y+= randFlowerDistance){
      for (int x = (randFlowerDistance / 2); x <= width - (randFlowerDistance / 2); x+= randFlowerDistance){
        if (flowerAlg(x, y, randFlowerSize) ){
          randFlower(x, y, (int)random(2,5), randFlowerSize);
        }
      }
    }
    // the random flowers are here because if 

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // the centeral flower
	  flower(width/2, height/2, centerSize);

  }
  
  /*
   * draws a flower
   * 
   * @param X. The X position of the flower.
   * @param Y. The Y position of the flower.
   * @param size. The size of the flower's pistil.
   */
  private void flower(int X, int Y, int size){
    pushMatrix();

    translate(X, Y);
    
    // pedals 1

    rotate(QUARTER_PI / 2);
    fill(213, 185, 243);
    pedals(8, size, (size / 2), (float)(size * 0.8) );

    // pedals 2
    rotate(QUARTER_PI / 2);
    fill(255);
    pedals(12, (float)(size * 1.5), size / 3, (float)(size * 0.8) );

    // pedals 3
    rotate(QUARTER_PI);
    fill(57, 184, 221);
    pedals(4, (float)(size * 1.2), (float)(size / 2), (float)(size * 0.8) );
    
    // pistil
    fill(255, 255, 64);
    circle(0, 0, size);

    popMatrix();
  }

  /*
   * draws a randomized flower
   * 
   * @param X. The X position of the flower.
   * @param Y. The Y position of the flower.
   * @param layers. the number of rings of petels.
   * @param size. The size of the flower's pistil.
   */
  private void randFlower(int X, int Y, int layers, int size){

    int scale;

    scale = (int)(size + (layers * (size / 12) * 0.5) );

    pushMatrix();

    translate(X, Y);

    for (int i = 0; i < layers; i++){

      // random variables
      int pedalsAmount = Math.round(random(3,12) );
      float pedalLegnth = random( (float)1.2, 2);
      float pedalWidth = random( (float)0.3, 1);

      // heads or tails?
      int coin = Math.round(random(1) );

      // these pedal amounts don't look good.
      if (pedalsAmount == 7){
        pedalsAmount *= 2;
      } else if (pedalsAmount == 9 || pedalsAmount == 11){
        pedalsAmount++;
      }

      // flip a coin to see if the pedals are rotated a bit
      if(coin == 1){
        rotate(TWO_PI / pedalsAmount / 2);
      }

      // draws a ring of pedals for the flower
      fill(randColor() );
      pedals( pedalsAmount, (scale * pedalLegnth), (scale * pedalWidth), (float)(scale * 0.8) );

      // gotta rotate it back
      if(coin == 1){
        rotate(-1 * (TWO_PI / pedalsAmount / 2) );
      }
      
      scale = scale - (int)(size * 0.2);

    }

    // the flower pistil

    // random yellow
    int yellow = (int)random(220,255);
    int blue = (int)random(0, 150);
    fill(yellow, yellow, blue);
    circle(0, 0, size);

    popMatrix();
  }

  /*
   * draws a ring of pedals
   * needs to be used within a pushmatrix method
   * 
   * @param amount. The amount of pedals.
   * @param length. The length of each pedal.
   * @param width. The width of each pedal.
   * @param distance. The radius of where the pedals get drawn.
   */
  private void pedals(int amount, float length, float width, float distance){
    for (int i = 0; i < amount; i++)
    {
      rotate(TWO_PI / amount);

      ellipse(0, distance, width, length);
    }
  }

  /*
   * selects a random colour.
   * 
   * @return a random colour.
   */
  public int randColor(){

    // variable set up

    int randR;
    int randG = (int)random(255);
    int randB;

    // flip a coin. heads or tails?
    int coin = Math.round(random(1) );

    // this is just to get more vibrant colours
    if (randG < 100)
    {
      if (coin == 1){
        randR = lightColor();
        randB = (int)random(255);
      } else {
        randB = lightColor();
        randR = (int)random(255);
      }
    } else if (randG > 200){
      if (coin == 1){
        randR = darkColor();
        randB = (int)random(255);
      } else {
        randB = darkColor();
        randR = (int)random(255);
      }
    } else {
      randR = (int)random(255);
      randB = (int)random(255);
    }
    
    // putting the colours all together
    int randColor = color(randR, randG, randB);
    return randColor;
  }

  /*
   * selects a random low color value.
   * 
   * @return a random low colour value.
   */
  public int darkColor(){
    int color = (int)random(0, 60);
    return color;
  }

  /*
   * selects a random high color value.
   * 
   * @return a random high colour value.
   */
  public int lightColor(){
    int color = (int)random(160, 255);
    return color;
  }

  /*
   * a simple algorithm to avoid spawning flowers on the center flower
   * 
   * @param posX. the current X position of where a flower wants to be spawned.
   * @param posY. the current Y position of where a flower wants to be spawned.
   * @return clear. determins if it's too close to the centeral flower to spawn a flower
   */
  public boolean flowerAlg(int posX, int posY, int flowerSize){

    boolean clear = true;

    int flowerDistanceX = Math.abs(posX - (width / 2) );
    int flowerDistanceY = Math.abs(posY - (height / 2) );
    double flowerDistance = Math.sqrt( (flowerDistanceX * flowerDistanceX) + (flowerDistanceY * flowerDistanceY) );

    if (flowerDistance < (centerSize * 2.5) + flowerSize){
      clear = false;
    }

    return clear;
  }

}