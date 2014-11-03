import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.event.*;

public class BreakOut extends GraphicsProgram{

	private GRect myBend;
	private Ball myBall;
	private boolean isBallRunning = false;
	private int myScore = 0;
	private static final int CIRCLE = 20;
	private static final int BEND_WIDTH = 100;
	private static final int BEND_HEIGHT = 30;
	private static final int BRICK_WIDTH = 120;
	private static final int BRICK_HEIGHT = 40;
	public void run()
	{	
		addMouseListeners();
		addKeyListeners();
		gameStart();	
		myBall = new Ball(CIRCLE,CIRCLE);
		myBall.setFilled(true);
		myBall.setColor(Color.BLACK);
		myBend = new GRect(BEND_WIDTH,BEND_HEIGHT);
		add(myBend,getWidth()/2 - myBend.getWidth()/2,getHeight() - myBend.getHeight() - 10);
		add(myBall,getWidth()/2 - myBall.getWidth()/2,getHeight() - myBend.getHeight() - 10 - myBall.getHeight());
		waitForClick();
		isBallRunning = true;
		while(isBallRunning == true)
		{
			moveBall();
			addScore();
			isWall();
			pause(50);
		}
	}
	private void gameStart()  
	{
		for(int i = 0;i < 4;i++)
		{
			for(int j = 0;j < getWidth() / BRICK_WIDTH;j++)
				add(new GRect(BRICK_WIDTH,BRICK_HEIGHT),j * BRICK_WIDTH,i * BRICK_HEIGHT);
		}
	}
	
	private void moveBall()
	{
		myBall.moveNow();
	}
	private void isWall()
	{
		if(myBall.getX() <= 0)
		{
			if(myBall.getDirection() == 2)myBall.changeDirectionToFirst();
			else myBall.changeDirectionToForth();
		}
		else if(myBall.getX() + myBall.getWidth() >= getWidth())
		{
			if(myBall.getDirection() == 1)myBall.changeDirectionToSecond();
			else myBall.changeDirectionToThird();
		}
		else if(myBall.getY() <= 0)
		{
			if(myBall.getDirection() == 1)myBall.changeDirectionToForth();
			else myBall.changeDirectionToThird();
		}
		else if(myBall.getY() + myBall.getHeight() >= getHeight())
		{
			isBallRunning = false;
		}
	}
	private void addScore()
	{
		double tmpx1 = myBall.getX();
		double tmpx2 = tmpx1 + myBall.getWidth();
		double tmpy1 = myBall.getY();
		double tmpy2 = tmpy1 + myBall.getWidth();
		if(isABrick(tmpx1,tmpy1) != false)
		{
			bang(myBall.getDirection(),1);//1代表水平方向，2代表竖直方向
			myScore++;
			remove(getElementAt(tmpx1,tmpy1));
		}
		else if(isABrick(tmpx1,tmpy2) != false)
		{
			bang(myBall.getDirection(),2);
			myScore++;
			remove(getElementAt(tmpx1,tmpy2));
		}
		else if(isABrick(tmpx2,tmpy1) != false)
		{
			bang(myBall.getDirection(),2);
			myScore++;
			remove(getElementAt(tmpx2,tmpy1));
		}
		else if(isABrick(tmpx2,tmpy2) != false)
		{
			bang(myBall.getDirection(),1);
			myScore++;
			remove(getElementAt(tmpx2,tmpy2));
		}
		
		if(isABend(tmpx2,tmpy2) != false)
		{
			bang(myBall.getDirection(),1);
		}
	}
	
	private boolean isABend(double x,double y)
	{
		if(getElementAt(x,y) != null)
		{
			if(getElementAt(x,y).getWidth() == BEND_WIDTH)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean isABrick(double x,double y)
	{
		if(getElementAt(x,y) != null)
		{
			if(getElementAt(x,y).getWidth() == BRICK_WIDTH)
			{
				return true;
			}
		}
		return false;
	}
	
	private void bang(int direction,int target) //接收现有方向，根据需要往哪个方向转向给出运算
	{
		switch(direction)
		{
			case 1 : if(target == 1)
					 	myBall.changeDirectionToForth();
					 else myBall.changeDirectionToSecond();
				     break;
			case 2 : if(target == 1)
			 			myBall.changeDirectionToThird();
			 		 else myBall.changeDirectionToFirst();
		     		 break;
			case 3 : if(target == 1)
	 					myBall.changeDirectionToSecond();
	 		 		 else myBall.changeDirectionToForth();
     		 		 break;
			case 4 : if(target == 1)
	 					myBall.changeDirectionToFirst();
	 		 		 else myBall.changeDirectionToThird();
					 break;
		}
	}
	
	public void mouseMoved(MouseEvent a)
	{
		if(a.getX() + myBend.getWidth() >= getWidth())
			return;
		else if(isBallRunning == false)
		{
			myBend.setLocation(a.getX(), myBend.getY());
			myBall.setLocation(myBend.getX() + myBend.getWidth()/2 - myBall.getWidth()/2,myBend.getY() - myBall.getHeight());
		}
		else myBend.setLocation(a.getX(), myBend.getY());
	}
}
