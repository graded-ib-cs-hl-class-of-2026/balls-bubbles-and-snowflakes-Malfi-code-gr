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

    public boolean hIsHeld = false;

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
        ball1 = new Ball(this);
        ball2 = new Ball(this);
        ball3 = new Ball(this);
        ball4 = new Ball(this);

        bubble1 = new Bubble(this, 20, 150, -20, 2, -7);
        bubble2 = new Bubble(this, 50, 250, -50, 1, -1);
        bubble3 = new Bubble(this, 10, 350, -10, -2, -5);
        bubble4 = new Bubble(this, 30, 450, -30, -1, -2);

        snowflake1 = new Snowflake(this);
        snowflake2 = new Snowflake(this);
        snowflake3 = new Snowflake(this);
        snowflake4 = new Snowflake(this);
    }

    /**
     * This method runs over and over and over, approximately 60 times per second!
     * By moving objects a tiny bit each frame, you can get the appearance of
     * movement.
     */
    public void draw() {
        background(180, 180, 255);
        ball1.collidesWith(ball2);
        ball1.collidesWith(ball3);
        ball1.collidesWith(ball4);
        ball1.draw();
        if (!ball1.mouseOver() || !hIsHeld) {
            ball1.move();
        }

        ball2.collidesWith(ball1);
        ball2.collidesWith(ball3);
        ball2.collidesWith(ball4);
        ball2.draw();
        if (!ball2.mouseOver() || !hIsHeld) {
            ball2.move();
        }

        ball3.collidesWith(ball1);
        ball3.collidesWith(ball2);
        ball3.collidesWith(ball4);
        ball3.draw();
        if (!ball3.mouseOver() || !hIsHeld) {
            ball3.move();
        }

        ball4.collidesWith(ball1);
        ball4.collidesWith(ball2);
        ball4.collidesWith(ball3);
        ball4.draw();
        if (!ball4.mouseOver() || !hIsHeld) {
            ball4.move();
        }

        bubble1.draw();
        if (!bubble1.mouseOver() || !hIsHeld) {
            bubble1.move();
        }
        bubble2.draw();
        if (!bubble2.mouseOver() || !hIsHeld) {
            bubble2.move();
        }
        bubble3.draw();
        if (!bubble3.mouseOver() || !hIsHeld) {
            bubble3.move();
        }
        bubble4.draw();
        if (!bubble4.mouseOver() || !hIsHeld) {
            bubble4.move();
        }

        snowflake1.draw();
        if (!snowflake1.mouseOver() || !hIsHeld) {
            snowflake1.move();
        }
        snowflake2.draw();
        if (!snowflake2.mouseOver() || !hIsHeld) {
            snowflake2.move();
        }
        snowflake3.draw();
        if (!snowflake3.mouseOver() || !hIsHeld) {
            snowflake3.move();
        }
        snowflake4.draw();
        if (!snowflake4.mouseOver() || !hIsHeld) {
            snowflake4.move();
        }
    }

    /** 
     * Whenever the mouse is clicked, checks if all bubbles can be popped and does
     */
    public void mousePressed() {
        bubble1.popBubble();
        bubble2.popBubble();
        bubble3.popBubble();
        bubble4.popBubble();
    }

    public void keyPressed() {
        if (keyCode == 32){ 
            ball1.launch();
            ball2.launch();
            ball3.launch();
            ball4.launch();
        }
        if (keyCode == 67){
            ball1.changeColor();
            ball2.changeColor();
            ball3.changeColor();
            ball4.changeColor();
        }
        if (keyCode == 72) {
            hIsHeld = true;
        }
    }

    public void keyReleased() {
        if (keyCode == 72) {
            hIsHeld = false;
        }
    }

    /** All processing sketches have to use this main method. Don't touch this! */
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
