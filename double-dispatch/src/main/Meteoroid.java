package main;

import main.constants.AppConstants;

// 游戏对象陨石
public class Meteoroid extends GameObject {

	public Meteoroid(int left, int top, int right, int bottom) {
		super(left, top, right, bottom);
	}

	@Override
	public void collision(GameObject gameObject) {
		gameObject.collisionResolve(this);	
	}

	@Override
	public void collisionResolve(FlamingAsteroid asteroid) {
		System.out.println(String.format(AppConstants.HITS, asteroid.getClass().getSimpleName(), this.getClass().getSimpleName()));
	}

	@Override
	public void collisionResolve(Meteoroid meteoroid) {
		System.out.println(String.format(AppConstants.HITS, meteoroid.getClass().getSimpleName(), this.getClass().getSimpleName()));
	}

	@Override
	public void collisionResolve(SpaceStationMir mir) {
		System.out.println(String.format(AppConstants.HITS, mir.getClass().getSimpleName(), this.getClass().getSimpleName()));
	}

	@Override
	public void collisionResolve(SpaceStationIss iss) {
		System.out.println(String.format(AppConstants.HITS, iss.getClass().getSimpleName(), this.getClass().getSimpleName()));
	}

}
