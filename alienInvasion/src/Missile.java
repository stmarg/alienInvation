import javafx.scene.canvas.GraphicsContext; 
/**
 * The Missile class contains represents the idea of a missile in the Alien Invasion game. 
 * @author Charlie
 * @author Gavin
 *
 */
public class Missile
{
	private double xPos, yPos, diameter, angle, speed, sx, sy;
	private boolean DidHit = false;
	
	public Missile (double x, double y, double setdiameter, double setangle, double setspeed)
	{
		
		xPos = x;
		yPos = y;
		diameter = setdiameter;
		angle = setangle;
		speed = setspeed;
		
		sx = speed * Math.cos(angle/180*3.1416);
		sy = -speed * Math.sin(angle/180*3.1416);
		
	}
	
	public Missile (double setangle)//takes the angle in degrees
	{
		this (800, 700, 50, setangle, 4);
	}
	
	
	public void draw(GraphicsContext gc)
	{	
			gc.fillOval(xPos, yPos, diameter, diameter);
	//}
	
	//public void shoot()
	//{
			
		//this.xPos = this.xPos + speed * Math.cos(angle/180*3.1416);
		//this.yPos = this.yPos - speed * Math.sin(angle/180*3.1416);
			xPos += sx;
			yPos += sy;
	}
	
	public void setPos(double x, double y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	
	public boolean getDidHit()
	{
		return DidHit;
	}
	
	public void setDidHit(boolean dh)
	{
		this.DidHit = dh;
	}
	
	public double getxPos()
	{
		return xPos;
	}
	
	public double getyPos()
	{
		return yPos;
	}
	
	public double getDiameter()
	{
		return diameter;
	}
	
}	