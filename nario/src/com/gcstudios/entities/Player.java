package com.gcstudios.entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;


public class Player extends Entity{
public static double life = 100;	//LIFE 
public static int currentCoins = 0;
public static int maxCoints = 0;
public boolean right, left;
public int dir =1;
private double gravity = 2; //VELOCIDADE GRAVIDADE
public boolean jump = false;
public boolean isJumping = false;
public int jumpHeigth = 38; //PULO 
public int framesJump = 0;
private int jumpFrames;

private int framesAnimation = 0;
private int maxFrames = 15;

private int maxSprite =2;
private int curSprite =0;
private int jumpHeight;
private String newWorld;
//private int maxSprite;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick(){
		
		depth = 2;
		if(World.isFree((int)x,(int) (y+gravity))&& isJumping == false) { //SÓ CAI QUANDO NAO TIVER ACONTECENDO O PULO
			y+=gravity;
			for(int i = 0; i < Game.entities.size(); i ++) {
			Entity e= Game.entities.get(i);
			if (e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {			
					
					//APLICAR O PULO		
					
						isJumping = true;
						Sound.jumpz.play();                  //EFEITO EM CIMA NO ENEMY
						jumpHeight = 32;
						
						//REMOVER VIDA NO INIMIGO
					((Enemy) e).vida--;				
					if(((Enemy) e).vida ==0) {
						Game.entities.remove(i);
						Sound.dano.play();
						break;
						
					
					}
					
				}
			}
			
			}
			
			
			
		}
		if (right && World.isFree((int)(x+speed),(int)y)) {
			x+=speed;
			dir =1;
			
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {
			x-= speed;
			dir=-1;
			
		}
		if (jump) {					//PULO
			if(!World.isFree(this.getX(), this.getY()+1)) {
				isJumping = true;
			}else {
				jump = false;
			}
		}
		if (isJumping) {
			if(World.isFree(this.getX(), this.getY()-2)) {
				y-=2;
				jumpFrames+=2;
				if(jumpFrames == jumpHeigth) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping = false;
				jump = false;
				jumpFrames =0;
			}
		}
		
		//DETECTAR DANO
		for(int i = 0; i < Game.entities.size(); i ++) {
			Entity e= Game.entities.get(i);
			if (e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					
					//PERDENDO VIDA
					if(Entity.rand.nextInt(100) < 5)
					life--;
					
				}
			}
			
		}
		
		//DETECTAR COLISAO COM A MOEDA E CONTAGEGM
		for(int i = 0; i < Game.entities.size(); i ++) {
			Entity e= Game.entities.get(i);
			if (e instanceof Moeda) {
				if(Entity.isColidding(this, e)) {			
					Game.entities.remove(i);
					Sound.coin.play();
					Player.currentCoins++;
					break;
				}
			}
			
		} 
		if(life<=0) {
			//GAME OVER
	//		life = 0;
			Game.gameState = "GAME_OVER";
			
			
		
			
		}
		
		//CAMERA 
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2,0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT /2, 0, World.HEIGHT * 16 - Game.HEIGHT);
		
	//	System.out.println();
	}

	public void render(Graphics g) {
	framesAnimation++;
	if(framesAnimation ==maxFrames) {
		curSprite++;
		framesAnimation =0;				//ANIMAÇAO 
		if(curSprite == maxSprite) {
			curSprite = 0;
		}
	}
		if(dir ==1) {
			sprite = Entity.PLAYER_SPRITE_RIGTH[curSprite];
			
		}else if(dir ==-1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			
		}
		super.render(g);
		
	}


	


}
