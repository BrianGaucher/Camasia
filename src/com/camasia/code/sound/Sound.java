package com.camasia.code.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound playerHurt = new Sound( "/resources/playerhurt.wav" ); //creates a sound from playerhurt.wav file
	public static final Sound playerDeath = new Sound( "/resources/death.wav" ); //creates a sound from death.wav file
	public static final Sound monsterHurt = new Sound( "/resources/monsterhurt.wav" ); //creates a sound from monsterhurt.wav file
	public static final Sound test = new Sound( "/resources/test.wav" ); //creates a sound from test.wav file
	public static final Sound pickup = new Sound( "/resources/pickup.wav" ); //creates a sound from pickup.wav file
	public static final Sound bossdeath = new Sound( "/resources/bossdeath.wav" ); //creates a sound from bossdeath.wav file
	public static final Sound craft = new Sound( "/resources/craft.wav" ); //creates a sound from craft.wav file

	private AudioClip clip; // Creates a audio clip to be played

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name)); //tries to load the audio clip from the name you gave above.
		} catch (Throwable e) {
			e.printStackTrace(); // else it will throw an error
		}
	}

	public void play() {
		try {
			new Thread() { //creates a new thread (string of events)
				public void run() { //runs the thread
					clip.play(); // plays the sound clip when called
				}
			}.start(); // starts the thread
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}