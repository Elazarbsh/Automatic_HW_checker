
public class TestComplexMain {

	public static void main(String[] args) {
		IComplex c11 = new Complex1();
		IComplex c12 = new Complex1(3, 4);

		IComplex c21 = new Complex2();
		IComplex c22 = new Complex2(c12);

		IComplex c23 = new Complex2(1, Math.PI / 2);
		IComplex c13 = new Complex1(c23);

		System.out.println(c11);
		System.out.println(c12);

		System.out.println(c21);
		System.out.println(c22);

		System.out.println(c23);
		System.out.println(c13);

		System.out.println();
				
		IComplex con = c12.conjugate();
		System.out.println(con);
		System.out.println(c12.equalTo(con));
		System.out.println(c12 == con);
		System.out.println(c22.mul(con));

		System.out.println();

		IComplex sub1 = c12.sub(c23);
		System.out.println(sub1);
		System.out.println(sub1.equalTo(c12));
		System.out.println(sub1 == c12);

		IComplex add1 = c12.add(sub1);
		System.out.println(add1);
		System.out.println(add1.equalTo(c12));
		System.out.println(sub1 == c12);
	
	}

}
