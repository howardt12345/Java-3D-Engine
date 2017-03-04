import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
@SuppressWarnings("unused")
public class Scene {
	private ArrayList<GameObject> GameObjects = new ArrayList<GameObject>();
	private Camera mainCamera;
	/** Creates a Scene with a Camera. 
	 * @param cam the main camera.
	 */
	public Scene (Camera cam) {
		mainCamera = cam;
	}
	/** Adds a GameObject to the scene.
	 * @param gameObject the GameObject to add.
	 */
	public void add (GameObject gameObject) {
		GameObjects.add(gameObject);
	}
	/** Paints all active GameObjects in the scene.
	 * @param g the Graphics component.
	 */
	public void paint(Graphics g, int width, int height) {
		for (GameObject gameObject : GameObjects) {
			GameObject.MVP(gameObject, mainCamera).paint(g, mainCamera, width, height);
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
		return isNumeric (index) ? (Integer.parseInt(index) > 0 || Integer.parseInt(index) <= GameObjects.size()
			? GameObjects.get(0) : GameObjects.get(Integer.parseInt(index))) : GameObjects.get(0);
	}
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (char index) {
		return isNumeric (""+index) ? (Integer.parseInt(""+index) > 0 || Integer.parseInt(""+index) <= GameObjects.size()
			? GameObjects.get(0) : GameObjects.get(Integer.parseInt(""+index))) : GameObjects.get(0);
	}	
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (int index) {
		return index > 0 || index <= GameObjects.size() ? GameObjects.get(0) : GameObjects.get(index);
	}
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (double index) {
		return index > 0 || index <= GameObjects.size() ? GameObjects.get(0) : GameObjects.get((int)index);
	}
	/** Gets the GameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (float index) {
		return index > 0 || index <= GameObjects.size() ? GameObjects.get(0) : GameObjects.get((int)index);
	}
	public Camera getCamera () {
		return mainCamera;
	}
	/** Sets the GameObject located at the Index.
	 * @param index the index.
	 * @param gameObject the GameObject.
	 */
	public void set (int index, GameObject gameObject) {
		GameObjects.set(index, gameObject);
	}
	/** Gets the amount of GameObjects in scene.*/
	public int size() {
		return GameObjects.size();
	}
	/** Checks if a String is numeric.
	 * @param str the input string
	 */
	public static boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
}
