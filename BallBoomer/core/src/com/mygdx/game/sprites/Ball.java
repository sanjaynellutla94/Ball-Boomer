package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.BallBoomer;
import com.mygdx.game.screens.LevelOne;
import com.mygdx.game.screens.LevelThree;
import com.mygdx.game.screens.LevelTwo;
import com.mygdx.game.screens.MainScreen;



public class Ball extends Sprite {


	public enum State {STANDING,RUNNINGLEFT,RUNNINGRIGHT,DEAD} 
	public State currentState;
	public State previousState;
	public World world;
	public Body body;
	public FixtureDef fdef;
	private TextureRegion stand;
	private Animation ballRunRight;
	private Animation ballRunLeft;
	public BallBoomer bb;
    public Rectangle bound;
    public Sound sound,sound1;
	
	private float stateTimer;
	
	public Ball(World world, BallBoomer bb,float x,float y)
	{
		
		super(bb.atlas.findRegion("F-1"));
		this.bb=bb;
		sound=Gdx.audio.newSound(Gdx.files.internal("effect.wav"));
		sound1=Gdx.audio.newSound(Gdx.files.internal("woo.wav"));
		
	
		currentState=State.STANDING;
		previousState=State.STANDING;
		stateTimer=0;
		bound=new Rectangle(x,y,80/100f,80/100f);
	
		
		Array<TextureRegion> frames=new Array<TextureRegion>();
		for(int i=1;i<=7;i++)
		
		{
			frames.add(new TextureRegion(getTexture(),i*100,919,100,100));
		
			
		
		
		}
		ballRunRight=new Animation(1/10f,frames);
		
		frames.clear();
		for(int i=1;i<=7;i++)
			
		{
			frames.add(new TextureRegion(getTexture(),i*100,817,100,100));
		
		}
		ballRunLeft=new Animation(1/10f,frames);
		
		frames.clear();
		
		
		
		
		
		this.world=world;
		BodyDef bdef=new BodyDef();
		bdef.position.set(x/BallBoomer.PPM, y/BallBoomer.PPM);
		bdef.type=BodyDef.BodyType.DynamicBody;
		
		body=world.createBody(bdef);
		CircleShape circle=new CircleShape();
		circle.setRadius(38/BallBoomer.PPM);
		FixtureDef fdef =new FixtureDef();
		fdef.shape=circle;
	
		
		/*CircleShape circle2=new CircleShape();
		circle2.setRadius(38/BallBoomer.PPM);
	
		fdef.shape=circle2;
		fdef.isSensor=true;
		*/
		fdef.filter.categoryBits=BallBoomer.BIT_BALL;
		fdef.filter.maskBits=BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_PURPLEBALL|BallBoomer.BIT_HOME|BallBoomer.BIT_HOMETWO|BallBoomer.BIT_HOMETHREE|BallBoomer.BIT_GROUND;
		
		
		

		
		body.createFixture(fdef).setUserData(this);
		
		stand=new TextureRegion(getTexture(),1, 919,100,100);
		setBounds(0,0,100/42,100/41);
	
		
	}
	
	public void update(float dt)
	{
		setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
		setRegion(getFrame(dt));
		bound.setPosition(this.getWidth(), this.getHeight());
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
		
		case STANDING:
			region=stand;
			break;
		
		case DEAD:
			region=stand;
			effect(this);
			break;
		
		default:	
			region=stand;
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
           if(body.getLinearVelocity().x<0)
			
			return State.RUNNINGLEFT;
		
		if(body.getLinearVelocity().x==0)
		    
			return State.STANDING;
	    
		
		else return State.DEAD;
		
		
	}

	public void effect(Ball ball) {
		
		
		sound.play(); 
		bb.setScreen(new LevelOne(bb));
		
		
	}

	public void effect() {
		
		sound1.play();
		bb.setScreen(new LevelTwo(bb));
		
		
	}

	public void effectOne() {
		sound1.play();
		bb.setScreen(new LevelThree(bb));
		
	}

	

	public void effectTwo() {
		sound1.play();
	
		bb.setScreen(new MainScreen(bb));
		
	}



	

	
	
}
