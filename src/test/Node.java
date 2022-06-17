package test;

public class Node extends Rect{

	private final Node[] child;
	Rect rectangle;
	
	public Node() {
		child = new Node[2];
	}
	
	public Node(int x, int y, int width, int height) {
		super(x, y, width, height);
		child = new Node[2];
	}
	
	public Node[] insert(Rect rect) {
		if(getWidth() < rect.getWidth() + Atlas.PADDING * 2  || getHeight() < rect.getHeight() + Atlas.PADDING * 2) {
			return null;
		}
		
		this.rectangle = rect;
		rect.setX(getLeft() + Atlas.PADDING);
		rect.setY(getTop() + Atlas.PADDING);
		
		int dw = getWidth() - rectangle.getWidth();
		int dh = getHeight() - rectangle.getHeight();
		
		if(dw > dh) {
			child[0] = new Node(rectangle.getRight(), getTop(), getRight(), getBottom());
			child[1] = new Node(getLeft(), rectangle.getBottom(), rectangle.getRight(), getBottom());
		}else {
			child[0] = new Node(rectangle.getRight(), getTop(), getRight(), rectangle.getBottom());
			child[1] = new Node(getLeft(), rectangle.getBottom(), getRight(), getBottom());
		}
		
		return child;
	}
	

	public boolean isFilled() {
		return child[0] != null && child[1] != null; 
	}
}
