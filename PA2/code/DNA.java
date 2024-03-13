/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 2,13,2022
* PA2
* Explanation: This program will process DNA sequences of a given text and print processed data to a new output file 
*/
import java.io.*;
import java.util.*;

/**
 * @author Lenovo
 */
public class DNA {
	public static final int MIN_NUMBER_CONDONS = 5; // define four class constants
	public static final double PERCENT_OF_MASS_OF_CG = 30;
	public static final int UNIQUE_NUCLEOTIDES = 4;
	public static final int NUCLEOTIDES_PER_CONDON = 3;
	
/**
 * The main method will process two lines group each time by using while loop
 * @param args
 * @throws FileNotFoundException : Declare in advance that I will not handle the error FileNotFoundException
 */
	public static void main(String[] args) throws FileNotFoundException { 
		Scanner input = new Scanner (System.in);
		Scanner output = new Scanner (System.in);
		int lineNumber = 0;
		int [] nucCounts = new int [UNIQUE_NUCLEOTIDES];
		double [] massPercent = new double [UNIQUE_NUCLEOTIDES];
		double [] mass = {135.128, 111.103, 151.128, 125.107}; // define arrays for later uses
		char [] a = {'A', 'C', 'G', 'T'};
		double sum = 0;
		String isProtein = "";
		System.out.println("This program reports information about DNA");
		System.out.println("nucleotide sequences that may encode proteins.");
		System.out.print("Input file name? ");
		String inputText = input.next();
		Scanner inputFile = new Scanner (new File(inputText)); // Allow user to enter the name of file that needed to be process
		System.out.print("Output file name?");
		String outputTextName = output.next(); // Allow user to enter the name of a blank file used to print processed content
		PrintStream outputFile = new PrintStream(new File (outputTextName));
		
		while (inputFile.hasNextLine()){  
			lineNumber++;
			String line = inputFile.nextLine();
			String [] condonsList = new String [line.length()/3];
			if (lineNumber % 2 == 1) { // if line is odd, we only need to print region name to output file
				outputFile.println("Region name: " + line);
			}
			else { // if the line is even, we need to do all the processes such as count nucleotides, calculate mass percent, etc.
				line = line.toUpperCase();
				outputFile.println("Nucleotides: " + line);
				nucCounts = countNucleotides (line, a);
				massPercent = massPercentage ( nucCounts, line, mass);
				sum = calculatingSum (nucCounts, line, mass);
				sum = Math.round(sum * 10.0) / 10.0;
				condonsList = codonsList (line);
				isProtein = isProtein (condonsList, massPercent);
				printOutput (nucCounts, massPercent, sum, condonsList, isProtein, outputFile);
			}
		}
	}
	
/**
 * This method can extract all the codons from a given DNA sequence
 * @param DnaSequence : This parameter is extract from all even lines of the input text and it will be separate to groups of three letters
 * @return
 */
	public static String[] codonsList (String dnaSequence) {
		dnaSequence = dnaSequence.replace("-", ""); // replace all dashes with empty spaces since dashes do not included in codon
		String [] condonsList = new String [dnaSequence.length()/NUCLEOTIDES_PER_CONDON];
		int index = 0;
		for (int i = 0 ; i < dnaSequence.length() / NUCLEOTIDES_PER_CONDON ; i++) { // traversal
			condonsList[i] = dnaSequence.substring(index,index+3); // extract each codon by using substring method
			index+=3;
		}
		return condonsList;
	}

/**
 * This method will print everything except Region name and dna sequence to the output file
 * @param NucCounts
 * @param percentageOfMass
 * @param sum
 * @param condonsList
 * @param whetherProtein
 * @param output
 * @throws FileNotFoundException
 */
	public static void printOutput (int [] NucCounts, double [] percentageOfMass, double sum, String [] condonsList, String whetherProtein, PrintStream output) throws FileNotFoundException {
		output.println("Nuc. Counts: " + Arrays.toString(NucCounts));
		output.println("Total Mass%: " + Arrays.toString(percentageOfMass) + " of " + sum);
		output.println("Codons List:" + Arrays.toString(condonsList));
		output.println("Is Protein?: " + whetherProtein);
		output.println();
	}

/**
 * This method will count number of each nucleotide and use array to store values
 * @param line
 * @param a
 * @param mass
 * @return
 */
	public static int [] countNucleotides (String line, char [] a) {
		int [] countNucleotides = new int [UNIQUE_NUCLEOTIDES];
		for (int i=0; i < line.length(); i++) { // use nested for loop to match the index of nucleotide 
			for (int j=0; j < a.length; j++) { // so that it is more concise 
				if (line.charAt(i) == a[j]) { // justify if the char equals to the array of the same index
					countNucleotides[j]++;
				}
			}
	}
		return countNucleotides;
}

/**
 * This method calculate the mass percent of each nucleotide
 * @param countNucleotides
 * @param line
 * @param mass
 * @return
 */
	public static double [] massPercentage (int [] countNucleotides , String line, double [] mass) {
		double [] massPercentage = new double [UNIQUE_NUCLEOTIDES];
		double sum = 0;
		int dashNumber = line.length(); // define the number of dash as the total length first
		for (int i = 0; i < countNucleotides.length; i++ ) {
			sum += (countNucleotides[i] * mass [i]);
			dashNumber -= countNucleotides[i]; // when each time sum adds the mass of one nucleotide
		}                                      //  the dash number will minus number of each nucleotide, the final result will be dash number
		sum += dashNumber * 100;               // adds mass of dashes to the sum
		
		for (int j = 0; j < countNucleotides.length; j++) { // this for loop will round each value to one digit past the decimal
			massPercentage [j] = Math.round((countNucleotides[j] * mass [j] / sum * 100) * 10.0) / 10.0;
		}
		return massPercentage;
}

/**
 * This method evaluate whether the given DNA sequence is a protein by justifying four criteria
 * @param CondonsList
 * @param massPercent
 * @return
 */
	public static String isProtein (String [] CondonsList, double [] massPercent ) { 
		if (!CondonsList [0].equals("ATG")) { // User four if statements to justify whether each criteria is satisfied                
			return "NO";                      // As long as any one criteria cannot be satisfied, it is not protein
		}
		if (!CondonsList [CondonsList.length-1].equals("TAA") && !CondonsList [CondonsList.length-1].equals("TAG") && !CondonsList [CondonsList.length-1].equals("TGA")) {
			return "NO";
		}
		if (CondonsList.length < MIN_NUMBER_CONDONS) {
			return "NO";
		}
		if (massPercent [1] + massPercent [2] < PERCENT_OF_MASS_OF_CG ) {
			return "NO";
		}
		return "YES"; // it is protein only when all four criteria is satisfied
	}

/**
 * This method calculate the sum of the weights of 'A', 'C', 'G', 'T' and '-'
 * @param countNucleotides
 * @param line
 * @param mass
 * @return
 */
	public static double calculatingSum (int [] countNucleotides , String line, double [] mass) { 
		double sum = 0;
		int dashNumber = line.length(); // define the number of dash as the total length first
		for (int i = 0; i < countNucleotides.length; i++ ) { //
			sum += (countNucleotides[i] * mass [i]);
			dashNumber -= countNucleotides[i];  // when each time sum adds the mass of one nucleotide
		}                                       //  the dash number will minus number of each nucleotide, the final result will be dash number
		sum += dashNumber * 100;                // adds mass of dashes to the sum
		return sum;
	}
}