package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BallBoomer;
import com.mygdx.game.screens.LevelOne;


public class CenterSpring extends Sprite {
	
//Declaration*********************************************************************************
	
	public World world;
	public Body body;
	private TextureRegion region;
	private Animation springJump;
	float stateTimer=0;
	public Ball ball;
//**********************************************************************************************
	
	
//Initialization*******************************************************************************	
	

	public CenterSpring(World world, BallBoomer bb,float x,float y)
	{
		
		super(bb.atlas.findRegion("Sprite-00016 (3)"));
	   
		//Graphics/Animation*******************************************************************
		Array<TextureRegion> frames=new Array<TextureRegion>();
		
		for(int i=1;i<=4;i++)
		
			frames.add(new TextureRegion(getTexture(),i*100,307,100,100));
		
		springJump=new Animation(1/10f,frames);
		frames.clear();
	
		region=springJump.getKeyFrame(stateTimer,true);
		setBounds(0,0,100/42,100/41);
		
		//Physics******************************************************************************	
		this.world=world;
		BodyDef bdef=new BodyDef();
		bdef.position.set(x/BallBoomer.PPM, y/BallBoomer.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		
		
		body=world.createBody(bdef);
		PolygonShape rect=new PolygonShape();
		rect.setAsBox(37/BallBoomer.PPM, 60/BallBoomer.PPM);
		FixtureDef fdef =new FixtureDef();
		fdef.shape=rect;
		fdef.filter.categoryBits=BallBoomer.BIT_CENTERSPRING;
		fdef.filter.maskBits=BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_BALL|BallBoomer.BIT_PURPLEBALL|BallBoomer.BIT_GROUND;
		
		body.createFixture(fdef);
		
		/*PolygonShape rect2=new PolygonShape();
		rect2.setAsBox(37/BallBoomer.PPM, 80/BallBoomer.PPM);
		FixtureDef fdef2 =new FixtureDef();
		fdef2.shape=rect;
		fdef2.isSensor=true;
		fdef2.filter.categoryBits=BallBoomer.BIT_CENTERSPRING;
		fdef2.filter.maskBits=BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_BALL|BallBoomer.BIT_PURPLEBALL;
		
		body.createFixture(fdef2);*/
	
	}
	
	
//*********************************************************************************************	
	

	
//Methods*************************************************************************************
	
	public void update(float dt)
	{
		setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
		setRegion(region);
	}


	public void effect() {
		body.applyLinearImpulse(new Vector2(0, 8), body.getWorldCenter(), true);
		
	}
	
	
//*********************************************************************************************	
		
}
