
public class Lab08TestStaff {
	 public static void main( String[] args )
	   {
	     /*String[] fileNames = { "non-existant.txt",
	                             "empty-input.txt",
	                             "non-numbers.txt",
	                             "number-too-big.txt",
	                             "sum-too-big.txt",
	                             "too-many-lines.txt",
	                             "too-many-numbers-per-line.txt" };
	      
	      */
	      Exceptions e = new Exceptions();

	      for( String s : args )
	      {
	         e.process( s );

	         /*
	          * wait a second, to let any exception processing complete before
	          * examining another input file.
	          */
	         try
	         {
	            Thread.sleep( 1000 );
	         }
	         catch( InterruptedException ie )
	         {
	            ie.printStackTrace();
	         }
	      }
	   }

}
