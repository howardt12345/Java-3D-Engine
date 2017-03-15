import java.io.*;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameObject implements Serializable {
	/** The Transform of the GameObject.
	 * @param transform the Transform.*/
	private Transform transform;
	/** A single ArrayList of all the Points.*/
	private ArrayList<Coordinate> points;
	/** The ArrayList of ArrayList of Points representing the Object.
	 * @param object the Object.*/
	private ArrayList<ArrayList<Coordinate>> object = new ArrayList<ArrayList<Coordinate>>();
	/** Whether or not the GameObject is active.
	 * @param active the boolean*/
	private boolean active;
	/** The File name.*/
	private String FileName;
	/** Creates a new GameObject with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the GameObject.
	 * @param filename the file name.
	 * @param active whether or not the GameObject is active.
	 */
	public GameObject (Transform t, String filename, boolean a) {
		transform = t;
		active = a;
		ReadFile(filename);
		FileName = filename;
	}
	/** new GameObject.*/
	public GameObject () {}
	
	@Override
	public GameObject clone () throws CloneNotSupportedException {
		GameObject clone = (GameObject)super.clone();
		return clone;
	}
	/** Returns boolean of whether or not GameObject is active.*/
	public boolean getActive() {
		return active;
	}
	/** Sets whether or not GameObject is active./
	 * @param active the value.
	 */
	public void setActive(boolean value) {
		active = value;
	}
	/** Gets the fileName of the GameObject.
	 * @return the fileName
	 */
	public String getFileName() {
		return FileName;
	}
	/** Gets the Transform of the GameObject.*/
	public Transform getTransform () {
		return transform;
	}
	/** Sets the Transform of the Gameobject.
	 * @param t the Transform to set.
	 */
	public void setTransform (Transform t) {
		transform = t;
	}
	/** Reads the file and loads values in ArrayList.*/
	private void ReadFile(String filename) {
		try {
			ArrayList<Coordinate> polygon;
			ArrayList<Coordinate> clone;
			points = new ArrayList<Coordinate>();
			FileReader file = new FileReader(filename);
			Scanner input = new Scanner(file);
			Scanner line = new Scanner(input.nextLine());
			while (input.hasNextLine() || line.hasNext()) {			
				polygon=new ArrayList<Coordinate>();	
				clone = new ArrayList<Coordinate>();
				while (line.hasNextDouble()) {	
					double tmpX = line.nextDouble(),
							tmpY = line.nextDouble(),
							tmpZ = line.nextDouble();
					polygon.add(new Coordinate(tmpX, tmpY, tmpZ));
					clone.add(new Coordinate (tmpX, tmpY, tmpZ));
					points.add(new Coordinate (tmpX, tmpY, tmpZ));
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
	/** Adds an amount to the specified Axis. 
	 * @param amount the amount.
	 * @param axis the Axis.
	 */
	public void addTranslate (double amount, Axis axis) {
		switch (axis) {
		case X:
			transform.setPosX(transform.getPosX()+amount);
			break;
		case Y:
			transform.setPosY(transform.getPosY()+amount);
			break;
		case Z:
			transform.setPosZ(transform.getPosZ()+amount);
			break;
		}
	}
	/** Sets a value to the specified Axis. 
	 * @param value the value.
	 * @param axis the Axis.
	 */
	public void setTranslate (double amount, Axis axis) {
		switch (axis) {
		case X:
			transform.setPosX(amount);
			break;
		case Y:
			transform.setPosY(amount);
			break;
		case Z:
			transform.setPosZ(amount);
			break;
		}
	}
	/** Adds an amount to the Rotation.
	 * @param amount the amount.
	 * @param axis the Axis.
	 */
	public void addRotate (double amount, Rotate axis) {
		switch (axis) {
		case X_Axis:
			transform.setRotX(transform.getRotX()+amount);
			break;
		case Y_Axis:
			transform.setRotY(transform.getRotY()+amount);
			break;
		case Z_Axis:
			transform.setRotZ(transform.getRotZ()+amount);
			break;
		}
	}
	/** Sets a value to the Rotation.
	 * @param value the value.
	 * @param axis the Axis.
	 */
	public void setRotate (double value, Rotate axis) {
		switch (axis) {
		case X_Axis:
			transform.setRotX(value);
			break;
		case Y_Axis:
			transform.setRotY(value);
			break;
		case Z_Axis:
			transform.setRotZ(value);
			break;
		}
	}
	/** Adds an amount to the specified Direction. 
	 * @param amount the amount.
	 * @param dir the Direction.
	 */
	public void addTranslate (double amount, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setPosZ(transform.getPosZ()+amount);
			break;
		case Backward:
			transform.setPosZ(transform.getPosZ()-amount);
			break;
		case Left:
			transform.setPosX(transform.getPosX()-amount);
			break;
		case Right:
			transform.setPosX(transform.getPosX()+amount);
			break;
		case Up:
			transform.setPosY(transform.getPosY()-amount);
			break;
		case Down:
			transform.setPosY(transform.getPosY()+amount);
			break;
		}
	}
	/** Sets a value to the specified Direction. 
	 * @param value the value.
	 * @param dir the Direction.
	 */
	public void setTranslate (double value, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setPosZ(value);
			break;
		case Backward:
			transform.setPosZ(-value);
			break;
		case Left:
			transform.setPosX(-value);
			break;
		case Right:
			transform.setPosX(value);
			break;
		case Up:
			transform.setPosY(-value);
			break;
		case Down:
			transform.setPosY(value);
			break;
		}
	}
	/** Adds an amount to the specified Direction. 
	 * @param amount the amount.
	 * @param dir the Direction.
	 */
	public void addRotate (double amount, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setRotZ(transform.getRotZ()+amount);
			break;
		case Backward:
			transform.setRotZ(transform.getRotZ()-amount);
			break;
		case Left:
			transform.setRotX(transform.getRotX()-amount);
			break;
		case Right:
			transform.setRotX(transform.getRotX()+amount);
			break;
		case Up:
			transform.setRotY(transform.getRotY()-amount);
			break;
		case Down:
			transform.setRotY(transform.getRotY()+amount);
			break;
		}
	}
	/** Sets a value to the specified Direction. 
	 * @param value the value.
	 * @param dir the Direction.
	 */
	public void setRotate (double value, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setRotZ(value);
			break;
		case Backward:
			transform.setRotZ(-value);
			break;
		case Left:
			transform.setRotX(-value);
			break;
		case Right:
			transform.setRotX(value);
			break;
		case Up:
			transform.setRotY(-value);
			break;
		case Down:
			transform.setRotY(value);
			break;
		}
	}
	/** The Model View Perspective Matrix Transformation.
	 * @param gameObject the GameObject to transform.
	 * @param cam the Camera to transform to.
	 */
	public static GameObject MVP (GameObject gameObject, Camera cam) {
		GameObject g = (GameObject) deepClone(gameObject);
		for (int a = 0; a < g.object.size(); a++) {
			ArrayList<Coordinate> poly = g.object.get(a);
			for (int b = 0; b < poly.size(); b++) {
				poly.set(b, poly.get(b).Transform(new Matrix (g.transform)));
				poly.set(b, poly.get(b).Transform(cam.LookAtMatrix()));
				poly.set(b, poly.get(b).Transform(cam.perspectiveMatrix()).Normalized());
				//poly.get(b).print();
			}
			g.object.set(a, poly);
		}
		return g;
	}
	/** Draws the GameObject*/
	public void paint (Graphics g, Camera cam, int width, int height) {
		int[] xCoord, yCoord;
		for (int a = 0; a < object.size(); a++) {
			xCoord = new int [object.get(a).size()];
			yCoord = new int [object.get(a).size()];
			if (cam.isVisible(object.get(a).get(0), 
					Coordinate.getNormal(object.get(a).get(0), object.get(a).get(1), object.get(a).get(2)))
					&& object.get(a).get(0).getX() >= -1 && object.get(a).get(0).getX() <= 1
					&& object.get(a).get(0).getY() >= -1 && object.get(a).get(0).getY() <= 1
					&& object.get(a).get(0).getZ() < -cam.getNearClip() && object.get(a).get(0).getZ() > -cam.getFarClip()) {
				for (int b = 0; b < object.get(a).size(); b++) {
					xCoord[b] = (int) Math.rint((object.get(a).get(b).getX()/object.get(a).get(b).getZ()*300)+width/2);
					yCoord[b] = (int) Math.rint((object.get(a).get(b).getY()/object.get(a).get(b).getZ()*300)+height/2);
				}
				g.setColor(Color.gray);
				g.fillPolygon(xCoord, yCoord, xCoord.length);
				g.setColor(Color.BLACK);
				g.drawPolygon(xCoord, yCoord, xCoord.length);
			}
		}
	}
	/** Prints the information on the GameObject.*/
	public void print () {
		System.out.println("GameObject File Name: " + FileName);
		System.out.println("Object active: " + Boolean.toString(active));
		printCoordinates();
	}
	/** Prints out the GameObject Coordinates.*/
	public void printCoordinates () {
		for (int a = 0; a < object.size(); a++) {
			System.out.println("Polygon " + (a+1) + ":");
			for (int b = 0; b < object.get(a).size(); b++) {
				object.get(a).get(b).print();
			}
		}
	}
	/** "Deep Clone" of any Java Object.*/
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
