package CodeDay;

import java.awt.*;

import javax.swing.ImageIcon;

public class Character {
	Image charImage;  //this is the actual image representing the character right now
	int direction;  //1 is right, add 1 as you turn clockwise
	boolean isMoving;  //this determines whether we need to update the sprite to moving
	int charImageWidth;
	int charImageHeight;
	int x, y; //these are the coordinates
	String name;
	double currentLife;  //current life total
	double maxLife;  //max life
	boolean dead;  //is he dead?
	Weapon weapon; //this is the character's weapon
	int movement;
	int weaponPref;
	Image[][][] imgArray = new Image[4][4][3];
	String[] dirArray = {"Right", "Back", "Left", "Forward"};
	String[] weapArray = {"Axe", "Spear", "Sword", "Unarmed"};
	String[] moveArray = {"Stationary", "Left", "Right"};
	
	public Character(int xCoord, int yCoord, String givenName){
		maxLife = 1500;
		currentLife = maxLife;
		direction = 1;
		movement = 0;
		charImage = createImage("/CodeDay/SteveRightStationaryUnarmed.png");
		x = xCoord;
		y = yCoord;  //start at the top left (origin)
		charImageWidth = 0;
		charImageHeight = 0;
		dead = false;
		weapon = new Weapon();
		weapon.pickWeapon(3);
		weaponPref = 0;
		name = givenName;
		setUpImages();
	}
	
	private void setUpImages(){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 3; k++){
					imgArray[i][j][k] = createImage("/CodeDay/" + name + dirArray[i] + "" + moveArray[k] + "" + weapArray[j] + ".png");
				}
			}
		}
		
	}
	
	public void updateImage(){
		charImage = imgArray[direction -1][weapon.weaponChoice][movement];
	}
	
	public void takeDamage(int amount){
		currentLife -= amount;
		if(currentLife <= 0){  //took too much damage
			dead = true;
			if(name == "Enemy"){
				x = 100000;
				y = 100000;
			}
		}
	}
	
	protected Image createImage(String path)

	{

		java.net.URL imgURL = getClass().getResource(path);

		if (imgURL != null) {

			return new ImageIcon(imgURL).getImage();

		}

		else {

			System.err.println("Couldn't find file: " + path);

			return null;

		}

	}
	public void move(Character c1){
		if(c1.x>x+charImageWidth){
			x+=1;
			direction=1;
		}
		if(c1.y>y+charImageHeight){
			y+=1;
			direction=2;
		}
		if(c1.x<x-charImageWidth){
			x-=1;
			direction=3;
		}
		if(c1.y<y-charImageHeight){
			y-=1;
			direction =4;
		}
	}
	
	public void attack(Character c1)
	{
		
		if(canHit(c1))
		{
			
			c1.takeDamage(300);
			if(name == "Steve"){
				World.playerScore += 10;
			}
		}
	}

	 boolean canHit(Character c1)
	{
		if(direction==1)
		{
			if((x+charImageWidth)<=(c1.x+c1.charImageWidth)&&(c1.x)<=(x+charImageWidth+weapon.horizRange)&&(y-weapon.vertRange)<=(c1.y+c1.charImageHeight)&&(c1.y)<=(y+charImageHeight+weapon.vertRange))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(direction==2)
		{
			if(x-weapon.horizRange<=c1.x+c1.charImageWidth&&c1.x<=(x+charImageWidth+weapon.horizRange)&&y+charImageHeight<=c1.y+c1.charImageHeight&&c1.y<=y+charImageHeight+weapon.vertRange)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(direction==3)
		{
			if((x-weapon.horizRange)<=c1.x+c1.charImageWidth&&c1.x<=x&&(y-weapon.vertRange)<=c1.y+c1.charImageHeight&&c1.y<=(y+charImageHeight+weapon.vertRange))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(direction==4)
		{
			if(x-weapon.horizRange<=c1.x+c1.charImageWidth&&c1.x<=(x+charImageWidth+weapon.horizRange)&&y-weapon.vertRange<=c1.y+c1.charImageHeight&&c1.y<=y)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}










