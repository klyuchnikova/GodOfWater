package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class BackgroundWaterActor extends Actor {
    private Texture river,ocean,stones;
    public int map,prev_map;
    private String [] maps;
    public float speed,y,y_prev,min_y;
    private Sprite one,two,three;

    public boolean next_level_activate=false;
    private float screen_width;

    Random rnd = new Random();

    private Sprite[] backgroundSprites;
    public BackgroundWaterActor() {
        screen_width = Gdx.graphics.getWidth();

        river = new Texture(Gdx.files.internal("images/maps/river/river3.jpg"));
        ocean = new Texture(Gdx.files.internal("images/maps/ocean/sand3.jpg"));
        stones = new Texture(Gdx.files.internal("images/maps/stones/forest.png"));

        one = new Sprite(river);
        two = new Sprite(ocean);
        three = new Sprite(stones);

        one.setBounds(0,0,screen_width*3,river.getHeight()*screen_width*3/river.getWidth());
        one.setX(-screen_width);
        one.setY(0);

        two.setBounds(0,0,screen_width*3,ocean.getHeight()*screen_width*3/ocean.getWidth());
        two.setX(-screen_width);
        two.setY(0);

        three.setBounds(0,0,screen_width*3,stones.getHeight()*screen_width*3/stones.getWidth());
        three.setX(-screen_width);

        backgroundSprites = new Sprite[]{one,two,three};

        maps = new String[]{"river", "ocean", "stones"};

        map =1;
        speed = 20;
        y=0;
        min_y = -(river.getHeight()-Gdx.graphics.getHeight());
        //shadowSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {

        if (y>=0){
            backgroundSprites[prev_map].draw(batch,10);
        }else if (next_level_activate){
            next_level_activate = false;
        }
        backgroundSprites[map].draw(batch,10);
        //shadowSprite.draw(batch,n);
    }

    @Override
    public void act(float delta) {
        y-=speed*delta;
        if (y<=min_y) {
            if (!next_level_activate) {
                next_level_activate = true;
                NextMap();
            }
        }
        backgroundSprites[map].setY(y);
        if (y>=0){
            y_prev -= speed*delta;
            backgroundSprites[prev_map].setY(y_prev);
        }
    }

    public void NextMap(){
        prev_map = map;
        y_prev = y;
        map = (rnd.nextInt()%3+3)%3;
        if (map==prev_map){
            map = (prev_map+2)%3;
        }
        y=Gdx.graphics.getHeight();
    }
}