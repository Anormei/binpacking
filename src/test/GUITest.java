package test;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUITest extends JFrame{
	
	private static final Random r = new Random();
	private static final int SIZE = 50;
	private static final int MIN_SIZE = 5;
	private static final int BOXSIZE = 512;
	private static final int XOFFSET = 20;
	private static final int YOFFSET = 20;
	private static final int ITEM_SIZE = 270;
	private List<JPanel> panels = new ArrayList<>();
	private List<Rect> rectangles = new ArrayList<>();
	
	private Atlas atlas;
	
	public GUITest() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLocation(XOFFSET, YOFFSET);
		panel.setSize(BOXSIZE, BOXSIZE);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setOpaque(false);
		add(panel);
		
		setSize(600, 600);
		//addRectangle(XOFFSET, 0, 100, 20);
		doPacking();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUITest();
	}
	
	public void doPacking() {
		//atlas = new Atlas(BOXSIZE, BOXSIZE);
		for(int i = 0; i < ITEM_SIZE; i++) {
			
			int w = r.nextInt(SIZE - MIN_SIZE) + MIN_SIZE;
			int h = r.nextInt(SIZE - MIN_SIZE) + MIN_SIZE;
			
			rectangles.add(new Rect(0, 0, w, h));
		}
		
		int count = 0;
		int[] largestUnfit = new int[] {0,0};
		
		long timestamp = System.nanoTime();
		/*rectangles.sort(new Comparator<Rect>() {

			@Override
			public int compare(Rect rect1, Rect rect2) {
				int a = rect1.getWidth() * rect1.getHeight();
				int b = rect2.getWidth() * rect2.getHeight();
				return b-a;
			}
			
		});*/
		atlas = new Atlas(BOXSIZE, BOXSIZE);
		
		Rect previous;
		/*for(Iterator<Rect> iterator = rectangles.iterator(); iterator.hasNext();) {
			Rect rect = iterator.next();
			if(!atlas.insertRect(rect)) {
				/*if(rect.getWidth() > largestUnfit[0] && rect.getHeight() > largestUnfit[1]) {
					largestUnfit[0] = rect.getWidth();
					largestUnfit[1] = rect.getHeight();
				}
				
				iterator.remove();
				count++;
			}
			previous = rect;
		}*/
		
		for(int i = 0; i < rectangles.size(); i++) {
			Rect rect = rectangles.get(i);
			if(!atlas.insertRect(rect)) {
				atlas.extendAtlas();
				i--;
			}
		}

		/*try {
			Thread.sleep(1000);
		}catch(Exception e) {
			
		}*/
		System.out.println("Routine was completed in - \'" + ((float)(System.nanoTime() - timestamp)/1000000000f) + "\' seconds");
		
		for(int i = 0; i < rectangles.size(); i++) {
			Rect rect = rectangles.get(i);
			//System.out.println("width = " + rect.getWidth() + ", height = " + rect.getHeight());
			addRectangle(rect.getLeft() + XOFFSET, rect.getTop() + YOFFSET, rect.getWidth(), rect.getHeight());

		}
		
		int[] largestActive= new int[] {0,0};
		
		for(int i = 0; i < atlas.activeNodes.size(); i++) {
			Rect rect = atlas.activeNodes.get(i);
			if(rect.getWidth() > largestActive[0] && rect.getHeight() > largestActive[1]) {
				largestActive[0] = rect.getWidth();
				largestActive[1] = rect.getHeight();
			}
			System.out.println("width = " + rect.getWidth() + ", height = " + rect.getHeight());
			//addRectangle(rect.getLeft() + XOFFSET, rect.getTop() + YOFFSET, rect.getWidth(), rect.getHeight(), r.nextFloat());
		}
		
		float percentage = (1.0f - (float)count/(float)ITEM_SIZE) * 100f;
		int asInt = (int)percentage;
		
		System.out.println("Unable to fit \'" + count + "\' rectangles out of " + ITEM_SIZE + " (" + asInt + "%)");
		
		System.out.println("Largest unfittable: width = " + largestUnfit[0] + ", height = " + largestUnfit[1]);
		System.out.println("Largest active node: width = " + largestActive[0] + ", height = " + largestActive[1]);
		
		
	}
	
	public void addRectangle(int x, int y, int width, int height) {
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(width, height));
		panel.setLocation(x, y);
		panel.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 0.5f));
		add(panel);
		panels.add(panel);
	}
	
	public void addRectangle(int x, int y, int width, int height, float alpha) {
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(width, height));
		panel.setLocation(x, y);
		panel.setBackground(new Color(1, 0, 0, alpha));
		add(panel);
		panels.add(panel);
	}

}
