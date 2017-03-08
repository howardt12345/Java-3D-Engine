/** The Matrix class.*/
public class Matrix {
	/** The Matrix.*/
	private double[][] matrix = {
			{1, 0, 0, 0},
			{0, 1, 0, 0}, 
			{0, 0, 1, 0},
			{0, 0, 0, 1}
		};
	/** New Matrix. Default 4x4 Matrix.*/
	public Matrix () {}
	/** New Transformation matrix from Transform.
	 * @param t
	 */
	public Matrix (Transform t) {
		Translate (t.getPosition());
		RotateXYZ (t.getRotation());
		//Scale (t.getScale());
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
		RotateXYZ (r);
	}
	/** new Scaling Matrix from Scale.
	 * @param s the Scale.
	 */
	public Matrix (Scale s) {
		Scale (s);
	}
	public Matrix (int rows, int columns) {
		matrix = new double[rows][columns];
		for (int a = 0; a < rows; a++) {
			for (int b = 0; b < columns; b++) {
				matrix[a][b] = a == b ? 1 : 0;
			}
		}
	}
	/** Identity Matrix from rows and columns.
	 * @param rows
	 * @param columns
	 */
	public static Matrix identity (int rows, int columns) {
		Matrix m = new Matrix (rows, columns);
		m.matrix = new double[rows][columns];
		for (int a = 0; a < rows; a++) {
			for (int b = 0; b < columns; b++) {
				m.matrix[a][b] = a == b ? 1 : 0;
			}
		}
		return m;
	}
	/** Converts Matrix to identity Matrix.*/
	public void identity () {
		for (int a = 0; a < rowLength(); a++) {
			for (int b = 0; b < columnLength(); b++) {
				matrix[a][b] = a == b ? 1 : 0;
			}
		}
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
	/** Applies XYZ rotation to Matrix .
	 * @param r the Rotation.
	 */
	public void RotateXYZ (Rotation r) {
		/*rotateX (r);
		rotateY (r);
		rotateZ (r);*/
		set(Math.cos(r.getRadianY())*Math.cos(r.getRadianZ()), 0, 0);
		set(Math.cos(r.getRadianZ())*Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				- Math.cos(r.getRadianX())*Math.sin(r.getRadianZ()), 0, 1);
		set(Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())*Math.sin(r.getRadianY())
				+ Math.sin(r.getRadianX())*Math.sin(r.getRadianZ()), 0, 2);
		
		set(Math.cos(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 0);
		set(Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())
			+Math.sin(r.getRadianX())*Math.sin(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 1);
		set(-Math.cos(r.getRadianZ())*Math.sin(r.getRadianX())
			+Math.cos(r.getRadianX())*Math.sin(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 2);
		
		set(-Math.sin(r.getRadianY()), 2, 0);
		set(Math.cos(r.getRadianY())*Math.sin(r.getRadianX()), 2, 1);
		set(Math.cos(r.getRadianX())*Math.cos(r.getRadianY()), 2, 2);
	}
	/** Returns a Matrix with XYZ rotation applied.
	 * @param r the Rotation.
	 */
	public Matrix rotationXYZ (Rotation r) {
		Matrix m = new Matrix ();
		/*m.rotationX (r);
		m.rotationY (r);
		m.rotationZ (r);*/
		m.set(Math.cos(r.getRadianY())*Math.cos(r.getRadianZ()), 0, 0);
		m.set(Math.cos(r.getRadianZ())*Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				- Math.cos(r.getRadianX())*Math.sin(r.getRadianZ()), 0, 1);
		m.set(Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())*Math.sin(r.getRadianY())
				+ Math.sin(r.getRadianX())*Math.sin(r.getRadianZ()), 0, 2);
		
		m.set(Math.cos(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 0);
		m.set(Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())
			+Math.sin(r.getRadianX())*Math.sin(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 1);
		m.set(-Math.cos(r.getRadianZ())*Math.sin(r.getRadianX())
			+Math.cos(r.getRadianX())*Math.sin(r.getRadianY())*Math.sin(r.getRadianZ()), 1, 2);
		
		m.set(-Math.sin(r.getRadianY()), 2, 0);
		m.set(Math.cos(r.getRadianY())*Math.sin(r.getRadianX()), 2, 1);
		m.set(Math.cos(r.getRadianX())*Math.cos(r.getRadianY()), 2, 2);
		return m;
	}
	public void RotateZYX (Rotation r) {
		/*rotateZ (r);
		rotateY (r);
		rotateX (r);*/
		set((Math.cos(r.getRadianY())*Math.cos(r.getRadianZ())), 0, 0);
		set((-Math.cos(r.getRadianY())*Math.sin(r.getRadianZ())), 0, 1);
		set((Math.sin(r.getRadianY())), 0, 2);
		
		set((Math.cos(r.getRadianX())*Math.sin(r.getRadianZ())
				+Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.cos(r.getRadianZ())), 1, 0);
		set((Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())
				-Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.sin(r.getRadianZ())), 1, 1);
		set(-(Math.sin(r.getRadianX())*Math.cos(r.getRadianY())), 1, 2);
		
		set((Math.sin(r.getRadianX())*Math.sin(r.getRadianZ())
				-Math.cos(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.cos(r.getRadianZ())), 2, 0);
		set((Math.sin(r.getRadianX())*Math.cos(r.getRadianZ())
				+Math.cos(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.sin(r.getRadianZ())), 2, 1);
		set((Math.cos(r.getRadianX())*Math.cos(r.getRadianY())), 2, 2);
	}
	public Matrix rotationZYX (Rotation r) {
		Matrix m = new Matrix();
		/*m.rotationZ (r);
		m.rotationY (r);
		m.rotationX (r);*/
		m.set((Math.cos(r.getRadianY())*Math.cos(r.getRadianZ())), 0, 0);
		m.set((-Math.cos(r.getRadianY())*Math.sin(r.getRadianZ())), 0, 1);
		m.set( (Math.sin(r.getRadianY())), 0, 2);
		
		m.set((Math.cos(r.getRadianX())*Math.sin(r.getRadianZ())
				+Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.cos(r.getRadianZ())), 1, 0);
		m.set((Math.cos(r.getRadianX())*Math.cos(r.getRadianZ())
				-Math.sin(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.sin(r.getRadianZ())), 1, 1);
		m.set(-(Math.sin(r.getRadianX())*Math.cos(r.getRadianY())), 1, 2);
		
		m.set((Math.sin(r.getRadianX())*Math.sin(r.getRadianZ())
				-Math.cos(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.cos(r.getRadianZ())), 2, 0);
		m.set((Math.sin(r.getRadianX())*Math.cos(r.getRadianZ())
				+Math.cos(r.getRadianX())*Math.sin(r.getRadianY())
				*Math.sin(r.getRadianZ())), 2, 1);
		m.set((Math.cos(r.getRadianX())*Math.cos(r.getRadianY())), 2, 2);
		return m;
	}
	/** Applies Rotational values on X Axis to Matrix.
	 * @param r the rotation.
	 */
	public void rotateX (Rotation r) {
		set(Math.cos(r.getRadianX()), 1, 1);
		set(-Math.sin(r.getRadianX()), 1, 2);
		set(Math.sin(r.getRadianX()), 2, 1);
		set(Math.cos(r.getRadianX()), 2, 2);
	}
	/** Returns the Rotational Matrix on the X Axis of the Rotation.
	 * @param r the rotation.
	 */
	public Matrix rotationX (Rotation r) {
		Matrix m = new Matrix ();
		m.set(Math.cos(r.getRadianX()), 1, 1);
		m.set(-Math.sin(r.getRadianX()), 1, 2);
		m.set(Math.sin(r.getRadianX()), 2, 1);
		m.set(Math.cos(r.getRadianX()), 2, 2);
		return m;
	}
	/** Applied Rotational values on Y Axis to Matrix.
	 * @param r the rotation.
	 */
	public void rotateY (Rotation r) {
		set(Math.cos(r.getRadianY()), 0, 0);
		set(Math.sin(r.getRadianY()), 0, 2);
		set(-Math.sin(r.getRadianY()), 2, 0);
		set(Math.cos(r.getRadianY()), 2, 2);
	}
	/** Returns the Rotational Matrix on the Y Axis of the Rotation.
	 * @param r the rotation.
	 */
	public Matrix rotationY (Rotation r) {
		Matrix m = new Matrix ();
		m.set(Math.cos(r.getRadianY()), 0, 0);
		m.set(Math.sin(r.getRadianY()), 0, 2);
		m.set(-Math.sin(r.getRadianY()), 2, 0);
		m.set(Math.cos(r.getRadianY()), 2, 2);
		return m;
	}
	/** Applied Rotational values on Z Axis to Matrix.
	 * @param r the rotation.
	 */
	public void rotateZ (Rotation r) {
		set(Math.cos(r.getRadianZ()), 0, 0);
		set(-Math.sin(r.getRadianZ()), 0, 1);
		set(Math.sin(r.getRadianZ()), 1, 0);
		set(Math.cos(r.getRadianZ()), 1, 1);
	}
	/** Returns the Rotational Matrix on the Z Axis of the Rotation.
	 * @param r the rotation.
	 */
	public Matrix rotationZ (Rotation r) {
		Matrix m = new Matrix ();
		m.set(Math.cos(r.getRadianZ()), 0, 0);
		m.set(-Math.sin(r.getRadianZ()), 0, 1);
		m.set(Math.sin(r.getRadianZ()), 1, 0);
		m.set(Math.cos(r.getRadianZ()), 1, 1);
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
		if (m1.columnLength() != m2.rowLength()) return null;
		else {
			Matrix m = new Matrix (m1.rowLength(), m2.columnLength());
			for (int a = 0; a < m1.rowLength(); a++) {
				for (int b = 0; b < m2.columnLength(); b++) {
					int sum = 0;
					for (int c = 0; c < m2.rowLength(); c++) {
						sum += m1.get(a, c)*m2.get(c, b);
					}
					m.set(sum, a, b);
				}
			}
			return m;
		}
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
	public int rowLength () {
		return matrix.length;
	}
	public int columnLength () {
		return matrix[0].length;
	}
	/** Prints the Matrix.*/
	public void print () {
		for (int a = 0; a < rowLength(); a++) {
			for (int b = 0; b < columnLength(); b++) {
				System.out.print(matrix[a][b] + " ");
			}
			System.out.println("");
		}
	}
}
