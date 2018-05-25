package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


class MyComp implements Comparator<Barrier>{

    @Override
    public int compare(Barrier barrier, Barrier t1) {
        if (barrier.getY()>t1.getY()){
            return 1;
        }else if(barrier.getY()<t1.getY()) {
            return -1;
        }
        return 0;
    }
}

public class Level extends Group {
    private Array<Barrier> barriers = new Array<Barrier>();
    private Map<String, Array<Array<String>>> barrierPos;

    private float delay;
    private boolean playMusic = false;

    public String num;

    static MyGdxGame  game;
    private boolean loose;

   // private final Sound loosed = Gdx.audio.newSound(Gdx.files.internal("sounds/drawn.mp3"));

    public Level(String level,String map,MyGdxGame game) {
        num = level;

        this.game = game;

        //Map<String,Barrier> xml_barriers = xml_parse.XMLparseBarriers();
        barrierPos = game.xmlParse.getPos(level);

        Barrier br;
        for (Map.Entry<String, Array<Array<String>>> entry : barrierPos.entrySet()) {
            String namebr = entry.getKey();
            namebr += map;
            Array<Array<String>> arr = entry.getValue();
            for (int j = 0; j < arr.size; j++) {
                    br =new Barrier(namebr,Float.parseFloat(arr.get(j).get(0)),Float.parseFloat(arr.get(j).get(1)),game);
                    br.setLevel(this);
                    //br.setXY(Float.parseFloat(arr.get(j).get(0)),Float.parseFloat(arr.get(j).get(1)));
                    barriers.add(br);
                    this.addActor(barriers.get(j));
                }
        }

        delay = 0;
        this.loose = false;

        setPlayMusic();


        //Arrays.sort(barriers, new MyComp());
    }

    public boolean isDrown() {
        return this.loose;
    }


    public Array<Barrier> getBarriers() {
        return this.barriers;
    }


    public void setPlayMusic() {
        if (playMusic) {
            playMusic = false;
        } else {
            playMusic = true;
        }
    }



}
