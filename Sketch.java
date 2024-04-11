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
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    noStroke();
	  flower(width/2, height/2, 50);
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
    for (int i = 0; i < layers; i++){
      pedals( (int)(random(5) * 2), size, size, size);
    }
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
    int randColor = color( (int)random(255), (int)random(255), (int)random(255) );
    return randColor;
  }
}