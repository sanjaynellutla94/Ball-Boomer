package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.screens.LevelOne;
import com.mygdx.game.screens.MainScreen;

public class BallBoomer extends Game {
	public SpriteBatch batch;
	public TextureAtlas atlas;

	public Texture img;
	
	public static final float V_WIDTH=1280;
	public static final float  V_HEIGHT=720;
	public static final float  PPM=100;
	
	public static final short BIT_BALL=2;
	public static final short BIT_SIDESPRING=4;
	public static final short BIT_CENTERSPRING=6;
	public static final short BIT_PURPLEBALL=4;
	public static final short BIT_GROUND=28;
	public static final short BIT_HOME=16;
	/*public static final short NOTHING_BIT = 0;
	public static final short DESTROYED_BIT = 14;
	public static final short BIT_BODY = 16;*/
	public static final short BIT_HOMETHREE = 24;
	public static final short BIT_HOMETWO = 20;
	public Music music;
	
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas= new TextureAtlas("BallBoomeer.pack");
		music=Gdx.audio.newMusic(Gdx.files.internal("Music.wav"));
		
		music.play();
		music.setVolume(1f);
	
		

	setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
	super.render();
		
	}
}
