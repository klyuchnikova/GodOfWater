package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ForwardWaterAtor extends Actor {
    Animation anim = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL,Gdx.files.internal("my-gif-anumation.gif").read());

    private Texture river,ocean,stones;
    public int map;
    private String [] maps;
    public double speed;
    private Sprite one,two,three;
    private Sprite[] backgroundSprites;

    @Override
    public void draw(Batch batch, float alpha) {

        backgroundSprites[map].draw(batch,100);
        //shadowSprite.draw(batch,n);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}