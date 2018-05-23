package com.mygdx.game.objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PlayStage extends Stage{
    public ScreenViewport port;
    public PlayStage(ScreenViewport screenViewport) {
        super(screenViewport);
        port = screenViewport;
    }
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK) {
            if(getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 1);
        }
        return super.keyDown(keyCode);
    }

    public Camera getCamera(){
        return port.getCamera();
    }

    @Override
    public boolean keyUp(int keyCode) {
        if(keyCode == Input.Keys.BACK){
            if(getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 0);
        }
        return super.keyUp(keyCode);
    }

    public interface OnHardKeyListener{
        /**
         * Happens when user press hard key
         * @param keyCode Back or Menu key (keyCode one of the constants in Input.Keys)
         * @param state 1 - key down, 0 - key up
         */
        public abstract void onHardKey(int keyCode, int state);
    }
    private OnHardKeyListener _HardKeyListener = null;
    public void setHardKeyListener(OnHardKeyListener HardKeyListener) {
        _HardKeyListener = HardKeyListener;
    }
    public OnHardKeyListener getHardKeyListener() {
        return _HardKeyListener;
    }
}
