import java.io.*;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
/** The Model Class, extends GameObject and implements Serializable.*/
public class Model extends GameObject implements Serializable {
	/** The Arraylist of Polygons in the Model.*/
	private ArrayList<Polygon> object = new ArrayList<Polygon>();
	/** The File name.*/
	private String FileName;
	/** Creates a new Model with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Model.
	 * @param filename the file name.
	 * @param active whether or not the Model is active.
	 */
	public Model (Transform t, String filename, boolean a) {
		super (t);
		super.setActive(a);
		ReadFile(filename);
		FileName = filename;
	}
	/** Gets the fileName of the Model.
	 * @return the fileName
	 */
	public String getFileName() {
		return FileName;
	}
	/** Reads the file and loads values in ArrayList.
	 * @param filename the filename.
	 */
	private void ReadFile(String filename) {
		try {
			Scanner input = new Scanner(new FileReader(filename));
			Scanner line = new Scanner(input.nextLine());
			while (input.hasNextLine() || line.hasNext()) {			
				Polygon polygon = new Polygon ();
				while (line.hasNextDouble()) {
					double tmpX = line.nextDouble(),
							tmpY = line.nextDouble(),
							tmpZ = line.nextDouble();
					polygon.add(new Vec4(tmpX, tmpY, tmpZ));
				}
				if (input.hasNextLine()){
					line = new Scanner(input.nextLine());			
				}
				object.add(polygon);
			}
			input.close();
			line.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** The Model transformed by the Model View Perspective Matrix.
	 * @param model the Model to transform.
	 * @param cam the Camera to transform to.
	 * @param lights the lights in scene.
	 */
	public static Model MVP (Model model, Camera cam, ArrayList<Light> lights) {
		Model m = (Model) Utils.deepClone(model); //Deep Clones model.
		for (int a = 0; a < m.object.size(); a++) { //Goes through all polygons.
			m.object.set(a, m.object.get(a).Transform(new Matrix (m.transform.getScale()))); //Scales Polygon.
			m.object.set(a, m.object.get(a).Transform(new Matrix (m.transform))); //Translates and Rotates Polygon.
			m.object.get(a).calculateIntensity(lights); //Calculates light intensity.
			float tmp = m.object.get(a).getIntensity(); //stores in tmp value.
			m.object.set(a, m.object.get(a).Transform(cam.LookAtMatrix())); //Transforms Polygon to Camera space.
			m.object.set(a, m.object.get(a).Transform(cam.perspectiveMatrix()).Normalized());// Transforms Polygon to Projection space.
			m.object.get(a).setVisible(cam.isVisible(m.object.get(a))); //Calculates Polygon visibility.
			m.object.get(a).setIntensity(tmp); //loads tmp value.
		}
		return m;
	}
	/** Paints the Model.
	 * @param g the Graphics component.
	 * @param cam the Camera.
	 * @param lights the lights in scene.
	 * @param width the width.
	 * @param height the height.
	 */
	public void paint (Graphics g, Camera cam, int width, int height) {
		for (Polygon p : object) {
			if (p.isVisible() && p.getCenter().getX() > -1 && p.getCenter().getX() < 1
				&& p.getCenter().getY() > -1 && p.getCenter().getY() < 1
				&& p.getCenter().getZ() < -cam.getNearClip() && p.getCenter().getZ() > -cam.getFarClip()) {
				p.paint(g, width, height);
			}
		}
	}
	/** Prints the information on the Model.*/
	public void print () {
		System.out.println("Model File Name: " + FileName);
		System.out.println("Object active: " + Boolean.toString(isActive()));
		printCoordinates();
	}
	/** Prints out the Model Coordinates.*/
	public void printCoordinates () {
		for (int a = 0; a < object.size(); a++) {
			System.out.println("Polygon " + (a+1) + ":");
			object.get(a).detailedPrint();
			System.out.println("**********");
		}
	}
}
