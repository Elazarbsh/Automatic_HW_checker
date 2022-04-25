
public class Lab04Test {
	
	   public static void main( String[] args )
	   {
	      //Mammal  m = new Mammal( 30.0, 120 );     //not possible !
	      Dog     d1 = new Dog( 12.8, 70);
	      Dog     d2 = new Dog( 12.8, 70, true, "Rex", 14 );
	      
	      Cat     cat = new Cat( 3.6, 57, true, false );
	      Leopard l = new Leopard( 120, 112, true );
	      
	      System.out.println(cat.getGestation());
	      System.out.println(l.getGestation());
	      d2.setWeight(139);
	      System.out.println(d2.getWeight());
	   }
}
