import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
@SuppressWarnings({ "serial", "unused" })
public class Main extends JPanel {
	public static Scene scene;
	public static JFrame f = new JFrame();
	public static void main (String[] args) {
		Matrix m = new Matrix (new Transform (new Coordinate (1.5, 1, 1.5), new Rotation (180, 90, 0)));
		Camera cam = new Camera (new Coordinate (0, 0, -4), new Coordinate (0, 0, 1), Coordinate.up);
		int[][] maze;
		scene = new Scene (cam);
		scene.add(new GameObject 
				(new Transform (0, 0, 160), 
						"Enterprise.txt", 
						true)
				);
		
		f.addKeyListener(new KeyListener () {
			public void keyTyped(KeyEvent e) {
				scene.get(e.toString());
			}
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE: 
					System.exit(0);
					break;
				case KeyEvent.VK_W:
					scene.getCamera().addTranslate(1, Direction.Forward);
					break;
				case KeyEvent.VK_S:
					scene.getCamera().addTranslate(1, Direction.Backward);
					break;
				case KeyEvent.VK_A:
					scene.getCamera().addTranslate(1, Direction.Left);
					break;
				case KeyEvent.VK_D:
					scene.getCamera().addTranslate(1, Direction.Right);
					break;
				case KeyEvent.VK_Q:
					scene.getCamera().addTranslate(1, Direction.Down);
					break;
				case KeyEvent.VK_E:
					scene.getCamera().addTranslate(1, Direction.Up);
					break;
				case KeyEvent.VK_RIGHT:
					scene.getCamera().addRotate(1, Rotate.Y_Axis);
					break;
				case KeyEvent.VK_LEFT:
					scene.getCamera().addRotate(-1, Rotate.Y_Axis);
					break;
				case KeyEvent.VK_UP:
					scene.getCamera().addRotate(1, Rotate.X_Axis);
					break;
				case KeyEvent.VK_DOWN:
					scene.getCamera().addRotate(-1, Rotate.X_Axis);
					break;
				}
				scene.getCamera().Transform();
				System.out.println(e.getKeyChar());
				f.repaint();
			}
			public void keyReleased(KeyEvent e) 
			{
				
			}
		});
		f.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				cam.setFOV(cam.getFOV()+e.getWheelRotation());
				System.out.println(cam.getFOV());
				f.repaint();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Main());
		f.setSize(800, 600);
		f.setVisible(true);
	}
	public void paint(Graphics g) {
		scene.paint(g, f.getWidth(), f.getHeight());
	}
}