/*
* Mohammad Khan, Summer 2014
* The object Perceptron is defined as a node in a neural network. It has the ability
* to take input, take weights with each input, do a summation, run the summation 
* through a squashing function and determine output. Also has the ability to 
* set a desired output and compare it to actual output, and in doing so 
* produce an error. The error, along with the error gradient is used to update 
* the weights of the network to reduce error and train data sets (epochs).
*/


public class Perceptron 
{

	int i=-1;
	double add;
	double desiredOutput;
	double input[];
	double weight[]; //set of weights for each input
	double delta1[]; //set of delta errors for each outer layer
	double output;
	
	
	int limiter;
	int numOuts;
	int ins=-1;
	
	double outj3;
	double outj4;
	double outi1;
	double outi2;
	
	double deltaHid1;
	double deltaHid2;
	
	double deltaLast1;
	double deltaLast2;
	
	//determines how many inputs will be going into a node
	public void sizeofStrings(int x)
	{
		input= new double[x+1];
		weight= new double[x+1];
		limiter=x;
	}
	
  //actually inputs value of input into node
	public void input(double x)
	{
		i++;
		if(i==limiter+1)
		{
			i=0;
		}
		input[i]=x;
	}
	
	public void printIn()
	{
		for(int y=1; y<=limiter; y++)
		{
			System.out.print("["+ input[y]+"] " );
		}
	}
	
	//sums the weights x the input for each "i"th addition spanning i=0 to i=number of inputs
	public void calcOutput()
	{
		add=0.0;
		for(int x=0;x<=i;x++)
		{
			double a=input[x];
			double b=weight[x];
			
			double k= (a*b);
			add+=k;
		}
	}
	
	//squashing function restricts output (0,1)
	public double getOutput()
	{
		output= 1 / (1 + Math.exp(-add));
		return output;
	}
	
	public void setDesiredOutput(double x)
	{
		desiredOutput= x;
	}
	
	public double getDesiredOutput()
	{
		return desiredOutput;
	}
	
	//weights are initially set to random to be update and trained- in essence learned
	public void setWeight()
	{
		System.out.println("Set initial  random weight for threshold:    ");
		weight[0]=(double)(Math.random()*1);
		
		System.out.println(weight[0]);
		
		for(int u=1;u<=limiter;u++)
		{
			System.out.println("Set initial  random weight for input:    ");
			weight[u]=(double)(Math.random()*1);
			System.out.println(weight[u]);
		}
	} 

	public double error()
	{
		return (desiredOutput-output);
	}
	
	//weights of the initial nodes are updated
	public void updateWeight()
	{
			double rate=.05;
			
			for(int v=0;v<=limiter;v++)
			{
				weight[v]= weight[v]+ (rate*input[v]*error());
			}
	}
	
	// weights of the layers spanning down from the outer layer are updated- since the delta error
	// of the outer layer is utilized
	public void updateWeightOut()
	{
		double rate=.05;
		
		for(int v=0;v<=limiter;v++)
		{
			weight[v]= weight[v]+ (rate*input[v]*deltaOut());
		}
	}
	
	// weights of the hidden layers is updated 
	// two different updates going on here since there will be different delta
	// values for each hidden node 
	public void updateWeightHid(int x)
	{
		double rate=.05;

		if(x==1)
		{
			for(int v=0;v<=limiter;v++)
			{
				weight[v]= weight[v]+ (rate*input[v]*deltaHid1);
			}
		}
		if(x==2)
		{
			for(int v=0;v<=limiter;v++)
			{
				weight[v]= weight[v]+ (rate*input[v]*deltaHid2);
			}
		}
	}
	
	//weights of second set of hidden nodes is updated - same case as above
	public void updateWeightHid2(int x)
	{
		double rate=.05;
		
		if(x==1)
		{
			for(int v=0;v<=limiter;v++)
			{
				weight[v]= weight[v]+ (rate*input[v]*deltaLast1);
			}
		}
		if(x==2)
		{
			for(int v=0;v<=limiter;v++)
			{
				weight[v]= weight[v]+ (rate*input[v]*deltaLast2);
			}
		}
	}
	
	public void printWeight()
	{
		System.out.println("T-weight:  " + weight[0]);
		for(int n=1;n<=limiter;n++)
		{
			System.out.println("weight" + n + ":  " + weight[n]);
		}
	}
	
	
	public double getWeight(int x)
	{
		return weight[x];
	}
	
	public double deltaOut()
	{
		double x= (getOutput() *( 1-getOutput())* error());
		return x;
	}

	//calculates the delta error of the hidden nodes before the output layer
	public double deltaHid1(double x, double y )
	{
		outj3=x;
		double a=outj3*(1-outj3)*y;
		deltaHid1=a;
		return a;
	}
	
	//calculates the delta error of the hidden nodes before the second hidden layer
	public double deltaHid2(double x, double y )
	{
		outj4=x;
		double a=outj4*(1-outj4)*y;
		deltaHid2=a;
		return a;
	}
	
	//calculates the delta of the last layer corresponding to second hidden layer- node 1
	public double deltaHidLast1(double x, double y)
	{
		outi1=x;
		double a= outi1*(1-outi1)*y;
		deltaLast1=a;
		return a;
	}
	
	//calculates the delta of the last layer corresponding to second hidden layer- node 1
	public double deltaHidLast2(double x, double y)
	{
		outi2=x;
		double a= outi2*(1-outi2)*y;
		deltaLast2=a;
		return a;
	}
}

