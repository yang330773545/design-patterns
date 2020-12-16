package main;

// 矩形具有坐标，可以检查是否与其他矩形重叠。
public class Rectangle {
	private final int left;
	private final int top;
	private final int right;
	private final int bottom;
	
	public Rectangle(final int left, final int top, final int right, final int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}
	
	boolean intersectsWith(Rectangle r) {
		return !(r.getLeft() > getRight() || r.getRight() < getLeft() || r.getTop() > getBottom() || r.getBottom() < getTop());
	}

    @Override
    public String toString() {
    	return String.format("[%d,%d,%d,%d]", getLeft(), getTop(), getRight(), getBottom());
    }
	
}
