package java3dengine;

import java.io.*;
import java.text.*;
import java.util.*;

/** The utility Class. */
public class Utils {
	/** Sorts an ArrayList of Polyhedrons relative to the Camera.
	 * @param tmp2 the input data.
	 * @return the sorted ArrayList.
	 */
	public static List<Polyhedron> zSort (List<Polyhedron> tmp2, Camera c)
	{
		try
		{
			List<Polyhedron> tmp = tmp2;
			QuickSort (tmp, c, 0, tmp2.size()-1);
			return tmp;
		}
		catch (Exception e) 
		{
			return tmp2;
		}
	}
	/** Quick Sort.
	 * @param data the input data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static void QuickSort (List<Polyhedron> data, Camera c, int left, int right) 
	{
		int index = Partition(data, c, left, right);
		if (left < index - 1)
			QuickSort(data, c, left, index - 1);
		if (index < right)
			QuickSort(data, c, index, right);
	}
	/** Partitions the Array.
	 * @param data the data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static int Partition (List<Polyhedron> data, Camera cam, int left, int right) 
	{
		int a = left, b = right;
		double pivot = Vec4.getDistance(cam.getLookFrom(), data.get((int)Math.rint((left + right) / 2)).getGlobalTP());
		while (a <= b) 
		{
			while (Vec4.getDistance(cam.getLookFrom(), data.get(a).getGlobalTP()) > pivot)
				a++;
			while (Vec4.getDistance(cam.getLookFrom(), data.get(b).getGlobalTP()) < pivot)
				b--;
			if (a <= b) 
			{
				Swap (data, a, b);
				a++;
				b--;
			}
		}
		return a;
	}
	/** Swaps the elements in the ArrayList.
	 * @param array the array.
	 * @param a the first index
	 * @param b the second index.
	 */
	private static void Swap (List<Polyhedron> array, int a, int b) 
	{
		Polyhedron tmp = array.get(a);
		array.set(a, array.get(b));
		array.set(b, tmp);
	}
	/** Sorts the polygons in a Polyhedron relative to the Origin.
	 * @param data the input data.
	 */
	public static Polyhedron polygonSort (Polyhedron data)
	{
		try 
		{
			Polyhedron tmp = data;
			quickSort (tmp.getPolygons(), 0, tmp.getPolygons().size()-1);
			return tmp;
		}
		catch (Exception e) 
		{
			return data;
		}
	}
	public static void polygonSort (ArrayList<Polygon> data)
	{
		try 
		{
			quickSort (data, 0, data.size()-1);
		}
		catch (Exception e) 
		{
		}
	}
	/** Quick Sort.
	 * @param data the input data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static void quickSort (ArrayList<Polygon> data, int left, int right) 
	{
		int index = partition(data, left, right);
		if (left < index - 1)
			quickSort(data, left, index - 1);
		if (index < right)
			quickSort(data, index, right);
	}
	/** Partitions the Array.
	 * @param data the data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static int partition (ArrayList<Polygon> data, int left, int right) 
	{
		int a = left, b = right;
		double pivot = Vec4.getDistance(new Vec4 (0, 0, 1), data.get((int) (left + right)/2).getClosest(Vec4.center));
		while (a <= b) 
		{
			while (Double.compare(Vec4.getDistance(new Vec4 (0, 0, 1), data.get(a).getClosest(Vec4.center)), pivot) > 0)
				a++;
			while (Double.compare(Vec4.getDistance(new Vec4 (0, 0, 1), data.get(b).getClosest(Vec4.center)), pivot) < 0)
				b--;
			if (a <= b) 
			{
				swap (data, a, b);
				a++;
				b--;
			}
		}
		return a;
	}
	/** Swaps the elements in the ArrayList.
	 * @param array the array.
	 * @param a the first index
	 * @param b the second index.
	 */
	private static void swap (ArrayList<Polygon> array, int a, int b) 
	{
		Polygon tmp = array.get(a);
		array.set(a, array.get(b));
		array.set(b, tmp);
	}
	/** Checks if a String is numeric.
	 * @param str the input string.
	 */
	public static boolean isNumeric (String str) 
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	public static Vec4 isDuplicate (Vec4 v, ArrayList<Vec4> list)
	{
		if (!list.isEmpty()) 
		{
			for (Vec4 v1 : list)
			{
				if (Double.compare(v.getX(), v1.getX()) == 0 && Double.compare(v.getY(), v1.getY()) == 0
						 && Double.compare(v.getZ(), v1.getZ()) == 0  && Double.compare(v.getW(), v1.getW()) == 0)
					return v1;
			}
		}
		return null;
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (int[] in) 
	{
		for (int a = 0; a < in.length; a++) 
			System.out.println(in[a]);
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (int[][] in) 
	{
		for (int i = 0; i < in.length; i++) 
		{
			for (int j = 0; j < in[i].length; j++) 
				System.out.print(" " + in[i][j]);
			System.out.println();
		}
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (double[] in) 
	{
		for (int a = 0; a < in.length; a++) 
			System.out.println(in[a]);
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (double[][] in) 
	{
		for (int i = 0; i < in.length; i++) 
		{
			for (int j = 0; j < in[i].length; j++) 
				System.out.print(" " + in[i][j]);
			System.out.println();
		}
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (String[] in) 
	{
		for (int a = 0; a < in.length; a++) 
			System.out.println(in[a]);
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (String[][] in) 
	{
		for (int i = 0; i < in.length; i++)
		{
			for (int j = 0; j < in[i].length; j++) 
				System.out.print(" " + in[i][j]);
			System.out.println();
		}
	}
	/** Counts how many lines are in a file.
	 * @param filename the file name.
	 * @return the number of lines in the file.
	 * @throws IOException 
	 */
	public static int countLines (String filename) throws IOException 
	{
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try 
		{
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) 
			{
				empty = false;
				for (int i = 0; i < readChars; ++i) 
				{
					if (c[i] == '\n') 
						count++;
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}
	/** Deep Clone of any Java Object.
	A deep clone copies all fields, and makes copies of dynamically 
	allocated memory pointed to by the fields. Unlike a shallow clone, a deep clone 
	will be 100% independent from the original and any changes made to clone object 
	will not be reflected in the original object.
	* @param object the object to deep clone.
	* @return deep clone of object.
	*/
	public static Object deepClone (Object object) 
	{
		try 
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}