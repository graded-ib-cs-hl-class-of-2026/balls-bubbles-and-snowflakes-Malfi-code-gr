import processing.core.PApplet;

public class Sketch extends PApplet {

    /** Represents one ball */
    private Ball ball1;
    private Ball ball2;
    private Ball ball3;
    private Ball ball4;
    private Bubble bubble1;
    private Bubble bubble2;
    private Bubble bubble3;
    private Bubble bubble4;
    private Snowflake snowflake1;
    private Snowflake snowflake2;
    private Snowflake snowflake3;
    private Snowflake snowflake4;

    /**
     * This method can only be used to change the window size. It runs before the
     * window is created.
     */
    public void settings() {
        size(500, 800);
    }

    /**
     * Runs once at the beginning of the program, after the window is created. Use
     * this to initialize the sketch.
     */
    public void setup() {
        ball1 = new Ball(this, 20, 100, 100, 2, -1);
        ball2 = new Ball(this, 50, 200, 200, 1, 2);
        ball3 = new Ball(this, 10, 300, 500, -2, 1);
        ball4 = new Ball(this, 30, 400, 600, -1, -2);
        
        ball1.setColors(color(255,255,255), color(0,0,0));
        ball2.setColors(color(255,0,255), color(0,255,0));
        ball3.setColors(color(0,255,0), color(0,0,255));
        ball4.setColors(color(0,0,255), color(255,0,0));

        bubble1 = new Bubble(this, 20, 150, -20, 2, -7);
        bubble2 = new Bubble(this, 50, 250, -50, 1, -1);
        bubble3 = new Bubble(this, 10, 350, -10, -2, -5);
        bubble4 = new Bubble(this, 30, 450, -30, -1, -2);

        snowflake1 = new Snowflake(this);
        snowflake2 = new Snowflake(this,40,200,-50,1,7);
        snowflake3 = new Snowflake(this,20,100,-200,-2,8);
        snowflake4 = new Snowflake(this,15,-15,-100,0,12);
    }

    /**
     * This method runs over and over and over, approximately 60 times per second!
     * By moving objects a tiny bit each frame, you can get the appearance of
     * movement.
     */
    public void draw() {
        background(180, 180, 255);
        ball1.draw();
        ball1.move();
        ball2.draw();
        ball2.move();
        ball3.draw();
        ball3.move();
        ball4.draw();
        ball4.move();

        bubble1.draw();
        bubble1.move();
        bubble2.draw();
        bubble2.move();
        bubble3.draw();
        bubble3.move();
        bubble4.draw();
        bubble4.move();

        snowflake1.draw();
        snowflake1.move();
        snowflake2.draw();
        snowflake2.move();
        snowflake3.draw();
        snowflake3.move();
        snowflake4.draw();
        snowflake4.move();
    }

    /** All processing sketches have to use this main method. Don't touch this! */
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
