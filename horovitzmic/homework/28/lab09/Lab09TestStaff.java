import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lab09TestStaff{

	StoreHouse sh;
	Map<String, Product> lookup;

	public Lab09TestStaff() {
		lookup = new HashMap<String, Product>();
		sh = new StoreHouse();
	}   

	public void process(String[] input) {
		if( input.length != 3 )
		{
			if( input[0].length() > 0 )
				System.out.println( "bad input line <" + input[0] + ">" );
			return;
		}

		int amount = Integer.parseInt( input[2] );

		Product p = null;
		try
		{
			if( input[0].equals( "define" ) )
			{
				p = new Product( input[1], amount );
				lookup.put( input[1], p );
				System.out.println( "Defined product " + input[1] );
				return;
			}

			if( lookup.containsKey( input[1] ) )
				p = lookup.get( input[1] );
			else
			{
				System.out.println( "Unknown product " + input[1] );
				return;
			}

			if( input[0].equals( "order" ) )
			{
				sh.order( p, amount );
				System.out.println( "Ordered " + amount + " " + p.getProductName() );
			}
			else if( input[0].equals( "supply" ) )
			{
				sh.supply( p, amount );
				System.out.println( "Supplied " + amount + " " + p.getProductName() );
			}
			else
			{
				System.out.println( "Unrecognized operation " + input[0] );
			}

			System.out.println( sh );
		}
		catch( OutOfStockException oose )
		{
			System.out.println( "Product <" + oose.getProduct().getProductName()
					+ "> is out of stock: " + oose.getNeeded() + 
					" are needed but only " + sh.stock( oose.getProduct() ) + 
					" are in stock." );
		}
		catch( OverStockException ose )
		{
			System.out.println( "Product <" + ose.getProduct().getProductName()
					+ "> is overstocked: \n" + "   there are "
					+ sh.stock( ose.getProduct() )
					+ " in stock already, and additional " + ose.getProvided()
					+ " are supplied,\n" + "   but there is room for only "
					+ ose.getProduct().getMaxQuantity() );
		}

	}

	public static void main( String[] args )
	{
		Lab09TestStaff test = new Lab09TestStaff();


		Scanner sc = null;
		try
		{
			sc = new Scanner( new File( args[0] ) );
		}
		catch( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while( sc.hasNextLine() )
		{
			String line = sc.nextLine();
			String[] input = line.split( "\\s+" );
			test.process(input);
		} /* while */

	}

}
