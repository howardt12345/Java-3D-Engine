import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
/** The GameObject Class, used as a Superclass for objects. Implements Serializable.*/
public class GameObject implements Serializable{
	/** The Transform of the GameObject.
	 * @param transform the Transform.*/
	protected Transform transform;
	/** Whether or not the Model is active.
	 * @param active the boolean.*/
	private boolean active = true;
	/** New GameObject from a Transform.*/
	public GameObject (Transform t) {
		transform = t;
	}
	/** new GameObject.*/
	public GameObject() {
		transform = new Transform ();
	}
	/** Gets the Transform of the GameObject.
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}
	/** Sets the Transform of the GameObject.
	 * @param transform the transform to set
	 */
	public void setTransform (Transform transform) {
		this.transform = transform;
	}
	/** Adds an amount to the specified Axis. 
	 * @param amount the amount.
	 * @param axis the Axis.
	 */
	public void addTranslate (double amount, Axis axis) {
		switch (axis) {
		case X:
			transform.setPosX(transform.getPosX()+amount);
			break;
		case Y:
			transform.setPosY(transform.getPosY()+amount);
			break;
		case Z:
			transform.setPosZ(transform.getPosZ()+amount);
			break;
		}
	}
	/** Sets a value to the specified Axis. 
	 * @param value the value.
	 * @param axis the Axis.
	 */
	public void setTranslate (double amount, Axis axis) {
		switch (axis) {
		case X:
			transform.setPosX(amount);
			break;
		case Y:
			transform.setPosY(amount);
			break;
		case Z:
			transform.setPosZ(amount);
			break;
		}
	}
	/** Adds an amount to the Rotation.
	 * @param amount the amount.
	 * @param axis the Axis.
	 */
	public void addRotate (double amount, Rotate axis) {
		switch (axis) {
		case X:
			transform.setRotX(transform.getRotX()+amount);
			break;
		case Y:
			transform.setRotY(transform.getRotY()+amount);
			break;
		case Z:
			transform.setRotZ(transform.getRotZ()+amount);
			break;
		}
	}
	/** Sets a value to the Rotation.
	 * @param value the value.
	 * @param axis the Axis.
	 */
	public void setRotate (double value, Rotate axis) {
		switch (axis) {
		case X:
			transform.setRotX(value);
			break;
		case Y:
			transform.setRotY(value);
			break;
		case Z:
			transform.setRotZ(value);
			break;
		}
	}
	/** Adds an amount to the specified Direction. 
	 * @param amount the amount.
	 * @param dir the Direction.
	 */
	public void addTranslate (double amount, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setPosZ(transform.getPosZ()+amount);
			break;
		case Backward:
			transform.setPosZ(transform.getPosZ()-amount);
			break;
		case Left:
			transform.setPosX(transform.getPosX()-amount);
			break;
		case Right:
			transform.setPosX(transform.getPosX()+amount);
			break;
		case Up:
			transform.setPosY(transform.getPosY()-amount);
			break;
		case Down:
			transform.setPosY(transform.getPosY()+amount);
			break;
		}
	}
	/** Sets a value to the specified Direction. 
	 * @param value the value.
	 * @param dir the Direction.
	 */
	public void setTranslate (double value, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setPosZ(value);
			break;
		case Backward:
			transform.setPosZ(-value);
			break;
		case Left:
			transform.setPosX(-value);
			break;
		case Right:
			transform.setPosX(value);
			break;
		case Up:
			transform.setPosY(-value);
			break;
		case Down:
			transform.setPosY(value);
			break;
		}
	}
	/** Adds an amount to the specified Direction. 
	 * @param amount the amount.
	 * @param dir the Direction.
	 */
	public void addRotate (double amount, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setRotZ(transform.getRotZ()+amount);
			break;
		case Backward:
			transform.setRotZ(transform.getRotZ()-amount);
			break;
		case Left:
			transform.setRotX(transform.getRotX()-amount);
			break;
		case Right:
			transform.setRotX(transform.getRotX()+amount);
			break;
		case Up:
			transform.setRotY(transform.getRotY()-amount);
			break;
		case Down:
			transform.setRotY(transform.getRotY()+amount);
			break;
		}
	}
	/** Sets a value to the specified Direction. 
	 * @param value the value.
	 * @param dir the Direction.
	 */
	public void setRotate (double value, Direction dir) {
		switch (dir) {
		case Forward:
			transform.setRotZ(value);
			break;
		case Backward:
			transform.setRotZ(-value);
			break;
		case Left:
			transform.setRotX(-value);
			break;
		case Right:
			transform.setRotX(value);
			break;
		case Up:
			transform.setRotY(-value);
			break;
		case Down:
			transform.setRotY(value);
			break;
		}
	}
	/** Returns whether or not GameObject is active.*/
	public boolean isActive() {
		return active;
	}
	/** Sets whether or not GameObject is active.
	 * @param active the value.
	 */
	public void setActive(boolean value) {
		active = value;
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
