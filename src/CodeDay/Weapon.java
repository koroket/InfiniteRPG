package CodeDay;

import java.awt.*;

public class Weapon {

int horizRange;  //this refers to distance in 'x' direction that weapon can hit
int vertRange;  //this refers to distance in 'y' direction that weapon can hit
final int selectionWidth = 100;  //this is important for recognizing which weapon the user has clicked
final int selectionHeight = 100;  //this is important for recognizing which weapon the user has clicked
int weaponChoice;
int damage;
Image sprite; //this is the sprite to display on the sidebar and at the very beginning
	
public Weapon(){
	horizRange = 0;
	vertRange = 0;
	damage = 50;
}

public void pickWeapon(int choice){
	if(choice == 0){
		horizRange = 10;
		vertRange = 30;
		//sprite = axe location
	}else if(choice == 1){
		horizRange = 20;
		vertRange = 20;
		//sprite = sword location
	}else if(choice == 2){ //this case is the spear
		horizRange = 30;
		vertRange = 10;
		//sprite = spear location
	}else{
		choice = 3;
		horizRange = 5;
		vertRange = 5;
	}
	weaponChoice = choice;
}
}

