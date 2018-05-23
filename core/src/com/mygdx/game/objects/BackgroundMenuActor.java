package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundMenuActor extends Actor {
    private Texture backgroundTexture,shadowTexture;
    private Sprite backgroundSprite,shadowSprite;
    private float n = (float)-0.9;
    public BackgroundMenuActor() {
        backgroundTexture = new Texture("images/background/menu.png");
        backgroundSprite = new Sprite(backgroundTexture);
        //shadowTexture = new Texture("images/menu_shadows.jpg");
        //shadowSprite = new Sprite(shadowTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //shadowSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {

        backgroundSprite.draw(batch);
        //shadowSprite.draw(batch,n);
    }
}
