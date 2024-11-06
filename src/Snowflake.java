class Snowflake {
    /** The sketch that the snowflake is inside */
    private Sketch s;
    /** the radius of the snowflake */
    private float radius;
    /** the x coordinate of the snowflake */
    private float x;
    /** the y coordinate of the snowflake */
    private float y;
    /** the speed of the snowflake in the x direction */
    private float xSpeed;
    /** the speed of the snowflake in the y direction */
    private float ySpeed;
    /** the color of the border of the snowflake */
    private int borderColor;

    /** Empty constructor assigns some random values */
    public Snowflake(Sketch sketch) {
        s = sketch;
        radius = (int)(Math.random()*40)+10;
        x = s.width/2;
        y = -radius;
        xSpeed = (float)(Math.random()-0.5)*2;
        ySpeed = (float)(Math.random()*8.0+2);
        borderColor = s.color(245,245,255);
    }

    /** Constructor that assigns all values except color */
    public Snowflake(Sketch sketch, float radius, float x, float y, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
        borderColor = s.color(245,245,255);
    }

    /** draws the snowflake on the given sketch */
    public void draw() {
        s.stroke(borderColor);
        s.line(x+radius,y,x-radius,y);
        s.line(x,y+radius,x,y-radius);
        s.line((float)(x+radius*.707),(float)(y+radius*.707),(float)(x-radius*.707),(float)(y-radius*.707));
        s.line((float)(x+radius*.707),(float)(y-radius*.707),(float)(x-radius*.707),(float)(y+radius*.707));
    }

    /** moves the snowflake so that the next time it draws it will be in a different place */
    public void move() {
        x += xSpeed;
        y += ySpeed;
        if (x > s.width + radius || x < -radius) {
            x = (float)(Math.random()*s.width);
            y = -radius;
        }
        if (y > s.height + radius) {
            x = (float)(Math.random()*s.width);
            y = -radius;
        }
    }

    /** returns true if the mouse is over the snowflake */
    public boolean mouseOver() {
        return (Sketch.dist(s.mouseX,s.mouseY,x,y) < radius);
    }

    // accessors go here

    public float getRadius() {
        return radius;
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

    public int getBorderColor() {
        return borderColor;
    }
}