package main;

// 游戏对象燃烧的陨石
public class FlamingAsteroid extends Meteoroid {

	public FlamingAsteroid(int left, int top, int right, int bottom) {
		super(left, top, right, bottom);
		setOnFire(true);
	}

	@Override
	public void collision(GameObject gameObject) {
		gameObject.collisionResolve(this);
	}
}
