class Ball {

    /*
     * In Processing, the Sketch works a lot like the World in the turtles. You
     * can't
     * draw a ball without knowing which sketch to draw it on, so you always need to
     * specify the sketch for a Ball object.
     */

    /** The sketch that the ball is inside */
    private Sketch s;
    /** the radius of the ball */
    private float radius;
    private float x;
    private float y;
    /** The number of pixels the ball moves right per frame */
    private float xSpeed;
    /** The number of pixels the ball moves down per frame */
    private float ySpeed;
    /** The color of the inside of the ball */
    private int fillColor;
    /** The color of the outside of the ball */
    private int borderColor;

    private boolean isGrabbed = false;
    private float lastMouseX, lastMouseY;
    private float distanceMouseX, distanceMouseY;


    /** Empty constructor assigns random values */
    public Ball(Sketch sketch) {
        s = sketch;
        radius = s.random(20,50);
        x = s.random(radius, s.width - radius);
        y = s.random(radius,s.height/2);
        // random speed between -5 and -1 or 1 and 5
        xSpeed = s.random(1,5)*(s.random(0,1) < 0.5 ? -1 : 1);
        ySpeed = 0;
        fillColor = s.color(s.random(0,255), s.random(0,255), s.random(0,255));
        borderColor = s.color(s.random(0,255), s.random(0,255), s.random(0,255));
    }

    /** Fully specified constructor to allow changes to size, position, speed */
    /** Does NOT allow changing color! Need to use setColors() for that. */
    public Ball(Sketch sketch, float radius, float x, float y, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
        fillColor = s.color((int)(Math.random()*255),(int)(Math.random()*255), (int)(Math.random()*255));
        borderColor = s.color(0, 0, 0);
    }

    // Accessors (getters) go here

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

    // Setters that you need go here - by default, only colors

    public void setColors(int fill, int border) {
        borderColor = border;
        fillColor = fill;
    }

