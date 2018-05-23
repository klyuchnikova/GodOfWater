package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FrontWaterActor extends Actor {
        private Texture lightwater;
        public double speed;
        private Sprite waterSprite;
        public FrontWaterActor() {
            lightwater = new Texture("images/darkwater.png");
            waterSprite = new Sprite(lightwater);
            //shadowTexture = new Texture("images/menu_shadows.jpg");
            //shadowSprite = new Sprite(shadowTexture);
            waterSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            //shadowSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        @Override
        public void draw(Batch batch, float alpha) {

            waterSprite.draw(batch);
            //shadowSprite.draw(batch,n);
        }

    }
