
import java.io.*;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
/** The Polyhedron Class, extends GameObject and implements Serializable.*/
public class Polyhedron extends GameObject implements Serializable {
	/** The Arraylist of Polygons in the Polyhedron.*/
	private ArrayList<Polygon> object = new ArrayList<Polygon>();
	/** The File name.*/
	private String FileName;
	/** Creates a new Polyhedron with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Polyhedron.
	 * @param filename the file name.
	 * @param active whether or not the Polyhedron is active.
	 */
	public Polyhedron (Transform t, String filename, boolean a) {
		super (t);
		super.setActive(a);
		ReadFile(filename);
		FileName = filename;
	}
	/** Gets the fileName of the Polyhedron.
	 * @return the fileName
	 */
	public String getFileName () {
		return FileName;
	}
	/** Reads the file and loads values in ArrayList.
	 * @param filename the filename.
	 */
	private void ReadFile (String filename) {
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
	/** The Polyhedron transformed by the Polyhedron View Perspective Matrix.
	 * @param polyhedron the Polyhedron to transform.
	 * @param cam the Camera to transform to.
	 * @param lights the lights in scene.
	 */
	public static Polyhedron MVP (Polyhedron polyhedron, Camera cam, ArrayList<Light> lights) {
		Polyhedron m = (Polyhedron) Utils.deepClone(polyhedron); //Deep Clones model.
		for (int a = 0; a < m.object.size(); a++) { //Goes through all polygons.
			m.object.set(a, m.object.get(a).Transform(new Matrix (m.transform.getScale()))); //Scales Polygon.
			m.object.set(a, m.object.get(a).Transform(new Matrix (m.transform))); //Translates and Rotates Polygon.
			boolean isVisible = cam.isVisible(m.object.get(a)); //Calculates Polygon visibility.
			m.object.get(a).calculateIntensity(lights); //Calculates light intensity.
			float tmp = m.object.get(a).getIntensity(); //stores in tmp value.
			m.object.set(a, m.object.get(a).Transform(cam.LookAtMatrix())); //Transforms Polygon to Camera space.
			m.object.set(a, m.object.get(a).Transform(cam.perspectiveMatrix()).Normalized());// Transforms Polygon to Projection space.
			m.object.get(a).setVisible(isVisible); //loads tmp value.
			m.object.get(a).setIntensity(tmp); //loads tmp value.
		}
		return m;
	}
	/** Paints the Polyhedron.
	 * @param g the Graphics component.
	 * @param cam the Camera.
	 * @param width the width.
	 * @param height the height.
	 * @param shiftX the screen shift on X axis
	 * @param shiftY the screen shift on Y axis.
	 */
	public void paint (Graphics g, Camera cam, int width, int height, int shiftX, int shiftY) {
		for (Polygon p : object) {
			if (p.isVisible() && p.getCenter().getX() >= -0.9 && p.getCenter().getX() <= 0.9
				&& p.getCenter().getY() >= -0.9 && p.getCenter().getY() <= 0.9
				&& p.getCenter().getZ() < -cam.getNearClip() && p.getCenter().getZ() > -cam.getFarClip()) {
				p.paint(g, width, height, shiftX, shiftY);
			}
		}
	}
	/** Prints the information on the Polyhedron.*/
	public void print () {
		System.out.println("Polyhedron File Name: " + FileName);
		System.out.println("Object active: " + Boolean.toString(isActive()));
		printCoordinates();
	}
	/** Prints out the Polyhedron Coordinates.*/
	public void printCoordinates () {
		for (int a = 0; a < object.size(); a++) {
			System.out.println("Polygon " + (a+1) + ":");
			object.get(a).detailedPrint();
			System.out.println("**********");
		}
	}
}
