import java.util.*;
import java.io.*;
/** The Coordinate class.*/
@SuppressWarnings("serial")
public class Coordinate implements Serializable{
	/** The internal values of the Coordinate.
	 * @param X the X component.
	 * @param Y the Y component.
	 * @param Z the Z component.
	 * @param W the W component.
	 */
	private double X = 0, Y = 0, Z = 0, W = 1;
	/** Creates a new Coordinate with given x, y, z, and w Coordinates.
	 * @param x.
	 * @param y.
	 * @param z.
	 * @param w
	 *  */
	public Coordinate (double x, double y, double z, double w) {
		X = x;
		Y = y;
		Z = z;
		W = w;
	}
	/** Creates a new Coordinate with given x, y, z Coordinates and W at 1.
	 * @param x.
	 * @param y.
	 * @param z. */
	public Coordinate (double x, double y, double z) {
		X = x;
		Y = y;
		Z = z;
		W = 1;
	}
	/** New Coordinate with default values.*/
	public Coordinate () {}
	/** Calculates the distance between 2 Coordinates.
	 * @param c1 Coordinate #1.
	 * @param c2 Coordinate #2.
	 */
	public static double getDistance (Coordinate c1, Coordinate c2) {
		return Math.abs(
		Math.sqrt(
				Math.pow(c2.X - c1.X, 2) + 
				Math.pow(c2.Y - c1.Y, 2) + 
				Math.pow(c2.Z - c1.Z, 2)
			)
		);
	}
	/** Returns the Magnitude of this coordinate.*/
	public double magnitude () {
		return Math.abs(
		Math.sqrt(
				Math.pow(X, 2) + Math.pow(Y, 2) + Math.pow(Z, 2)
			)
		);
	}
	/** Returns a normalized Coordinate through dividing by W.*/
	public Coordinate Normalized() {
		return new Coordinate (X/W,Y/W,Z/W, 1);
	}
	/** Normalizes the Coordinate through dividing by W.*/
	public void Normalize () {
		X /= W;
		Y /= W;
		Z /= W;
	}
	/** Returns a normalized directional Coordinate through dividing by the magnitude.*/
	public Coordinate normalized () {
		return new Coordinate (X/magnitude(), Y/magnitude(), Z/magnitude(), 0);
	}
	/** Normalizes the directional Coordinate through dividing by the magnitude.*/
	public void normalize () {
		X /= magnitude();
		Y /= magnitude();
		Z /= magnitude();
	}
	/** Dot Product of two Coordinates.
	 * @param coord1 Coordinate #1
	 * @param coord2 Coordinate #2*/
	public static double dot (Coordinate coord1, Coordinate coord2) {
		return (coord1.X*coord2.X) + (coord1.Y*coord2.Y) + (coord1.Z*coord2.Z) + (coord1.W*coord2.W);
	}
	/** Returns the Cross Product of two Coordinates.
	 * @param c1 Vector #1
	 * @param c2 Vector #2*/
	public static Coordinate cross (Coordinate c1, Coordinate c2) {
		return new Coordinate (
				c1.Y*c2.Z - c1.Z*c2.Y,
				c1.Z*c2.X - c1.X*c2.Z,
				c1.X*c2.Y - c1.Y*c2.X,
				0
				);
	}
	public Coordinate cross (Coordinate c) {
		return new Coordinate (				
				Y*c.Z - Z*c.Y,
				Z*c.X - X*c.Z,
				X*c.Y - Y*c.X);
	}
	/** Adds a Coordinate to the current Coordinate.
	 * @param Coordinate the Coordinate to add.
	 * */
	public void add (Coordinate Coordinate) {
		X += Coordinate.X;
		Y += Coordinate.Y;
		Z += Coordinate.Z;
	}
	/** Subtracts a Coordinate to the current Coordinate.
	 * @param Coordinate the Coordinate to subtract.
	 * */
	public void subtract (Coordinate Coordinate) {
		X -= Coordinate.X;
		Y -= Coordinate.Y;
		Z -= Coordinate.Z;
	}
	/** Adds two Coordinates.
	 * @param c1 Coordinate #1.
	 * @param c2 Coordinate #2*/
	public static Coordinate add (Coordinate c1, Coordinate c2) {
		return new Coordinate (c1.X + c2.X, c1.Y + c2.Y, c1.Z + c2.Z);
	}
	/** Subtract two Coordinates.
	 * @param c1 Coordinate #1.
	 * @param c2 Coordinate #2*/
	public static Coordinate subtract (Coordinate c1, Coordinate c2) {
		return new Coordinate (c1.X - c2.X, c1.Y - c2.Y, c1.Z - c2.Z);
	}
	/** Returns a new Vector with the Coordinate added.
	 * @param Coordinate the Coordinate to modify.
	 * @param vec Vector to add to Coordinate.
	 */
	public static Coordinate getVector (Coordinate c1, Coordinate c2) {
		return new Coordinate 
				(c2.X - c1.X, 
				c2.Y - c1.Y, 
				c2.Z - c1.Z, 
				0);
	}

