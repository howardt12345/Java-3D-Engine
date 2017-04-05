import java.io.*;
import java.text.*;

/** The Utils Class. */
public class Utils {
	/** Checks if a String is numeric.
	 * @param str the input string.
	 */
	public static boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	/** Deep Clone of any Java Object.
	Explanation: A deep clone copies all fields, and makes copies of dynamically 
	allocated memory pointed to by the fields. Unlike a shallow clone, a deep clone 
	will be 100% independent from the original and any changes made to clone object 
	will not be reflected in the original object.
	* @param object the object to deep clone.
	* @return deep clone of object.
	*/
	public static Object deepClone(Object object) {
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
