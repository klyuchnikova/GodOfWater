package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.BackgroundMenuActor;

public class MainMenuScreen implements Screen {

    final MyGdxGame game;

    private SpriteBatch batch;

    private Stage stage;
    private TextButton play ;
    //private TextButton shop,settings;
    private Table table;
    private Label.LabelStyle labelStyle;

    public MainMenuScreen(final MyGdxGame gam) {
        game = gam;

        stage = new Stage();


        //das ist fignya which may not work
        batch = (SpriteBatch) stage.getBatch();

        stage.addActor(game.menu_drawer);


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
        table = new Table();
        table.setFillParent(true);

        play = new TextButton(game.langStr.get("Play"), textButtonStyle);

        play.setBounds(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,play.getWidth(),play.getHeight());

        stage.addActor(play);

        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                dispose();
            };
        });
        /*
        settings = new TextButton(game.langStr.get("Settings"), textButtonStyle);
        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingsScreen(game));
                dispose();
            };
        });
        shop = new TextButton(game.langStr.get("Shop"), textButtonStyle);
        shop.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ShopScreen(game));
                dispose();
            };
        });
        */
        //table.add(play);
        //table.row();
        //table.add(shop);
        //table.row();
        //table.add(settings);
        //stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        game.dispose();
    }
}