import java.util.Scanner;

public class Lab06TestStaff {
	/**
	    * main - obtain clues from the user and present them to the associative
	    * array to obtain names of capital cities of countries whose names match the
	    * clues
	    * 
	    */
	   public static void main( String[] args ) throws Exception
	   {
	      
	      Capitals c = new Capitals();

	      /*
	       * Open the keyboard for input: prompt the user to enter a name of a
	       * country or a prefix of one, or "end" to end the session.
	       */
	      Scanner input = new Scanner( System.in );
	      while( true )
	      {
	         System.out.println( "Enter a country name (\"end\" to exit): " );
	         String clue = input.nextLine();
	         if( clue.equalsIgnoreCase( "end" ) )
	         {
	            System.out.println( "Good bye!" );
	            return;
	         }

	         /*
	          * list all countries matching the user input and their capitals
	          */
	         for( String s : c.prefixMatch( clue ) ){
		      System.out.println( "The capital of " + s + " is " + c.exactMatch( s ) + "\n" );
}
	      } /* while */

	   } /* main( String[] ) */

}
