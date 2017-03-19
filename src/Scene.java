import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
@SuppressWarnings("unused")
public class Scene {
	private ArrayList<Model> Models = new ArrayList<Model>();
	private Camera mainCamera;
	/** Creates a Scene with a Camera. 
	 * @param cam the main camera.
	 */
	public Scene (Camera cam) {
		mainCamera = cam;
	}
	/** Adds a Model to the scene.
	 * @param model the Model to add.
	 */
	public void add (Model model) {
		Models.add(model);
	}
	/** Paints all active Models in the scene.
	 * @param g the Graphics component.
	 */
	public void paint(Graphics g, int width, int height) {
		for (Model model : Models) {
			model.paint(g, mainCamera, width, height);
		}
	}
	/** Sets the Main Camera in the Scene.
	 * @param cam the Camera.
	 */
	public void setCamera (Camera cam) {
		mainCamera = cam;
	}
	/** Gets the Model located at the index.
	 * @param index the index.
	 */
	public Model get (String index) {
		return isNumeric (index) ? (Integer.parseInt(index) > 0 || Integer.parseInt(index) <= Models.size()
			? Models.get(0) : Models.get(Integer.parseInt(index))) : Models.get(0);
	}
	/** Gets the Model located at the index.
	 * @param index the index.
	 */
	public Model get (char index) {
		return isNumeric (""+index) ? (Integer.parseInt(""+index) > 0 || Integer.parseInt(""+index) <= Models.size()
			? Models.get(0) : Models.get(Integer.parseInt(""+index))) : Models.get(0);
	}	
	/** Gets the Model located at the index.
	 * @param index the index.
	 */
	public Model get (int index) {
		return index > 0 || index <= Models.size() ? Models.get(0) : Models.get(index);
	}
	/** Gets the Model located at the index.
	 * @param index the index.
	 */
	public Model get (double index) {
		return index > 0 || index <= Models.size() ? Models.get(0) : Models.get((int)index);
	}
	/** Gets the Model located at the index.
	 * @param index the index.
	 */
	public Model get (float index) {
		return index > 0 || index <= Models.size() ? Models.get(0) : Models.get((int)index);
	}
	public Camera getCamera () {
		return mainCamera;
	}
	/** Gets the amount of Models in scene.*/
	public int size() {
		return Models.size();
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
