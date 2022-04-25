
public class Lab04TestStaff {
	
	   public static void main( String[] args )
	   {
	      //Mammal  m = new Mammal( 30.0, 120 );
	      Canine  c = new Canine( 16.5, 60, true );
	      Dog     d = new Dog( 12.8, 70, true, "Rex", 14 );
	      Feline  f = new Feline( 4.9, 54, false );
	      Cat     cat = new Cat( 3.6, 57, true, false );
	      Leopard l = new Leopard( 120, 112, true, 6 );
	      System.out.println(d.getGestation());
	      System.out.println(cat.getGestation());
	      System.out.println(l.getGestation());
	      l.setWeight(138);
	      System.out.println(l.getWeight());
	   }
}
