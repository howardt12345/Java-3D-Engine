import java.io.Serializable;

@SuppressWarnings("serial")
/** The Scale class, implements Serializable.*/
public class Scale implements Serializable{
	/** The internal values of the scale
	 * @param ScaleX the X Scale.
	 * @param ScaleY the Y Scale.
	 * @param ScaleZ the Z Scale.
	 * @param Scalar the scalar.
	 */
	private double X = 1, Y = 1, Z = 1;
	/** Create s anew Scale from 3 scale values.
	 * @param x The scale along the X axis.
	 * @param y The scale along the Y axis.
	 * @param z The scale along the Z axis.
	 */
	public Scale (double x, double y, double z) {
		X = x;
		Y = y;
		Z = z;
	}
	/** Creates a new Scale from a scalar.
	 * @param scalar the scalar.
	 */
	public Scale (double scalar) {
		X = scalar;
		Y = scalar;
		Z = scalar;
	}
	/** Creates a new Scale.*/
	public Scale() {}
	/** Returns the X scale.*/
	public double getX() {
		return X;
	}
	/** Returns the Y scale.*/
	public double getY() {
		return Y;
	}
	/** Returns the Z scale.*/
	public double getZ() {
		return Z;
	}
	/** Sets the X value of the Scale.
	 * @param new_X new X component
	 */
	public void setX (double new_X) {
		X = new_X;
	}
	/** Sets the Y value of the Scale.
	 * @param new_Y new Y component
	 */
	public void setY (double new_Y) {
		Y = new_Y;
	}
	/** Sets the Z value of the Scale.
	 * @param new_Z new Z component
	 */
	public void setZ (double new_Z) {
		Z = new_Z;
	}
	/** Gets the average of the 3 scale factors*/
	public double getAverage() {
		return (X + Y + Z)/3;
	}
	/** Set x, y and z components of an existing Scale.
	 * @param new_x new X component.
	 * @param new_y new Y component.
	 * @param new_z new Z component.
	 */
	public void Set (double new_x, double new_y, double new_z) {
		X = new_x;
		Y = new_y;
		Z = new_z;
	}
	/** Updates the scale by a scalar.
	 * @param scalar the Scalar*/
	public void Set (double scalar) {
		X = scalar;
		Y = scalar;
		Z = scalar;
	}
	/** Returns true if the Scale is equal.
	 * @param sc Point to compare point to
	 * @return
	 */
	public boolean equals (Scale sc) {
		return X == sc.X && Y == sc.Y && Z == sc.Z ? true : false;
	}
	/** Prints out the values of the Scale, structured as "X, Y, Z".*/
	public void print() {
		System.out.println(X + ", " + Y + ", " + Z);
	}
	/** Gets the values of the Scale as a String structured as "X, Y, Z"*/
	public String asString() {
		return X + ", " + Y + ", " + Z;
	}
	/** Gets the values of the Scale as a formatted String.
	 * Recommended format: "%1$f, %2$f, %3$f"
	 * @param format the formatting of the string.
	 */
	public String asString(String format) {
		return String.format(format, X, Y, Z);
	}
}
