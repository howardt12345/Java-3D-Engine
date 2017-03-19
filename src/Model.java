import java.io.*;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Model extends GameObject implements Serializable {
	/** The ArrayList of ArrayList of Points representing the Object.
	 * @param object the Object.*/
	private ArrayList<Polygon> object = new ArrayList<Polygon>();
	/** Whether or not the Model is active.
	 * @param active the boolean*/
	private boolean active;
	/** The File name.*/
	private String FileName;
	/** Creates a new Model with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Model.
	 * @param filename the file name.
	 * @param active whether or not the Model is active.
	 */
	public Model (Transform t, String filename, boolean a) {
		super (t);
		active = a;
		ReadFile(filename);
		FileName = filename;
	}
	/** Returns boolean of whether or not Model is active.*/
	public boolean getActive() {
		return active;
	}
	/** Sets whether or not Model is active./
	 * @param active the value.
	 */
	public void setActive(boolean value) {
		active = value;
	}
	/** Gets the fileName of the Model.
	 * @return the fileName
	 */
	public String getFileName() {
		return FileName;
	}
	/** Reads the file and loads values in ArrayList.*/
	private void ReadFile(String filename) {
		try {
			FileReader file = new FileReader(filename);
			Scanner input = new Scanner(file);
			Scanner line = new Scanner(input.nextLine());
			while (input.hasNextLine() || line.hasNext()) {			
				Polygon polygon = new Polygon ();
				while (line.hasNextDouble()) {
					double tmpX = line.nextDouble(),
							tmpY = line.nextDouble(),
							tmpZ = line.nextDouble();
					polygon.add(new Coordinate(tmpX, tmpY, tmpZ));
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
	/** The Model View Perspective Matrix Transformation.
	 * @param model the Model to transform.
	 * @param cam the Camera to transform to.
	 */
	public static Model MVP (Model model, Camera cam) {
		Model m = (Model) deepClone(model);
		for (int a = 0; a < m.object.size(); a++) {
			if (m.getTransform().getRotation().equals(Rotation.zero))
				m.object.set(a, m.object.get(a).Transform(new Matrix (new Rotation (-cam.getTransform().getRotX(), -cam.getTransform().getRotY(), -cam.getTransform().getRotZ()))));
			m.object.set(a, m.object.get(a).Transform(new Matrix (m.transform)));
			m.object.set(a, m.object.get(a).Transform(cam.LookAtMatrix()));
			m.object.set(a, m.object.get(a).Transform(cam.perspectiveMatrix()).Normalized());
			m.object.get(a).setVisible(cam.isVisible(m.object.get(a).getCenter(), m.object.get(a).getNormal()));
		}
		return m;
	}
	/** Draws the Model*/
	public void paint (Graphics g, Camera cam, int width, int height) {
		Model m = MVP (this, cam);
		cam.setAspectRatio(width/height);
		int[] xCoord, yCoord;
		for (int a = 0; a < m.object.size(); a++) {
			xCoord = new int [m.object.get(a).size()];
			yCoord = new int [m.object.get(a).size()];
			if (m.object.get(a).isVisible()
				&& m.object.get(a).getCenter().getX() > -1 && m.object.get(a).getCenter().getX() < 1
				&& m.object.get(a).getCenter().getY() > -1 && m.object.get(a).getCenter().getY() < 1
				&& m.object.get(a).getCenter().getZ() < -cam.getNearClip() && m.object.get(a).getCenter().getZ() > -cam.getFarClip()) {
				for (int b = 0; b < m.object.get(a).size(); b++) {
					xCoord[b] = (int) Math.rint((m.object.get(a).get(b).getX()/m.object.get(a).get(b).getZ()*width/2)+width/2);
					yCoord[b] = (int) Math.rint((m.object.get(a).get(b).getY()/m.object.get(a).get(b).getZ()*width/2)+height/2);
				}
				g.setColor(Color.getHSBColor(0, 0, 0.5f));
				g.fillPolygon(xCoord, yCoord, xCoord.length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xCoord, yCoord, xCoord.length);
			}
		}
	}
	/** Prints the information on the Model.*/
	public void print () {
		System.out.println("Model File Name: " + FileName);
		System.out.println("Object active: " + Boolean.toString(active));
		printCoordinates();
	}
	/** Prints out the Model Coordinates.*/
	public void printCoordinates () {
		for (int a = 0; a < object.size(); a++) {
			System.out.println("Polygon " + (a+1) + ":");
		}
	}
}
