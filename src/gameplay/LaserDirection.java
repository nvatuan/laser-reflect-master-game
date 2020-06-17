package gameplay;

public class LaserDirection {
	public static final Integer D_UP = -1, D_DOWN = 1, D_LEFT = -1, D_RIGHT = 1, D_STILL = 0;
	public static final Integer D_WIN = 9;
	public static final Integer D_NULL = null;
	
	public static final LaserDirection LaserWin = new LaserDirection(D_WIN, D_WIN);
	public static final LaserDirection LaserLose = new LaserDirection(D_NULL, D_NULL);
	public static final LaserDirection 
		LaserUp = new LaserDirection(D_UP, D_STILL),
		LaserRight = new LaserDirection(D_STILL, D_RIGHT),
		LaserDown = new LaserDirection(D_DOWN, D_STILL),
		LaserLeft = new LaserDirection(D_STILL, D_LEFT);
	
	private Integer dx, dy;
	public Integer getDx() { return this.dx; }
	public Integer getDy() { return this.dy; }
	
	// -- Constructors
	private LaserDirection(Integer ddx, Integer ddy) {
		this.dx = ddx; this.dy = ddy;
	}
	public LaserDirection(LaserDirection ref) {
		this.dx = ref.dx;
		this.dy = ref.dy;
	}
	// -- methods
	public LaserDirection flip() {
		if (this.equals(LaserUp)) return LaserDown;
		if (this.equals(LaserDown)) return LaserUp;
		if (this.equals(LaserLeft)) return LaserRight;
		if (this.equals(LaserRight)) return LaserLeft;
		return this;
	}
	
	// -- equals
	@Override
	public boolean equals(Object obj) {
		LaserDirection o = ((LaserDirection) obj);
		if (this.dx == o.dx && this.dy == o.dy) return true;
		return false;
	}
	
	// -- toString	
	@Override
	public String toString() {
		if (this.equals(LaserDirection.LaserUp)) return "LaserUp";
		if (this.equals(LaserDirection.LaserRight)) return "LaserRight";
		if (this.equals(LaserDirection.LaserDown)) return "LaserDown";
		if (this.equals(LaserDirection.LaserLeft)) return "LaserLeft";
		if (this.equals(LaserDirection.LaserWin)) return "LaserWin";
		if (this.equals(LaserDirection.LaserLose)) return "LaserLose";
		return "Invalid Laser";
	}
	
	// -- LaserDirection boolean checker functions
	public boolean isStraight() {
		return ((this.dx * this.dy == 0) && (this.dx != this.dy));
	}
	public boolean isUp() {
		return (this.isStraight() && this.dx == LaserDirection.D_UP);
	}
	public boolean isRight() {
		return (this.isStraight() && this.dy == LaserDirection.D_RIGHT);
	}
	public boolean isDown() {
		return (this.isStraight() && this.dx == LaserDirection.D_DOWN);
	}
	public boolean isLeft() {
		return (this.isStraight() && this.dy == LaserDirection.D_LEFT);
	}
	
	public boolean isStuck() {
		return (this.dx == 0 && this.dy == 0);
	}
	public boolean isWin() {
		return (this.dx == LaserDirection.D_WIN) && (this.dy == LaserDirection.D_WIN);
	}
	public boolean isLose() {
		return (this.dx == LaserDirection.D_NULL) && (this.dy == LaserDirection.D_NULL);
	}
}
