package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.managers.MainMenuScreen;
import com.mygdx.game.objects.BackgroundMenuActor;
import com.mygdx.game.objects.BackgroundWaterActor;
import com.mygdx.game.objects.ForwardWaterAtor;
import com.mygdx.game.objects.XMLparse;

import java.util.HashMap;

public class MyGdxGame extends Game {


	public BitmapFont font, levels;
	private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

	public Music play_music;
	public BackgroundMenuActor menu_drawer;
	public BackgroundWaterActor water_drawer;
	public ForwardWaterAtor front_water_drawer;

	public XMLparse xmlParse;

	public HashMap<String, String> langStr = new HashMap<String, String>();

	public MyGdxGame(){ }

	public OrthographicCamera cam;
	public OrthographicCamera twocam;

	public boolean loose = false;

	@Override
	public void create () {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/russian.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = Gdx.graphics.getHeight() / 18;
		param.characters = FONT_CHARACTERS;
		font = generator.generateFont(param);
		param.size = Gdx.graphics.getHeight() / 20;
		levels = generator.generateFont(param);
		font.setColor(Color.WHITE);
		levels.setColor(Color.WHITE);
		generator.dispose();

		play_music = Gdx.audio.newMusic(Gdx.files.internal("MusicTailand.mp3"));
		play_music.setVolume(0.5f);
		//play_music.play();

		menu_drawer = new BackgroundMenuActor();
		menu_drawer.setPosition(0,0);

		water_drawer = new BackgroundWaterActor();
		water_drawer.setPosition(0,0);

		front_water_drawer = new ForwardWaterAtor();
        front_water_drawer.setPosition(0,0);

		xmlParse = new XMLparse(this);
		String locale = java.util.Locale.getDefault().toString().split("_")[0];
		langStr = xmlParse.XMLparseLangs(locale);

		this.setScreen(new MainMenuScreen(this));
	}
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		play_music.dispose();
	}


}
