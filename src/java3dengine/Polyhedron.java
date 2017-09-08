package java3dengine;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
/** The Polyhedron Class, extends GameObject.*/
public class Polyhedron extends GameObject {
	/** The Arraylist of Polygons in the Polyhedron.*/
	private ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	private ArrayList<Vec4> index = new ArrayList<Vec4>();
	/** The File name.*/
	private String FileName;
	/** The Color of the Polyhedron.*/
	private Color color = Color.gray;
	/** Creates a new Polyhedron with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Polyhedron.
	 * @param filename the file name.
	 * @param active whether or not the Polyhedron is active.
	 * @param cull whether or not this Polyhedron will be culled.
	 */
	public Polyhedron (Transform t, String filename, boolean active, boolean cull)
	{
		super (t, cull);
		super.setActive(active);
		ReadFile(filename);
		FileName = filename;
	}
	/** Creates a new Polyhedron with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Polyhedron.
	 * @param filename the file name.
	 * @param active whether or not the Polyhedron is active.
	 * @param cull whether or not this Polyhedron will be culled.
	 * @param color the Color of this Polyhedron.
	 */
	public Polyhedron (Transform t, 
			String filename, 
			boolean active, boolean cull, 
			Color color)
	{
		super (t, cull);
		super.setActive(active);
		ReadFile(filename);
		FileName = filename;
		this.color = color;
	}
	/** Creates a new Polyhedron with Transform, Scring filename, and Camera.
	 * @param transform the Transform of the Polyhedron.
	 * @param filename the file name.
	 * @param active whether or not the Polyhedron is active.
	 * @param cull whether or not this Polyhedron will be culled.
	 * @param color the Color of this Polyhedron.
	 */
	public Polyhedron (Transform t, 
			String filename, 
			boolean active, boolean cull, 
			String color)
	{
		super (t, cull);
		super.setActive(active);
		ReadFile(filename);
		FileName = filename;
		try {
			Field f = Color.class.getField(color);
			this.setColor((Color)f.get(null));
		}
		catch (Exception e1)
		{
			try {
				this.color = Color.decode(color);
			}
			catch (Exception e2) {
				e1.printStackTrace();
				e2.printStackTrace();
			}
		}
	}
	/** Reads the file and loads values in ArrayList.
	 * @param filename the filename.
	 */
	private void ReadFile (String filename)
	{
		try
		{
			Scanner input = new Scanner(new FileReader(filename));
			Scanner line = new Scanner(input.nextLine());
			System.out.println("Loading: " + filename);
			while (input.hasNextLine() || line.hasNext())
			{
				Polygon polygon = new Polygon ();
				while (line.hasNextDouble())
				{
					Vec4 v = new Vec4 (line.nextDouble(), line.nextDouble(), line.nextDouble())
							, tmp = Utils.isDuplicate(v, index);
					if (tmp != null) 
					{
						polygon.add(tmp);
					} else {
						index.add(v);
						polygon.add(v);
					}
				}
				if (input.hasNextLine())
					line = new Scanner(input.nextLine());
				polygons.add(polygon);
			}
			System.out.println(filename + " loaded successfully. " + index.size() + " total points.");
			input.close();
			line.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** The Polyhedron transformed by the Model View Perspective Matrix.
	 * @param polyhedron the Polyhedron to transform.
	 * @param cam the Camera to transform to.
	 * @param lights the lights in scene.
	 * @param width the width.
	 * @param height the height.
	 * */
	public static Polyhedron MVP (
			Polyhedron polyhedron, 
			Camera cam, 
			ArrayList<Light> lights, 
			double width, double height)
	{
		Polyhedron m = (Polyhedron) Utils.deepClone(polyhedron); //Deep Clones model.
		ArrayList<Polygon> tmp = new ArrayList<Polygon>();
		Matrix globalTM = m.getGlobalTM(),
				viewPerspective = Matrix.multiply(cam.getPerspectiveMatrix(width, height), cam.getLookAtMatrix());
		for (int a = 0; a < m.polygons.size(); a++)
		{ //Goes through all polygons.
			boolean isVisible = cam.isVisible(m.polygons.get(a).getCenter().Transform(globalTM), m.polygons.get(a).getNormal().Transform(globalTM)); //Calculates Polygon visibility.
			if (isVisible)
			{
				m.polygons.get(a).transform(globalTM);//Translates and Rotates Polygon.
				float intensity = m.polygons.get(a).calculateIntensity(lights); //Calculates light intensity.
				m.polygons.get(a).transform(viewPerspective);//Transforms Polygon to Projection space.
				m.polygons.get(a).Normalize();
				if (m.polygons.get(a).getClosest(Vec4.center).getX() > -0.9
					&& m.polygons.get(a).getClosest(Vec4.center).getX() < 0.9
					&& m.polygons.get(a).getClosest(Vec4.center).getY() > -0.9 
					&& m.polygons.get(a).getClosest(Vec4.center).getY() < 0.9
					&& m.polygons.get(a).getClosest(Vec4.center).getZ() < -cam.getNearClip() 
					&& m.polygons.get(a).getClosest(Vec4.center).getZ() > -cam.getFarClip())
				{
					m.polygons.get(a).setIntensity(intensity); //loads tmp value.
					tmp.add(m.polygons.get(a));
				}
			}
		}
		Utils.polygonSort(tmp);
		m.polygons = tmp;
		return m;
	}
	/** Paints the Polyhedron.
	 * @param g the Graphics component.
	 * @param cam the Camera.
	 * @param lights the lights.
	 * @param width the width.
	 * @param height the height.
	 * @param shiftX the screen shift on X axis
	 * @param shiftY the screen shift on Y axis.
	 * @param wire if wireframe enabled.
	 * @param shade if shading enabled.
	 * @return the number of polygons rendered.
	 */
	public int paint (
			Graphics g, 
			Camera cam, 
			ArrayList<Light> lights, 
			int width, int height, int shiftX, int shiftY, 
			boolean wire, boolean shade)
	{
		Polyhedron P = Polyhedron.MVP (this, cam, lights, width, height);
		for (Polygon p : P.polygons)
			p.paint(g, width, height, shiftX, shiftY, wire, shade, color);
		return P.polygons.size();
	}
	/** Prints the information on the Polyhedron.*/
	public void print ()
	{
		System.out.println("Polyhedron File Name: " + FileName);
		System.out.println("Object active: " + Boolean.toString(isActive()));
		printCoordinates();
	}
	/** Prints out the Polyhedron Coordinates.*/
	public void printCoordinates ()
	{
		for (int a = 0; a < polygons.size(); a++)
		{
			System.out.println("Polygon " + (a+1) + ":");
			polygons.get(a).detailedPrint();
			System.out.println("**********");
		}
	}
	/** Gets the closest polygon to a Vec4.
	 * @param v the Vec4.
	 * @return the closest polygon.
	 */
	public Polygon getClosest (Vec4 v)
	{
		Polygon tmp = polygons.get(0);
		for (int a = 0; a < polygons.size(); a++)
		{
			if (Vec4.getDistance(v, polygons.get(a).getClosest(v)) < Vec4.getDistance(v, tmp.getClosest(v)))
				tmp = polygons.get(a);
		}
		return tmp;
	}
	/** Gets the farthest polygon to a Vec4.
	 * @param v the Vec4.
	 * @return the fathest polygon.
	 */
	public Polygon getFarthest (Vec4 v)
	{
		Polygon tmp = polygons.get(0);
		for (int a = 0; a < polygons.size(); a++)
		{
			if (Vec4.getDistance(v, polygons.get(a).getClosest(v)) > Vec4.getDistance(v, tmp.getClosest(v)))
				tmp = polygons.get(a);
		}
		return tmp;
	}
	/** Gets the polygons in the Polyhedron.
	 * @return the Polygons in the Polyhedron.
	 */
	public ArrayList<Polygon> getPolygons ()
	{
		return polygons;
	}
	/** Gets the fileName of the Polyhedron.
	 * @return the fileName
	 */
	public String getFileName ()
	{
		return FileName;
	}
	/** Gets the color of the Polyhedron.
	 * @return the color
	 */
	public Color getColor() 
	{
		return color;
	}
	/** Sets the color of the Polyhedron.
	 * @param color the color to set
	 */
	public void setColor(Color color) 
	{
		this.color = color;
	}
	/** Sets the color of the Polyhedron.
	 * @param color the color to set
	 */
	public void setColor (String color)
	{
		try {
			Field f = Color.class.getField(color);
			this.setColor((Color)f.get(null));
		}
		catch (Exception e1)
		{
			try {
				this.color = Color.decode(color);
			}
			catch (Exception e2) {
				e1.printStackTrace();
				e2.printStackTrace();
			}
		}
	}
}
