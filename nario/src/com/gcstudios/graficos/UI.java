package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(10, 10, 250, 30);
		
		g.setColor(Color.green);
		g.fillRect(10, 10, (int) ((Player.life/ 100) * 250), 30);
		
		g.setColor(Color.white);
		g.drawRect(10, 10, 250, 30); //borda
		g.setFont(new Font("arial",Font.BOLD,22));
		g.drawString("moedas: " +Player.currentCoins+"/"+Player.maxCoints,( Game.WIDTH*Game.SCALE) -160 ,30 );
	
	}
	
}
