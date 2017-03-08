import java.io.Serializable;

@SuppressWarnings("serial")
public class Rotation implements Serializable{
	/** The internal values of the Rotation
	 * @param X the X Rotation.
	 * @param Y the Y Rotation.
	 * @param Z the Z Rotation.
	 */
	private double X = 0, Y = 0, Z = 0;
	public Rotation (double x, double y, double z) {
		X = x;
		Y = y;
		Z = z;
	}
	/** Prints out the values of the Rotation, structured as "X, Y, Z".*/
	public void print() {
		System.out.println(X + ", " + Y + ", " + Z);
	}
	/** Gets the values of the Rotations as a String structured as "X, Y, Z".*/
	public String asString() {
		return X + ", " + Y + ", " + Z;
	}
	/** Gets the values of the Rotations as a formatted String.
	 * Recommended format: "%1$f, %2$f, %3$f".
	 * @param format the formatting of the string.
	 */
	public String asString(String format) {
		return String.format(format, X, Y, Z);
	}
	/** Returns the X value of the Rotation.*/
	public double getX () {
		return X;
	}
	/** Returns the Y value of the Rotation.*/
	public double getY () {
		return Y;
	}
	/** Returns the Z value of the Rotation.*/
	public double getZ () {
		return Z;
	}
	/** Returns the X value of the Rotation in Radians.*/
	public double getRadianX () {
		return Math.toRadians(X);
	}
	/** Returns the Y value of the Rotation in Radians.*/
	public double getRadianY () {
		return Math.toRadians(Y);
	}
	/** Returns the Z value of the Rotation in Radians.*/
	public double getRadianZ () {
		return Math.toRadians(Z);
	}
	/** Set x, y and z components of an existing Rotation.
	 * @param new_x new X component.
	 * @param new_y new Y component.
	 * @param new_z new Z component.
	 */
	public void Set (double new_x, double new_y, double new_z) {
		X = new_x;
		Y = new_y;
		Z = new_z;
	}
	/** Sets the X value of the Rotation.
	 * @param new_X new X component.
	 */
	public void setX (double new_X) {
		X = new_X;
	}
	/** Sets the Y value of the Rotation.
	 * @param new_Y new Y component.
	 */
	public void setY (double new_Y) {
		Y = new_Y;
	}
	/** Sets the Z value of the Rotation.
	 * @param new_Z new Z component.
	 */
	public void setZ (double new_Z) {
		Z = new_Z;
	}
	/** @param zero Shorthand for writing Rotation (0,0,0)*/
	public static Rotation zero = new Rotation (0, 0, 0);
}
