package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.BackgroundWaterActor;
import com.mygdx.game.objects.Barrier;
import com.mygdx.game.objects.GamePreferences;
import com.mygdx.game.objects.Level;
import com.mygdx.game.objects.PlayStage;
import com.mygdx.game.objects.Ship;

import java.util.Map;
import java.util.Random;


public class PlayScreen  implements Screen {

    final MyGdxGame game;
    final float screen_width, screen_height;

    private GamePreferences pref;

    private int num_level;
    public String name_level,map;

    Vector3 touchPos;

    public int money;
    private Level current_level,prev_Level;
    private Map<String,Barrier> barriers;
    private PlayStage stage;
    private Label.LabelStyle labelStyle;
    private Ship ship;

    private boolean time_to_change = false;

    public float screen_speed;
    public float max_y_screen;
    public boolean next_level_activate = false;

    public Table table = new Table();
    public Table table2 = new Table();

    final Random random = new Random();

    public ParticleEffect pe;

    private SpriteBatch batch;

    public PlayScreen(final MyGdxGame gam) {
        ship = new Ship("ship2",gam);


        //num_level = random.nextInt()%10;
        num_level = 1;
        name_level = "1";

        map = "o";
        //pe = new ParticleEffect();

        next_level_activate = false;
        screen_speed = 10;

        screen_width = Gdx.graphics.getWidth();
        screen_height = Gdx.graphics.getHeight();

        game = gam;
        touchPos = new Vector3();

        game.play_music.setVolume(0.4f);
        game.play_music.play();
        game.play_music.setLooping(true);

        stage = new PlayStage(new ScreenViewport());
        batch = (SpriteBatch) stage.getBatch();

        current_level = new Level(name_level,map,game);
        ship.setLevel(current_level);

        stage.addActor(game.water_drawer);
        stage.addActor(game.front_water_drawer);
        stage.addActor(current_level);
        stage.addActor(ship);


        max_y_screen = game.water_drawer.getHeight()-screen_height;

        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("images/buttons/images.pack"));
        skin.addRegions(buttonAtlas);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font;
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
        textButtonStyle.checked = skin.getDrawable("button-up");
        labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font;

        table.padTop(20);
        table.center().top();
        table.setFillParent(true);
        Label label = new Label(game.langStr.get("End"), labelStyle);
        table.add(label);
        table.setVisible(false);
        stage.addActor(table);

        table2.center().bottom();
        table2.setFillParent(true);
        TextButton button = new TextButton(gam.langStr.get("Again"), textButtonStyle);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                table.setVisible(false);
                table2.setVisible(false);
                game.loose = false;
                game.water_drawer = new BackgroundWaterActor();
                game.setScreen(new PlayScreen(game));
                game.loose = false;
                dispose();
            };
        });
        table2.add(button);
        table2.setVisible(false);
        stage.addActor(table2);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {

    }

    public void setCamera(float x, float y) {
        stage.getCamera().position.set(x, y, 0);
        stage.getCamera().update();

    }


    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //game.play_music.play();

        ship.act(delta);
        current_level.act(delta);
        game.water_drawer.act(delta);
        game.front_water_drawer.act(delta);
        stage.act(delta);
        stage.draw();

       if (isLevelChanged()){
           if (!time_to_change) {
               time_to_change = true;
               ChangeMap();
               prev_Level.act(delta);
           }
        }else if (time_to_change){
            time_to_change = false;
            ship.setLevel(current_level);
            if (prev_Level != null)
                prev_Level.remove();
        }

        if (isGameOver()) {
            game.play_music.setVolume(0.1f);
            table.setVisible(true);
            table2.setVisible(true);
        } else {
            if (Gdx.input.isTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.water_drawer.speed += screen_height / 15 * delta;
                if (touchPos.x > screen_width / 2) {
                    ship.speed += screen_width / 6 * delta;
                } else {
                    ship.speed -= screen_width / 6 * delta;
                }
            } else if (ship.speed > 0.5) {
                ship.speed -= ship.speed / 2 * delta;
            }
            if (game.water_drawer.speed > 20) {
                game.water_drawer.speed -= game.water_drawer.speed / 2 * delta;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        //game.dispose();
    }
    public boolean isGameOver(){
        return game.loose;
    }
    public boolean isLevelChanged(){
        return game.water_drawer.next_level_activate;
    }

    public void ChangeMap(){
        if (game.water_drawer.map==0){
            map = "r";
        }else if (game.water_drawer.map==1){
            map = "o";
        }else{
            map = "s";
        }

        int mapchic = Math.abs(random.nextInt())%5;
        if (mapchic == Integer.parseInt(current_level.num)){
            mapchic = (mapchic+1)%5;
        }
        prev_Level = current_level;
        current_level = new Level(Integer.toString(mapchic),map,game);
        stage.addActor(current_level);
        //prev_Level.remove();
    }
}
