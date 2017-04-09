
public class Animation extends Thread implements Runnable{
	private double duration = 5;
	private Transformation t;
	private GameObject target;
	private volatile boolean running = true;
	private javax.swing.JFrame f;
	
	public Animation (Transformation t) {
		this.t = t;
	}
	public Animation (Transformation t, double d) {
		this.t = t;
		duration = d;
	}
	public void run (GameObject g, javax.swing.JFrame f) throws InterruptedException {
		target = g;
		this.f = f;
		this.start();
		Thread.sleep((long) (duration * 1000));
		this.shutdown();
	}
	public void shutdown () {
		running = false;
	}
	public void run () {
		while (running) {
			f.repaint();
			if (t.getClass() == Vec3.class) {
				target.addTranslate(((Vec3)t).getX()/duration/100, Axis.X);
				target.addTranslate(((Vec3)t).getY()/duration/100, Axis.Y);
				target.addTranslate(((Vec3)t).getZ()/duration/100, Axis.Z);
			}
			else if (t.getClass() == Rotation.class) {
				target.addRotate(((Rotation)t).getX()/duration/100, Axis.X);
				target.addRotate(((Rotation)t).getY()/duration/100, Axis.Y);
				target.addRotate(((Rotation)t).getZ()/duration/100, Axis.Z);
			}
			else if (t.getClass() == Scale.class) {
				target.addScale(((Scale)t).getX()/duration/100, Axis.X);
				target.addScale(((Scale)t).getY()/duration/100, Axis.Y);
				target.addScale(((Scale)t).getZ()/duration/100, Axis.Z);
			}
			target.transform.print();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}