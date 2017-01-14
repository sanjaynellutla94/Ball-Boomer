package com.mygdx.game.screens;


import java.util.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BallBoomer;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.CenterSpring;
import com.mygdx.game.sprites.PurpleBall;
import com.mygdx.game.sprites.SideSpring;
import com.mygdx.game.tools.Hud;
import com.mygdx.game.tools.WorldContactListner;


public class LevelThree implements Screen {

	//Declaration*****************************************************************************
	
	public BallBoomer bb;
	//Graphics Objects
	private TextureAtlas atlas;
	private OrthographicCamera camera;
	private Viewport gameport;
	private TiledMap map;
	private TmxMapLoader maploader;
	private OrthogonalTiledMapRenderer tilerenderer;
	public Texture end;
	
	
	//Physics Objects
	public World world;
	private Box2DDebugRenderer b2dr;
	//Sprite
	public Ball ball;
	public SideSpring ss,pb3;
	public CenterSpring cs,pb1;
	public PurpleBall pb,pb2;
	public Hud hud;
	public Rectangle bound;
	public Music music;

	
	//********************************************************************************************
	
   //Initialization*****************************************************************************
	
	public LevelThree(BallBoomer bb)
	{
		this.bb=bb;
	    
		//Graphics Objects************************************************************************
		
		end=new Texture("end.png");
		atlas=new TextureAtlas("BallBoomeer.pack");
		camera=new OrthographicCamera();
		gameport=new FitViewport(BallBoomer.V_WIDTH/BallBoomer.PPM, BallBoomer.V_HEIGHT/BallBoomer.PPM, camera);
		maploader=new TmxMapLoader();
		map=maploader.load("Level3.tmx");
		tilerenderer=new OrthogonalTiledMapRenderer(map,1/BallBoomer.PPM);
		camera.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2, 0);
		//hud=new Hud(bb.batch);
		bound=new Rectangle(11,1.9f,0.4f,0.4f);
		
		
		//**************************************************************************************
		
		
		//Physics Objects***********************************************************************
		
		
		world=new World(new Vector2(0,-10), true);
		defineWorld();
		b2dr=new Box2DDebugRenderer();
	   
		
		//***************************************************************************************
		
		
		//Sprite***************************************************************************************
		
		
		ball=new Ball(world,bb,700,238);
		ss=new SideSpring(world,bb,2700,238);
	    cs=new CenterSpring(world,bb,8500,238); 
	    pb=new PurpleBall(world,bb,3700,238);  
	    pb1=new CenterSpring(world,bb,4760,238);  
	    pb2=new PurpleBall(world,bb,5700,238); 
	    pb3=new SideSpring(world,bb,6730,238); 
	  
		
	   
	    //***************************************************************************************
		
	    
	    //Contact Listener***************************************************************************************

	     world.setContactListener(new WorldContactListner());
	
	}
	//**************************************************************************************************
	
	//HandleInput********************************************************************************
	public void handleInput(float dt)
	{
		
	
		if(Gdx.input.justTouched()&& ball.body.getLinearVelocity().y<=3f)
			ball.body.applyLinearImpulse(new Vector2(0,7f), ball.body.getWorldCenter(), true);
		else if (ball.body.getLinearVelocity().x <= 2)
            ball.body.applyLinearImpulse(new Vector2(0.8f, 0), ball.body.getWorldCenter(), true);
		//else if (ball.body.getLinearVelocity().x >= -2)
           // ball.body.applyLinearImpulse(new Vector2(-0.8f, 0), ball.body.getWorldCenter(), true);
		
	
		
	
				
	}
	
	//***************************************************************************************
	
	
	//Update***************************************************************************************
	public void update(float delta)
	{
		
		handleInput(delta);  
	
	    camera.update();
	    camera.position.x=ball.body.getPosition().x;
	
	 
	    world.step(1/60f, 6, 2);
		
	    ball.update(delta);
	    ss.update(delta);
        cs.update(delta);
	   pb.update(delta);
	   pb1.update(delta);
	   pb2.update(delta);
	   pb3.update(delta);
		tilerenderer.setView(camera); 
		 
	 
		
	}
	//***************************************************************************************

	//render********************************************************************************************
	@Override
	public void render(float delta) {
		update(delta);
	
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		bb.batch.setProjectionMatrix(camera.combined);
		//b.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		//hud.stage.draw();
	    
	    tilerenderer.render();
	    bb.batch.begin();
	  
	     ball.draw(bb.batch);
	    ss.draw(bb.batch);
	    
	   pb.draw(bb.batch);
	    cs.draw(bb.batch);
	    pb1.draw(bb.batch);
	    pb2.draw(bb.batch);
	    pb3.draw(bb.batch);
	  
	   
	    bb.batch.end();
	    
	 
	   // b2dr.render(world, camera.combined);
	   
	   
	}

	//resize********************************************************************************************
	@Override
	public void resize(int width, int height) {
		
		gameport.update(width,height);

		
	}

	
	//Dispose********************************************************************************************
	@Override
	public void dispose() {
		map.dispose();
		tilerenderer.dispose();
		world.dispose();
		b2dr.dispose();
	
	}

	
	//********************************************************************************************
  public void collide()
   {
	   if(ball.bound.overlaps(bound))
		   bb.setScreen(new MainScreen(bb));
   }
	
	//********************************************************************************************
	public TextureAtlas getAtlas()
	  {
		  return atlas;
	  }	
	//******************************************************************************************** 
	 
	 public void defineWorld() 
	 {
		 BodyDef bdef=new BodyDef();
		    PolygonShape shape= new PolygonShape();
		    FixtureDef fdef=new FixtureDef();
		    Body body;
		    for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		    {
		    	
		    	Rectangle rect=((RectangleMapObject)object).getRectangle();
		    	
		    	
		    	
		    	bdef.type=BodyType.StaticBody;
		    	bdef.position.set((rect.getX() + rect.getWidth() / 2)/BallBoomer.PPM , (rect.getY() + rect.getHeight() / 2)/BallBoomer.PPM) ;

	            body = world.createBody(bdef);

	            shape.setAsBox((rect.getWidth() / 2)/BallBoomer.PPM , (rect.getHeight() / 2) /BallBoomer.PPM);
	            fdef.shape = shape;
	           
	            fdef.filter.categoryBits=BallBoomer.BIT_GROUND;
	            body.createFixture(fdef);
		    }
		    for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
		    {
		    	
		    	Rectangle rect=((RectangleMapObject)object).getRectangle();
		    	
		    	
		    	
		    	bdef.type=BodyType.StaticBody;
		    	bdef.position.set((rect.getX() + rect.getWidth() / 2)/BallBoomer.PPM , (rect.getY() + rect.getHeight() / 2)/BallBoomer.PPM) ;

	            body = world.createBody(bdef);

	            shape.setAsBox((rect.getWidth() / 2)/BallBoomer.PPM , (rect.getHeight() / 2) /BallBoomer.PPM);
	            fdef.shape = shape;
	            fdef.filter.categoryBits=BallBoomer.BIT_HOMETHREE;
	            body.createFixture(fdef);
		    }	
		    
	 }
	 
//Unused******************************************************************************************** 
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
		public void show() {
			
		}

		public void effect() {
			bb.setScreen(new MainScreen(bb));
			
		}
		
		

	


//********************************************************************************************
	
	
	

}