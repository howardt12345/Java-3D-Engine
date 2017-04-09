import java.util.*;
import javax.swing.*;

/** The Animation class, extends SwingWorker.*/
public class Animation extends SwingWorker <Integer, String> {
	/** The duration of the Animation.*/
	private double duration = 1;
	/** The ArrayList of Transformations in this Animation.*/
	private ArrayList<Transformation> transformations = new ArrayList<Transformation>();
	/** The target of this Animation.*/
	private GameObject target;
	/** The JFrame.*/
	private JFrame f;
	/** The Starting time of the Animation.*/
	private long start;
	/** New Animation from a Transformation.
	 * @param t the Transformation.
	 */
	public Animation (Transformation t) 
	{
		transformations.add(t);
	}
	/** New Animation from a Transformation and a duration.
	 * @param t the Transformation.
	 * @param d the duration.
	 */
	public Animation (Transformation t, double d) 
	{
		transformations.add(t);
		duration = d;
	}
	/** New Animation from a list of Transformations.
	 * @param t the list of Transformations.
	 */
	public Animation (ArrayList<Transformation> t)
	{
		transformations = t;
	}
	/** New Animation from a list of Transformations and a duration.
	 * @param t the list of Transformations.
	 * @param d the duration.
	 */
	public Animation (ArrayList<Transformation> t, double d)
	{
		transformations = t;
		duration = d;
	}
	/** Runs the Animation. Note that all transformations in Animation
	 * runs simultaneously.
	 * @param g the target of this Animation. 
	 * @param f the JFrame.
	 */
	protected void run (GameObject g, JFrame f) {
		start = System.currentTimeMillis();
		target = g;
		this.f = f;
		this.execute();
	}
	/** Adds a Transformation to the Animation.
	 * @param t the Transformation to add.
	 */
	public void add (Transformation t) {
		transformations.add(t);
	}
	@Override
	/** Note that all transformations in Animation runs simultaneously.*/
	protected Integer doInBackground() throws Exception {
		while (System.currentTimeMillis() < (start + (duration*1000))) {
			f.repaint();
			for (Transformation t : transformations) {
				if (t.getClass() == Vec4.class) {
					if (((Vec4)t).getW() == 0) {
						target.addTranslate(((Vec4)t).getX()/duration/95, Axis.X);
						target.addTranslate(((Vec4)t).getY()/duration/95, Axis.Y);
						target.addTranslate(((Vec4)t).getZ()/duration/95, Axis.Z);
					}
				}
				if (t.getClass() == Rotation.class && target.getClass() != Light.class) {
					target.addRotate(((Rotation)t).getX()/duration/95, Axis.X);
					target.addRotate(((Rotation)t).getY()/duration/95, Axis.Y);
					target.addRotate(((Rotation)t).getZ()/duration/95, Axis.Z);
				}
				if (t.getClass() == Scale.class) {
					target.addScale(((Scale)t).getX()/duration/95, Axis.X);
					target.addScale(((Scale)t).getY()/duration/95, Axis.Y);
					target.addScale(((Scale)t).getZ()/duration/95, Axis.Z);
				}
			}
			try {
				Thread.sleep(10);
				f.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.cancel(true);
		return 1;
	}
	/** Gets the duration of this Animation. */
	public double getDuration ()
	{
		return duration;
	}
}