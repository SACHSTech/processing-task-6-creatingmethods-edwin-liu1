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
  public void setup() {
    background(137, 196, 118);
    randFlower(100, 100, (int)random(2,5), 20);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  flower(width/2, height/2, 70);
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

    int scale = size;

    pushMatrix();

    translate(X, Y);

    for (int i = 0; i < layers; i++){
      int pedalsAmount = (int)random(2,6) * 2;
      float pedalLegnth = random( (float)1.2, 2);
      float pedalWidth = random( (float)0.3, 1);

      if(pedalsAmount % 6 == 0){
        rotate(PI / 3);
      } else {
        rotate(QUARTER_PI / 2);
      }

      fill(randColor() );
      pedals( pedalsAmount, (scale * pedalLegnth), (scale * pedalWidth), (float)(scale * 0.8) );
      
      scale = scale + (int)(size * 0.2);

      if(pedalsAmount % 6 == 0){
        rotate(PI / 3);
      }

    }
    int yellow = (int)random(200,255);
    int blue = (int)random(0, 50);
    fill(yellow, yellow, blue);
    circle(0, 0, size);

    popMatrix();
  }

  /*
   * draws a ring of pedals
   * 
   * @param amount. The amount of pedals.
   * @param length. The length of each pedal.
   * @param width. The width of each pedal.
   * @param distance. The radius of where the pedals get drawn.
   */
  private void pedals(int amount, float length, float width, float distance){
    for (int i = 0; i < amount; i++)
    {
      ellipse(0, distance, width, (length));

      rotate(TWO_PI / amount);
    }
  }

  /*
   * selects a random colour.
   * 
   * @return a random colour.
   */
  public int randColor(){
    int randR;
    int randG = (int)random(255);
    int randB;
    int coin = (int)random(0,1);

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
      if (coin ==1){
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
    
    int randColor = color(randR, randG, randB);
    return randColor;
  }

  /*
   * selects a random darker color value.
   * 
   * @return a random darker colour value.
   */
  public int darkColor(){
    int color = (int)random(0, 60);
    return color;
  }

  /*
   * selects a random lighter color value.
   * 
   * @return a random lighter colour value.
   */
  public int lightColor(){
    int color = (int)random(160, 255);
    return color;
  }

  /*
   * 
   */
  public int flowerSpawn(int posX, int posY, int flowerSize, int columns){
    for (int i = 0; i < columns; i++){
      if (posX - flowerSize * 3){

      }
    }
    return X;
  }

}