    /**
     * Draws the ball on the given sketch
     */
    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius * 2);
    }

    /**
     * Moves the ball so that the next time it draws it will be in a different place
     */
    public void move() {
        if (isGrabbed) {
            x = s.mouseX + distanceMouseX;
            y = s.mouseY + distanceMouseY;
        } else {
            x += xSpeed;
            y += ySpeed;
            if (x > s.width - radius) {
                x = (s.width - radius);
                xSpeed = -xSpeed * 0.9f;
            }
            if (x < radius) {
                x = (radius);
                xSpeed = -xSpeed * 0.9f;
            }
            if (y > s.height - radius) {
                y = s.height - radius;
                ySpeed = -(ySpeed) * 0.95f; // damping
            }
            if (y < radius) {
                y = radius;
                ySpeed = -(ySpeed) * 0.95f;
            }
            ySpeed += 1; // gravity
            if (ySpeed > 50) { // terminal velocity
                ySpeed = 50;
            }
        }
    }

    // public void debug(String parameter) {
    //     System.out.println(parameter + " = " + this.parameter);
    // }

    public void collidesWith(Ball b) {
        if (Sketch.dist(this.x,this.y,b.x,b.y) < this.radius + b.radius) {
            // move them back to where they're just touching
            float distance = Sketch.dist(this.x,this.y,b.x,b.y); // distance between centers
            float overlap = (this.radius + b.radius) - distance; // how much they're overlapping
            float dx = b.x - this.x; // x distance between centers
            float dy = b.y - this.y; // y distance between centers
            float angle = Sketch.atan2(dy,dx); // angle between centers
            this.x -= Sketch.cos(angle) * overlap / 2; // move this one back half the overlap in accordance with the angle
            this.y -= Sketch.sin(angle) * overlap / 2; // same
            b.x += Sketch.cos(angle) * overlap / 2; // move the other one back half the overlap in accordance with the angle
            b.y += Sketch.sin(angle) * overlap / 2; // same
            // since i already had to figure out the angle of collision to "reset" the balls backwards, the rest of the physics was a lot easier to figure out
            // handle collisions with balls bouncing with xSpeed and ySpeed updating in accordance with the angle
            // finds the total speed of each ball (not just horizontal or vertical)
            float thisVelocity = Sketch.sqrt(this.xSpeed * this.xSpeed + this.ySpeed * this.ySpeed);
            float bVelocity = Sketch.sqrt(b.xSpeed * b.xSpeed + b.ySpeed * b.ySpeed);
            // finds the direction (vector angle) of each ball. used to project the velocity onto the line of collision
            float thisVectorAngle = (float) Math.atan2(this.ySpeed, this.xSpeed);
            float bVectorAngle = (float) Math.atan2(b.ySpeed, b.xSpeed);
            // projects each ball’s velocity onto the line of collision to see how much of each goes into where
            float thisProjectedVelocity = thisVelocity * (float) Sketch.cos(thisVectorAngle - angle);
            float bProjectedVelocity = bVelocity * (float) Sketch.cos(bVectorAngle - angle);
            // swap the projected velocities to simulate an elastic collision along the line of impact
            float tempVelocity = thisProjectedVelocity;
            thisProjectedVelocity = bProjectedVelocity;
            bProjectedVelocity = tempVelocity;
            // find the perpendicular velocities to the line of impact. thisVectorAngle - angle gives the angle between this ball's velocity vector and the collision line
            // when projecting a vector onto a line at an angle, we use cos to get the component along that line. the perpendicular component is obtained by taking the sin of the angle difference.
            float thisPerpendicularVelocity = thisVelocity * Sketch.sin(thisVectorAngle - angle);
            float bPerpendicularVelocity = bVelocity * Sketch.sin(bVectorAngle - angle);
            // update the xSpeed and ySpeed of each ball based on the new velocities
            // also adds the perpendicular velocities back in. the reason for angle + pi/2 is because the perpendicular velocity is 90 degrees off from the projected velocity
            this.xSpeed = Sketch.cos(angle) * thisProjectedVelocity + Sketch.cos(angle + Sketch.PI / 2) * thisPerpendicularVelocity;
            this.ySpeed = Sketch.sin(angle) * thisProjectedVelocity + Sketch.sin(angle + Sketch.PI / 2) * thisPerpendicularVelocity;
            b.xSpeed = Sketch.cos(angle) * bProjectedVelocity + Sketch.cos(angle + Sketch.PI / 2) * bPerpendicularVelocity;
            b.ySpeed = Sketch.sin(angle) * bProjectedVelocity + Sketch.sin(angle + Sketch.PI / 2) * bPerpendicularVelocity;
        }
    }

    public void launch() {
        xSpeed = s.random(1,5)*(s.random(0,1) < 0.5 ? -1 : 1);
        ySpeed += s.random(20,40)*(s.random(0,1) < 0.5 ? -1 : 1);
    }

    public void updateGrabStatus(float mouseX,float mouseY) {
        distanceMouseX = x - mouseX;
        distanceMouseY = y - mouseY;
        if (s.mousePressed && mouseOver()) {
            isGrabbed = true;
            lastMouseX = mouseX;
            lastMouseY = mouseY;
            xSpeed = 0; // Stop ball movement while grabbed
            ySpeed = 0;
        } else if (!s.mousePressed && isGrabbed) {
            // On mouse release, calculate throw speed
            xSpeed = (mouseX - lastMouseX) / 6.0f; // Adjusted divisor for smoother throw
            ySpeed = (mouseY - lastMouseY) / 6.0f;
            isGrabbed = false;
        }
    }
    
    public void changeColor() {
        fillColor = s.color(s.random(0,255),s.random(0,255),s.random(0,255));
        borderColor = s.color(s.random(0,255),s.random(0,255),s.random(0,255));
    }

    public boolean mouseOver() {
        return (Sketch.dist(s.mouseX,s.mouseY,x,y) < radius);
    }
}