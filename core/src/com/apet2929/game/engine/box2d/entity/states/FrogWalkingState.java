package com.apet2929.game.engine.box2d.entity.states;

import com.apet2929.game.engine.box2d.entity.Direction;
import com.apet2929.game.engine.box2d.entity.Frog;
import com.apet2929.game.engine.box2d.entity.SmartEntity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import static com.apet2929.game.engine.Utils.WALKING_FORCE;

public class FrogWalkingState extends FrogState{
    public FrogWalkingState(SmartEntity entity) {
        super(entity, Frog.WALKING);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        Frog frog = (Frog) this.entity;
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && frog.canJump()){
            frog.changeState(Frog.JUMP_CHARGING);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            frog.applyForceToCenter(Direction.LEFT.getVector(), WALKING_FORCE);
            frog.setDirection(Direction.LEFT);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            frog.applyForceToCenter(Direction.RIGHT.getVector(), WALKING_FORCE);
            frog.setDirection(Direction.RIGHT);
        }
        else if(frog.getNumFootContacts() == 0){
            frog.changeState(Frog.JUMPING);
        }
        else {
            this.entity.changeState(Frog.IDLE);
        }

    }

    @Override
    public void onEnter() {
        this.entity.changeAnimation(Frog.WALKING);
    }

    @Override
    public void onExit() {
        super.onExit();
    }


}
