package main;

// 具有坐标和其他一些信息
public abstract class GameObject extends Rectangle {
	
	private boolean damaged;
	private boolean onFire;

	public GameObject(int left, int top, int right, int bottom) {
		super(left, top, right, bottom);
	}
	@Override
	public String toString() {
		return String.format("%s at %s damaged=%b onFire=%b", this.getClass().getSimpleName(), super.toString(), isDamaged(), isOnFire());
	}

	public boolean isDamaged() {
		return damaged;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}

	public boolean isOnFire() {
		return onFire;
	}

	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

	public abstract void collision(GameObject gameObject);

	public abstract void collisionResolve(FlamingAsteroid asteroid);

	public abstract void collisionResolve(Meteoroid meteoroid);

	public abstract void collisionResolve(SpaceStationMir mir);

	public abstract void collisionResolve(SpaceStationIss iss);
}
