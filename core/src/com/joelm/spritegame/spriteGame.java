package com.joelm.spritegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class spriteGame extends ApplicationAdapter {
	private static final int FRAME_COLS = 4, FRAME_ROWS = 1;
	Animation<TextureRegion> topAnimation, leftAnimation, rightAnimation, bottomAnimation, idleAnimation;
	Texture idleSheet, topSheet, leftSheet, rightSheet, bottomSheet;
	SpriteBatch batch;
	float stateTime;
	int posX = 0;
	int posY = 0;

	
	@Override
	public void create() {
		idleSheet = new Texture(Gdx.files.internal("spriteIdle.png"));
		topSheet = new Texture(Gdx.files.internal("spriteTop.png"));
		leftSheet = new Texture(Gdx.files.internal("spriteLeft.png"));
		rightSheet = new Texture(Gdx.files.internal("spriteRight.png"));
		bottomSheet = new Texture(Gdx.files.internal("spriteBottom.png"));

		TextureRegion[][] tmp = TextureRegion.split(topSheet, topSheet.getWidth() / FRAME_COLS, topSheet.getHeight() / FRAME_ROWS);
		topAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);

		tmp = TextureRegion.split(leftSheet, leftSheet.getWidth() / FRAME_COLS, leftSheet.getHeight() / FRAME_ROWS);
		leftAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);

		tmp = TextureRegion.split(rightSheet, rightSheet.getWidth() / FRAME_COLS, rightSheet.getHeight() / FRAME_ROWS);
		rightAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);

		tmp = TextureRegion.split(bottomSheet, bottomSheet.getWidth() / FRAME_COLS, bottomSheet.getHeight() / FRAME_ROWS);
		bottomAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);

		tmp = TextureRegion.split(idleSheet, idleSheet.getWidth(), idleSheet.getHeight());
		idleAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);

		batch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = idleAnimation.getKeyFrame(stateTime,true);;
		int speed = 100;
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			currentFrame = topAnimation.getKeyFrame(stateTime, true);
			posY += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			currentFrame = leftAnimation.getKeyFrame(stateTime, true);
			posX -= speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			currentFrame = rightAnimation.getKeyFrame(stateTime,true);
			posX += speed * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			currentFrame = bottomAnimation.getKeyFrame(stateTime, true);
			posY -= speed * Gdx.graphics.getDeltaTime();
		}


		batch.begin();
		batch.draw(currentFrame, posX, posY);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		idleSheet.dispose();
		topSheet.dispose();
		leftSheet.dispose();
		rightSheet.dispose();
		bottomSheet.dispose();
	}
}
