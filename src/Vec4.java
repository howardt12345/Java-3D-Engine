import java.util.*;
import java.io.*;
/** The Vec4 class, implements Serializable.*/
@SuppressWarnings("serial")
public class Vec4 implements Serializable{
	/** The internal values of the Vec4.
	 * @param X the X component.
	 * @param Y the Y component.
	 * @param Z the Z component.
	 * @param W the W component.
	 */
	private double X = 0, Y = 0, Z = 0, W = 1;
	/** Creates a new Vec4 with given x, y, z, and w Vec4s.
	 * @param x.
	 * @param y.
	 * @param z.
	 * @param w
	 *  */
	public Vec4 (double x, double y, double z, double w) {
		X = x;
		Y = y;
		Z = z;
		W = w;
	}
	/** Creates a new Vec4 with given x, y, z Vec4s and W at 1.
	 * @param x.
	 * @param y.
	 * @param z. */
	public Vec4 (double x, double y, double z) {
		X = x;
		Y = y;
		Z = z;
		W = 1;
	}
	/** New Vec4 with default values.*/
	public Vec4 () {}
	/** Calculates the distance between 2 Vec4s.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2.
	 */
	public static double getDistance (Vec4 v1, Vec4 v2) {
		return Math.abs(
		Math.sqrt(
				Math.pow(v2.X - v1.X, 2) + 
				Math.pow(v2.Y - v1.Y, 2) + 
				Math.pow(v2.Z - v1.Z, 2)
			)
		);
	}
	/** Returns the Magnitude of this Vec4.*/
	public double magnitude () {
		return Math.abs(
		Math.sqrt(
				Math.pow(X, 2) + Math.pow(Y, 2) + Math.pow(Z, 2)
			)
		);
	}
	/** Returns a normalized Vec4 through dividing by W.*/
	public Vec4 Normalized() {
		return new Vec4 (X/W,Y/W,Z/W, 1);
	}
	/** Normalizes the Vec4 through dividing by W.*/
	public void Normalize () {
		X /= W;
		Y /= W;
		Z /= W;
	}
	/** Returns a normalized directional Vec4 through dividing by the magnitude.*/
	public Vec4 normalized () {
		return new Vec4 (X/magnitude(), Y/magnitude(), Z/magnitude(), 0);
	}
	/** Normalizes the directional Vec4 through dividing by the magnitude.*/
	public void normalize () {
		X /= magnitude();
		Y /= magnitude();
		Z /= magnitude();
	}
	/** Dot Product of two Vec4s.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2.
	 * @return the dot product.
	 */
	public static double dot (Vec4 v1, Vec4 v2) {
		return (v1.X*v2.X) + (v1.Y*v2.Y) + (v1.Z*v2.Z) + (v1.W*v2.W);
	}
	/** Returns the Cross Product of two Vec4s.
	 * @param v1 Vector #1.
	 * @param v2 Vector #2.
	 * @return the cross product.
	 */
	public static Vec4 cross (Vec4 v1, Vec4 v2) {
		return new Vec4 (
				v1.Y*v2.Z - v1.Z*v2.Y,
				v1.Z*v2.X - v1.X*v2.Z,
				v1.X*v2.Y - v1.Y*v2.X,
				0
				);
	}
	/** Returns the Cross Product of this Vec4 and another Vec4.
	 * @param v the Vec4
	 * @return the cross product.
	 */
	public Vec4 cross (Vec4 v) {
		return new Vec4 (				
				Y*v.Z - Z*v.Y,
				Z*v.X - X*v.Z,
				X*v.Y - Y*v.X);
	}
	/** Adds a Vec4 to the current Vec4.
	 * @param v the Vec4 to add.
	 * */
	public void add (Vec4 v) {
		X += v.X;
		Y += v.Y;
		Z += v.Z;
	}
	/** Subtracts a Vec4 to the current Vec4.
	 * @param v the Vec4 to subtract.
	 * */
	public void subtract (Vec4 v) {
		X -= v.X;
		Y -= v.Y;
		Z -= v.Z;
	}
	/** Adds two Vec4s.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2*/
	public static Vec4 add (Vec4 v1, Vec4 v2) {
		return new Vec4 (v1.X + v2.X, v1.Y + v2.Y, v1.Z + v2.Z);
	}
	/** Subtract two Vec4s.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2*/
	public static Vec4 subtract (Vec4 v1, Vec4 v2) {
		return new Vec4 (v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z);
	}
	/** Returns a new Vector with the Vec4 added.
	 * @param Vec4 the Vec4 to modify.
	 * @param vec Vector to add to Vec4.
	 */
	public static Vec4 getVector (Vec4 v1, Vec4 v2) {
		return new Vec4 
				(v2.X - v1.X, 
				v2.Y - v1.Y, 
				v2.Z - v1.Z, 
				0);
	}
	/** Returns a Vec4 of the getMidpoint of 2 Vec4s.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2.
	 */
	public static Vec4 getMidpoint(Vec4 v1, Vec4 v2) {
		return new Vec4 (
				(v1.X + v2.X)/2, 
				(v1.Y + v2.Y)/2, 
				(v1.Z + v2.Z)/2,
				1
			);
	}
	/** Returns the centroid of 3 Vec4s as a Vec4.
	 * @param v1 Vec4 #1.
	 * @param v2 Vec4 #2.
	 * @param v3 Vec4 #3.
	 */
	public static Vec4 getCentroid(Vec4 v1, Vec4 v2, Vec4 v3) {
		return new Vec4 (
				(v1.X + v2.X + v3.X)/3, 
				(v1.Y + v2.Y + v3.Y)/3, 
				(v1.Z + v2.Z + v3.Z)/3,
				(v1.W + v2.W + v3.W)/3
			);
	}
	/** Returns the center of an array of Vec4s.
	 * @param v array of Vec4s.
	 * @return new Vec4.
	 */
	public static Vec4 getCenter (Vec4[] v) {
		double tmpX = 0, tmpY = 0, tmpZ = 0, tmpW = 0;
		for (int a = 0; a < v.length; a++) {
			tmpX += v[a].X;
			tmpY += v[a].Y;
			tmpZ += v[a].Z;
			tmpW += v[a].W;
		}
		return new Vec4 (tmpX/v.length, tmpY/v.length, tmpZ/v.length, tmpW/v.length);
	}
	/** Returns the center of an ArrayList of Vec4s.
	 * @param ArrayList of Vec4s.
	 * @return new Vec4.
	 * */
	public static Vec4 getCenter (ArrayList<Vec4> v) {
		double tmpX = 0, tmpY = 0, tmpZ = 0, tmpW = 0;
		for (int a = 0; a < v.size(); a++) {
			tmpX += v.get(a).X;
			tmpY += v.get(a).Y;
			tmpZ += v.get(a).Z;
			tmpW += v.get(a).W;
		}
		return new Vec4 (tmpX/v.size(), 
				tmpY/v.size(), 
				tmpZ/v.size(),
				tmpW/v.size());
	}
	/** Transforms a Vec4 by a Matrix.
	 * @param v the Vec4 to Tranaform.
	 * @param m The Matrix to Transform Coordinat with.
	 */
	public static Vec4 Transform (Vec4 v, Matrix m) {
		return m.multiply(v);
	}
	/** Transforms the Vec4 by a Matrix.
	 * @param m the Matrix to Tranform Vec4 with.
	 */
	public Vec4 Transform (Matrix m) {
		return m.multiply(this);
	}
	public static Vec4 getNormal (Vec4 v1, Vec4 v2, Vec4 v3) {
		Vec4 a = Vec4.subtract(v1, v2), 
		b = Vec4.subtract(v1, v3);
		return Vec4.cross(a, b);
	}
	/** Returns true if the Vec4s are equal.
	 * @param v Vec4 to compare Vec4 to.
	 */
	public boolean equals (Vec4 v) {
		return X == v.X && Y == v.Y && Z == v.Z && W == v.W;
	}
	/** Prints out the values of the Vec4, structured as "X, Y, Z, W".*/
	public void print() {
		System.out.println(X + ", " + Y + ", " + Z + ", " + W);
	}
	/** Gets the values of the Vec4s as a String structured as "X, Y, Z, W".*/
	public String asString() {
		return X + ", " + Y + ", " + Z + ", " + W;
	}
	/** Gets the values of the Vec4s as a formatted String.
	 * Recommended format: "%1$f, %2$f, %3$f, %4$f".
	 * @param format the formatting of the string.
	 */
	public String asString(String format) {
		return String.format(format, X, Y, Z, W);
	}
	/** Returns the X value of the Vec4.*/
	public double getX () {
		return X;
	}
	/** Returns the Y value of the Vec4.*/
	public double getY () {
		return Y;
	}
	/** Returns the Z value of the Vec4.*/
	public double getZ () {
		return Z;
	}
	/** Returns the W value of the Vec4.*/
	public double getW () {
		return W;
	}
	/** Set x, y, z, and w components of an existing Vec4.
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
	/** Sets the X value of the Vec4.
	 * @param new_X new X component.
	 */
	public void setX (double new_X) {
		X = new_X;
	}
	/** Sets the Y value of the Vec4.
	 * @param new_Y new Y component.
	 */
	public void setY (double new_Y) {
		Y = new_Y;
	}
	/** Sets the Z value of the Vec4.
	 * @param new_Z new Z component.
	 */
	public void setZ (double new_Z) {
		Z = new_Z;
	}
	/** Sets the W value of the Vec4.
	 * @param new_W new W component.
	 */
	public void setW (double new_W) {
		W = new_W;
	}
	/** @param up Shorthand for writing Vec4(0, 1, 0, 0).*/
	public static Vec4 up = new Vec4 (0, 1, 0, 0);
	/** @param down Shorthand for writing Vec4(0, -1, 0, 0).*/
	public static Vec4 down = new Vec4 (0, -1, 0, 0);
	/** @param forward Shorthand for writing Vec4(0, 0, 1, 0).*/
	public static Vec4 forward = new Vec4 (0, 0, 1, 0);
	/** @param back Shorthand for writing Vec4(0, 0, -1, 0).*/
	public static Vec4 back = new Vec4 (0, 0, -1, 0);
	/** @param left Shorthand for writing Vec4(-1, 0, 0. 0).*/
	public static Vec4 left = new Vec4 (-1, 0, 0, 0);
	/** @param right Shorthand for writing Vec4(1, 0, 0, 0).*/
	public static Vec4 right = new Vec4 (1, 0, 0, 0);
	/** @param zero Shorthand for writing Vec4(0, 0, 0, 0).*/
	public static Vec4 zero = new Vec4 (0, 0, 0, 0);
	/** @param one Shorthand for writing Vec4(1, 1, 1, 1).*/
	public static Vec4 one = new Vec4 (1, 1, 1, 1);
	/** @param center Shorthand for writng Vec4 (0, 0, 0, 1)*/
	public static Vec4 center = new Vec4 (0, 0, 0, 1);
}