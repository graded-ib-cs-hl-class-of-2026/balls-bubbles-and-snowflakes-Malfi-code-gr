class Bubble {

    private Sketch s;
    private float radius;
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private int fillColor;
    private int borderColor;

    public Bubble(Sketch sketch) {
        s = sketch;
        radius = (int)((Math.random()*80)+20);
        x = (int)(Math.random()*s.width);
        y = s.height + radius;
        xSpeed = 2;
        ySpeed = -1;
        fillColor = s.color(255,255,255,0);
        borderColor = s.color(0, 0, 0);
    }

    public Bubble(Sketch sketch, float radius, float x, float y, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
        fillColor = s.color(255,255,255,0);
        borderColor = s.color(0, 0, 0);
    }
    // accessors for the radius, diameter, x, and y values 
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

    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius*2);
    }

    public void move() {
        x = x + xSpeed;
        y = y + ySpeed;
        if (x > s.width - radius) {
            x = radius;
            y = s.height;
        } else if(x < radius) {
            x = s.width - radius;
        } else if (y > s.height - radius) {
            y = radius;
        } else if (y < radius) {
            y = s.height - radius;
        }

        // x = x + xSpeed;
        // y = y + ySpeed;
        // if (x > s.width - radius) {
        //     radius = (float)(Math.random()*40)+10; // random radius between 10 and 50
        //     x = (float)(Math.random()*s.width); // places at a random location x. Doesn't account for being placed inside a wall, but in such case, it just repeats on the next frame (this whole process is invisible since it's still below the screen and won't cause flashes)
        //     y = s.height + radius; // places bellow the bottom of the screen
        //     xSpeed = (float)((Math.random()-0.5)*5); // assigns a random xSpeed between -2.5 and 2.5
        //     ySpeed = -((float)(Math.random()*6)+2); // random ySpeed between 2 and 8
        // } else if(x < radius) {
        //     radius = (float)(Math.random()*90)+10;
        //     x = (float)(Math.random()*s.width);
        //     y = s.height + radius;
        //     xSpeed = (float)((Math.random()-0.5)*5);
        //     ySpeed = -((float)(Math.random()*6)+2);
        // } else if (y < radius) {
        //     radius = (float)(Math.random()*90)+10;
        //     y = s.height + radius;
        //     xSpeed = (float)((Math.random()-0.5)*5);
        //     ySpeed = -((float)(Math.random()*6)+2);
        // }
    }

}