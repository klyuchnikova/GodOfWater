package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.MyGdxGame;

public class Ship extends Actor {
    private Sprite img,shad;
    private Texture img_texture,shad_texture;

    public float least_left,max_right;

    public float speed;
    public float x;
    final private float screen_width;

    private Level level;

    MyGdxGame game;
    public Circle shipCircle;

    public Ship(String str_img,MyGdxGame game) {
        this.game = game;

        img_texture = new Texture("images/ship/" + str_img + ".png");
        shad_texture = new Texture("images/ship/shad.png");
        //img_texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        img = new Sprite(img_texture);
        shad = new Sprite(shad_texture);

        screen_width = Gdx.graphics.getWidth();


        img.setBounds(0,0,screen_width/3,img_texture.getHeight()*screen_width/3/img_texture.getWidth());
        shad.setBounds(0,0,screen_width/3,shad_texture.getHeight()*screen_width/3/img_texture.getWidth());

        Vector2 center = new Vector2();
        center.x=img.getX() + (img.getWidth()/2);
        center.y=img.getY() + (img.getHeight()/2);
        shipCircle = new Circle(center, (img.getHeight()/2));

        least_left=0;
        max_right = screen_width*2/3;


        x = max_right;
        speed = 0;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setSpeed(float speed){ this.speed = speed; }
    public double getSpeed(){ return this.speed; }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
    }


    @Override
    public void act(float delta) {
        checkCollisionsWithBarriers();
        x += speed*delta;
        if (least_left>x ){
            x=least_left;
            speed = 0;
        }
        if (x>max_right){
            x=max_right;
            speed=0;
        }
        shipCircle.x =x;
        img.setX(x);
        shad.setX(x);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        this.shad.draw(batch);
        this.img.draw(batch);
    }
    public Sprite getImg() {
        return this.img;
    }

    public boolean checkCollisionsWithBarriers() {
        for (Barrier br : level.getBarriers()){
            if (Intersector.overlaps(shipCircle, br.brCircle)) {
                game.loose = true;
                return true;
            }
        }
        return false;
    }
}
