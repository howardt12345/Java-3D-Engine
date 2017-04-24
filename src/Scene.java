import java.util.*;
import java.awt.*;
import java.io.*;
@SuppressWarnings({ "serial" })
/** The Scene class, implements Serializable.*/
public class Scene implements Serializable {
	/** The GameObjects in the Scene.*/
	private ArrayList<GameObject> scene = new ArrayList<GameObject>();
	/** The main camera.*/
	private Camera cam = new Camera ();
	/** Creates a Scene with a Camera. 
	 * @param cam the main camera.
	 */
	public Scene (Camera cam) 
	{
		this.cam = cam;
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
	 * @param debug if debugging enabled.
	 */
	public void paint (Graphics g, int width, int height, int shiftX, int shiftY, boolean wire, boolean shade, boolean debug) 
	{
		long start = System.currentTimeMillis(), end;
		ArrayList<Light> lights = new ArrayList<Light>();
		ArrayList<Polyhedron> tmp = new ArrayList<Polyhedron>();
		int total = 0, active = 0, calculated = 0;
		for (GameObject gameObject : scene) {
			total++;
			if (gameObject.isActive()) {
				active++;
				if (gameObject instanceof Light) 
					lights.add((Light) gameObject);
				else if (gameObject instanceof Polyhedron)
					tmp.add((Polyhedron) gameObject);
			}
		}
		for (Polyhedron p : Utils.zSort(tmp, cam)) {
			double d = p.getFarthest(Vec4.center).getFarthest(Vec4.center).magnitude();
			double d1 = Vec4.dot(Vec4.subtract(cam.getLookFrom(), p.transform.getPosition()).normalized(), 
					Vec4.subtract(cam.getLookFrom(), cam.getLookAt()).normalized()) > 0
					? Vec4.subtract(cam.getLookFrom(), p.transform.getPosition()).magnitude()
						: -Vec4.subtract(cam.getLookFrom(), p.transform.getPosition()).magnitude();
			Vec4 v = Vec4.add(Vec4.multiply(Vec4.subtract(cam.getLookAt(), cam.getLookFrom()), d + d1), cam.getLookFrom());
			if (Vec4.dot(Vec4.subtract(cam.getLookFrom(), cam.getLookAt()), Vec4.subtract(cam.getLookFrom(), v)) > 0) {
				p.paint(g, cam, lights, width, height, shiftX, shiftY, wire, shade);
				calculated++;
			}
		}
		end = System.currentTimeMillis();
		if (debug) {
			g.drawRect(shiftX, shiftY, width, height);
			g.drawString(total + " objects total", width + 2*shiftX - 150, height + 2*shiftY - 75);
			g.drawString(active + " objects active", width + 2*shiftX - 150, height + 2*shiftY - 60);
			g.drawString(calculated + " objects calculated", width + 2*shiftX - 150, height + 2*shiftY - 45);
			g.drawString((end - start) + "ms", width + 2*shiftX - 60, 30);
			g.setColor(Color.black);
			g.drawString(width + " X " + height, width > 1000 ? width-100 : width-75, 15);
			g.drawString("Camera:", 5, 15);
			g.drawString("Position: " + cam.getTransform().getPosition().asString("%1$.5f, %2$.5f, %3$.5f, %4$.0f"), 5, 30);
			g.drawString("Rotation: " + cam.getTransform().getRotation().asString("%1$.2f, %2$.2f, %3$.2f"), 5, 45);
			for (int a = 0; a < scene.size(); a++) {
				g.drawString("Object " + a + ": " + scene.get(a).getClass().getName(), 5, (a+1)*70);
				g.drawString("Position: " + scene.get(a).getTransform().getPosition().asString("%1$.2f, %2$.2f, %3$.2f, %4$.0f"), 5, (a+1)*70+15);
				g.drawString("Rotation: " + scene.get(a).getTransform().getRotation().asString("%1$.2f, %2$.2f, %3$.2f"), 5, (a+1)*70+30);
				g.drawString("Scale: " + scene.get(a).getTransform().getScale().asString("%1$.2f, %2$.2f, %3$.2f"), 5, (a+1)*70+45);
			}
		}
	}
	/** Sets the Main Camera in the Scene.
	 * @param cam the Camera.
	 */
	public void setCamera (Camera cam) 
	{
		this.cam = cam;
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
		return cam;
	}
	/** Gets the amount of GameObjects in scene.*/
	public int size () 
	{
		return scene.size();
	}
}