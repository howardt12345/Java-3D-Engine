import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
@SuppressWarnings({ "serial", "unused" })
/** The Main Class, used to test the program. Anything in this class does not affect
 * the renderer.*/
public class Main extends JPanel {
	public static Scene scene;
	public static JFrame f = new JFrame();
    public static double oldX, newX, oldY, newY;
    public static double dx = 0, dy = 0, speed = 0.3;
    static int tmp = 0;
	public static void main (String[] args) {
		scene = new Scene (new Camera (new Transform (new Vec4 (0, 2, -4))));
		
		scene.add(new Light (new Transform (0, 4, 0)));

		scene.add(new Polyhedron (new Transform (new Vec4 (-7, 2, 20),
				new Rotation (-90, 90, 0), new Scale (2)), "house.txt", true));
		scene.add(new Polyhedron (new Transform (new Vec4 (0, 2, 20),
				new Rotation (-90, 90, 0), new Scale (2)), "house.txt", true));
		scene.add(new Polyhedron (new Transform (new Vec4 (7, 2, 20),
				new Rotation (-90, 90, 0), new Scale (2)), "house.txt", true));
		/*scene.add(new Light (new Transform (20, 2, 0)));
		scene.add(new Light (new Transform (-20, 2, 0)));
		scene.add(new Light (new Transform (0, 2, 20)));
		scene.add(new Light (new Transform (0, 2, -20)));*/
		scene.add(new Polyhedron (new Transform (
				new Vec4 (0, 0, 0), new Rotation (90, 0, 0), 
				new Scale (2)), 
				"plane.txt", true));
		/*scene.add(new Polyhedron (new Transform 
				(new Vec4 (0, 25, 25), new Rotation (-90, 90, 0), new Scale (1)
				), "Enterprise.txt", true));
		
		for (int a = -10; a < 10; a++) {
			for (int b = -10; b < 10; b++) {
				scene.add(new Polyhedron (new Transform (
						new Vec4 (a*10, 0, b*10), new Rotation (0, 45, 0)), 
						"cube.txt", true));
			}
		}*/
		f.addKeyListener(new KeyListener () 
		{
			public void keyTyped(KeyEvent e) 
			{
				if (Utils.isNumeric(""+e.getKeyChar()))
					tmp = Integer.parseInt(""+e.getKeyChar());
			}
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE: 
					System.exit(0);
					break;
				case KeyEvent.VK_ENTER:
					f.setExtendedState(JFrame.MAXIMIZED_BOTH);
					break;
				case (KeyEvent.VK_BACK_SLASH):
					scene.get(tmp).setActive(!scene.get(tmp).isActive());
					break;
				case KeyEvent.VK_W:
					scene.getCamera().addTranslate(speed, Direction.Forward);
					break;
				case KeyEvent.VK_S:
					scene.getCamera().addTranslate(speed, Direction.Backward);
					break;
				case KeyEvent.VK_A:
					scene.getCamera().addTranslate(speed, Direction.Left);
					break;
				case KeyEvent.VK_D:
					scene.getCamera().addTranslate(speed, Direction.Right);						
					break;
				case KeyEvent.VK_Q:
					scene.getCamera().addTranslate(speed, Direction.Down);
					break;
				case KeyEvent.VK_E:
					scene.getCamera().addTranslate(speed, Direction.Up);
					break;
				case KeyEvent.VK_UP:
					new Animator (new Animation (new Vec4 (0, 0, 1, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_DOWN:
					new Animator (new Animation (new Vec4 (0, 0, -1, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_LEFT:
					new Animator (new Animation (new Vec4 (-1, 0, 0, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_RIGHT:
					new Animator (new Animation (new Vec4 (1, 0, 0, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_COMMA:
					new Animator (new Animation (new Vec4 (0, -1, 0, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_PERIOD:
					new Animator (new Animation (new Vec4 (0, 1, 0, true), 0.5), 
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_OPEN_BRACKET:
					new Animator (new Animation (new Rotation (0, 15, 0), 0.5),
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_CLOSE_BRACKET:
					new Animator (new Animation (new Rotation (0, -15, 0), 0.5),
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_QUOTE:
					new Animator (new Animation (new Rotation (-15, 0, 0), 0.5),
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_SEMICOLON:
					new Animator (new Animation (new Rotation (15, 0, 0), 0.5),
							scene.get(tmp), f).play();
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_SPACE:
					Animator animator = new Animator (scene.get(tmp), f);
					animator.add(new Animation (new Vec4 (10, 0, 10, true), 6));
					animator.add(new Animation (new Rotation (-180, 0, 0), 3));
					animator.play();
					break;
				}
				System.out.println(e.getKeyChar());
				f.repaint();
			}
			public void keyReleased(KeyEvent e) 
			{
			}
		});
		f.addMouseWheelListener(new MouseWheelListener() 
		{
			public void mouseWheelMoved(MouseWheelEvent e) 
			{
				scene.getCamera().setFOV(scene.getCamera().getFOV()+e.getWheelRotation());
				System.out.println(scene.getCamera().getFOV());
				f.repaint();
			}
		});
		f.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				oldX = newX; 
				oldY = newY;
				newX = e.getX();
				newY = e.getY();
				dx = (newX - oldX)*0.1;
				dy = (newY - oldY)*0.1;
				if (SwingUtilities.isMiddleMouseButton(e)) {
					scene.getCamera().addTranslate(dx*speed, Direction.Left);
					scene.getCamera().addTranslate(dy*speed, Direction.Up);
				}
				else if (SwingUtilities.isRightMouseButton(e) && e.isAltDown()) {
					scene.getCamera().addTranslate(dx*speed + dy*speed, Direction.Forward);
				}
				else if (e.isAltDown()) {
					scene.getCamera().addTranslate(dx*speed, Direction.Left);
					scene.getCamera().addTranslate(dy*speed, Direction.Up);
					scene.getCamera().addRotate(dy, Axis.X);
					scene.getCamera().addRotate(dx, Axis.Y);
				}
				else {
					scene.getCamera().addRotate(dy, Axis.X);
					scene.getCamera().addRotate(dx, Axis.Y);
				}
				f.repaint();
			}
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				oldX = newX = e.getX();
				oldY = newY = e.getY();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Main());
		f.setSize(800, 600);
		f.setVisible(true);
	}
	public void paint (Graphics g) {
		scene.paint(g, f.getWidth(), f.getHeight(), 0, 0);
	}
}