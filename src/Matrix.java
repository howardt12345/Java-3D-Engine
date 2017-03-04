/** The Matrix class.*/
public class Matrix {
	/** The Matrix.*/
	private double[][] matrix = {
			{1, 0, 0, 0},
			{0, 1, 0, 0}, 
			{0, 0, 1, 0},
			{0, 0, 0, 1}
		};
	/** New Matrix.*/
	public Matrix () {}
	/** New Transformation matrix from Transform.
	 * @param t
	 */
	public Matrix (Transform t) {
		Translate (t.getPosition());
		Rotate (t.getRotation());
		Scale (t.getScale());
	}
	/** New Translation Matrix from Coordinate.
	 * @param c the Coordinate.
	 */
	public Matrix (Coordinate c) {
		Translate (c);
	}
	/** New Rotational Matrix from Rotation.
	 * @param r the Rotation.
	 */
	public Matrix (Rotation r) {
		Rotate (r);
	}
	/** new Scaling Matrix from Scale.
	 * @param s the Scale.
	 */
	public Matrix (Scale s) {
		Scale (s);
	}
	/** Applies translation to matrix.
	 * @param c the Coordinate to translate by.
	 */
	public void Translate (Coordinate c) {
		set(c.getX(), 0, 3);
		set(c.getY(), 1, 3);
		set(c.getZ(), 2, 3);
	}
	/** Returns a Matrix with translation applied.
	 * @param c the Coordinate to translate by.
	 */
	public Matrix translateMatrix (Coordinate c) {
		Matrix m = new Matrix ();
		m.set(c.getX(), 0, 3);
		m.set(c.getY(), 1, 3);
		m.set(c.getZ(), 2, 3);
		m.set(c.getW(), 3, 3);
		return m;
	}
	/** Applies rotation to Matrix.
	 * @param r the Rotation.
	 */
	public void Rotate (Rotation r) {
		set((Math.cos(Math.toRadians(r.getY()))*Math.cos(Math.toRadians(r.getZ()))), 0, 0);
		set((-Math.cos(Math.toRadians(r.getY()))*Math.sin(Math.toRadians(r.getZ()))), 0, 1);
		set( (Math.sin(Math.toRadians(r.getY()))), 0, 2);
		
		set((Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getZ()))
				+Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.cos(Math.toRadians(r.getZ()))), 1, 0);
		set((Math.cos(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getZ()))
				-Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.sin(Math.toRadians(r.getZ()))), 1, 1);
		set(-(Math.sin(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getY()))), 1, 2);
		
		set((Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getZ()))
				-Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.cos(Math.toRadians(r.getZ()))), 2, 0);
		set((Math.sin(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getZ()))
				+Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.sin(Math.toRadians(r.getZ()))), 2, 1);
		set((Math.cos(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getY()))), 2, 2);
	}
	/** Returns a Matrix with rotation applied.
	 * @param r the Rotation.
	 */
	public Matrix rotationMatrix (Rotation r) {
		Matrix m = new Matrix();
		m.set((Math.cos(Math.toRadians(r.getY()))*Math.cos(Math.toRadians(r.getZ()))), 0, 0);
		m.set((-Math.cos(Math.toRadians(r.getY()))*Math.sin(Math.toRadians(r.getZ()))), 0, 1);
		m.set( (Math.sin(Math.toRadians(r.getY()))), 0, 2);
		
		m.set((Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getZ()))
				+Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.cos(Math.toRadians(r.getZ()))), 1, 0);
		m.set((Math.cos(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getZ()))
				-Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.sin(Math.toRadians(r.getZ()))), 1, 1);
		m.set(-(Math.sin(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getY()))), 1, 2);
		
		m.set((Math.sin(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getZ()))
				-Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.cos(Math.toRadians(r.getZ()))), 2, 0);
		m.set((Math.sin(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getZ()))
				+Math.cos(Math.toRadians(r.getX()))*Math.sin(Math.toRadians(r.getY()))
				*Math.sin(Math.toRadians(r.getZ()))), 2, 1);
		m.set((Math.cos(Math.toRadians(r.getX()))*Math.cos(Math.toRadians(r.getY()))), 2, 2);
		return m;
	}
	public Matrix scale (Scale s) {
		Matrix m = new Matrix ();
		m.set(s.getX(), 0, 0);
		m.set(s.getY(), 1, 1);
		m.set(s.getZ(), 2, 2);
		return m;
	}
	public void Scale (Scale s) {
		set(get(0,0)*s.getX(),0,0);
		set(get(1,1)*s.getY(),1,1);
		set(get(2,2)*s.getZ(),2,2);
	}
	
	/** Multiplies a Coordinate by the Matrix.
	 * @param c the Coordinate to multiply.
	 * @return the multiplied Coordinate.
	 */
	public Coordinate multiply (Coordinate c) {
		return new Coordinate (
				get(0, 0)*c.getX() + get(0, 1)*c.getY() + get(0, 2)*c.getZ() + get(0, 3)*c.getW(), 
				get(1, 0)*c.getX() + get(1, 1)*c.getY() + get(1, 2)*c.getZ() + get(1, 3)*c.getW(), 
				get(2, 0)*c.getX() + get(2, 1)*c.getY() + get(2, 2)*c.getZ() + get(2, 3)*c.getW(),
				get(3, 0)*c.getX() + get(3, 1)*c.getY() + get(3, 2)*c.getZ() + get(3, 3)*c.getW()
			);
	}
	/** Multiplies two Matrices together.
	 * @param m1 the first Matrix.
	 * @param m2 the second Matrix.
	 */
	public static Matrix multiply (Matrix m1, Matrix m2) {
		Matrix m = new Matrix ();
		for (int a = 0; a < 4; a++) {
			m.set(m1.get(0, 0)*m2.get(0, a)+m1.get(0, 1)*m2.get(1, a)
					+m1.get(0, 2)*m2.get(2, a)+m1.get(0, 3)*m2.get(3, a), 0, a);
			m.set(m1.get(1, 0)*m2.get(0, a)+m1.get(1, 1)*m2.get(1, a)
					+m1.get(1, 2)*m2.get(2, a)+m1.get(1, 3)*m2.get(3, a), 1, a);
			m.set(m1.get(2, 0)*m2.get(0, a)+m1.get(2, 1)*m2.get(1, a)
					+m1.get(2, 2)*m2.get(2, a)+m1.get(2, 3)*m2.get(3, a), 2, a);
			m.set(m1.get(3, 0)*m2.get(0, a)+m1.get(3, 1)*m2.get(1, a)
					+m1.get(3, 2)*m2.get(2, a)+m1.get(3, 3)*m2.get(3, a), 3, a);
		}
		return m;
	}
	/** Sets the Matrix at the provided index.
	 * @param value the value to set.
	 * @param row the row index.
	 * @param column the column index.
	 */
	public void set (double value, int row, int column) {
		matrix[row][column] = value;
	}
	/** Gets the Matrix value at the provided index.
	 * @param row the row index.
	 * @param column the column index.
	 * @return the Matrix value.
	 */
	public double get (int row, int column) {
		return matrix[row][column];
	}
	/** Prints the Matrix.*/
	public void print () {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				System.out.print(matrix[a][b] + " ");
			}
			System.out.println("");
		}
	}
}
