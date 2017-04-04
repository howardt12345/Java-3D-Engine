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
    public static double dx = 0, dy = 0;
    static int tmp = 0;
	public static void main (String[] args) {
		Camera cam = new Camera (new Vec4 (0, 5, 0));
		scene = new Scene (cam);
		
		scene.add(new Light (new Transform (0, 2, 0)));

		scene.add(new Model (new Transform (new Vec4 (5, 0, 10),
				new Rotation (-90, 90, 0), new Scale (2)), "house.txt", true));
		
		/*scene.add(new Light (new Transform (5, 2, 0)));
		scene.add(new Light (new Transform (-5, 2, 0)));
		scene.add(new Light (new Transform (0, 2, 5)));
		scene.add(new Light (new Transform (0, 2, -5)));*/

		scene.add(new Model (new Transform (
						new Vec4 (0, 0, 0), new Rotation (90, 0, 0)), 
						"plane.txt", true));
		/*scene.add(new Model (new Transform 
				(new Vec4 (0, 25, 25), new Rotation (-90, 90, 0), new Scale (1)
				), "Enterprise.txt", true));*/
		
		/*for (int a = -10; a < 10; a++) {
			for (int b = -10; b < 10; b++) {
				scene.add(new Model (new Transform (
						new Vec4 (a*10, 0, b*10), new Rotation (0, 45, 0)), 
						"cube.txt", true));
			}
		}*/
		f.addKeyListener(new KeyListener () 
		{
			public void keyTyped(KeyEvent e) 
			{
				if (Scene.isNumeric(""+e.getKeyChar()))
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
					scene.getCamera().addTranslate((Math.cos(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2), Axis.Z);
					scene.getCamera().addTranslate(Math.sin(scene.getCamera().getTransform().getRotation().getRadianY()) * 
							Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.X);
					scene.getCamera().addTranslate(-Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Y);					
					break;
				case KeyEvent.VK_S:
					scene.getCamera().addTranslate(-(Math.cos(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2), Axis.Z);
					scene.getCamera().addTranslate(-Math.sin(scene.getCamera().getTransform().getRotation().getRadianY()) * 
							Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.X);
					scene.getCamera().addTranslate(Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Y);
					break;
				case KeyEvent.VK_A:
					scene.getCamera().addTranslate(Math.sin(scene.getCamera().getTransform().getRotation().getRadianY()) * 0.2, Axis.Z);
					scene.getCamera().addTranslate(-Math.cos(scene.getCamera().getTransform().getRotation().getRadianY()) * 0.2, Axis.X);		
					break;
				case KeyEvent.VK_D:
					scene.getCamera().addTranslate(-Math.sin(scene.getCamera().getTransform().getRotation().getRadianY()) * 0.2, Axis.Z);
					scene.getCamera().addTranslate(Math.cos(scene.getCamera().getTransform().getRotation().getRadianY()) * 0.2, Axis.X);							
					break;
				case KeyEvent.VK_Q:
					scene.getCamera().addTranslate(-Math.cos(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Z);
					scene.getCamera().addTranslate(-Math.sin(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.X);
					scene.getCamera().addTranslate(-Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Y);
					break;
				case KeyEvent.VK_E:
					scene.getCamera().addTranslate(Math.cos(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Z);
					scene.getCamera().addTranslate(Math.sin(scene.getCamera().getTransform().getRotation().getRadianY())
							* Math.sin(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.X);
					scene.getCamera().addTranslate(Math.cos(scene.getCamera().getTransform().getRotation().getRadianX()) * 0.2, Axis.Y);					
					break;
				case KeyEvent.VK_UP:
					scene.get(tmp).addTranslate(0.5, Axis.Z);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_DOWN:
					scene.get(tmp).addTranslate(-0.5, Axis.Z);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_LEFT:
					scene.get(tmp).addTranslate(-0.5, Axis.X);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_RIGHT:
					scene.get(tmp).addTranslate(0.5, Axis.X);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_COMMA:
					scene.get(tmp).addTranslate(-0.5, Axis.Y);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_PERIOD:
					scene.get(tmp).addTranslate(0.5, Axis.Y);
					scene.get(tmp).getTransform().getPosition().print();
					break;
				case KeyEvent.VK_OPEN_BRACKET:
					scene.get(tmp).addRotate(1, Rotate.Y);
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_CLOSE_BRACKET:
					scene.get(tmp).addRotate(-1, Rotate.Y);
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_QUOTE:
					scene.get(tmp).addRotate(1, Rotate.X);
					scene.get(tmp).getTransform().getRotation().print();
					break;
				case KeyEvent.VK_SLASH:
					scene.get(tmp).addRotate(-1, Rotate.X);
					scene.get(tmp).getTransform().getRotation().print();
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
				cam.setFOV(cam.getFOV()+e.getWheelRotation());
				System.out.println(cam.getFOV());
				f.repaint();
			}
		});
		f.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				if (e.getX() <= 50 || e.getX() >= f.getWidth() - 50
						|| e.getY() <= 50 || e.getY() >= f.getHeight() - 50){
					try {
						Robot r = new Robot();
						r.mouseMove((int)f.getWidth()/2, (int)f.getHeight()/2);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}					
					oldX = newX = f.getWidth()/2;
					oldY = newY = f.getHeight()/2;
					dx = 0; 
					dy = 0;
				}
				else {
					oldX = newX; 
					oldY = newY;
					newX = e.getX();
					newY = e.getY();
					dx = (newX -oldX)*0.1;
					dy = (newY -oldY)*0.1;
				}
				scene.getCamera().addRotate(dy, Rotate.X);
				scene.getCamera().addRotate(dx, Rotate.Y);
				f.repaint();
			}
			@Override
			public void mouseMoved(MouseEvent e) 
			{
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