
public class Camera {
	/** The Transform for the Camera.*/
	private Transform transform = new Transform ();
	/** The lookFrom Coordinate.*/
	private Coordinate lookFrom = Coordinate.center;
	/** The lookAt Coordinate.*/
	private Coordinate lookAt = new Coordinate (0, 0, 1);
	/** The lookUp Coordinate.*/
	private Coordinate lookUp = Coordinate.up;
	/** Internal values of the Camera.*/
	private double nearClip = 0.3, farClip = 1000, FOV = 60, aspectRatio = 16/9;
	/** The LookAt Translation Matrix.*/
	private Matrix lookAtTranslate = new Matrix (new Coordinate (0, 0, 1));
	/** Creaes a Camera from a lookFrom Coordinate, lookAt Coordinate, lookUp Coordinate, 
	 * near clip value, far clip value, FOV (Field of View) value, and aspect ratio value.
	 * @param lookFrom the lookFrom.
	 * @param lookAt the lookAt.
	 * @param lookUp the lookUp.
	 * @param nearClip the near clip.
	 * @param farClip the far clip.
	 * @param FOV the FOV (Field of View).
	 * @param aspectRatio the aspect ratio.
	 */
	public Camera (Coordinate lookFrom, Coordinate lookAt, Coordinate lookUp,
			double nearClip, double farClip, double FOV, double aspectRatio) {
		this.lookFrom = lookFrom;
		this.lookAt = lookAt;
		this.lookUp = lookUp;
		this.nearClip = nearClip;
		this.farClip = farClip;
		this.FOV = FOV;
		this.aspectRatio = aspectRatio;
	}
	/** Creates a Camera from a near clip value, far clip value, 
	 * FOV (Field of View) value, and aspect ratio value.
	 * @param nearClip the near clip.
	 * @param farClip the far clip.
	 * @param FOV the FOV (Field of View).
	 * @param aspectRatio the aspect ratio.
	 */
	public Camera (double nearClip, double farClip, double FOV, double aspectRatio) {
		this.nearClip = nearClip;
		this.farClip = farClip;
		this.FOV = FOV;
		this.aspectRatio = aspectRatio;
	}
	/** Creates a Camera from a lookFrom Coordinate, lookAt Coordinate, and lookUp Coordinate.
	 * @param lookFrom the lookFrom.
	 * @param lookAt the lookAt.
	 * @param lookUp the lookUp.
	 */
	public Camera (Coordinate lookFrom, Coordinate lookAt, Coordinate lookUp) {
		this.lookFrom = lookFrom;
		this.lookAt = lookAt;
		this.lookUp = lookUp;
	}
	/** Creates a Camera from a lookFrom Coordinate and lookAt Coordinate.
	 * @param lookFrom the lookFrom.
	 * @param lookAt the lookAt.
	 */
	public Camera (Coordinate lookFrom, Coordinate lookAt) {
		this.lookFrom = lookFrom;
		this.lookAt = lookAt;
	}
	/** The lookAt Matrix.
	 * @param lookFrom the lookFrom.
	 * @param lookAt the lookAt.
	 * @param lookUp the lookUp.
	 * @return the lookAt Matrix.
	 */
	public Matrix LookAtMatrix () {
		Coordinate vForward = Coordinate.subtract(lookAt, lookFrom).normalized();
		Coordinate vUpNorm = lookUp.normalized().normalized();
		Coordinate vSide = vUpNorm.cross(vForward).normalized();
		vUpNorm = vForward.cross(vSide).normalized();
		
		Matrix viewMatrix = new Matrix();
		
		viewMatrix.set(vSide.getX(), 0, 0);
		viewMatrix.set(vSide.getY(), 1, 0);
		viewMatrix.set(vSide.getZ(), 2, 0);

		viewMatrix.set(vUpNorm.getX(), 0, 1);
		viewMatrix.set(vUpNorm.getY(), 1, 1);
		viewMatrix.set(vUpNorm.getZ(), 2, 1);

		viewMatrix.set(vForward.getX(), 0, 2);
		viewMatrix.set(vForward.getY(), 1, 2);
		viewMatrix.set(vForward.getZ(), 2, 2);
		
		viewMatrix.set(-lookFrom.getX(), 0, 3);
		viewMatrix.set(-lookFrom.getY(), 1, 3);
		viewMatrix.set(-lookFrom.getZ(), 2, 3);
		
		return viewMatrix;
	}
	/** The Camera Perspective projection Matrix.
	 * @return the projection matrix.
	 */
    public Matrix perspectiveMatrix () {
        Matrix projectionMatrix = new Matrix ();
        
        double d = 1/Math.tan(FOV/2 * Math.PI/180);
        
        projectionMatrix.set(d/aspectRatio, 0, 0);
        
        projectionMatrix.set(d, 1, 1);
        
        projectionMatrix.set(-(nearClip+farClip)/(nearClip-farClip), 2, 2);
        projectionMatrix.set(-1, 2, 3);
        
        projectionMatrix.set(-(2*farClip*nearClip)/(nearClip-farClip), 3, 2);
        projectionMatrix.set(0, 3, 3);
        
        return projectionMatrix;
    }
	/** Gets the transform.
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}
	/** Sets the Transform.
	 * @param transform the transform to set.
	 */
	public void setTransform (Transform transform) {
		this.transform = transform;
	}
	/** Transforms the Camera by a Transform.
	 * @param transform the Transform.
	 */
	public void Transform () {
		System.out.println("***************");
		transform.print();
		System.out.println("***************");
		new Matrix (transform).print();
		System.out.println("***************");
		lookFrom = Coordinate.Transform(Coordinate.center, new Matrix (transform.getPosition()));
		lookAt = Coordinate.Transform(Coordinate.center, Matrix.multiply(new Matrix (transform), lookAtTranslate));
		System.out.println ("LookFrom: " + lookFrom.asString());
		System.out.println ("LookAt: " + lookAt.asString());
	}
	/** Adds an amount to the specified Axis. 
	 * @param amount the amount.
	 * @param axis the Axis.
	 */
	public void addTranslate (float amount, Axis axis) {
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
	public void setTranslate (float amount, Axis axis) {
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
	public void addRotate (float amount, Rotate axis) {
		switch (axis) {
		case X_Axis:
			transform.setRotX(transform.getRotX()+amount);
			break;
		case Y_Axis:
			transform.setRotY(transform.getRotY()+amount);
			break;
		case Z_Axis:
			transform.setRotZ(transform.getRotZ()+amount);
			break;
		}
	}
	/** Sets a value to the Rotation.
	 * @param value the value.
	 * @param axis the Axis.
	 */
	public void setRotate (float value, Rotate axis) {
		switch (axis) {
		case X_Axis:
			transform.setRotX(value);
			break;
		case Y_Axis:
			transform.setRotY(value);
			break;
		case Z_Axis:
			transform.setRotZ(value);
			break;
		}
	}
	/** Adds an amount to the specified Direction. 
	 * @param amount the amount.
	 * @param dir the Direction.
	 */
	public void addTranslate (float amount, Direction dir) {
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
	public void setTranslate (float value, Direction dir) {
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
	/** Gets the LookAt Translation Matrix.
	 * @return the lookAtTranslate.
	 */
	public Matrix getLookAtTranslate() {
		return lookAtTranslate;
	}
	/** Sets the LookAt Translation Matrix.
	 * @param lookAtTranslate the lookAtTranslate to set.
	 */
	public void setLookAtTranslate(Matrix lookAtTranslate) {
		this.lookAtTranslate = lookAtTranslate;
	}
	/** Gets the LookAt Coordinate.*/
	public Coordinate getLookAt() {
		return lookAt;
	}
	/** Sets the LookAt Coordinate.
	 * @param lookAt the lookAt Coordinate to set.
	 */
	public void setLookAt(Coordinate lookAt) {
		this.lookAt = lookAt;
	}
	/** Gets the LookFrom Coordinate.*/
	public Coordinate getLookFrom() {
		return lookFrom;
	}
	/** Sets the lookFrom Coordinate.
	 * @param lookFrom the lookFrom Coordinate to set.
	 */
	public void setLookFrom(Coordinate lookFrom) {
		this.lookFrom = lookFrom;
	}
	/** Gets the lookUp Coordinate.*/
	public Coordinate getLookUp() {
		return lookUp;
	}
	/** Sets the lookUp Coordinate.
	 * @param lookUp the lookUp Coordinate to set.
	 */
	public void setLookUp(Coordinate lookUp) {
		this.lookUp = lookUp;
	}
	/** Gets the near clip of the Camera.*/
	public double getNearClip() {
		return nearClip;
	}
	/** Sets the near clip of the Camera.
	 * @param nearClip the near clip to set.
	 */
	public void setNearClip(double nearClip) {
		this.nearClip = nearClip;
	}
	/** Gets the far clip of the Camera.*/
	public double getFarClip() {
		return farClip;
	}
	/** Sets the far clip of the Camera.
	 * @param farClip the far clip to set.
	 */
	public void setFarClip(double farClip) {
		this.farClip = farClip;
	}
	/** Gets the FOV of the Camera.*/
	public double getFOV() {
		return FOV;
	}
	/** Sets the FOV of the Camera.
	 * @param fov the FOV to set.
	 */
	public void setFOV(double fov) {
		if (fov <= 1) FOV = 1;
		else if (fov > 179) FOV = 179;
		else FOV = fov;
	}
	/** Gets the Aspect Ratio of the Camera.*/
	public double getAspectRatio() {
		return aspectRatio;
	}
	/** Sets the Aspect Ratio of the Camera.
	 * @param aspectRatio the Aspect Ratio to set.
	 */
	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
}
