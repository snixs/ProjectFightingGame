package Game;


/**
 * An entity which represents one of our space invader aliens.
 * 
 * @author Kevin Glass
 */
public class EnemyEntity extends Entity {
	/** The game in which the entity exists */
	private Game game;
	
	/**
	 * Create a new alien entity
	 * 
	 * @param game The game in which this entity is being created
	 * @param ref The sprite which should be displayed for this alien
	 * @param x The intial x location of this alien
	 * @param y The intial y location of this alient
	 */
	public EnemyEntity(Game game,String urlleft, String urlright, String urlspecial, int x, int y) {
		super(urlleft, urlright, urlspecial, x, y);
		s.setAnimation("normal_Left");
		this.game = game;
		
	}

	/**
	 * Request that this alien moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// if we're moving left and have reached the left hand side
				// of the screen, don't move

				if ((dx < 0) && (x < 10)) {
					return;
				}
				// if we're moving right and have reached the right hand side
				// of the screen, don't move

				if ((dx > 0) && (x > 750)) {
					return;
				}
				
				if ((dy < 0) && (y < 500)){
					return;
				}
				
				if((dy > 0) && (y > 200)){
					return;
				}
				
				super.move(delta);
	}
	
	/**
	 * Update the game logic related to aliens
	 */
	public void doLogic() {
		// swap over horizontal movement and move down the

		// screen a bit

//		dx = -dx;
	//	y += 10;
		
		// if we've reached the bottom of the screen then the player

		// dies

	}
	
}