package Game;

/**
 * An entity representing a shot fired by the player's ship
 * 
 * @author Kevin Glass
 */
public class ShotEntity extends Entity {
	/** The vertical speed at which the players shot moves */
	private double moveSpeed = -300;
	/** The game in which this entity exists */
	private Game game;
	/**
	 * Create a new shot from the player
	 * 
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public ShotEntity(Game game,String urlleft, String urlright, String urlspecial, int x, int y) {
		super(urlleft, urlright, urlspecial, x, y);
		
		this.game = game;
		
		dy = moveSpeed;
	}

	/**
	 * Request that this shot moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// proceed with normal move

		super.move(delta);
		
		// if we shot off the screen, remove ourselfs

		if (y < -100) {
			game.removeEntity(this);
		}

	}
}
