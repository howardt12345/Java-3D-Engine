package java3dengine;

import java.io.*;
import javax.swing.*;

/** The Animation class, extends SwingWorker and implements Serializable.*/
@SuppressWarnings("serial")
public class Animation extends SwingWorker <Integer, String> implements Serializable {
	private double start = 0, end = 1;
	/** Whether or not the Animation loops looply.*/
	private boolean loop = false;
	private boolean remove = false;
	/** The target of this Animation.*/
	/** The JFrame.*/
	private JFrame f;
	/** The start time of the Animation.*/
	private long startTime;
	private Scene scene;
	 * @param t the Transformation.
	 */
	{
		target = a;
	}
	 * @param t the Transformation.
	 * @param loop whether or not the Animation loops looply.
	 */
	{
		target = a;
		this.loop = loop;
	}
	 * @param t the Transformation.
	 * @param duration the duration.
	 */
	{
		target = a;
		end = duration;
	}
	 * @param t the Transformation.
	 * @param duration the duration.
	 */
	{
		target = a;
		end = duration;
		this.scene = scene;
		this.remove = remove;
	}
	 * @param t the Transformation.
	 * @param duration the duration.
	 * @param loop whether or not the Animation loops looply.
	 */
	{
		target = a;
		end = duration;
		this.loop = loop;
	}
	 * @param start the start.
	 * @param end the end.
	 * @param loop whether or not the Animation loops.
	 */
	{
		target = a;
		this.start = start;
		this.end = end;
		this.loop = loop;
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
	@Override 
	/** Note that all transformations in Animation runs simultaneously. */
	public Integer doInBackground() throws Exception
	{
		while (loop || System.currentTimeMillis() < (startTime + (end*1000))) 
		{
			if (System.currentTimeMillis() > startTime+(start*1000)) 
			{
				target.animate();
			}
			f.repaint();
			Thread.sleep(10);
		}
		if (System.currentTimeMillis() > startTime + (end*1000))
		{
			if (remove)
			{
				scene.remove(scene.indexOf(target));
			}
			this.cancel(true);
		}
		return 1;
	}
	/** Gets the duration of this Animation. */
	public double getDuration ()
	{
		return end-start;
	}
}
