 
package CodeDay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import sun.audio.*;

import java.applet.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class World extends JFrame implements Runnable {

	private Image dbImage;

	Image horizontalWall = createImage("/CodeDay/HorizontalWall.gif");
	Image verticalWall = createImage("/CodeDay/VerticalWall.gif");
	Image minihorizontalWall = createImage("/CodeDay/minihwall.png");
	Image miniverticalWall = createImage("/CodeDay/minivwall.png");
Image Axe = createImage("/CodeDay/Axe.png");
		Image Sword = createImage("/CodeDay/Spear.png");
		Image Spear = createImage("/CodeDay/Sword.png");



	Image background = createImage("/CodeDay/IronTiles.png");
	// Image character = createImage("/CodeDay/Character.gif");

	private Graphics dbg;

	LifeBar bar = new LifeBar(5, 5); // places LifeBar at 5, 5
	LifeBar enemyBar = new LifeBar(845, 5);

	Character player = new Character(540, 360, "Steve");

	double gameTime;

boolean atIntro = true;
	int speed = 5;
int operate = 0;
	int flash = 1;
	int choose = 0;
	Sound sound1;
	boolean attack = true;
	int Xvelocity, Yvelocity;
	int x, y;
	 
int minix = 1220;
	int miniy = 170;

	int frequency = 0;
	int gameWidth = 1280;
	int gameHeight = 720;
	static int playerScore = 0;

	Room myRoom = new Room();

	public void run() {

		try {

			myRoom.used = true;
			Room.roomUpdate(myRoom);
	sound1.playSound();
			while (true) {

while(atIntro)
				{
					Thread.sleep(300);
					flash = flash*-1;
					
				}


				myRoom.Enemy.charImageHeight = myRoom.Enemy.charImage
						.getHeight(rootPane);
				myRoom.Enemy.charImageWidth = myRoom.Enemy.charImage
						.getWidth(rootPane);
				player.charImageHeight = myRoom.Enemy.charImage
						.getHeight(rootPane);
				player.charImageWidth = myRoom.Enemy.charImage
						.getWidth(rootPane);
				player.x += Xvelocity;
				player.y += Yvelocity;
				if (player.x > (gameWidth - (verticalWall.getWidth(rootPane) + player.charImage
						.getWidth(rootPane)))) {
					if (myRoom.right != null) {
						if (player.x > (gameWidth - (player.charImage
								.getWidth(rootPane)))) {
							player.x = 0;
							myRoom.right.check = myRoom.check;
							myRoom = myRoom.right;
							if (!myRoom.used) {
								myRoom.used = true;
								Room.roomUpdate(myRoom);
							}
						}

					} else {
						player.x = ((gameWidth - (verticalWall
								.getWidth(rootPane) + player.charImage
								.getWidth(rootPane))));
					}

				} else if (player.x < verticalWall.getWidth(rootPane)) {
					if (myRoom.left != null) {
						if (player.x < 0) {
							player.x = ((gameWidth - player.charImage
									.getWidth(rootPane)));
							myRoom.left.check = myRoom.check;
							myRoom = myRoom.left;
							if (!myRoom.used) {
								myRoom.used = true;
								Room.roomUpdate(myRoom);
							}
						}

					} else {
						player.x = (verticalWall.getWidth(rootPane));
					}

				}
				if (player.y < horizontalWall.getHeight(rootPane)) {
					if (myRoom.up != null) {
						if (player.y < 0) {
							player.y = gameHeight
									- (player.charImage.getHeight(rootPane));
							myRoom.up.check = myRoom.check;
							myRoom = myRoom.up;
							if (!myRoom.used) {
								myRoom.used = true;
								Room.roomUpdate(myRoom);
							}
						}

					} else {
						player.y = horizontalWall.getHeight(rootPane);
					}

				} else if (player.y > gameHeight
						- (horizontalWall.getHeight(rootPane) + player.charImage
								.getHeight(rootPane))) {
					if (myRoom.down != null) {
						if (player.y > gameHeight
								- (player.charImage.getHeight(rootPane))) {
							player.y = 0;
							myRoom.down.check = myRoom.check;
							myRoom = myRoom.down;
							if (!myRoom.used) {
								myRoom.used = true;
								Room.roomUpdate(myRoom);
							}
						}

					} else {
						player.y = gameHeight
								- (horizontalWall.getHeight(rootPane) + player.charImage
										.getHeight(rootPane));
					}

				}

				if (myRoom.weather == 0) {
					speed = 5;
				}
				bar.updateLength(player); // checks player health and updates
											// currLength of bar accordingly
				enemyBar.updateLength(myRoom.Enemy);

				if (player.isMoving && ((int) (gameTime * 3)) % 2 == 1) {
					player.movement = 2;
				} else {
					player.movement = 1;
				}
				if (((int) (gameTime * 3)) % 2 == 1) {
					myRoom.Enemy.movement = 2;
				} else {
					myRoom.Enemy.movement = 1;
				}

				if (!player.isMoving) {
					player.movement = 0;
				}
				player.updateImage();
				myRoom.Enemy.updateImage();
				if (frequency == 0) {
					myRoom.Enemy.attack(player);
					frequency += 100;
				}
				frequency--;
				myRoom.Enemy.move(player);

				gameTime += 0.01; // this is 10 milliseconds in seconds

				Thread.sleep(10);

				if (myRoom.weather == 1) {
					speed = 3;
				}
				if (myRoom.weather == 2) {

					if (myRoom.wind == 0
							&& player.x < (gameWidth - (player.charImage
									.getWidth(rootPane)))) {
						player.x += 1;
					} else if (myRoom.wind == 1 && player.x > 0) {
						player.x -= 1;
					} else if (myRoom.wind == 2
							&& player.y < gameHeight
									- (player.charImage.getHeight(rootPane))) {
						player.y += 1;
					} else if (myRoom.wind == 3 && player.y > 0) {
						player.y -= 1;
					}
				}
				if (myRoom.weather == 0) {
					speed = 5;
				}

			}

		}

		catch (Exception e) {

			System.out.print("error");

		}

	}

	World() {

		addKeyListener(new World.AL());

		setTitle("Code Day 2K13 BayBee");

		setSize(gameWidth, gameHeight);

		setResizable(false);

		setVisible(true);

		setBackground(Color.GREEN);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

sound1 = new Sound("gameback.wav");

		Yvelocity = 0;

		Xvelocity = 0;

	}

	
	public class AL extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();

			if (keyCode == e.VK_RIGHT) {
				if(atIntro)
				{
					if(choose==2)
					{
						choose =0;
					}
					else
						{
						choose++;
						}
				}
				else
				{
					Xvelocity = speed;
					player.direction = 1;
					player.isMoving = true;
				}
				
			}
			if (keyCode == e.VK_LEFT) {
				if(atIntro)
				{
					if(choose==0)
					{
						choose =2;
					}
					else
						{
						choose--;
						}
				}
				else
				{
					Xvelocity = -speed;
					player.direction = 3;
					player.isMoving = true;
				}
				
			}
			if (keyCode == e.VK_DOWN) {
				Yvelocity = speed;
				player.direction = 2;
				player.isMoving = true;
			}
			if (keyCode == e.VK_UP) {
				Yvelocity = -speed;
				player.direction = 4;
				player.isMoving = true;
			}
			
			if(keyCode==e.VK_SPACE){
				if(attack)
				{
				player.attack(myRoom.Enemy);
				player.weapon.pickWeapon(player.weaponPref); //sets the default unarmed weapon to the weapon selected
				attack = false;
				}
			}
			if(keyCode==e.VK_ENTER){
				if(atIntro)
				{
					if(operate!=3)
					{
				operate++;
					}
					else
					{
						atIntro=false;
						player.weaponPref=choose;
					}
				}
				if(player.dead==true){
					myRoom = new Room();
					myRoom.used = true;
					Room.roomUpdate(myRoom);
					player.currentLife=player.maxLife;
					player.dead=false;
					atIntro =true;
					World.playerScore=0;
					
				}

			}

		}

		public void keyReleased(KeyEvent e) {

			int keyCode = e.getKeyCode();

			if (keyCode == e.VK_RIGHT) {
				
				
					Xvelocity= 0;
					if(Yvelocity > 0){
						player.direction = 2;
						player.updateImage();
					}else if(Yvelocity < 0){
						player.direction = 4;
						player.updateImage();
					}else{
						player.isMoving = false;
					}
				
				
			}

			if (keyCode == e.VK_LEFT) {
				
					Xvelocity = 0;
					if(Yvelocity > 0){
						player.direction = 2;
						player.updateImage();
					}else if(Yvelocity < 0){
						player.direction = 4;
						player.updateImage();
					}else{
						player.isMoving = false;
					}
				
				
			}
			if (keyCode == e.VK_DOWN) {
				Yvelocity = 0;
				if(Xvelocity > 0){
					player.direction = 1;
					player.updateImage();
				}else if(Xvelocity < 0){
					player.direction = 3;
					player.updateImage();
				}else{
					player.isMoving = false;
				}
			}
			if (keyCode == e.VK_UP) {
				Yvelocity = 0;
				if(Xvelocity > 0){
					player.direction = 1;
					player.updateImage();
				}else if(Xvelocity < 0){
					player.direction = 3;
					player.updateImage();
				}else{
					player.isMoving = false;
				}
			}
			
			if(keyCode==e.VK_SPACE){
				player.weapon.pickWeapon(3);//unarmed again
				attack=true;
			}

		}

	}


	public boolean paintRoom(Room r1, int i, Graphics g, int i1, int x, int y,
			int drawx, int drawy) {
		if (x > 0 && x < i1 + 1 && y > 0 && y < i1 + 1) {
			if (r1 == null) {

				return false;
			} else if (r1.used == false) {
				g.setColor(Color.gray);
				g.fillRect(drawx, drawy, 10, 10);
				return false;
			} else {

				if (i != 1) {
					paintRoom(r1.right, 3, g, i1, x + 1, y, drawx + 10, drawy);
				}
				if (i != 2) {
					paintRoom(r1.down, 4, g, i1, x, y - 1, drawx, drawy + 10);
				}
				if (i != 3) {
					paintRoom(r1.left, 1, g, i1, x - 1, y, drawx - 10, drawy);
				}
				if (i != 4) {
					paintRoom(r1.up, 2, g, i1, x, y + 1, drawx, drawy - 10);
				}

				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(drawx, drawy, 10, 10);

				if (r1.up == null) {
					g.drawImage(minihorizontalWall, drawx, drawy, this);
				}
				if (r1.down == null) {
					g.drawImage(minihorizontalWall, drawx, drawy + 9, this);
				}
				if (r1.left == null) {
					g.drawImage(miniverticalWall, drawx, drawy, this);
				}
				if (r1.right == null) {
					g.drawImage(miniverticalWall, drawx + 9, drawy, this);

				}
				return false;
			}
		} else {
			return false;
		}

	}

	public void paint(Graphics g) {

		dbImage = createImage(getWidth(), getHeight());

		dbg = dbImage.getGraphics();

		paintComponent(dbg);

		g.drawImage(dbImage, 0, 0, this);
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

public class Sound // Holds one audio file

    {

  private AudioClip song; // Sound player

  private URL songPath; // Sound path

  Sound(String filename)

  {

     try

     {

    songPath = this.getClass().getResource(filename);

    song = Applet.newAudioClip(songPath); // Load the Sound

     }

     catch(Exception e){} // Satisfy the catch

  }

  public void playSound()

  {

     song.loop(); // Play

  }

  public void stopSound()

  {

     song.stop(); // Stop

  }

  public void playSoundOnce()

  {

     song.play(); // Play only once

  }

    }


	public void paintComponent(Graphics g) {

		g.setColor(Color.black);

		g.drawImage(background, 0, 0, this);

		g.drawImage(myRoom.Enemy.charImage, myRoom.Enemy.x, myRoom.Enemy.y,
				this);

		g.drawImage(player.charImage, player.x, player.y, this);
		if (myRoom.up == null) {
			g.drawImage(horizontalWall, 0, 0, this);
		}
		if (myRoom.down == null) {
			g.drawImage(horizontalWall, 0, 620, this);
		}
		if (myRoom.left == null) {
			g.drawImage(verticalWall, 0, 0, this);
		}
		if (myRoom.right == null) {
			g.drawImage(verticalWall, 1180, 0, this);
		}

		g.setColor(Color.BLACK);
		g.fillRect(15, 45, (int) bar.maxLength, 40); // draws the empty part of
														// the LifeBar
		g.fillRect(855, 45, (int) enemyBar.maxLength, 40);
		g.setColor(Color.RED);
		g.fillRect(20, 50, (int) bar.currLength, 30); // draws the filling of
														// the LifeBar in red
		g.fillRect(860, 50, (int) enemyBar.currLength, 30);
		g.setColor(Color.BLACK);
		g.drawString("Life: " + player.currentLife + " / " + player.maxLife,
				15, 97);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		g.drawString("Time Elapsed: " + df.format(gameTime), 15, 120);
		g.drawString("Player Score: " + playerScore, 15, 150);
		// g.drawImage(player.weapon.sprite, 0, 300, this);

		g.setColor(Color.white);
		g.fillRect(minix - 30, miniy - 30, 70, 70);
		paintRoom(myRoom, 5, g, 7, 4, 4, minix, miniy);
		g.setColor(Color.MAGENTA);
		g.fillRect(minix, miniy, 10, 10);
		if (myRoom.up == null) {
			g.drawImage(minihorizontalWall, minix, miniy, this);
		}
		if (myRoom.down == null) {
			g.drawImage(minihorizontalWall, minix, miniy + 9, this);
		}
		if (myRoom.left == null) {
			g.drawImage(miniverticalWall, minix, miniy, this);
		}
		if (myRoom.right == null) {
			g.drawImage(miniverticalWall, minix + 9, miniy, this);
		}

Font f1;
		if(atIntro)
		{
			 f1 = new Font("monospaced", Font.BOLD, 80);

			g.setFont(f1);
			g.setColor(Color.white);
			g.fillRect(0, 0, 1280, 720);
			g.setColor(Color.black);
			if(operate==0)
			{
				g.drawString("...", 300, 300);
			}
			else if(operate==1)
			{
				g.drawString("Where am I?", 300, 300);
			}
			else if(operate==2)
			{
				g.setColor(Color.BLUE);
				g.drawString("You are stranded", 300, 300);
			}
			else if(operate==3)
			{
				
				g.setColor(Color.BLUE);
				if(flash==1)
				{

					g.drawString("Choose Your Weapon", 300, 170);
				}
				g.drawImage(Axe, 350, 300, this);
				g.drawImage(Sword, 550, 300, this);
				g.drawImage(Spear, 750, 300, this);
				g.setColor(Color.red);
				g.drawRect(350+choose*205, 300, 200, 200);
				
			}
			
			g.setColor(Color.black);
			f1 = new Font("monospaced", Font.BOLD, 40);
			g.setFont(f1);
			g.drawString("Hit Enter", 500, 650);
		}

		if(player.dead==true){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0,dbImage.getWidth(rootPane), dbImage.getHeight(rootPane));
			g.setColor(Color.BLACK);
			Font font = new Font("monospaced",Font.BOLD,100);
			g.setFont(font);
			g.drawString("You have died", 200, 360);
			g.drawString("Your score was "+World.playerScore,200,450);
			Font font1 = new Font("monospaced",Font.BOLD,70);
			g.setFont(font1);
			g.drawString("Hit enter to continue", 200, 550);
			
				myRoom = new Room();
				myRoom.used = true;
				Room.roomUpdate(myRoom);
			
		}



		repaint();

	}

}




