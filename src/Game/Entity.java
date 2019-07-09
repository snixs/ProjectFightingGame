package Game;


import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * An entity represents any element that appears in the game. The
 * entity is responsible for resolving collisions and movement
 * based on a set of properties defined either by subclass or externally.
 * 
 * Note that doubles are used for positions. This may seem strange
 * given that pixels locations are integers. However, using double means
 * that an entity can move a partial pixel. It doesn't of course mean that
 * they will be display half way through a pixel but allows us not lose
 * accuracy as we move.
 * 
 * @author Kevin Glass
 */
public abstract class Entity {
	/** The bounds of the game */ 
	protected double topBound = 0 , lowBound = 301 , rightBound = 720 , leftBound = 0;
	/** The current x location of this entity */ 
	protected double x;
	/** The current y location of this entity */
	protected double y;
	/** The sprite that represents this entity */
	protected Sprite spritesheet_right;
	protected Sprite spritesheet_left;
	protected Sprite spritesheet_special;
	/** The current direction of this entity horizontally (pixels/sec) */
	protected double dx ;
	/** The current direction of this entity vertically (pixels/sec) */
	protected double dy ;
	/** The rectangle used for this entity during collisions  resolution */
	protected double dj = -500;
	/** collison detecion ractangle */
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();
	/** Animating opp class */
	protected AnimatedSprite s;
	
	protected double lastframe = 100;
	protected double interval = 1000;
	protected double moveSpeed = 80;
	
	protected boolean InAction = false;
	protected boolean InTouch = false;
	protected boolean InHit = false;
	protected boolean InJump = false;
	protected boolean InFall = false;
	protected boolean InStand = true;
	protected boolean JumpLooped = false;
	protected boolean CanJump = true;
	protected boolean Left = false;
	protected boolean Right = true;
	protected boolean JumpAct = false;
	protected boolean Atack = false;
	protected boolean AtackJump = false;
	protected double starjump = 100;
	protected Time t;
	protected double lastupdate;
	protected double updateint = 10;
	/**
	 * Construct a entity based on a sprite image and a location.
	 * 
	 * @param ref The reference to the image to be displayed for this entity
 	 * @param x The initial x location of this entity
	 * @param y The initial y location of this entity
	 */
	public Entity(String urlright, String urlleft, String urlspecial, int x, int y) {
		
		this.spritesheet_right = SpriteStore.get().getSprite(urlright);
		this.spritesheet_left = SpriteStore.get().getSprite(urlleft);
		this.spritesheet_special = SpriteStore.get().getSprite(urlspecial);
		s = new AnimatedSprite(spritesheet_left.getImage(), spritesheet_right.getImage(), spritesheet_special.getImage(), x, y);

		
		s.addNewAnimation("normal_Left", new int[][]{{0, 9}, {0, 8}, {0, 7}, {0, 6} });
		s.addNewAnimation("walk_Left", new int[][] { {0, 5}, {0, 4}, {0, 3}, {0, 2} });
		s.addNewAnimation("walk_Right", new int[][] { {0, 4}, {0, 5}, {0, 6}, {0, 7} });
		s.addNewAnimation("sjump_Right", new int [][] { {6, 0}, {6, 1} });
		s.addNewAnimation("sjump_Left", new int [][] { {6, 9}, {6, 8} });
		s.addNewAnimation("jump_Right", new int [][] { {6, 2}, {6, 2} });
		s.addNewAnimation("jump_Left", new int [][] { {6, 7}, {6, 7} });

		s.addNewAnimation("atack_Left", new int [][] { {1, 9}, {1, 8} });
		s.addNewAnimation("atack_Right", new int [][] { {1, 0}, {1, 1} });
		s.addNewAnimation("aJump_Left", new int [][] { {1, 4}, {1, 5} });		
		s.addNewAnimation("aJump_Right", new int [][] { {1, 5}, {1, 4} });
		
		s.addNewAnimation("normal_Right", new int[][] { {0, 0}, {0, 1}, {0, 2}, {0, 3} });
		AnimChange();
		this.x = x;
		this.y = y;
		t = new Time();
		t.setDelay(100);
				
	}
	
