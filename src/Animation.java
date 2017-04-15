import java.io.*;
import java.util.*;
import javax.swing.*;

/** The Animation class, extends SwingWorker and implements Serializable.*/
@SuppressWarnings("serial")
public class Animation extends SwingWorker <Integer, String> implements Serializable {
	private double start = 0, end = 1;
	/** The loop of the Animation.*/
	private double loop = 1;
	/** Whether or not the Animation loops infinitely.*/
	private boolean loopInfinite = false;
	/** The ArrayList of Transformations in this Animation.*/
	private ArrayList<Transformation> transformations = new ArrayList<Transformation>();
	/** The target of this Animation.*/
	private GameObject target;
	/** The JFrame.*/
	private JFrame f;
	/** The Starting time of the Animation.*/
	private long startTime;
	/** New Animation from a Transformation.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 */
	public Animation (GameObject g, Transformation t) 
	{
		target = g;
		transformations.add(t);
	}
	/** New Animation from a Transformation and loopInfinite.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param loopInfinite whether or not the Animation loops infinitely.
	 */
	public Animation (GameObject g, Transformation t, boolean loopInfinte) 
	{
		target = g;
		transformations.add(t);
		this.loopInfinite = loopInfinte;
	}
	/** New Animation from a Transformation and a duration.
	 * @param g the target of this Animation.
	 * @param t the Transformation.
	 * @param duration the duration.
	 */
	public Animation (GameObject g, Transformation t, double duration) 
	{
		target = g;
		transformations.add(t);
		end = duration;
	}
	/** New Animation from a Transformation, duration, and loop.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param duration the duration.
	 * @param loop how many times the Animation will loop.
	 */
	public Animation (GameObject g, Transformation t, double duration, double loop) 
	{
		target = g;
		transformations.add(t);
		end = duration;
		this.loop = loop;
	}
	/** New Animation from a Transformation, duration, and loopInfinite.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param duration the duration.
	 * @param loopInfinite whether or not the Animation loops infinitely.
	 */
	public Animation (GameObject g, Transformation t, double duration, boolean loopInfinte) 
	{
		target = g;
		transformations.add(t);
		end = duration;
		this.loopInfinite = loopInfinte;
	}
	/** New Animation from a Transformation, duration, loop, and loopInfinite.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param duration the duration.
	 * @param loop how many times the Animation will loop.
	 * @param loopInfinite whether or not the Animation loops infinitely.
	 */
	public Animation (GameObject g, Transformation t, double duration, double loop, boolean loopInfinte) 
	{
		target = g;
		transformations.add(t);
		end = duration;
		this.loop = loop;
		this.loopInfinite = loopInfinte;
	}
	/** New Animation from a Transformation, start, end, and loop.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param start the start.
	 * @param end the end.
	 * @param loop how many times the Animation will loop.
	 */
	public Animation (GameObject g, Transformation t, double start, double end, double loop) 
	{
		target = g;
		transformations.add(t);
		this.start = start;
		this.end = end;
		this.loop = loop;
	}
	/** New Animation from a Transformation, duration, and loopInfinite.
	 * @param g the target of this Animation. 
	 * @param start the start.
	 * @param end the end.
	 * @param loopInfinite whether or not the Animation loops.
	 */
	public Animation (GameObject g, Transformation t, boolean loopInfinte, double start, double end) 
	{
		target = g;
		transformations.add(t);
		this.start = start;
		this.end = end;
		this.loopInfinite = loopInfinte;
	}
	/** New Animation from a Transformation, duration, loop, and loopInfinite.
	 * @param g the target of this Animation. 
	 * @param t the Transformation.
	 * @param duration the duration.
	 * @param how many times the Animation will loop.
	 * @param loopInfinite whether or not the Animation loops infinitely.
	 */
	public Animation (GameObject g, Transformation t, double start, double end, double loop, boolean loopInfinte) 
	{
		target = g;
		transformations.add(t);
		this.start = start;
		this.end = end;
		this.loop = loop;
		this.loopInfinite = loopInfinte;
	}
	/** Runs the Animation. Note that all transformations in Animation
	 * runs simultaneously.
	 * @param f the JFrame.
	 */
	protected void play (JFrame f) 
	{
		startTime = System.currentTimeMillis();
		this.f = f;
		this.execute();
	}
	/** Adds a Transformation to the Animation.
	 * @param t the Transformation to add.
	 */
	public void add (Transformation t) 
	{
		transformations.add(t);
	}
	@Override 
	/** Note that all transformations in Animation runs simultaneously. */
	protected Integer doInBackground() throws Exception
	{
		while (loopInfinite || System.currentTimeMillis() < (startTime + (end*1000))) {
			if (System.currentTimeMillis() > startTime+(start*1000)) {
				f.repaint();
				for (Transformation t : transformations) {
					if (t.getClass() == Vec4.class) {
						if (((Vec4)t).getW() == 0) {
							target.addTranslate((((Vec4)t).getX()/(end-start)/100)*loop, Axis.X);
							target.addTranslate((((Vec4)t).getY()/(end-start)/100)*loop, Axis.Y);
							target.addTranslate((((Vec4)t).getZ()/(end-start)/100)*loop, Axis.Z);
						}
						else throw new IllegalArgumentException ("Vec4 must be a vector.");
					}
					if (t.getClass() == Rotation.class && target.getClass() != Light.class) {
						target.addRotate((((Rotation)t).getX()/(end-start)/100)*loop, Axis.X);
						target.addRotate((((Rotation)t).getY()/(end-start)/100)*loop, Axis.Y);
						target.addRotate((((Rotation)t).getZ()/(end-start)/100)*loop, Axis.Z);
					}
					if (t.getClass() == Scale.class) {
						target.addScale((((Scale)t).getX()/(end-start)/100)*loop, Axis.X);
						target.addScale((((Scale)t).getY()/(end-start)/100)*loop, Axis.Y);
						target.addScale((((Scale)t).getZ()/(end-start)/100)*loop, Axis.Z);
					}
				}
			}
			Thread.sleep(10);
		}
		if (System.currentTimeMillis() > startTime + (end*1000)) this.cancel(true);
		return 1;
	}
	/** Gets whether or not the Animation loops infinitely.*/
	public boolean isloopInfinteing ()
	{
		return loopInfinite;
	}
	/** Sets whether or not the Animation loops infintely.
	 * @param loopInfinite the loopInfinite.
	 */
	public void setloopInfinte (boolean loopInfinte)
	{
		this.loopInfinite = loopInfinte;
	}
	/** Gets the duration of this Animation. */
	public double getDuration ()
	{
		return end-start;
	}
}