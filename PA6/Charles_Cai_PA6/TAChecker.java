/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 5,2,2022
* PA6
* Explanation: This TA checker  class and also main method contains a sortworklog method to extract and 
* store all necessary data into the TARecord object. And the checkValidity method will check and print
* all frauds.
*/
import java.io.*;
import java.util.*;

/**
 * 
 * @author Lenovo
 *
 */
public class TAChecker {
	
	public static HashMap <String, TARecord> RECORD = new HashMap <String, TARecord>(); // record all TA's name as key and TARecord objects as value
	public static int CURRENT_INVOICE_ID = 0; // set the current highest Invoice id
	public static String filename;
	/**
	 * This is the main method and it will deal with a work log text that user input
	 * and detect any fraud 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String [] args) throws FileNotFoundException {
		System.out.println("Enter a work log:");
		Scanner console = new Scanner (System.in);
		filename = console.next();
		sortWorkLog();
		checkValidity();
	}
	
	/**
	 * This method will extract and store all necessary data into the TARecord object
	 * @param input
	 * @throws FileNotFoundException
	 */
	public static void sortWorkLog() throws FileNotFoundException {
		int lineNumber = 0;
		Scanner input = new Scanner(new File(filename));
		while (input.hasNextLine()) {
			String line = input.nextLine();
			lineNumber++;
			Scanner lineScan = new Scanner (line);
			lineScan.useDelimiter("[^0-9a-zA-Z']+");	// eliminate all ";" in the text
			String TAname = lineScan.next();
			
			if (!RECORD.containsKey(TAname)) { // if TA's name is not exist, store TA's name
				RECORD.put(TAname, new TARecord(TAname));
			}
			
			if (!lineScan.hasNextInt()) { // when next item is a string instead of number, which will be "START"
				RECORD.get(TAname).addStartLine(lineNumber);
				RECORD.get(TAname).setCurrentInvoice(CURRENT_INVOICE_ID);
				lineScan.next();
			}else {
				while (lineScan.hasNextInt()) { // when next item is number
					int invoiceId = lineScan.nextInt();
					
					if (CURRENT_INVOICE_ID < invoiceId) {
						CURRENT_INVOICE_ID = invoiceId;
					}
		
					RECORD.get(TAname).checkStartLine(invoiceId, lineNumber);  // store start line number and invoice id if it is not a unstarted job
					RECORD.get(TAname).IdstoEndline.put(invoiceId, lineNumber);// store end line number and its invoice id
				}
				RECORD.get(TAname).checkShortenedJobAndSuspicousBatches(lineNumber);
			}
		}
	}
	
	/**
	 * This method will go through all the data structures that store
	 * information about fraud in TARecords and print all the existing frauds
	 */
	public static void checkValidity() {
		ArrayList TAnames = new ArrayList <String> ();
		
		for (String name: RECORD.keySet()) {
			TAnames.add(name);   // create a array list and add all TA's name into the array list
		}
		
		for (int i = 0 ; i < TAnames.size(); i++) {
			if (RECORD.get(TAnames.get(i)).unstartedJob.size() > 0) { // check if the array list of unstarted job contains element
				ArrayList a = RECORD.get(TAnames.get(i)).unstartedJob;
				for (int j = 0 ; j < a.size(); j++ ) {
					System.out.println(a.get(j) + ";" + TAnames.get(i) + ";" + "UNSTARTED_JOB");
				}
			}
			
			if (RECORD.get(TAnames.get(i)).shortenedJob.size() > 0) { // check if the array list of shortenedjob contains element
				ArrayList b = RECORD.get(TAnames.get(i)).shortenedJob;
				for (int k = 0 ; k < b.size(); k++ ) {
					System.out.println(b.get(k) + ";" + TAnames.get(i) + ";" + "SHORTENED_JOB");
				}
			}
			
			if (RECORD.get(TAnames.get(i)).suspiciousBatches.size() > 0) { // check if the array list of suspiciousBatches contains element
				ArrayList c = RECORD.get(TAnames.get(i)).suspiciousBatches;
				for (int m = 0 ; m < c.size(); m++ ) {
					System.out.println(c.get(m) + ";" + TAnames.get(i) + ";" + "SUSPICIOUS_BATCH");
				}
			}
		}
	}
}
