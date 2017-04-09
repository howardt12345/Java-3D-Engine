import java.io.*;
@SuppressWarnings("serial")
/** The Vec3 class, extends Transformation and implements Serializable.*/
public class Vec3 extends Transformation implements Serializable {
	/** Creates a new Vec3 with given x, y, z Vec3s and W at 1.
	 * @param x.
	 * @param y.
	 * @param z. */
	public Vec3 (double x, double y, double z) 
	{
		super (x, y, z);
	}
	/** New Vec3 with default values.*/
	public Vec3 () 
	{
		super();
	}
	/** Dot Product of two Vec3s.
	 * @param coord1 Vec3 #1
	 * @param coord2 Vec3 #2*/
	public static double dot (Vec3 coord1, Vec3 coord2) 
	{
		return (coord1.X*coord2.X) + (coord1.Y*coord2.Y) + (coord1.Z*coord2.Z);
	}
	/** Returns the Cross Product of two Vec3s.
	 * @param v1 Vec3 #1
	 * @param v2 Vec3 #2*/
	public static Vec3 cross (Vec3 v1, Vec3 v2) 
	{
		return new Vec3 (
				v1.Y*v2.Z - v1.Z*v2.Y,
				v1.Z*v2.X - v1.X*v2.Z,
				v1.X*v2.Y - v1.Y*v2.X
				);
	}
	public Vec3 cross (Vec3 c) 
	{
		return new Vec3 (				
				Y*c.Z - Z*c.Y,
				Z*c.X - X*c.Z,
				X*c.Y - Y*c.X);
	}
	/** Adds a Vec3 to the current Vec3.
	 * @param Vec3 the Vec3 to add.
	 * */
	public void add (Vec3 Vec3) 
	{
		X += Vec3.X;
		Y += Vec3.Y;
		Z += Vec3.Z;
	}
	/** Subtracts a Vec3 to the current Vec3.
	 * @param Vec3 the Vec3 to subtract.
	 * */
	public void subtract (Vec3 Vec3) 
	{
		X -= Vec3.X;
		Y -= Vec3.Y;
		Z -= Vec3.Z;
	}
	/** Adds two Vec3s.
	 * @param v1 Vec3 #1.
	 * @param v2 Vec3 #2*/
	public static Vec3 add (Vec3 v1, Vec3 v2) 
	{
		return new Vec3 (v1.X + v2.X, v1.Y + v2.Y, v1.Z + v2.Z);
	}
	/** Subtract two Vec3s.
	 * @param v1 Vec3 #1.
	 * @param v2 Vec3 #2*/
	public static Vec3 subtract (Vec3 v1, Vec3 v2) 
	{
		return new Vec3 (v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z);
	}
	
	public Vec3 multiply (double scalar) {
		return new Vec3 (X*scalar, Y*scalar, Z*scalar);
	}
	
	/** Returns the Magnitude of this Vec3.*/
	public double getMagnitude () 
	{
		return 
		Math.sqrt(
				Math.abs(Math.pow(X, 2))
				 + Math.abs(Math.pow(Y, 2))
				 + Math.abs(Math.pow(Z, 2))
			)
		;
	}
	/** Returns a normalized directional Vec3 through dividing by the magnitude.*/
	public Vec3 normalized ()
	{
		return new Vec3 (X/getMagnitude(), Y/getMagnitude(), Z/getMagnitude());
	}
	/** Normalizes the directional Vec3 through dividing by the magnitude.*/
	public void normalize () 
	{
		X /= getMagnitude();
		Y /= getMagnitude();
		Z /= getMagnitude();
	}
	public void setMagnitude (double value) 
	{
		double m = getMagnitude();
		X /= (m/value);
		Y /= (m/value);
		Z /= (m/value);
	}
	public Vec3 SetMagnitude (double value) {
		double m = getMagnitude();
		return new Vec3 (
				X /= (m/value),
				Y /= (m/value),
				Z /= (m/value)
			);
	}
	/** @param up Shorthand for writing Vec3(0, 1, 0).*/
	public static Vec3 up = new Vec3 (0, 1, 0);
	/** @param down Shorthand for writing Vec3(0, -1, 0).*/
	public static Vec3 down = new Vec3 (0, -1, 0);
	/** @param forward Shorthand for writing Vec3(0, 0, 1).*/
	public static Vec3 forward = new Vec3 (0, 0, 1);
	/** @param back Shorthand for writing Vec3(0, 0, -1).*/
	public static Vec3 back = new Vec3 (0, 0, -1);
	/** @param left Shorthand for writing Vec3(-1, 0, 0).*/
	public static Vec3 left = new Vec3 (-1, 0, 0);
	/** @param right Shorthand for writing Vec3(1, 0, 0).*/
	public static Vec3 right = new Vec3 (1, 0, 0);
	/** @param zero Shorthand for writing Vec3(0, 0, 0).*/
	public static Vec3 zero = new Vec3 (0, 0, 0);
	/** @param one Shorthand for writing Vec3(1, 1, 1).*/
	public static Vec3 one = new Vec3 (1, 1, 1);
	/** @param center Shorthand for writng Vec3 (0, 0, 0)*/
	public static Vec3 center = new Vec3 (0, 0, 0);
}
