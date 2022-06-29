package com.apet2929.game.engine.box2d.entity.states;

import com.apet2929.game.engine.box2d.entity.Direction;
import com.apet2929.game.engine.box2d.entity.Frog;
import com.apet2929.game.engine.box2d.entity.SmartEntity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import static com.apet2929.game.engine.box2d.entity.states.FrogWalkingState.WALKING_FORCE;

public class FrogIdleState extends EntityState{
    public FrogIdleState(SmartEntity entity) {
        super(entity, Frog.IDLE);
    }

    @Override
    public void update(float delta) {
        Frog frog = (Frog) this.entity;

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && frog.canJump()){
            FrogJumpingState.jump(frog);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            frog.applyImpulseToCenter(Direction.LEFT.getVector(), WALKING_FORCE);
            walk();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            frog.applyImpulseToCenter(Direction.RIGHT.getVector(), WALKING_FORCE);
            walk();
        }
        else if(frog.getNumFootContacts() == 0){
            frog.changeState(Frog.JUMPING);
        }

    }

    @Override
    public void onEnter() {
        this.entity.changeAnimation(Frog.IDLE);
    }

    @Override
    public void onExit() {

    }
    void walk(){
        this.entity.changeState(Frog.WALKING);
    }
}