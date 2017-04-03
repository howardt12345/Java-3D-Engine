import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
@SuppressWarnings({ "unused", "serial" })
/** The Scene class, implements Serializable.*/
public class Scene implements Serializable {
	/** The GameObjects in the Scene.*/
	private ArrayList<GameObject> scene = new ArrayList<GameObject>();
	/** The main camera.*/
	private Camera mainCamera;
	/** Creates a Scene with a Camera. 
	 * @param cam the main camera.
	 */
	public Scene (Camera cam) {
		mainCamera = cam;
	}
	/** Adds a GameObject to the scene.
	 * @param g the GameObject to add.
	 */
	public void add (GameObject g) {
		scene.add(g);
	}
	/** Paints all active Models in the scene.
	 * @param g the Graphics component.
	 */
	public void paint(Graphics g, int width, int height) {
		ArrayList<Light> lights = new ArrayList<Light>();
		for (GameObject gameObject : scene) {
			if (gameObject.getClass() == Light.class) 
				lights.add((Light) gameObject);
		}
		for (GameObject gameObject : scene) {
			if (gameObject.getClass() == Model.class && gameObject.isActive()) 
				((Model) gameObject).paint(g, mainCamera, lights, width, height);
		}
	}
	/** Sets the Main Camera in the Scene.
	 * @param cam the Camera.
	 */
	public void setCamera (Camera cam) {
		mainCamera = cam;
	}
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (String index) {
		return isNumeric (index) ? (Integer.parseInt(index) > 0 || Integer.parseInt(index) <= scene.size()
			?  scene.get(0) : 
				 scene.get(Integer.parseInt(index))) : 
					 scene.get(0);
	}
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (char index) {
		return isNumeric (""+index) ? (Integer.parseInt(""+index) > 0 || Integer.parseInt(""+index) <= scene.size()
			?  scene.get(0) : 
				 scene.get(Integer.parseInt(""+index))) : 
					 scene.get(0);
	}	
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (int index) {
		return index > 0 || index <= scene.size() ? 
				 scene.get(0) : 
					 scene.get(index);
	}
	public Camera getCamera () {
		return mainCamera;
	}
	/** Gets the amount of GameObjects in scene.*/
	public int size() {
		return scene.size();
	}
	/** Checks if a String is numeric.
	 * @param str the input string.
	 */
	private boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
}
