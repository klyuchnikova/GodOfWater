package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

import javax.swing.ButtonGroup;
import javax.swing.plaf.basic.BasicBorders;

public class SettingsScreen implements Screen {

    final MyGdxGame game;


    private Stage stage;
    private Label.LabelStyle labelStyle;
    private Label lang;
    private Table table;
    private CheckBox russian,english;


    ButtonGroup buttonGroup;

    SettingsScreen(final MyGdxGame gam){
        game = gam;
        stage = new Stage(new ScreenViewport());

        stage.addActor(game.menu_drawer);

        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("images/game/images.pack"));
        skin.addRegions(buttonAtlas);
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = game.font;
        checkBoxStyle.up = skin.getDrawable("checkbutton-up");
        checkBoxStyle.down = skin.getDrawable("checkbutton-down");
        checkBoxStyle.checked = skin.getDrawable("checkbutton-up");


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
