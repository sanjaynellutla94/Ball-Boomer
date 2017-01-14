package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BallBoomer;
import com.mygdx.game.screens.LevelOne;
import com.mygdx.game.sprites.PurpleBall.State;



public class SideSpring extends Sprite {
	
//Declaration*********************************************************************************
	
	
	//Graphics/Animation***********************************************************************
	public enum State {JUMPINGLEFT,JUMPINGRIGHT} 
	public State currentState;
	public State previousState;
	private TextureRegion region;
	private Animation springJumpRight;
	private Animation springJumpLeft;
	
	
	//Physics**********************************************************************************
	private float stateTimer;
	public World world;
	public Body body;
	public FixtureDef fdef;
	
//**********************************************************************************************
	
	
//Initialization*******************************************************************************	
	public SideSpring(World world, BallBoomer bb,float x,float y)
	{
		
		super(bb.atlas.findRegion("Sprite-00017)L"));
		
		//Graphics/Animation*******************************************************************
		currentState=State.JUMPINGRIGHT;
		previousState=State.JUMPINGRIGHT;
		stateTimer=0;
		
		Array<TextureRegion> frames=new Array<TextureRegion>();
		for(int i=1;i<=2;i++)
		{
			frames.add(new TextureRegion(getTexture(),i*1,1,100,100));
		}
		springJumpLeft=new Animation(1/5f,frames);
		frames.clear();
		
		for(int i=1;i<=3;i++)	
		{
			frames.add(new TextureRegion(getTexture(),i*1,1,100,100));
		}
		springJumpRight=new Animation(1/10f,frames);
		frames.clear();
		setBounds(0,0,100/42,100/41);
		
		//Physics******************************************************************************
		
		this.world=world;
		BodyDef bdef=new BodyDef();
		bdef.position.set(x/BallBoomer.PPM, y/BallBoomer.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		body=world.createBody(bdef);
		
		PolygonShape rect=new PolygonShape();
		rect.setAsBox(37/BallBoomer.PPM, 80/BallBoomer.PPM);
		FixtureDef fdef =new FixtureDef();
		fdef.shape=rect;
	
	
		
		fdef.filter.categoryBits=BallBoomer.BIT_SIDESPRING;
		fdef.filter.maskBits=BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_PURPLEBALL|BallBoomer.BIT_GROUND;
		
	
		body.createFixture(fdef);
		
		/*PolygonShape rect2=new PolygonShape();
		rect2.setAsBox(37/BallBoomer.PPM, 80/BallBoomer.PPM);
		FixtureDef fdef2 =new FixtureDef();
		fdef2.shape=rect;
		fdef2.filter.categoryBits=BallBoomer.BIT_SIDESPRING;
		fdef2.filter.maskBits=BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_PURPLEBALL;
		body.createFixture(fdef2);*/
		

		/*PolygonShape rect2=new PolygonShape();
		rect2.setAsBox(37/BallBoomer.PPM, 80/BallBoomer.PPM);
		fdef.shape=rect2;
	*/
		
		
		
		//body.setLinearVelocity(0,0.5f);
		
     
		
	}
	
//*********************************************************************************************	
	

	
//Methods*************************************************************************************
	public void update(float dt)
	{
		setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
		setRegion(getFrame(dt));
	
	}
		
	public TextureRegion getFrame(float dt)
	{
	
       currentState=getState();
		
		TextureRegion region;
		switch(currentState)
		{
		case JUMPINGRIGHT:
			region=springJumpRight.getKeyFrame(stateTimer,true);
			break;
		case JUMPINGLEFT:
			region=springJumpLeft.getKeyFrame(stateTimer,true);
			break;
		
		default:	
			region=springJumpRight.getKeyFrame(stateTimer,true);
		}
		return region;
	}
	
	public State getState()
	{
if(body.getLinearVelocity().y>0)
			
			return State.JUMPINGRIGHT;
		else if(body.getLinearVelocity().y<0)
			
			return State.JUMPINGLEFT;
		
		else 
		return State.JUMPINGLEFT;


    		
	}

	

	public void effect() {
	
		body.applyLinearImpulse(new Vector2(0, 8), body.getWorldCenter(), true);
		
	}
	

//*********************************************************************************************	

}

