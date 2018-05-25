package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class ForwardWaterAtor extends Actor {
    AnimatedSprite anim = new AnimatedSprite(GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP_PINGPONG,Gdx.files.internal("images/background/water4.gif").read()));

    float elapsed;
    public double speed;

    public ForwardWaterAtor(){

        anim.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //anim.rotate90(true);

    }

    @Override
    public void draw(Batch batch, float alpha) {
        anim.draw(batch,0.4f);
    }

    @Override
    public void act(float delta) {
        elapsed += delta;
    }
}