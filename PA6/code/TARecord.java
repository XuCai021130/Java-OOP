/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 5,2,2022
* PA6
* Explanation: This TARecord class will store all the useful data into  its fields and provide
* get and set method to modify these fields
*/
import java.util.*;

/**
 * this class is mainly a tool that provide data structures and methods to  add data into 
 * these data structure for a TA
 * @author Lenovo
 *
 */
public class TARecord {
	String name;
	int numberOfJobs;
	int currentInvoiceId;
	HashMap <Integer,Integer> startLineToId;
	HashMap <Integer,Integer> IdstoEndline;
	LinkedList <Integer> startLineNumber;
	ArrayList <Integer> unstartedJob;
	ArrayList <Integer> shortenedJob;
	ArrayList <Integer> suspiciousBatches;

	/**
	 * this constructor initialize all the fields
	 * @param name
	 */
	public TARecord (String name) {
		this.name = name;
		numberOfJobs = 0;
		currentInvoiceId = 0;
		startLineToId = new HashMap <Integer,Integer>();
		IdstoEndline = new HashMap <Integer, Integer>();
		startLineNumber = new LinkedList <Integer>();
		unstartedJob = new ArrayList <Integer>();
		shortenedJob = new ArrayList <Integer>();
		suspiciousBatches = new ArrayList <Integer>();
		
	}
	/**
	 * add line number to start line linked list
	 * @param lineNumber
	 */
	public void addStartLine (int lineNumber) {
		startLineNumber.add(lineNumber);
	}
	
	/**
	 * add key and value pair to startLineToId hash map
	 * @param startLineNumber
	 * @param InvoiceId
	 */
	public void setStartLineToId(int startLineNumber, int InvoiceId) {
		startLineToId.put(startLineNumber, InvoiceId);
	}
	
	/**
	 * add key and value pair to IdstoEndline hash map
	 * @param endLineNumber
	 * @param InvoiceId
	 */
	public void setEndLineToId(int endLineNumber, int InvoiceId) {
		IdstoEndline.put(endLineNumber, InvoiceId);
	}
	
	/**
	 * return number of jobs this TA finished
	 * @return
	 */
	public int getNumberOfJobs() {
		return numberOfJobs;
	}
	
	/**
	 * check shortened job and suspicious batches frauds
	 * @param lineNumber
	 */
	public void checkShortenedJobAndSuspicousBatches(int lineNumber) {
		int numberOfShortenedJob = 0;
		for (int i = 0; i < startLineNumber.size(); i++) {
			
			if (startLineToId.get(startLineNumber.get(i)) < currentInvoiceId) {
				numberOfShortenedJob++;
			}	
		}

		if (numberOfShortenedJob == startLineNumber.size()) {
			shortenedJob.addAll(startLineNumber); // if the number of shortened job equal to start line number
		}                                          // it proves that it is a shortened job fraud
		
		else if (numberOfShortenedJob != 0 && numberOfShortenedJob < startLineNumber.size()) {
			suspiciousBatches.add(lineNumber); // if there are at least one shortened job but not all
		}                                      // it is a suspicious batches job fraud
		numberOfJobs = 0;
		startLineNumber.clear();
		
	}
	
	/**
	 * this method will add start line and id pairs into hash map, but if it is a unstarted
	 * job fraud, it will be also stored
	 * @param invoiceId
	 * @param lineNumber
	 */
	public void checkStartLine(int invoiceId, int lineNumber) {
		if (numberOfJobs < startLineNumber.size()) {
			startLineToId.put(startLineNumber.get(numberOfJobs), invoiceId);
			numberOfJobs++;
		}
		else { // when there is no start line but have a invoice id, it is a unstarted job
			unstartedJob.add(lineNumber);
		}
	}
	
	/**
	 * set a new invoice id
	 * @param currentInvoice
	 */
	public void setCurrentInvoice(int currentInvoice) {
		this.currentInvoiceId = currentInvoice;
	}
	
	
}
		
	


	


