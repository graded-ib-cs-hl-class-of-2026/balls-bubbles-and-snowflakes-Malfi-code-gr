class Bubble {

    /** The sketch that the bubble is inside */
    private Sketch s;
    /** the radius of the ball */
    private float radius;
    /** the x coordinate of the ball */
    private float x;
    /** the y coordinate of the ball */
    private float y;
    /** the speed of the ball in the x direction */
    private float xSpeed;
    /** the speed of the ball in the y direction */
    private float ySpeed;
    /** the color of the ball */
    private int fillColor;
    /** the color of the border of the ball */
    private int borderColor;

    /** Empty constructor assigns some random values and some fixed */
    public Bubble(Sketch sketch) {
        s = sketch;
        radius = (int)((Math.random()*80)+20);
        x = (int)(Math.random()*s.width);
        y = s.height + radius;
        xSpeed = 2;
        ySpeed = -1;
        fillColor = s.color(0,0,255,50);
        borderColor = s.color(0, 0, 0);
    }

    /** Constructor that assigns all values except color */
    public Bubble(Sketch sketch, float radius, float x, float y, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
        fillColor = s.color(0,0,100,50);
        borderColor = s.color(0, 0, 0);
    }

    /** draws the bubble on the given sketch */
    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius*2);
    }

    /** moves the ball so that the next time it draws it will be in a different place */
    public void move() {
        x = x + xSpeed;
        y = y + ySpeed;
        if (x > s.width - radius) {
            radius = (float)(Math.random()*40)+10; // random radius between 10 and 50
            x = (float)(Math.random()*s.width); // places at a random location x. Doesn't account for being placed inside a wall, but in such case, it just repeats on the next frame (this whole process is invisible since it's still below the screen and won't cause flashes)
            y = s.height + radius; // places bellow the bottom of the screen
            xSpeed = (float)((Math.random()-0.5)*5); // assigns a random xSpeed between -2.5 and 2.5
            ySpeed = -((float)(Math.random()*6)+2); // random ySpeed between -2 and -8
        } else if(x < radius) {
            radius = (float)(Math.random()*90)+10;
            x = (float)(Math.random()*s.width);
            y = s.height + radius;
            xSpeed = (float)((Math.random()-0.5)*5);
            ySpeed = -((float)(Math.random()*6)+2);
        } else if (y < radius) {
            radius = (float)(Math.random()*90)+10;
            y = s.height + radius;
            xSpeed = (float)((Math.random()-0.5)*5);
            ySpeed = -((float)(Math.random()*6)+2);
        }
    }

    /** Returns true if the mouse is over the bubble */
    public boolean mouseOver() {
        return (Sketch.dist(s.mouseX,s.mouseY,x,y) < radius);
    }


    /**
     * Called whenever the mouse is clicked through bubble[num].popBubble(). If the mouse is over the bubble, it pops (resets position back to past the bottom of the screen and random x value)
     */
    public void popBubble(){
        if (mouseOver()) {
            x = (s.random(radius,s.width-radius));
            y = s.height + radius;
        }
    }

    // accessors
    public float getRadius() {
        return radius;
    }

    public float getDiameter() {
        return radius * 2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public float getXSpeed() {
        return xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public int getFillColor() {
        return fillColor;
    }

    public int getBorderColor() {
        return borderColor;
    }
}