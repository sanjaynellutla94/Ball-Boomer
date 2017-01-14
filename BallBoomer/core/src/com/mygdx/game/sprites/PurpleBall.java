package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BallBoomer;




public class PurpleBall extends Sprite 
{

//Declaration**********************************************************************************************	
	
	//Graphics**************************************************************************
	public enum State {RUNNINGLEFT,RUNNINGRIGHT};
	public State currentState;
	public State previousState;
	public Filter filter;
	private Animation ballRunRight;
	private Animation ballRunLeft;
	private float stateTimer;
	//Physics****************************************************************************
	public Body body;

	public World world;

	

//Initialization****************************************************************************************	
	public PurpleBall(World world, BallBoomer bb,float x,float y)
	{
		super(bb.atlas.findRegion("Sprite-00015"));
		
		//Animation/Graphics****************************************************8
		currentState=State.RUNNINGRIGHT;
		previousState=State.RUNNINGRIGHT;
		stateTimer=0;
		//----------------------------------------------------------------
		Array<TextureRegion> frames=new Array<TextureRegion>();
		for(int i=0;i<1;i++)
		{
			frames.add(new TextureRegion(getTexture(),i*1,409,100,100));
		
		}
		ballRunRight=new Animation(1/5f,frames);
		
		frames.clear();
		//-------------------------------------------------------------------
		for(int i=0;i<1;i++)
			
		{
			frames.add(new TextureRegion(getTexture(),i*1,409,100,100));
		
		}
		ballRunLeft=new Animation(1/5f,frames);
		frames.clear();
		//------------------------------------------------------------------
		setBounds(0,0,100/40,100/40);
		
		//***********************************************************************************************
		
		//Physics********************************************************************************
	
		this.world=world;
		BodyDef bdef=new BodyDef();
		bdef.position.set(x/BallBoomer.PPM, y/BallBoomer.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		
		body=world.createBody(bdef);
		
		CircleShape circle=new CircleShape();
		circle.setRadius(54/100f);
		FixtureDef fdef =new FixtureDef();
		fdef.shape=circle;

		
		fdef.filter.categoryBits=BallBoomer.BIT_PURPLEBALL;
		fdef.filter.maskBits=BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_GROUND;
		body.createFixture(fdef);
		

		//PolygonShape rect2=new PolygonShape();
		//rect2.setAsBox(47/BallBoomer.PPM, 50/BallBoomer.PPM);
		//FixtureDef fdef2 =new FixtureDef();
		//fdef2.shape=rect2;
		
		//fdef2.filter.categoryBits=BallBoomer.BIT_PURPLEBALL;
		//fdef2.filter.maskBits=BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_BALL;
		//body.createFixture(fdef2);*/
		
		/*this.world=world;
		BodyDef bdef=new BodyDef();
		bdef.position.set(x/BallBoomer.PPM, y/BallBoomer.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		
		
		body=world.createBody(bdef);
		PolygonShape rect=new PolygonShape();
		rect.setAsBox(46/BallBoomer.PPM, 50/BallBoomer.PPM);
		FixtureDef fdef =new FixtureDef();
		fdef.shape=rect;
	
		
		fdef.filter.categoryBits=BallBoomer.BIT_PURPLEBALL;
		fdef.filter.maskBits=BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_SIDESPRING;
		body.createFixture(fdef);
		PolygonShape rect2=new PolygonShape();
		rect2.setAsBox(46/BallBoomer.PPM, 50/BallBoomer.PPM);
		FixtureDef fdef2 =new FixtureDef();
		fdef2.shape=rect;
		fdef2.filter.categoryBits=BallBoomer.BIT_PURPLEBALL;
		fdef2.filter.maskBits=BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_SIDESPRING;
		body.createFixture(fdef2);*/
		

		
		
		//************************************************************************************************	
	
	}
//*****************************************************************************************************************************************	
	
	//Methods*************************************************************************
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
		case RUNNINGRIGHT:
			region=ballRunRight.getKeyFrame(stateTimer,true);
			break;
		case RUNNINGLEFT:
			region=ballRunLeft.getKeyFrame(stateTimer,true);
			break;
		
		default:	
			region=null;
		}
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
        //update previous state
        previousState = currentState;
        //return our final adjusted frame
        return region;
		
	}
	
	
	public State getState()
	{
		if(body.getLinearVelocity().x>0)
			
			return State.RUNNINGRIGHT;
		else if(body.getLinearVelocity().x<0)
			
			return State.RUNNINGLEFT;
		
		else 
		return State.RUNNINGRIGHT;
		
		
	}

	

	public void effect() {
		// TODO Auto-generated method stub
		
	}

//**********************************************************************************************


	
		
		
	
	
}







