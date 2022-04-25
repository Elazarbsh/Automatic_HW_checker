public class Lab07TestStaff {
	public static void main( String[] args ) throws Exception
	   {
	      
	      Cities cities = new Cities();

	      cities.listByDensity();
	      cities.listByPopulation();
	      cities.listAlphabetically();
	      cities.listByCountry( "China" );
	   }


}
