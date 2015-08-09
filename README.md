NeuralResearch-  Research and Personal Project
==============
Extensive information on my personal project can be found here:

https://sites.google.com/a/conncoll.edu/mkhan/



ReadMe.txt

							----To run the training ----

Run Pixel.java first with the images in the Processed Images file. 
You should change the input location in the code because it will be different than my Desktop! 
--- Change the number of rows (depending on how many images you want to train with) at the top of the code
--- if that value is change - also increment the pictureCount to be one greater than the number of pictures 
	so that the loop can execute.

	This will spit out the input into the "input.txt" file. 
	To actually train the NN - run the MLP3D.java file and enter the number of inputs along with the number of 
	outputs(one). Output 1 means Right, 0 means Left, .5 means Forward.
	and choose how many cycles you want to run ---> I recommend running around 40,000 cycles. 

	After the training is done - you will see the list of updated weights in the terminal.
	Unfortunately, at this point you will need to input the weights manually into Vision.java 


 							-----To run the trained NN-----

   Decide what new input you want by inputting them into the "new_weights.txt" file. 
   The inputs are restricted to 9 at this point. 
   Then, run Vision.java 

   

"Neural Network Implemented on a Wheeled Robot using Backpropagation and Arduino Chips Emulated as Nodes"

The two classes- Perceptron and MultiLayerPerceptron- in this Repository are the basics of a simulated Neural Network in machine learning and computational intelligence theory. A sample input file is given to help run the training sets- the inputs are separated by one space and the output is separated from a line of inputs by 2 spaces. **Be sure to change the file path in the MultiLayerPerceptron tester for the input file --should be at the top of the code--when running the program. This can be retrieved by a simple absolute file path method. 

Where I am going with this is to be able to implement a fully functional network through hardware- learning will also happen live and not offline. 
______________________________________________________________________________________

• Researched Artificial Neural Networks (ANN) as selected funded intern at Connecticut College to implement ANN on a wheeled remote controlled robot using Arduino microprocessor hardware in working towards publishing a paper 

• Programmed (Java/Scheme) a 4 layer ANN -2 hidden layers- that uses backpropagation and sigmoid activation functions to learn live within 1% error and take any amount of input and output nodes applied by a user

• Using Arudino software language, converted Java code to successfully program a single hardware Arduino to act as Perceptron node capable of taking input and producing output- blinking light

• Currently working on future goals: 
↪ utilizing 3 Arduino chips to solve XOR logic gate with all input-output options
↪ researching efficient ways to transfer data through pins on Arduino chips
↪ have Arduino chips take inputs from sensors that will go on robot 
↪ have a communicative Neural Network where each node is an Arduino chip itself
