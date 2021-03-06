package com.apet2929.game.engine.box2d.entity;

import com.apet2929.game.engine.Animation;
import com.apet2929.game.engine.box2d.entity.states.StateMachine;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public abstract class Entity{
    Sprite sprite;
    Body body;
    EntityType entityType;

    public Entity(EntityType entityType) {
        this.entityType = entityType;
        initAssets();

    }

    /*
     *   Initializes any animations used and the sprite
     */
    public abstract void initAssets();
    public void update(float delta){}
    public void render(SpriteBatch sb){
        sprite.setCenter(body.getPosition().x, body.getPosition().y);
        sprite.draw(sb);
    }

    public Vector2 getPosition(){
        return new Vector2(this.body.getPosition());
    }

    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void applyForceToCenter(Vector2 direction, float magnitude){
        this.body.applyForceToCenter(direction.cpy().scl(magnitude), true);
    }

    public void applyImpulseToCenter(Vector2 direction, float magnitude){
        this.body.applyLinearImpulse(direction.cpy().scl(magnitude), this.getPosition(), true);
    }

    public void setUserData(String data){
        this.body.getFixtureList().get(0).setUserData(data);
    }
}
