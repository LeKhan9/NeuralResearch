 /* Mohammad Khan, Summer 2014
 * Neural Network Multi Layer Perceptron
 * Reads input from a file of training sets (to be simulated as epochs) and assigns them to objects 
 * of the Perceptron class. This then enters a 2 hidden layer Neural Network to determine outputs. 
 * The output and inputs lengths (or number of) are determined by the user writing the file. 
 * ---Essentially tests having the user input a variable number of set inputs and outputs
 */


//imports needed for catching exceptions and reading from the file 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;



public class MultiLayerPerceptron
{
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		    String filename = "/Users/Owais/Desktop/NeuronMK/Perceptron/input2.txt"; 
		 
		    Scanner read= new Scanner(System.in);
		    System.out.println("How many test sets?");
		    int a= read.nextInt();
		    System.out.println("What is the length of the inputs?");
		    int b= read.nextInt();
		    System.out.println("What is the length of the outputs?");
		    int c= read.nextInt();
		    System.out.println("How many cycles do you want to run? Enter Number X: ");
			  int r= read.nextInt();
		    read.close();
		    
		    int[][] inp=null;
		    try
		    {
		      FileReader readConnectionToFile = new FileReader(filename);
		      BufferedReader reads = new BufferedReader(readConnectionToFile);
		      Scanner scan = new Scanner(reads);

		      inp = new int[a][(b+c)];
		      int counter = 0;
		      try
		      {
		        while(scan.hasNext() && counter < a)
		        {
		            for(int i = 0; i < a; i++)
		            {
		                counter = counter + 1;
		                for(int m = 0; m < (b+c); m++)
		                {
		                    inp[i][m] = scan.nextInt();
		                }
		            }
		        }
			    int z=0;
			    for(int i = 0; i < a; i++)
			    {
			       System.out.print("Set " + (i + 1) + " is: " );
			       z=0;
			       while(z<(b+c))
			       {
			          System.out.print(inp[i][z] + " ");
			          z++;
			       }
			       System.out.println();
			    }

		    } catch(InputMismatchException e)
		    {
		        System.out.println("Error converting number");
		    }
		    scan.close();
		    reads.close();
		    } catch (FileNotFoundException e)
		    {
		        System.out.println("File not found" + filename);
		    } catch (IOException e)
		    {
		        System.out.println("IO-Error open/close of file" + filename);
		    }
		    
		  //creates number of output perceptrons
		  Perceptron[] per= new Perceptron[c];
			for(int i=0; i<c;i++)
			{
				per[i]=new Perceptron();
				per[i].sizeofStrings(2);
			}
			
			//the four hidden layer neurons (perceptrons)
			Perceptron hid1= new Perceptron();
			Perceptron hid2= new Perceptron();
			Perceptron hid3= new Perceptron();
			Perceptron hid4= new Perceptron();
			
			//defines the number of inputs going into each node
			hid1.sizeofStrings(b);
			hid2.sizeofStrings(b);
			hid3.sizeofStrings(2);
			hid4.sizeofStrings(2);
			
		  
			hid1.setWeight();
			hid2.setWeight();
			hid3.setWeight();
			hid4.setWeight();
			for(int g=0;g<c;g++)
			{
				per[g].sizeofStrings(2);
				per[g].setWeight();
			}
			
			
			int s=0;//iterations to r
			int y=0;//track cycles
			while(s<r)
			{
				y++;
				System.out.println();
				System.out.println();
				System.out.println(" Cycle: " + y);

				int e=0; 
				while(e<a)//goes line by line from user inputs
				{
					hid1.input(-1.0);
					hid2.input(-1.0);
					hid3.input(-1.0);
					hid4.input(-1.0);
					for(int i=0;i<b;i++)
					{
						hid1.input((double)inp[e][i]);
						hid2.input((double)inp[e][i]);
					}
					System.out.println();
					System.out.println("inputs");
					hid1.printIn();
					
					hid1.calcOutput();
					hid2.calcOutput();
					
					hid3.input(hid1.getOutput());
					hid3.input(hid2.getOutput());
					hid3.calcOutput();
					
					hid4.input(hid1.getOutput());
					hid4.input(hid2.getOutput());
					hid4.calcOutput();
					
					int x=0;
					System.out.print("							Desired Output: " );
					for(int g=b;g<(b+c);g++)
					{
						per[x].setDesiredOutput((double)inp[e][g]);
						System.out.print("[" +per[x].getDesiredOutput()+"]");
						x++;
					}
					System.out.println();
					
					for(int g=0;g<c;g++)
					{
							per[g].input(-1.00);
							per[g].input(hid3.getOutput());
							per[g].input(hid4.getOutput());
							per[g].calcOutput();
							System.out.println("output "+ (g+1) + ":	"+ per[g].getOutput());
							System.out.println("error: 			             " + Math.abs(per[g].error()));
					}
					
					double v=0.0;
					double g=0.0;
					
					double f=0.0;
					double j=0.0;
					
					for(int g6=0;g6<c;g6++)
					{
						v+=per[g6].deltaOut() *per[g6].getWeight(1);
					}	
					for(int g6=0;g6<c;g6++)
					{
						g+=per[g6].deltaOut() *per[g6].getWeight(2);
					}	
					
					for(int g6=0;g6<c;g6++)
					{
						per[g6].updateWeightOut();
					}	
            			    // Equation for the calculation of the delta gradient for the outer hidden layer to be propagated backwards
            			    // Delta error along with normal error is used to change weights and update them
				    f= (hid3.deltaHid1(hid3.getOutput(), v))*(hid3.getWeight(1)) + (hid4.deltaHid2(hid4.getOutput(),g))*(hid4.getWeight(1));
				    j= (hid3.deltaHid1(hid3.getOutput(), v))*(hid3.getWeight(2)) + (hid4.deltaHid2(hid4.getOutput(),g))*(hid4.getWeight(2));
				    
					hid3.deltaHid1(hid3.getOutput(), v);
					hid3.updateWeightHid(1);
					hid4.deltaHid2(hid4.getOutput(),g);
					hid4.updateWeightHid(2);
					
					hid1.deltaHidLast1(hid1.getOutput(),f);
					hid1.updateWeightHid2(1);
					
					hid2.deltaHidLast2(hid2.getOutput(),j);
					hid2.updateWeightHid2(2);
					e++;
				}
				s++;
			}
		}    			
	}
		




