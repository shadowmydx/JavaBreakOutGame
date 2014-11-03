import acm.graphics.*;

public class Ball extends GOval{
	private int ySpeed 		= -20;
	private int xSpeed 		= -10;
	private int direction 	= 2;
	public Ball(int width,int height)
	{
		super(width,height);
	}
	
	public void moveNow()
	{
		move(xSpeed,ySpeed);
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	private void changeXspeed()
	{
		xSpeed = -xSpeed;
	}
	private void changeYspeed()
	{
		ySpeed = -ySpeed;
	}
	public void changeDirectionToFirst()
	{
		if(direction == 4)
			changeYspeed();
		else if(direction == 2)
			changeXspeed();
		direction = 1;
	}
	
	public void changeDirectionToSecond()
	{
		if(direction == 1)
			changeXspeed();
		else if(direction == 3)
			changeYspeed();
		direction = 2;
	}
	
	public void changeDirectionToThird()
	{
		if(direction == 2)
			changeYspeed();
		else if(direction == 4)
			changeXspeed();
		direction = 3;
	}
	
	public void changeDirectionToForth()
	{
		if(direction == 1)
			changeYspeed();
		else if(direction == 3)
			changeXspeed();
		direction = 4;
	}
}
