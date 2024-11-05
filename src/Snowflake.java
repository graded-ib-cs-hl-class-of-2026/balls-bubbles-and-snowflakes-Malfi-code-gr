class Snowflake {
    private Sketch s;
    private float radius;
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private int borderColor;

    public Snowflake(Sketch sketch) {
        s = sketch;
        radius = (int)(Math.random()*40)+10;
        x = s.width/2;
        y = -radius;
        xSpeed = (float)(Math.random()-0.5)*2;
        ySpeed = (float)(Math.random()*8.0+2);
        borderColor = s.color(245,245,255);
    }

    public Snowflake(Sketch sketch, float radius, float x, float y, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
        borderColor = s.color(245,245,255);
    }

    public void draw() {
        s.stroke(borderColor);
        s.line(x+radius,y,x-radius,y);
        s.line(x,y+radius,x,y-radius);
        s.line((float)(x+radius*.707),(float)(y+radius*.707),(float)(x-radius*.707),(float)(y-radius*.707));
        s.line((float)(x+radius*.707),(float)(y-radius*.707),(float)(x-radius*.707),(float)(y+radius*.707));
    }

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

    public boolean mouseOver() {
        return (Sketch.dist(s.mouseX,s.mouseY,x,y) < radius);
    }
}