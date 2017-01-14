package com.mygdx.game.tools;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.BallBoomer;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.CenterSpring;
import com.mygdx.game.sprites.PurpleBall;
import com.mygdx.game.sprites.SideSpring;




public class WorldContactListner implements ContactListener {

    public BallBoomer bb=new BallBoomer();
   /* public LevelThree lthree=new LevelThree(bb);
	public Ball ball;*/
	@Override
	public void beginContact(Contact contact) {

		
		Fixture fixA=contact.getFixtureA();
		Fixture fixB=contact.getFixtureB();
		
		  int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

	        switch (cDef){
	            case BallBoomer.BIT_BALL|BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_PURPLEBALL|BallBoomer.BIT_SIDESPRING:
	            	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_BALL)
	            		((Ball) fixA.getUserData()).effect((Ball) fixA.getUserData());	   
	            	else
	            	((Ball) fixB.getUserData()).effect((Ball) fixA.getUserData());
	            	break;
	          
	           case BallBoomer.BIT_BALL|BallBoomer.BIT_HOME:
	            	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_BALL)
	            	{	            		
	            		((Ball) fixA.getUserData()).effect();
	            	}
	            	   
	            	else
	            	((Ball) fixB.getUserData()).effect();
	            	
	            	break;
	 
	             case BallBoomer.BIT_BALL|BallBoomer.BIT_HOMETWO:
	  	            	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_BALL)
	  	            	{
	  	            			  	            		
	  	            	    ((Ball) fixA.getUserData()).effectOne();	  	            	    	  	            	   
	  	            	}
	  	            	   
	  	            	else	  	            
	  	            	((Ball) fixB.getUserData()).effectOne();	  	            
	  	            	break;
	          
	  	          case BallBoomer.BIT_BALL|BallBoomer.BIT_HOMETHREE:
  	            	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_BALL)
  	            	{
  	            		
  	            		
  	            	    ((Ball) fixA.getUserData()).effectTwo();
  	            	   
  	            	}
  	            	   
  	            	else
  	            
  	            	((Ball) fixB.getUserData()).effectTwo();
  	            	
  	            	break;
  	            	
	  	     /* case BallBoomer.BIT_CENTERSPRING|BallBoomer.BIT_GROUND:
	  	        	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_CENTERSPRING)
  	            	{
  	            		
  	            		
  	            	    ((CenterSpring) fixA.getUserData()).effect();
  	            	   
  	            	   
  	            	}
  	            	   
  	            	else
  	            
  	            	((CenterSpring) fixB.getUserData()).effect();
	  	        	
  	            
  	            	break;
  	            	
	  	    case BallBoomer.BIT_SIDESPRING|BallBoomer.BIT_GROUND:
  	        	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_SIDESPRING)
	            	{
	            		
	            		
	            	    ((SideSpring) fixA.getUserData()).effect();
	            	   
	            	   
	            	}
	            	   
	            	else
	            
	            	((SideSpring) fixB.getUserData()).effect();
  	        	
	            
	            	break;
	  	  
	  	    case BallBoomer.BIT_PURPLEBALL|BallBoomer.BIT_GROUND:
	        	if(fixA.getFilterData().categoryBits==BallBoomer.BIT_PURPLEBALL)
	            	{
	            		
	            		
	            	    ((PurpleBall) fixA.getUserData()).effect();
	            	   
	            	   
	            	}
	            	   
	            	else
	            
	            	((PurpleBall) fixB.getUserData()).effect();
	        	
	            
	            	break;*/
  	            	
	  	            	
		 
	          
	         
	        }
	    }
		
	@Override
	public void endContact(Contact contact) {

		
	}

	
	
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
		
	}

}
