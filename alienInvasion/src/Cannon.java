import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Cannon {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int barrelx;
	private int barrely;
	private int barrelw = 40;
	private int barrelh = 100;
	
	private double angle;
	
	private Image barrel = new Image("GunBarrel.png");
	private double rotspeed;

	public Cannon(int x, int y, int width, int height, double angle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	public Cannon(double angle) {
		x = 800;
		y = 700;
		this.angle = angle;
		width = 800;
		height = 800;
	}
	
	public void draw(GraphicsContext gc) {
		//This code should work. Not quite sure why the angle value needed to be manipulated for it to work, 
		//but Mr. Sinnot's code by itself was still not working on my comp -mAdI
		gc.save();
		gc.translate(x, y);
		gc.rotate(-angle+90);
		//gc.setFill(Color.BLACK);
		gc.drawImage(barrel, -barrelw/2, -barrelh/1.45, barrelw, barrelh);
		//gc.rect(x, y, -barrelw/2, -barrelh/1.45);
		gc.restore();
	}
	
	public Missile shoot() {
		double diameter = 10;
		double speed = 10;
		return new Missile(barrelx, barrely, diameter, angle, speed);
		
	}
	void rotate(double rotangle)
	{
		angle += rotangle;
	}
	public void setRotationSpeed(double rotspeed)
	{
		this.rotspeed = rotspeed;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getbX() {
		return barrelx;
	}

	public int getbY() {
		return barrely;
	}
	
	public int getWidth() {
		return width;
	}

	public int getLength() {
		return height;
	}

	public double getAngle() {
		return (angle);
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}