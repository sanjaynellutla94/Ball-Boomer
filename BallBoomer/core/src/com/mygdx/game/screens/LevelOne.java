package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
import com.mygdx.game.tools.WorldContactListner;


public class LevelOne implements Screen {

	//Declaration*****************************************************************************
	
	public BallBoomer bb;
	//Graphics Objects
	
	private OrthographicCamera camera;
	private Viewport gameport;
	private TiledMap map;
	private TmxMapLoader maploader;
	private OrthogonalTiledMapRenderer tilerenderer;
	
	//Physics Objects
	public World world;
	private Box2DDebugRenderer b2dr;
	//Sprite
	public Ball ball;
	public SideSpring ss,ss1,ss2;
	public CenterSpring cs,cs1,cs2;
	public PurpleBall pb;
	public Music music;

	
	//********************************************************************************************
	
   //Initialization*****************************************************************************
	
	public LevelOne(BallBoomer bb)
	{
		this.bb=bb;
		
		//Graphics Objects************************************************************************
	 
		
		camera=new OrthographicCamera();
		gameport=new FitViewport(BallBoomer.V_WIDTH/BallBoomer.PPM, BallBoomer.V_HEIGHT/BallBoomer.PPM, camera);
		maploader=new TmxMapLoader();
		map=maploader.load("Level.tmx");
		tilerenderer=new OrthogonalTiledMapRenderer(map,1/BallBoomer.PPM);
		camera.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2, 0);
		
		
		
		//**************************************************************************************
		
		
		//Physics Objects***********************************************************************
		
		
		world=new World(new Vector2(0,-10), true);
		defineWorld();
		b2dr=new Box2DDebugRenderer();
	   
		
		//***************************************************************************************
		
		
		//Sprite***************************************************************************************
		
		
		ball=new Ball(world,bb,160,238);
		ss=new SideSpring(world,bb,800,240);   
	    ss1=new SideSpring(world,bb,2640,340);
	    ss2=new SideSpring(world,bb,3050,340);
	    cs=new CenterSpring(world,bb,2300,340);
	    cs2=new CenterSpring(world,bb,3300,340);
	    cs1=new CenterSpring(world,bb,4000,340); 
	   
	    pb=new PurpleBall(world,bb,1160,238);  
		
	   
	    //***************************************************************************************
		
	    
	    //Contact Listener***************************************************************************************

		world.setContactListener(new WorldContactListner());
		//ss.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
		//ss1.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
		//cs2.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
		//cs.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
		//cs1.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
		//cs2.body.applyLinearImpulse(new Vector2(0,8),  ball.body.getWorldCenter(), true);
	
	    
	}
	//**************************************************************************************************
	
	//HandleInput********************************************************************************
	public void handleInput(float dt)
	{
		
		
		/*if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
			ball.body.applyLinearImpulse(new Vector2(0,5f), ball.body.getWorldCenter(), true);
		else if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT) && ball.body.getLinearVelocity().x <= 2))||Gdx.input.isTouched())
            ball.body.applyLinearImpulse(new Vector2(0.5f, 0), ball.body.getWorldCenter(), true);
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && ball.body.getLinearVelocity().x >= -2)
            ball.body.applyLinearImpulse(new Vector2(-0.5f, 0), ball.body.getWorldCenter(), true);
		
		
		pb.body.applyLinearImpulse(new Vector2(0.1f,0), pb.body.getWorldCenter(), true);*/
		
		if(Gdx.input.isTouched()&& ball.body.getLinearVelocity().y<=3f)
			ball.body.applyLinearImpulse(new Vector2(0,3f), ball.body.getWorldCenter(), true);
		else if (ball.body.getLinearVelocity().x <= 2)
            ball.body.applyLinearImpulse(new Vector2(0.8f, 0), ball.body.getWorldCenter(), true);
	
				
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
	    ss1.update(delta);
	    ss2.update(delta);
	    
        cs.update(delta);
        cs1.update(delta);
        cs2.update(delta);
	    pb.update(delta);
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
	    tilerenderer.render();
	    
	    bb.batch.begin();
	    ball.draw(bb.batch);
	    ss.draw(bb.batch);
	    ss1.draw(bb.batch);
	    ss2.draw(bb.batch);
	 
	    pb.draw(bb.batch);
	    cs.draw(bb.batch);
	    cs1.draw(bb.batch);
	    cs2.draw(bb.batch);
	   
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
	
	
	//********************************************************************************************

	//******************************************************************************************** 
	 
	 public void defineWorld() 
	 {
		 BodyDef bdef=new BodyDef();
		    PolygonShape shape= new PolygonShape();
		    FixtureDef fdef=new FixtureDef();
		    Body body;
		    for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class))
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
		    for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		    {
		    	
		    	Rectangle rect=((RectangleMapObject)object).getRectangle();
		    	
		    	
		    	
		    	bdef.type=BodyType.StaticBody;
		    	bdef.position.set((rect.getX() + rect.getWidth() / 2)/BallBoomer.PPM , (rect.getY() + rect.getHeight() / 2)/BallBoomer.PPM) ;

	            body = world.createBody(bdef);

	            shape.setAsBox((rect.getWidth() / 2)/BallBoomer.PPM , (rect.getHeight() / 2) /BallBoomer.PPM);
	            fdef.shape = shape;
	            fdef.filter.categoryBits=BallBoomer.BIT_HOME;
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


//********************************************************************************************
	
	
	

}