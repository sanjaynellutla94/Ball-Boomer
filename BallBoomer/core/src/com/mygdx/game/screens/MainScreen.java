package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BallBoomer;

public class MainScreen implements Screen {

	public Texture img;
	public BallBoomer bb;
	private OrthographicCamera camera;
	private Viewport vp;
	
	
	public MainScreen(BallBoomer bb)
	{
		this.bb=bb;
		img=new Texture("MainScreen.png");
		
		camera=new OrthographicCamera();
		vp=new FitViewport(BallBoomer.V_WIDTH/BallBoomer.PPM, BallBoomer.V_HEIGHT/BallBoomer.PPM, camera);
		camera.position.set(vp.getWorldWidth()/2,vp.getWorldHeight()/2, 0);
		
	
		
	}
	
	@Override
	
	
	public void show() {
	
		
	}

	public void handleInput(float delta)
	{
		if(Gdx.input.isTouched())
		{
			bb.setScreen(new LevelOne(bb));
		}	
		
		
	}
	public void update(float delta)
	{
		handleInput(delta);
		  camera.update();
		   
		  
		
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		
	
		Gdx.gl.glClearColor(0,0,0,.9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		bb.batch.begin();
		bb.batch.draw(img, 0,0);
	
		bb.batch.end();
	
		
	}

	@Override
	public void resize(int width, int height) {
	
		vp.update(width,height);

	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {

		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}
	

}
