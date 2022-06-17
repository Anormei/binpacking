package test;

public class Rect {

    public static float TO_RADIANS = (1.0f / 180.0f) * (float) Math.PI;
    
	private int left;
	private int top;
	private int right;
	private int bottom;
	
    protected float[] rotationMatrix = new float[]{
            1,  0,
            0,  1

    };
	
	public Rect(){
		
	}
	
	public Rect(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public Rect(Rect rect) {
		this.left = rect.left;
		this.top = rect.top;
		this.right = rect.right;
		this.bottom = rect.bottom;
	}
	
	public void set(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public void setX(int x) {
		this.right = x + getWidth();
		this.left = x;
	}
	
	public void setY(int y) {
		this.bottom = y + getHeight();
		this.top = y;
	}
	
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public void setTop(int top) {
		this.top = top;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	
    public void rotate(float angle) {
        //float halfWidth = getWidth() / 2;
        //float halfHeight = getHeight() / 2;

        float rad = angle * TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        rotationMatrix[0] = cos;
        rotationMatrix[1] = -sin;
        rotationMatrix[2] = sin;
        rotationMatrix[3] = cos;
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
	
	public int getWidth() {
		return right - left;
	}
	
	public int getHeight() {
		return bottom - top;
	}
}
