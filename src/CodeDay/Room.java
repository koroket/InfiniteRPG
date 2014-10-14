package CodeDay;

public class Room {
	Room left;
	Room right;
	Room up;
	Room down;
	int check = 0;
	boolean used;
	int weather;
	int wind;
	Character Enemy;
	static int counter = 0;
	public Room(){
		left = null;
		right = null;
		up = null;
		down = null;
		used = false;
		counter++;
		weather = (int)(Math.random()*3);
		wind = (int)(Math.random()*4);
		setUpEnemy();
		
	}
	

public static void roomUpdate(Room r1)
{
		if(r1.left==null&&((int)(Math.random()*3))==1)
		{
			r1.left = new Room();
			r1.left.right = r1;
		}
		if(r1.right==null&&((int)(Math.random()*3))==1)
		{
			r1.right = new Room();
			r1.right.left = r1;
		}
		if(r1.down==null&&((int)(Math.random()*3))==1)
		{
			r1.down = new Room();
			r1.down.up = r1;
		}
		if(r1.up==null&&((int)(Math.random()*3))==1)
		{
			r1.up = new Room();
			r1.up.down = r1;
		}
		
		while(existsUnusedRoom(r1)==false)
		{
			if(r1.left==null&&((int)(Math.random()*3))==1)
			{
				r1.left = new Room();
				r1.left.right = r1;
			}
			if(r1.right==null&&((int)(Math.random()*3))==1)
			{
				r1.right = new Room();
				r1.right.left = r1;
			}
			if(r1.down==null&&((int)(Math.random()*3))==1)
			{
				r1.down = new Room();
				r1.down.up = r1;
			}
			if(r1.up==null&&((int)(Math.random()*3))==1)
			{
				r1.up = new Room();
				r1.up.down = r1;
			}
		}
		
}

public static boolean existsUnusedRoom(Room r1)
{
	if(r1==null)
	{
		return false;
	}
	else if(r1.check==1)
	{
		if(!r1.used)
		{
			r1.check = 0;
			return true;
		}
		else
		{
			boolean b1,b2,b3,b4 = false;
			r1.check = 0;
			
			return(r1.down!=null&&r1.down.check!=0&&existsUnusedRoom(r1.down)||r1.up!=null&&r1.up.check!=0&&existsUnusedRoom(r1.up)||r1.left!=null&&r1.left.check!=0&&existsUnusedRoom(r1.left)||r1.right!=null&&r1.right.check!=0&&existsUnusedRoom(r1.right));
		}
	}
	else
	{
		if(!r1.used)
		{
			r1.check = 1;
			return true;
		}
		else
		{
			boolean b1,b2,b3,b4 = false;
			r1.check = 1;
			
			return(r1.down!=null&&r1.down.check!=1&&existsUnusedRoom(r1.down)||r1.up!=null&&r1.up.check!=1&&existsUnusedRoom(r1.up)||r1.left!=null&&r1.left.check!=1&&existsUnusedRoom(r1.left)||r1.right!=null&&r1.right.check!=1&&existsUnusedRoom(r1.right));
		}
	}
}


private  void setUpEnemy(){
	Enemy = new Character(250, 250, "Enemy");
	Enemy.weapon.pickWeapon((int)(Math.random()*3));
	Enemy.updateImage();
	
}





	
	
	
}








