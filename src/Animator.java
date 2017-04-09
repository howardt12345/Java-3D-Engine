import java.util.*;
import javax.swing.*;

public class Animator {
	/** The Animations to run.*/
	private ArrayList<Animation> anim = new ArrayList<Animation>();
	/** The target of the Animator.*/
	private GameObject target;
	/** The JFrame.*/
	private JFrame f;
	/** New Animator from Animation, target and JFrame.
	 * @param a the Animation.
	 * @param g the target.
	 * @param f the JFrame.
	 */
	public Animator (Animation a, GameObject g, JFrame f) 
	{
		anim.add(a);
		target = g;
		this.f = f;
	}
	/** New Animator from target and JFrame.
	 * @param g the target.
	 * @param f the JFrame.
	 */
	public Animator (GameObject g, JFrame f) 
	{
		target = g;
		this.f = f;
	}
	/** Runs all the Animations in the Animator.*/
	public void play () {
		for (Animation a : anim) 
		{
			a.run(target, f);
		}
	}
	/** Adds an Animation to the Animator.
	 * @param a the Animation to add.
	 */
	public void add (Animation a) 
	{
		anim.add(a);
	}
}
