package CodeDay;


import java.awt.*;

public class LifeBar {
	int x, y;  //coordinates of Image of empty bar
	Image bar;  //picture that represents the bar (empty)
	double currLength;
	double maxLength;
	
	public LifeBar(int xCoord, int yCoord){
		x = xCoord;
		y = yCoord;
		maxLength = 400;
		currLength = maxLength;
	}
	
	public void updateLength(Character player){
		currLength = player.currentLife/player.maxLife * maxLength - 10;
	}
}
