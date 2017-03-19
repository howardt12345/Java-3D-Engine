import java.io.*;
import java.util.*;
/** The Polygon Class.*/
@SuppressWarnings("serial")
public class Polygon implements Serializable{
	/** The vertices of the polygon.*/
	private ArrayList <Coordinate> polygon;
	/** Whether or not the Polygon is visible.*/
	private boolean visible = true;
	/** new Polygon.*/
	public Polygon () {
		polygon = new ArrayList <Coordinate>();
	}
	/** Adds a vertex to the Polygon.
	 * @param c the Coordinate to Add.
	 */
	public void add (Coordinate c) {
		polygon.add(c);
	}
	/** Returns the transformed Polygon.
	 * @param t the Transform.
	 * @return the transformed polygon.
	 */
	public Polygon Transform (Transform t) {
		Polygon p = this;
		p.transform(t);
		return p;
	}
	/** Returns the transformed Polygon.
	 * @param t the Matrix.
	 * @return the transformed polygon.
	 */
	public Polygon Transform (Matrix m) {
		Polygon p = this;
		p.transform(m);
		return p;
	}
	/** Transforms the Polygon.
	 * @param t the Transform.
	 */
	public void transform (Transform t) {
		for (int a = 0; a < polygon.size(); a++) {
			polygon.set(a, polygon.get(a).Transform(new Matrix (t)));
		}
	}
	/** Transforms the Polygon.
	 * @param t the Matrix to Transform with.
	 */
	public void transform (Matrix m) {
		for (int a = 0; a < polygon.size(); a++) {
			polygon.set(a, polygon.get(a).Transform(m));
		}
	}
	/** Normalizes the Polygon Coordinates by W.*/
	public void Normalize () {
		for (int a = 0; a < polygon.size(); a++) {
			polygon.set(a, polygon.get(a).Normalized());
		}
	}
	/** Returns a Polygon with normalized Coordinates by W.*/
	public Polygon Normalized () {
		Polygon p = new Polygon ();
		for (Coordinate c : polygon) {
			p.add(c.Normalized());
		}
		return p;
	}
	/** Normalizes the Polygon Coordinates by dividing by magnitude.*/
	public void normalize () {
		for (int a = 0; a < polygon.size(); a++) {
			polygon.set(a, polygon.get(a).Normalized());
		}
	}
	/** Returns a polygon with normalized Coordinates by dividing by magnitude.*/
	public Polygon normalized () {
		Polygon p = new Polygon ();
		for (Coordinate c : polygon) {
			p.add(c.normalized());
		}
		return p;
	}
	/** Returns the Coordinate at the specified position*/
	public Coordinate get (int index) {
		return polygon.get(index);
	}
	/** Replaces the Coordinate at the specified position with the specified Coordinate.*/
	public void set (int index, Coordinate c) {
		polygon.set(index, c);
	}
	/** Whether or not the polygon is visible.
	 */
	public boolean isVisible() {
		return visible;
	}
	/** Sets polygon visibility.
	 * @param visible polygon visibility.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/** Returns the number of vertices in polygon.*/
	public int size() {
		return polygon.size();
	}
	/** Gets the Normal of the Polygon.*/
	public Coordinate getNormal () {
		return !polygon.isEmpty() ? Coordinate.getNormal(polygon.get(0), polygon.get(1), polygon.get(2)) : null; 
	}
	/** Gets the Center Coordinate of the Polygon.*/
	public Coordinate getCenter () {
		return Coordinate.getCenter(polygon);
	}
	/** Prints out the vertices of the Polygon.*/
	public void print () {
		for (Coordinate c : polygon) {
			c.print();
		}
	}
	/** Prints out detailed information on the Polygon.*/
	public void detailedPrint () {
		System.out.println("Polygon:");
		for (int a = 0; a < polygon.size(); a++) {
			System.out.println("Vertex " + (a+1) + ":");
			polygon.get(a).print();
		}
		System.out.println("Normal:");
		getNormal().print();
	}
}
