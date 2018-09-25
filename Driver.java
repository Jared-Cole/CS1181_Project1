import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

	
	public static void main(String[] args){
		int choice = -1;
		File dataFile = new File("Media.dat");
		ArrayList<MediaItem> m1 = new ArrayList<>();
	
		try {
			FileInputStream fin = new FileInputStream(dataFile);
			ObjectInputStream oin = new ObjectInputStream(fin);
			m1 = (ArrayList<MediaItem>)oin.readObject();
			fin.close();
			oin.close();
		}catch(Exception e) {
			System.out.println("Could not read data from file. Continuing without it");
		}
		
		choice = getMenu();
	
		while (choice !=6) {
			controller(choice, m1, dataFile);
			choice = getMenu();	
		}
		try {
			FileOutputStream f = new FileOutputStream(dataFile);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(m1);
			o.close();
		}catch(IOException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("Goodbye!");
	}//main

	public static int getMenu() {
		Scanner input = new Scanner(System.in);
		int choice = -1;
		String menu = "1.\tAdd Item  \n"
				+ "2.\tRemove Item  \n"
				+ "3.\tLoan Item   \n"
				+ "4.\tItem Returned  \n"
				+ "5.\tList Items\n"
				+ "6.\tQuit\n";
		
		System.out.println(menu);
			
			while(true) {		    
				try {
					System.out.print("Enter a choice: 1-6: ");
					choice = input.nextInt();
				if(choice > 0 && choice < 7) {
					System.out.println();
					return choice;
					}
				else {
					System.err.print("Error: ");
					input.nextLine();
				}
				}catch(Exception e) {
					System.err.print("Error: ");
					input.nextLine();
				}
				
	}
	
	}
	public static void controller(int choice, ArrayList<MediaItem> m1, File d) {
		String title;
		String format;
		String loanedTo;
		String loanedOn;
		Scanner input = new Scanner(System.in);
		
		try {
			if (choice == 1) {		
				addItem(m1);
				
			}//if
			else if (choice == 2) {
				removeItem(m1);
			}
			else if (choice ==3) {
				loanTo(m1);
			}
			else if (choice ==4) {
				returned(m1);
			}
			else if (choice ==5) {
				output(m1);
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}//controller
	private static void output(ArrayList<MediaItem> m1) {
		Collections.sort(m1);
		System.out.println("Title       Format      Loaned To        Loaned On\n"
				+ "-------------------------------------------------------");
		for(MediaItem a : m1) {
			System.out.printf("%-6s", a.getName());
			System.out.printf("%10s", a.getFormat());
			if(a.getLoanedTo() != null) { 
				System.out.printf("%15s", a.getLoanedTo());
				System.out.printf("%18s", a.getLoanedOn());
			}
			System.out.println();
			
		}
		System.out.println("\n");
	}
	public static void addItem(ArrayList<MediaItem> m1) throws DuplicateItemException{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter Title: ");
		String title = input.nextLine();
		System.out.print("Enter Format: ");
		String format = input.nextLine();
		System.out.println("");
		
		for (MediaItem a : m1) {
			if(title.equalsIgnoreCase(a.getName())) {
				throw new DuplicateItemException("Item with title " + title + " already exists");		
			}
		}
		System.out.println("Enter format: ");
		MediaItem m = new MediaItem(title, format);
		m1.add(m);
	}//addItem
	public static void removeItem(ArrayList<MediaItem> m1) throws ItemDoesntExistException {
		String title;
		boolean found = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Title: ");
		title = input.nextLine();
		for (MediaItem a : m1) {
			if((a.getName().equalsIgnoreCase(title))) {
				m1.remove(a);
				found = true;
			}
			
		}
		if(!found) {
			throw new ItemDoesntExistException("Error: Item not found");
		}
	}//removeItem
	public static void loanTo(ArrayList<MediaItem> m1) throws ItemDoesntExistException{	
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Title: ");
		String title = input.nextLine();
		boolean found = false;
		
		for (MediaItem a : m1) {
			if(title.equalsIgnoreCase(a.getName())) {
				System.out.print("Name of Borrower: ");
				String loanedTo = input.nextLine();
				a.setLoanedTo(loanedTo);
				System.out.println();
				System.out.print("Date of Loan: ");
				String loanedOn = input.nextLine();
				a.setLoanedOn(loanedOn);
				found = true;
				}
		}
		if(!found) {
			throw new ItemDoesntExistException("Item with title" + title + "does not exist");
			}	
	}
	public static void returned(ArrayList<MediaItem> m1) throws ItemDoesntExistException{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Title: ");
		String title = input.nextLine();
		boolean found = false;
		
		for (MediaItem a : m1) {
			if((title.equalsIgnoreCase(a.getName()))) {
				a.setLoanedTo(null);
				a.setLoanedOn(null);
				found = true;
				}
		}
		if(!found){
				throw new ItemDoesntExistException("Item with title" + title + "does not exist");
			}		
	}

}//class