	/**
	 * Request that this entity move itself based on a certain ammount
	 * of time passing.
	 * 
	 * @param delta The ammount of time that has passed in milliseconds
	 */
	public void move(long delta) {
		// update the location of the entity based on move speeds

//		if(InAction)
	//	{

			x += (delta * dx) / 1000;
			y += (delta * dy) / 1000;
			checkGravity();
			AnimChange();
			
//		}
		
	}
	public void AnimChange()
	{
		 if(s.getDone())
			 InAction = false;
		 else
			 InAction = true;
		 
		if(InStand && !InAction && Left && !Atack && !AtackJump  && !InJump && !InFall)
			s.setAnimation("normal_Left");
		
		else if(Atack && Left && InStand && !InJump)
			s.setAnimation("atack_Left");
			
		else if(Atack && Right && InStand && !InJump)
			s.setAnimation("atack_Right");
		
		 if(InStand && !InAction && Right && !Atack && !AtackJump && !InJump && !InFall)
			s.setAnimation("normal_Right");
		
		else if(Left && !InJump && !InFall && !Atack  && !AtackJump && !InStand)
			s.setAnimation("walk_Left");
		
		else if(Right && !InJump && !InFall && !Atack && !AtackJump && !InStand)
			s.setAnimation("walk_Right");
		
		else if(InJump && Left)
			s.setAnimation("jump_Left");
		
		else if(InJump && Right)
			s.setAnimation("jump_Right");
		
		else if(InFall && Left)
			s.setAnimation("jump_Left");
		
		else if(InFall && Right)
			s.setAnimation("jump_Right");
		 
	}

		
  //System.out.println("stand-"+InStand+" left-"+Left+" right-"+Right);
			//InAction=true;
	public void Right()
	{
		InStand = false;
		Right = true;
		Left = false;
		Atack = false;
		AtackJump = false;
		AnimChange();
		if(InJump || InFall)
			return;
		dx = moveSpeed;
	}
	public void Left()
	{
		InStand = false;
		Atack = false;
		AtackJump = false;
		Right = false;
		Left = true;
		AnimChange();
		if(InJump || InFall)
			return;
		dx = -moveSpeed;
	}
	public void Run()
	{
		InStand = false;
		AnimChange();
		dx = -moveSpeed*2;
	}
	public void Stand()
	{
		if(InJump || InFall)
			return;
		InStand = true;
		AnimChange();
		dx = 0;
		
	}
	public void Punch()
	{
		Atack = true;
		InAction = true;
		AnimChange();
		System.out.println("atack = " + Atack+ " " +Right);
	}
	public boolean Jump()
	{
		if(!InFall && !InJump && CanJump)
		{
			InJump = true;
				dj = -300;
				dx = dx*2;

		}
		if(!InAction && System.currentTimeMillis() - lastframe > interval)
			CanJump = true;
			
		
		return InAction;
	}

	
	/**
	 * Draw this entity to the graphics context provided
	 * 
	 * @param g The graphics context on which to draw
	 */
	public void draw(Graphics2D g, long delta) {
		
		s.draw(g,(int) x,(int) y, delta);
	}
	
	/**
	 * Do the logic associated with this entity. This method
	 * will be called periodically based on game events
	 */
	public void doLogic() {
	}
	
	
	/**
	 * Check if this entity collised with another.
	 * 
	 * @param other The other entity to check collision against
	 * @return True if the entities collide with each other
	 */
	public boolean collidesWith(Entity other) {
	//	me.setBounds((int) x,(int) y,images[0].getWidth(),images[0].getHeight());
		//him.setBounds((int) other.x,(int) other.y,other.images[0].getWidth(),other.images[0].getHeight());
		if(me.intersects(him))
		{
		//	if(ImageUtil.isPixelCollide(x, y, images[0], other.x, other.y, other.images[0]))
			{
				return InTouch = true;
			}
	//		else{ return InTouch = false; } 
		}
		else{ return InTouch = false; }
		
	}

	public void checkGravity() {
		if (InJump) 
		{
			lastframe = System.currentTimeMillis();
			InAction = true;
			CanJump = false;
			InStand = false;
			InFall = false;

				 dj += 9.8;
			
			 if(dj >= 0)
			 {
				 InFall = true;
				 InJump = false;
			 }
		}
		
		if(InFall)
			 {
				 CanJump = false;

					 dj += 9.8;

				  if (collidesWithPlatform())
				  {
					  InFall = false;
					  InStand = true;
					  InJump = false;  
				  }
			 }
		
			 if(InStand)
			 {
				    InFall = false;
	                InStand = true;
	                InJump = false;
	                InAction = false;
	                dj = 0;
			 }	
			 
		
        dy = dj;
    }
	private boolean collidesWithPlatform()
	{
		if(y>lowBound)
		{
			return true;
		}
		else
			return false;
	}
}