package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.MyGdxGame;

public class Barrier extends Actor {



    static BackgroundWaterActor back;
    private Sound sound;
    private Texture img_texture,light_texture;
    private Sprite img,light;
    public int num;

    private Level level;
    public float speed,y,x;
    private Sprite one,two,three;

    public Circle brCircle;

    public MyGdxGame game;

    public boolean next_level_activate=false;
    private float screen_width,koeff;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Sprite getImg (){return img;}

    public Barrier(Barrier br){

        this.game  = br.game;
        back = game.water_drawer;

        this.koeff = br.koeff;
        this.img = new Sprite(br.img);

        this.y = br.y;
        this.x = br.x;

        Vector2 center = new Vector2();
        center.x=img.getX() + (img.getWidth()/2);
        center.y=img.getY() + (img.getHeight()/2);
        brCircle = new Circle(center, (img.getHeight()/2));
        //this.light = new Sprite(br.light);
    }

    public Barrier(String str_img,float x,float y,MyGdxGame game) {

        this.game  = game;
        back = game.water_drawer;

        screen_width = Gdx.graphics.getWidth();

        img_texture = new Texture("images/barriers/" + str_img + ".png");
        img = new Sprite(img_texture);
        if (str_img.startsWith("l")) {
            koeff  = img_texture.getWidth()/screen_width/3;
            this.x = x*koeff;
            this.y = y*koeff;
            img.setBounds(this.x, this.y+Gdx.graphics.getHeight(), screen_width / 3, img_texture.getHeight() * screen_width / (3 * img_texture.getWidth()));

        }else{
            koeff = img_texture.getWidth()*2/screen_width/3;
            this.x = x*koeff;
            this.y = y*koeff;
            img.setBounds(this.x, this.y+Gdx.graphics.getHeight(), screen_width*2 / 3, img_texture.getHeight() * screen_width*2 /( 3* img_texture.getWidth()));
        }

        Vector2 center = new Vector2();
        center.x=img.getX() + (img.getWidth()/2);
        center.y=img.getY() + (img.getHeight()/2);
        brCircle = new Circle(center, (img.getWidth()/2));


    }

    public void setXY(float x,float y){
        this.x = x*koeff;
        this.y = y*koeff;
        brCircle.x = x;
        brCircle.y = y+Gdx.graphics.getHeight();
        img.setX(x);
        img.setY(y+Gdx.graphics.getHeight());
    }


    @Override
    public void draw(Batch batch, float alpha) {

        this.img.draw(batch);
    }


    @Override
    public void act(float delta) {
        speed = back.speed;
        y -= speed*delta;
        brCircle.y =y+Gdx.graphics.getHeight();
        img.setY(y+Gdx.graphics.getHeight());
    }

}
