package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AnimatedSprite {
	/**
	 * This constant is for looping the animation backwards, so it would be animated, if the frames
	 * were "1,2,3" like this, "1,2,3,2,1" etc.
	 */
	public static final int LOOP_REVERSE = 2;
	/**
	 * This constant is for looping the animation to the beggining, so it would be animated, if the frames
	 * were "1,2,3" like this, "1,2,1,2,3" etc.
	 */
	public static final int LOOP_BEGINNING = 25;
	/**
	 * The loop method, will be one of the above
	 */
	private int loopMethod = LOOP_REVERSE;
	/**
	 * Up is true, Down is false
	 */
	private boolean loopDir = true;
	
	public static final int LOOP_ONCE = 3;
	
	private long interval = 100;
	
	/**
	left
	 */
	private double x;
	private double y;
	private boolean done;
	
	/**
	 * This holds all the frames, named by a string key;
	 * for instance, you could enter the key 'walk' which would return 
	 * a frameset, frameset-walk-right, frameset-walk-left... etc.
	 */
	private HashMap<String, int[][]> frames;
	
	/**
	 * All the images of this AnimatedSprite
	 */
	private BufferedImage [] FrameSet;
	private BufferedImage [][] sprite_left;
	private BufferedImage [][] sprite_right;
	private BufferedImage [][] sprite_special;
	private BufferedImage [][][] spritesheets;
	/**
	 * The current 'state' or animation that the sprite is in
	 */
	private String currentAnim;
	
	/**
	 * The current frame being rendered, it's not the number of the frame, its the key for the array
	 * as in it's this number "frameset[currentframe] = 1;" not this number "frameset[0] = currentframe;"
	 */
	private int currentFrame;
	
	/**
	 * The current set of frames being used
	 */
	private int[] currentFrameSet;
	
	private long lastframetime;
	
	public AnimatedSprite(BufferedImage left, BufferedImage  right, BufferedImage  special, int x, int y) {
		BufferedImage img = ImageUtil.makeColorTransparent(left, new Color (0,0,0));
		this.sprite_left = ImageUtil.splitImage(img, 10, 7);
		img = ImageUtil.makeColorTransparent(right, new Color (0,0,0));
		this.sprite_right = ImageUtil.splitImage(img, 10, 7);
		img = ImageUtil.makeColorTransparent(special, new Color (0,0,0));
		this.sprite_special = ImageUtil.splitImage(img, 10, 7);
		this.spritesheets = new BufferedImage [][][]{ sprite_left, sprite_right, sprite_special };
		frames = new HashMap<String, int[][]>();
		this.x = x;
		this.y = y;
		lastframetime= 100;

	}

	/**
	 * Lets you add a new animation set to the Sprite
	 * 
	 * @param name The name of the Sprite, this name will be used in setting the animation
	 * @param set The frames of the animation
	 */
	public void addNewAnimation(String name, int[][] set) {
		frames.put(name, set);
			
		setAnimation(name);
	}
	
	/**
	 * Draws the current frame of the sprite
	 * @param g
	 */
	public void draw(Graphics2D g, int x , int y, long delta) {
		// Erase the last run
	
	//g.clearRect((int) lastX-1, (int) lastY-1, getWidth()+1, getHeight()+1);

		// Now we need to get the current frame, from the current frameset
		int imgNum = currentFrame;
		g.drawImage(FrameSet[imgNum], null, (int) x, (int) y);
        interval = delta*10;
		// Increment the current frame
		if(System.currentTimeMillis() - lastframetime > interval)
		{
		if (currentFrame == FrameSet.length - 1) {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame = 0;
			} else if(loopMethod == LOOP_ONCE){
				done = true;
			}
			else{
				loopDir = false;
				currentFrame--;
			}
		} else {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame++;
			} else if(loopMethod == LOOP_ONCE){
				currentFrame++;
				done = false;
			}
			else{
				if(currentFrame == 0) {
					loopDir = true;
				}
				if (loopDir) {
					currentFrame++;
				}
				if (!loopDir) {
					currentFrame--;
				}
			}
		}
    }
		else
		{
			return;
		}
		lastframetime= System.currentTimeMillis();
	}
	
	/**
	 * Sets the current animation of the Sprite
	 * @param name
	 */
	public void setAnimation(String name) {
		if(currentAnim == name)
			return;
		if(frames.containsKey(name)) {
		if(name == "normal_Left" || name == "walk_Left" || name == "jump_Left" || name == "atack_Left")
		{
			if(name == "atack_Left")
				this.loopMethod = LOOP_ONCE;
			else
				this.loopMethod = LOOP_REVERSE;
			FrameSet = ImageUtil.GetCurrentFrameSet(spritesheets[0], frames.get(name));
		}
			
		else if(name == "normal_Right" || name == "walk_Right" || name == "jump_Right" || name == "atack_Right")
		{
			if(name == "atack_Right")
				this.loopMethod = LOOP_ONCE;
			else
				this.loopMethod = LOOP_REVERSE;
			FrameSet = ImageUtil.GetCurrentFrameSet(spritesheets[1], frames.get(name));
		}
			
		else if(name == "_Special")
			FrameSet = ImageUtil.GetCurrentFrameSet(spritesheets[2], frames.get(name));
		
		currentAnim = name;
		currentFrame = 0;
		}
	}
	
	/**
	 * Sets how the Sprite cycles through the animations.
	 * @param method  One of the constants LOOP_REVERSE or LOOP_BEGINNING
	 * 		LOOP_BEGINNING: Will start over when loop reaches the end
	 * 		LOOP_REVERSE: Will start backwards when loop is over
	 */
	public void setLoopMethod(int method) {
		if(method == LOOP_REVERSE || method == LOOP_BEGINNING) {
		this.loopMethod = method;
		}
	}
	
	public boolean getDone()
	{
		return done;
	}
	
	/**
	 * Sets the location of the sprite, it's upper left corner will be at the
	 * specified position
	 * @param x
	 * @param y
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the X coordinate
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Set the X coordinate
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Get the Y coordinate
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Set the Y coordinate
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Gets the name of the current animation
	 * @return
	 */
	public String getCurrentAnim() {
		return currentAnim;
	}
}