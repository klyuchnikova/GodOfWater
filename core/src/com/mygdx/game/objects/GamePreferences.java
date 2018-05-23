package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {
    private Preferences pref;

    private static final String PREFS_NAME = "God_Of_Water";
    private static final String PREF_LEVEL = "LEVEL_";
    private static final String PREF_LANG = "RUSSIAN";

    public GamePreferences() {
        pref = Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean getLevel(String level) {
        pref.putBoolean(PREF_LEVEL + 1, true);
        pref.flush();
        return pref.getBoolean(PREF_LEVEL + level, false);
    }

    public void setLevel(String level) {
        pref.putBoolean(PREF_LEVEL + level, true);
        pref.flush();
    }

    public boolean getLanguage(String level) {
        pref.putBoolean(PREF_LANG, true);
        pref.flush();
        return pref.getBoolean(PREF_LANG, false);
    }

    public void setLanguage(String level) {
        pref.putBoolean(PREF_LANG, true);
        pref.flush();
    }
}
