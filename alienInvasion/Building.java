import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Building {
	private int x;
	private int y;
	private int s;
	private int color;
	private boolean alive = true;

	public Building(int x, int y, int s) {
		this.x = x;
		this.y = y;
		this.s = s;
		this.color = color;
	}

	public void drawBuilding(GraphicsContext gc) {
		if (color == 1) {
			gc.setFill(Color.ORANGE);
			gc.fillRect(x, y, s, s);
			gc.fillRect(x + s / 6, y - s / 3, s / 1.8, s / 3);
			gc.fillRect(x + s / 4.2, y - s / 3 - s / 5, s / 4, s / 5);
			gc.setFill(Color.LIGHTYELLOW);
			gc.fillRect(x + s / 3, y + s / 4, s / 3, s - s / 4);
		}

		if (color == 2) {
			gc.setFill(Color.GREEN);
			gc.fillRect(x, y, s, s);
			gc.fillRect(x + s / 6, y - s / 3, s / 1.8, s / 3);
			gc.fillRect(x + s / 4.2, y - s / 3 - s / 5, s / 4, s / 5);
			gc.setFill(Color.LIGHTGREEN);
			gc.fillRect(x + s / 3, y + s / 4, s / 3, s - s / 4);
		}
	}

	public boolean checkHit(Spaceship s) {
		// if (this.x > x + s && this.x + this.s < x && this.y < y + s && alive
		// == true) {
		// return true;
		// }

		return false;
	}

	public boolean CheckHit(Spaceship s) {
		System.out.println("Hit");
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}