package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class BackgroundMenuActor extends Actor {
    AnimatedSprite anim = new AnimatedSprite(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,Gdx.files.internal("images/background/fires.gif").read()));
    float elapsed;

    private Texture backgroundTexture,shadowTexture;
    private Sprite backgroundSprite,shadowSprite;
    private float n = (float)-0.9;
    public BackgroundMenuActor() {

        anim.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        backgroundTexture = new Texture("images/background/menu_withwr.png");
        backgroundSprite = new Sprite(backgroundTexture);
        //shadowTexture = new Texture("images/menu_shadows.jpg");
        //shadowSprite = new Sprite(shadowTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //shadowSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {

        backgroundSprite.draw(batch);
        anim.draw(batch,0.1f);
        //shadowSprite.draw(batch,n);
    }
}
