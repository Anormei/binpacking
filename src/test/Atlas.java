package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Atlas extends Rect{
	
	public static final int PADDING = 2;

	private List<Node> nodes = new ArrayList<>();
	List<Node> activeNodes = new ArrayList<>();
	
	public Atlas(int width, int height) {
		set(0, 0, width, height);
	}
	
	public static Atlas createAtlas(List<Rect> rectangles) {
		int total = 0;
		
		for(int i = 0; i < rectangles.size(); i++) {
			Rect rect = rectangles.get(i);
			total += rect.getWidth() * rect.getHeight() + PADDING * 4;
		}
		
		int size = 2;
		
		while(size * size < total * 2) {
			size *= 2;
		}
		System.out.println("max size = " + size + ", ratio = " + (size * size) + ":" + total);
		return new Atlas(size, size);
		
	}
	
	public boolean insertRect(Rect rect) {
		Node node;
		
		if(nodes.isEmpty()) {
			node = new Node(0, 0, getWidth(), getHeight());
			nodes.add(node);
			
			Node[] child = node.insert(rect);
			
			nodes.add(child[0]);
			nodes.add(child[1]);
			
			activeNodes.add(child[0]);
			activeNodes.add(child[1]);
			return true;
		}else{
			
			for(Iterator<Node> iterator = activeNodes.iterator(); iterator.hasNext();) {
				node = iterator.next();
				if(node.isFilled()) {
					iterator.remove();
				}else {
					
					Node[] child = node.insert(rect);
						
					if(child != null) {
						nodes.add(child[0]);
						nodes.add(child[1]);
							
						activeNodes.add(child[0]);
						activeNodes.add(child[1]);
						return true;
					}
				}
			}
		
		}
		
		System.out.println("No room: " + "width = " + rect.getWidth() + ", height = " + rect.getHeight());
		return false;
	}
	
	public void extendAtlas() {
		
		int newSize = getWidth() * 2;
		
		for(int i = 0; i < activeNodes.size(); i++){
            Node node = activeNodes.get(i);
            if(node.getRight() == getWidth()){
                node.setRight(newSize);
            }

            if(node.getBottom() == getHeight()){
                node.setBottom(newSize);
            }
        }

        setRight(newSize);
        setBottom(newSize);
		
		System.out.println("Extended atlas - New size: width = " + getWidth() + ", height = " + getHeight());
	}
	
}