	/** Returns a Coordinate of the getMidpoint of 2 Coordinates.
	 * @param c1 Coordinate #1.
	 * @param c2 Coordinate #2.
	 */
	public static Coordinate getMidpoint(Coordinate c1, Coordinate c2) {
		return new Coordinate (
				(c1.X + c2.X)/2, 
				(c1.Y + c2.Y)/2, 
				(c1.Z + c2.Z)/2,
				1
			);
	}
	/** Returns the centroid of 3 Coordinates as a Coordinate.
	 * @param c1 Coordinate #1.
	 * @param c2 Coordinate #2.
	 * @param c3 Coordinate #3.
	 */
	public static Coordinate getCentroid(Coordinate c1, Coordinate c2, Coordinate c3) {
		return new Coordinate (
				(c1.X + c2.X + c3.X)/3, 
				(c1.Y + c2.Y + c3.Y)/3, 
				(c1.Z + c2.Z + c3.Z)/3,
				(c1.W + c2.W + c3.W)/3
			);
	}
	/** Returns the center of an array of Coordinates.
	 * @param Coordinates array of Coordinates.
	 * @return new Coordinate.
	 */
	public static Coordinate getCenter (Coordinate[] Coordinates) {
		double tmpX = 0, tmpY = 0, tmpZ = 0, tmpW = 0;
		for (int a = 0; a < Coordinates.length; a++) {
			tmpX += Coordinates[a].X;
			tmpY += Coordinates[a].Y;
			tmpZ += Coordinates[a].Z;
			tmpW += Coordinates[a].W;
		}
		return new Coordinate (tmpX/Coordinates.length, tmpY/Coordinates.length, tmpZ/Coordinates.length, tmpW/Coordinates.length);
	}
	/** Returns the center of an ArrayList of Coordinates.
	 * @param ArrayList of Coordinates.
	 * @return new Coordinate.
	 * */
	public static Coordinate getCenter (ArrayList<Coordinate> Coordinates) {
		double tmpX = 0, tmpY = 0, tmpZ = 0, tmpW = 0;
		for (int a = 0; a < Coordinates.size(); a++) {
			tmpX += Coordinates.get(a).X;
			tmpY += Coordinates.get(a).Y;
			tmpZ += Coordinates.get(a).Z;
			tmpW += Coordinates.get(a).W;
		}
		return new Coordinate (tmpX/Coordinates.size(), 
				tmpY/Coordinates.size(), 
				tmpZ/Coordinates.size(),
				tmpW/Coordinates.size());
	}
	/** Transforms a Coordinate by a Matrix.
	 * @param c the Coordinate to Tranaform.
	 * @param m The Matrix to Transform Coordinat with.
	 */
	public static Coordinate Transform (Coordinate c, Matrix m) {
		return m.multiply(c);
	}
	/** Transforms the Coordinate by a Matrix.
	 * @param m the Matrix to Tranform Coordinate with.
	 */
	public Coordinate Transform (Matrix m) {
		return m.multiply(this);
	}
	public static Coordinate getNormal (Coordinate c1, Coordinate c2, Coordinate c3) {
		Coordinate a, b;
		a = Coordinate.subtract(c1, c2);
		b = Coordinate.subtract(c1, c3);
		return Coordinate.cross(a, b);
	}
	/** Returns true if the Coordinates are equal.
	 * @param c Coordinate to compare Coordinate to.
	 */
	public boolean equals (Coordinate c) {
		return X == c.X && Y == c.Y && Z == c.Z && W == c.W;
	}
	/** Prints out the values of the Coordinate, structured as "X, Y, Z, W".*/
	public void print() {
		System.out.println(X + ", " + Y + ", " + Z + ", " + W);
	}
	/** Gets the values of the Coordinates as a String structured as "X, Y, Z, W".*/
	public String asString() {
		return X + ", " + Y + ", " + Z + ", " + W;
	}
	/** Gets the values of the Coordinates as a formatted String.
	 * Recommended format: "%1$f, %2$f, %3$f, %4$f".
	 * @param format the formatting of the string.
	 */
	public String asString(String format) {
		return String.format(format, X, Y, Z, W);
	}
	/** Returns the X value of the Coordinate.*/
	public double getX () {
		return X;
	}
	/** Returns the Y value of the Coordinate.*/
	public double getY () {
		return Y;
	}
	/** Returns the Z value of the Coordinate.*/
	public double getZ () {
		return Z;
	}
	/** Returns the W value of the Coordinate.*/
	public double getW () {
		return W;
	}
	/** Set x, y, z, and w components of an existing Coordinate.
	 * @param new_x new X component.
	 * @param new_y new Y component.
	 * @param new_z new Z component.
	 * @param new_w new w component.
	 */
	public void Set (double new_x, double new_y, double new_z, double new_w) {
		X = new_x;
		Y = new_y;
		Z = new_z;
		W = new_w;
	}
	/** Sets the X value of the Coordinate.
	 * @param new_X new X component.
	 */
	public void setX (double new_X) {
		X = new_X;
	}
	/** Sets the Y value of the Coordinate.
	 * @param new_Y new Y component.
	 */
	public void setY (double new_Y) {
		Y = new_Y;
	}
	/** Sets the Z value of the Coordinate.
	 * @param new_Z new Z component.
	 */
	public void setZ (double new_Z) {
		Z = new_Z;
	}
	/** Sets the W value of the Coordinate.
	 * @param new_W new W component.
	 */
	public void setW (double new_W) {
		W = new_W;
	}
	/** @param up Shorthand for writing Coordinate(0, 1, 0, 0).*/
	public static Coordinate up = new Coordinate (0, 1, 0, 0);
	/** @param down Shorthand for writing Coordinate(0, -1, 0, 0).*/
	public static Coordinate down = new Coordinate (0, -1, 0, 0);
	/** @param forward Shorthand for writing Coordinate(0, 0, 1, 0).*/
	public static Coordinate forward = new Coordinate (0, 0, 1, 0);
	/** @param back Shorthand for writing Coordinate(0, 0, -1, 0).*/
	public static Coordinate back = new Coordinate (0, 0, -1, 0);
	/** @param left Shorthand for writing Coordinate(-1, 0, 0. 0).*/
	public static Coordinate left = new Coordinate (-1, 0, 0, 0);
	/** @param right Shorthand for writing Coordinate(1, 0, 0, 0).*/
	public static Coordinate right = new Coordinate (1, 0, 0);
	/** @param zero Shorthand for writing Coordinate(0, 0, 0).*/
	public static Coordinate zero = new Coordinate (0, 0, 0, 0);
	/** @param one Shorthand for writing Coordinate(1, 1, 1, 0).*/
	public static Coordinate one = new Coordinate (1, 1, 1, 0);
	/** @param center Shorthand for writng Coordinate (0, 0, 0, 1)*/
	public static Coordinate center = new Coordinate (0, 0, 0, 1);
}
