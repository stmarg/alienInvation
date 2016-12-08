import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tsinn.ui.SimpleApp;
import java.util.ArrayList;

public class CharlieGame extends SimpleApp
{
	
	double count = 0;
	double limit = 0;
	boolean paused = false;
	
	Building[] buildings = new Building[16];
	
	ArrayList <Missile> missiles = new ArrayList<> ();
	
	ArrayList <Ship> ships = new ArrayList<> ();
	
	Cannon C = new Cannon(700, 800, 100, 100, 90);
	
	int lives = 10;
	
	
	public void onMouseMove(MouseEvent me)
	{
		double radian = Math.atan2(C.getY()-me.getY(), me.getX()-C.getX());
		double degree = radian*180/3.1416;
		C.setAngle(degree);
	}
	
	public void onKeyPressed(KeyEvent ke)
	{
		/*if (ke.isControlDown() == true)
		{
			C.setAngle(C.getAngle() + 22.5);
			//this.angle = this.angle + 22.5;
		}
		
		if (ke.isAltDown() == true)
		{
			C.setAngle(C.getAngle() - 22.5);
			//this.angle = this.angle - 22.5;
		}*/
		
		if (ke.isShiftDown() == true && paused == false)
		{
				
			missiles.add(new Missile(C.getX(), C.getY(), 50, C.getAngle(), 2, Color.BLACK));
			//missiles.add(C.shoot());

		}
		
		if (ke.isControlDown() == true)
		{
			if (paused == false)
			{
				paused = true;
			} else
			{
				paused = false;
			}
			
		}
		
	}
	
	@Override
	public void updateAnimation(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		if (!paused)
		{
		
		count++;
		
		if (count >= limit)
		{
			ships.add(new Ship((int)(this.getWidth() * Math.random()), 0, 100, 100));
			limit = limit + (int)((100 - count/30) * Math.random());
		}
		
		gc.fillText("Lives: " + lives, 50, 50);
		
		C.draw(gc);
			
			for (int k = 0; k < ships.size(); k++)
			{
				Ship s = ships.get(k);
				
				for (int i = 0; i < missiles.size(); i++)
				{
					Missile m = missiles.get(i);
					
					//m.fall(.05);
					//m.wallbounce(0, getWidth());
					
					if (m.getyPos() < -m.getDiameter())
					{
						missiles.remove(m);
						i--;
					}
				
					if (m.isActive())
					{
						m.draw(gc);
			
						if (m.didHit(s))
						{
						m.setInactive(true);
						
							ships.remove(k);
					
							break;
						}
					}
				}
				
				for (int j = 0; j < buildings.length; j++)
				{
					if (s.didHit(buildings[j]))
					{
						ships.remove(k);
						buildings[j].setAlive(false);
						lives--;
					}
				}	
		}
		
		if (lives <= 0)
		{
			gc.fillText("GAME OVER", this.getWidth()/2, this.getHeight()/2);
		}
		
		for (Ship s : ships)
		{
			s.draw(gc);
			s.move();
		}
		
		for (int i = 1; i < buildings.length; i++)
		{
			buildings[i].draw(gc);
		}
		
		}
		else
		{
			gc.fillText("PAUSED", getWidth()/2, getHeight()/2);
		}
	}	

	@Override
	public void setupApp(GraphicsContext arg0) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < buildings.length; i++)
		{
			buildings[i] = new Building(getWidth() * i / buildings.length, getHeight() - 50, 50, Color.BLACK, Color.BURLYWOOD);
		}
		
	}
	
	public static void main (String [] args)
	{
		launch();
	}
	
}