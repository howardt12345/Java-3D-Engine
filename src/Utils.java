import java.io.*;
import java.text.*;
import java.util.*;
/** The Utility Class. */
public class Utils {
	/** Sorts an ArrayList of GameObjects in order on the Z Axis.
	 * @param data the input data.
	 * @return the sorted ArrayList.
	 */
	public static ArrayList<GameObject> zSort(ArrayList<GameObject> data)
	{
		ArrayList<GameObject> tmp = data;
		QuickSort (tmp, 0, data.size()-1);
		return tmp;
	}
	/** Quick Sort.
	 * @param data the input data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static void QuickSort(ArrayList<GameObject> data, int left, int right) 
	{
		int index = partition(data, left, right);
		if (left < index - 1)
			QuickSort(data, left, index - 1);
		if (index < right)
			QuickSort(data, index, right);
	}
	/** Partitions the Array.
	 * @param data the data.
	 * @param left the left.
	 * @param right the right.
	 */
	private static int partition(ArrayList<GameObject> data, int left, int right) 
	{
		int a = left, b = right;
		double pivot = data.get((left + right) / 2).getTransform().getPosZ();
		while (a <= b) {
			while (data.get(a).getTransform().getPosZ() >= 0
					? data.get(a).getTransform().getPosZ() > pivot
							: data.get(a).getTransform().getPosZ() < pivot)
				a++;
			while (data.get(b).getTransform().getPosZ() >= 0
					? data.get(b).getTransform().getPosZ() < pivot
							: data.get(b).getTransform().getPosZ() > pivot)
				b--;
			if (a <= b) {
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
	private static void swap (ArrayList<GameObject> array, int a, int b) 
	{
		GameObject tmp = array.get(a);
		array.set(a, array.get(b));
		array.set(b, tmp);
	}
	/** Checks if a String is numeric.
	 * @param str the input string.
	 */
	public static boolean isNumeric(String str) 
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (int[] in) 
	{
		for (int a = 0; a < in.length; a++) {
			System.out.println(in[a]);
		}
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (int[][] in) 
	{
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				System.out.print(" " + in[i][j]);
			}
			System.out.println();
		}
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (double[] in) 
	{
		for (int a = 0; a < in.length; a++) {
			System.out.println(in[a]);
		}
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (double[][] in) 
	{
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				System.out.print(" " + in[i][j]);
			}
			System.out.println();
		}
	}
	/** Print Method for an Array.
	 * @param in the Array to print.
	 */
	public static void print (String[] in) 
	{
		for (int a = 0; a < in.length; a++) {
			System.out.println(in[a]);
		}
	}
	/** Print method for a 2D Array.
	 * @param in the 2D Array to print.
	 */
	public static void print (String[][] in) 
	{
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				System.out.print(" " + in[i][j]);
			}
			System.out.println();
		}
	}
	/** Deep Clone of any Java Object.
	Explanation: A deep clone copies all fields, and makes copies of dynamically 
	allocated memory pointed to by the fields. Unlike a shallow clone, a deep clone 
	will be 100% independent from the original and any changes made to clone object 
	will not be reflected in the original object.
	* @param object the object to deep clone.
	* @return deep clone of object.
	*/
	public static Object deepClone(Object object) 
	{
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
