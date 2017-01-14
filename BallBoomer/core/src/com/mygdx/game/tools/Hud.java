package com.mygdx.game.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BallBoomer;


public class Hud {
   public Stage stage;
   public Viewport vp;
   public ImageButton btleft,btright,btup;
   public Skin skin;
   public TextureAtlas atlas;
  
  


  
   public Hud(SpriteBatch sb)
   {
	   
	 atlas=new TextureAtlas("buttons.pack");
       vp=new FitViewport(BallBoomer.V_WIDTH/BallBoomer.PPM, BallBoomer.V_HEIGHT/BallBoomer.PPM, new OrthographicCamera());	   
       stage=new Stage(vp,sb);
      skin=new Skin();
      
      
     skin.getFont("click");
      
      



     
    		 
 
       Table table=new Table();
	   table.bottom();
	   table.setFillParent(true);
	   table.add(btleft);
	   table.add(btright);
	   table.add(btup);
	   table.row();
	   stage.addActor(table);
   }

	
	
	
	
	
	
	
	
}
