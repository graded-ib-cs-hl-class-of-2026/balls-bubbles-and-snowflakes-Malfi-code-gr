import processing.core.PApplet;

public class Sketch extends PApplet {
    /** An instance of a ball */
    private Ball ball1, ball2, ball3, ball4;
    /** An instance of a bubble */
    private Bubble bubble1, bubble2, bubble3, bubble4;
    /** An instance of a snowflake */
    private Snowflake snowflake1, snowflake2, snowflake3, snowflake4;

    /** Whether the H key is held */
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
        ball1.collidesWith(ball2); // checks if the ball is colliding with every other ball
        ball1.collidesWith(ball3);
        ball1.collidesWith(ball4);
        ball1.draw();
        if (!ball1.mouseOver() || !hIsHeld) { // If the mouse is over the snowflake and H is held, don't move it
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
        if (!bubble1.mouseOver() || !hIsHeld) { // If the mouse is over the snowflake and H is held, don't move it
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
        if (!snowflake1.mouseOver() || !hIsHeld) { // If the mouse is over the snowflake and H is held, don't move it
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
        ball1.updateGrabStatus(mouseX, mouseY);
        ball2.updateGrabStatus(mouseX, mouseY);
        ball3.updateGrabStatus(mouseX, mouseY);
        ball4.updateGrabStatus(mouseX, mouseY);
    }

    /** Whenever the mouse is released, updates the grab status of each ball */
    public void mouseReleased() {
        ball1.updateGrabStatus(mouseX,mouseY);
        ball2.updateGrabStatus(mouseX,mouseY);
        ball3.updateGrabStatus(mouseX,mouseY);
        ball4.updateGrabStatus(mouseX,mouseY);
    }
    
    /** Whenever the mouse is dragged, updates each ball's position if it's currently grabbed */
    public void mouseDragged() {
        ball1.updateGrabbedPosition(mouseX, mouseY);
        ball2.updateGrabbedPosition(mouseX, mouseY);
        ball3.updateGrabbedPosition(mouseX, mouseY);
        ball4.updateGrabbedPosition(mouseX, mouseY);
    }

    /** Called whenever any key is pressed. Checks if they're certain keys to perform certain actions */
    public void keyPressed() {
        if (keyCode == 32){ // If space is pressed, launch all balls
            ball1.launch();
            ball2.launch();
            ball3.launch();
            ball4.launch();
        }
        if (keyCode == 67){ // If c is pressed, change the color of all balls
            ball1.changeColor();
            ball2.changeColor();
            ball3.changeColor();
            ball4.changeColor();
        }
        if (keyCode == 72) { // If h is pressed, set hIsHeld to true
            hIsHeld = true;
        }
    }

    /** Called whenever a key is released. Works similarly to keyPressed */
    public void keyReleased() { // If h is released, set hIsHeld to false
        if (keyCode == 72) {
            hIsHeld = false;
        }
    }

    /** All processing sketches have to use this main method. Don't touch this! */
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
