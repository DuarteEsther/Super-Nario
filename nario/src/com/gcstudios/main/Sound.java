package com.gcstudios.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static Object musicBack;

	private AudioClip clip;
	
	public static final Sound back = new Sound("/musicz.wav");
	public static final Sound jumpz = new Sound("/jumpz.wav");
	public static final Sound coin = new Sound("/bit.wav");
	public static final Sound dano = new Sound("/dano.wav");
	public static final Sound gameov = new Sound("/gameover.wav");
	public static final Sound vitory = new Sound("/vitory.wav");

	
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		}catch(Throwable e) {}
	}
	
	public void loop() {
		try {
			new Thread() {
				@SuppressWarnings("deprecation")
				public void run() {
					clip.loop();
				}
			}.start();
		}catch(Throwable e) {}
	}
}
