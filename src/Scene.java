import java.util.*;
import java.awt.*;
import java.io.*;
@SuppressWarnings({ "serial" })
/** The Scene class, implements Serializable.*/
public class Scene implements Serializable {
	/** The GameObjects in the Scene.*/
	private ArrayList<GameObject> scene = new ArrayList<GameObject>();
	/** The main camera.*/
	private Camera mainCamera;
	/** Creates a Scene with a Camera. 
	 * @param cam the main camera.
	 */
	public Scene (Camera cam) 
	{
		mainCamera = cam;
	}
	/** Adds a gameObject to the scene.
	 * @param g the gameObject to add.
	 */
	public void add (GameObject g) 
	{
		scene.add(g);
	}
	/** Paints all active Models in the scene.
	 * @param g the Graphics component.	 
	 * @param width the width.
	 * @param height the height.
	 * @param shiftX the screen shift on X axis
	 * @param shiftY the screen shift on Y axis.
	 * @param wire if wireframe enabled.
	 * @param shade if shading enabled.
	 */
	public void paint (Graphics g, int width, int height, int shiftX, int shiftY, boolean wire, boolean shade) 
	{
		ArrayList<Light> lights = new ArrayList<Light>();
		ArrayList<GameObject> tmp = new ArrayList<GameObject>();
		for (GameObject gameObject : scene) {
			if (gameObject.getClass() == Light.class && gameObject.isActive()) 
				lights.add((Light) gameObject);
			if (gameObject.getClass() == Polyhedron.class && gameObject.isActive())
				tmp.add(gameObject);
		}
		for (GameObject gameObject : Utils.zSort(tmp, mainCamera)) {
			Polyhedron.MVP (((Polyhedron) gameObject), mainCamera, lights).paint(g, mainCamera, width, height, shiftX, shiftY, wire, shade);
		}
		g.drawRect(shiftX, shiftY, width, height);
	}
	/** Sets the Main Camera in the Scene.
	 * @param cam the Camera.
	 */
	public void setCamera (Camera cam) 
	{
		mainCamera = cam;
	}
	/** Gets the gameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (String index) 
	{
		return Utils.isNumeric (index) ? (Integer.parseInt(index) < 0 || Integer.parseInt(index) >= scene.size()
			?  scene.get(0) : scene.get(Integer.parseInt(index))) : scene.get(0);
	}
	/** Gets the gameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (char index) 
	{
		return Utils.isNumeric (""+index) ? (Integer.parseInt(""+index) < 0 || Integer.parseInt(""+index) >= scene.size()
			?  scene.get(0) : scene.get(Integer.parseInt(""+index))) : scene.get(0);
	}	
	/** Gets the gameObject located at the index.
	 * @param index the index.
	 */
	public GameObject get (int index) 
	{
		return index < 0 || index >= scene.size() ?  scene.get(0) : scene.get(index);
	}
	/** Removes the gameObject located at the index.
	 * @param index the index.
	 */
	public void remove (String index) 
	{
		scene.remove(Utils.isNumeric (index) ? (Integer.parseInt(index) < 0 || Integer.parseInt(index) >= scene.size()
				? 0 : Integer.parseInt(index)) : 0);
	}
	/** Removes the gameObject located at the index.
	 * @param index the index.
	 */
	public void remove (char index) 
	{
		scene.remove(Utils.isNumeric (""+index) ? (Integer.parseInt(""+index) < 0 || Integer.parseInt(""+index) >= scene.size()
				? 0 : Integer.parseInt(""+index)) : 0);
	}
	/** Removes the gameObject located at the index.
	 * @param index the index.
	 */
	public void remove (int index) 
	{
		scene.remove(index < 0 || index >= scene.size() ? 0 : index);
	}
	/** Gets the main camera of the scene.*/
	public Camera getCamera () 
	{
		return mainCamera;
	}
	/** Gets the amount of GameObjects in scene.*/
	public int size () 
	{
		return scene.size();
	}
}
