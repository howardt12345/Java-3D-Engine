
public class Test {
	public static void main (String[] args) {
		Vec4 v = new Vec4 (5, 5, 5);
		System.out.println(v.magnitude());
		v.normalize();
		System.out.println(v.magnitude());
		System.out.println(v.getX()*v.getX() + v.getY()*v.getY() + v.getZ()*v.getZ());
    }
}