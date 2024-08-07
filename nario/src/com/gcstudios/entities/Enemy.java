package com.gcstudios.entities;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import com.gcstudios.world.World;



public class Enemy extends Entity{
	
public boolean right = true,left = false;
public int vida = 3;  //NUMERO DE PULOS P MATAR ENEMY


	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
	super(x, y, width, height, speed, sprite);
	
	}	

	public void tick() {
		if(World.isFree((int)x,(int) (y+1))) { //SÓ CAI QUANDO NAO TIVER ACONTECENDO O PULO
			y+=1;			
		}else {
		
		if(right) {
			//LOGICA INDO PRA lA
			if(World.isFree((int) (x+speed), (int)y)) {
			x+= speed; 
			if(World.isFree((int)(x+16), (int) y+1)) {
				right = false;
				left = true;		
			}
			}else {
				right = false;
				left = true;
				
			}
			
		}
		
		if (left) {  //LOGICA INDO PRA CA
			if( World.isFree((int) (y+speed), (int)y)) {
	
			x-= speed; //POSSO ANDAR			
			if(World.isFree((int) (x-16), (int) y+1)) {
				right = true;
				left = false;
			
			}
			}else {
				right = true;
				left = false;
			}
		}
		
		
		}
	}
	
	public void render(Graphics g) {
		if(right)
		sprite = Entity.ENEMY1_RIGHT;
		else if(left)
		sprite = Entity.ENEMY1_LEFT;
		
		super.render(g);
	}
}

