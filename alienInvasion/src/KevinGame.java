import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tsinn.ui.SimpleApp;

public class KevinGame extends SimpleApp {
	private Cannon c;
	private Ship s1;

	private int score = 0;
	private int level = 1;
	private int ammo = 100;
	boolean fire = false;
	boolean addShip = false;

	//Ship[] ships = new Ship[3];
	Building[] buildings = new Building[4];
	ArrayList<Missile> bullets = new ArrayList<>();
	ArrayList<Ship> ships = new ArrayList<>();

	public static void main(String[] args) {
		launch();
	}

	public void updateAnimation(long arg0) {
		
	}

	public void draw(GraphicsContext gc) {
		for (Ship s : ships) {
			// Basic ship draw/move
			s.draw(gc);
			s.move();
			
			// If ship goes below screen
			if (s.getY() > getHeight()) {
				s.setY(-50);
				s.setX((int) (Math.random() * (getWidth() - getWidth() / 10) + getWidth() / 14));
				s.setOriginalx(s.getX());
				s.setSpeed((int) 1.5, ((int) (Math.random() * 4) + 3));
			}
			
			for (Building b : buildings) {
				b.draw(gc);
				
				// Check if ship1 hit building
				if (s.didHit(b) == true) {
					score = score + 100;
					gc.fillText("OUCH", getWidth()/2, getHeight()/2);
					ships.remove(s);
				}
				
				if (s1.didHit(b) == true) {
					score = score + 300;
					gc.fillText("OUCH", getWidth()/2, getHeight()/2);
					s1.setY(-100);
				}
			}
		}

		// Level 2 and beyond
		if (level >= 2) {
			s1.draw(gc);
			s1.move();
			s1.sChange();
			
			if (s1.getY() > getHeight()) {
				s1.setY(-50);
				s1.setX((int) (Math.random() * (getWidth() - getWidth() / 10) + getWidth() / 14));
				s1.setOriginalx(s1.getX());
				s1.setSpeed(1, ((int) (Math.random() * 4) + 3));
			}
		}
		
		// Score
		gc.fillText("Score: " + score + level, getWidth() - getWidth() / 8, getHeight() / 8);
		
		// Cannon
		c.draw(gc);
		//c.rotate(gc);

		// Drawing missile
		for (Missile m : bullets) {
			if (ammo != 0) {
				m.draw(gc);
			}
			
			// Remove missile if off screen
			if (m.getxPos() > getWidth() && m.getxPos() < 0 && m.getyPos() > getHeight() && m.getyPos() < 10) {
				bullets.remove(m);
			}
		}
		
		// Firing missiles
		if (fire == true) {
			gc.fillText("" + Math.floor(c.getAngle()), 100, 100);
			bullets.add(new Missile(c.getX() + c.getLength()/2, c.getY(), 10, c.getAngle(), 4));
			fire = false;
		}
		
		refillShip();
	}
	
	public void refillShip() {
		if (ships.size() == 0) {
			level = level + 1;
			addShip = true;
		}
		
		if (addShip == true) {
			for (int i = 0; i < level + 2; i++) {
				ships.add(new Ship((int) (Math.random() * (getWidth() - 100)) + 100, 50, 100, (int) (100 / 1.5)));
			}
			
			addShip = false;
		}
	}

	public void setupApp(GraphicsContext gc) {
		c = new Cannon(getWidth() / 2 - 20, getHeight() - 70, 40, 40, 90);
		
		ships.add(new Ship((int) (Math.random() * (getWidth() - 100)) + 100, 50, 100, (int) (100 / 1.5)));
		ships.add(new Ship((int) (Math.random() * (getWidth() - 100)) + 100, 50, 100, (int) (100 / 1.5)));
		ships.add(new Ship((int) (Math.random() * (getWidth() - 100)) + 100, 50, 100, (int) (100 / 1.5)));

		s1 = new Ship(getWidth() / 2, -50, 100, (int) (100 / 1.5), 2);

		for (int i = 0; i < buildings.length; i++) {
			buildings[i] = new Building((getWidth() / 4 + i * 300) - 110, getHeight() - 40, 40, Color.CORNFLOWERBLUE,
					Color.DARKBLUE);
		}
	}

	public void onKeyPressed(KeyEvent k) {
		/*if (k.getCode() == KeyCode.A && c.getAngle() < 180) {
			c.setAngle(c.getAngle() + 10);
		}

		if (k.getCode() == KeyCode.D && c.getAngle() > 0) {
			c.setAngle(c.getAngle() - 10);
		}
		*/
	}

	public void onMousePressed(MouseEvent m) {
		double radians =  Math.atan2(c.getY()-m.getY(), -c.getX()+m.getX());
		c.setAngle(Math.toDegrees(radians)); 
		ammo--;
		fire = true;
	}
}