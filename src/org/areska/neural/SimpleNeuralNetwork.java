package org.areska.neural;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleNeuralNetwork implements Serializable, NeuralNetwork {
	
    private static final long serialVersionUID = 1L;
    
    private int hiddenLayersNumber, inputNumber, outputNumber, neuronsPerLayer;
    private double bias, activationResponse;
    private float mutationSize, mutationRate, crossoverRate;
    
    private List<NeuronLayer> layers;
	private int fitness = 0;
	
	private Long id;
	
	public SimpleNeuralNetwork() {
		id = (long)(Math.random()*Math.random()*Math.random()*1000000000);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Double> update( List<Double> inputs ) {
		
		List<Double> outputs = new ArrayList<Double>();
		
		for( int i = 0; i < layers.size(); i++)	{
			if ( i > 0 ) inputs = outputs;
			outputs = new ArrayList<Double>();

			// Kiekvienam neuronui sumuojam (inputai * svoriai).
			// Rezultata pervarom per sigmoida
			NeuronLayer layer = layers.get( i );
			for( int j = 0; j < layer.neurons.size(); j++ ) {
				double netInput = 0;

				Neuron neuron = layer.neurons.get( j );
//				System.out.println ( "Neuron " + j );
				// kiekvienam svoriui
				for( int k = 0; k < neuron.inputs.size() - 1; k++ ) {
					// sumuojam svorius x is inputu
					double weight = neuron.inputs.get( k );
					double input = inputs.get( k );
//					System.out.print ( " " + (float)weight + " * " + (float)input );
					netInput += weight * input;
				}
//				System.out.println();
				double weight = neuron.inputs.get( neuron.inputs.size() - 1 );
				//pridedam Bias
				netInput += weight * bias;

				//outputs.add( sigmoid(netInput, activationResponse ) );
				outputs.add( simpleActivation(netInput) );
				//outputs.add( new Double( netInput ) );
			}
		}

		return outputs;
	}
	
	double simpleActivation( double netInput ) {
		if( netInput < 0 ) return -1;
		else return 1;
	}

	double sigmoid(double netInput, double response) {
		return ( 1 / ( 1 + Math.exp( -netInput / response ) ) );
	}
	
	public void createNetwork() {
		layers = new ArrayList<NeuronLayer>();
		if( hiddenLayersNumber > 0 )	{
		// input
			layers.add( new NeuronLayer( this, neuronsPerLayer, inputNumber ) );
	    // output
			layers.add( new NeuronLayer( this, outputNumber, neuronsPerLayer ) );
		}
		else {
	  // output
			layers.add( new NeuronLayer( this, outputNumber, inputNumber ) );
  		}
	}
	
	/**
     * @param layers  The layers to set.
     * @uml.property  name="layers"
     */
	private void setLayers( List<NeuronLayer> layers ) {
		this.layers = layers;
	}
	
	public NeuralNetwork mutate() {
		SimpleNeuralNetwork mutatedNN = new SimpleNeuralNetwork();
		List<NeuronLayer> mutadedLayers = new ArrayList<NeuronLayer>();
		for( int i = 0; i < layers.size(); i++ )
			mutadedLayers.add( layers.get( i ).mutate() );
		mutatedNN.setLayers( mutadedLayers );
		return mutatedNN;
	}
	
	public NeuralNetwork crossover( NeuralNetwork friendN ) {
		SimpleNeuralNetwork friend = (SimpleNeuralNetwork)friendN;
		SimpleNeuralNetwork crossedNN = new SimpleNeuralNetwork();
		List<NeuronLayer> crossedLayers = new ArrayList<NeuronLayer>();
		for( int i = 0; i < layers.size(); i++ )
			crossedLayers.add( layers.get( i ).crossover( friend.getLayer( i ) ) );
		crossedNN.setLayers( crossedLayers );
		return crossedNN;
	}
	
	private NeuronLayer getLayer( int i ) {
		return layers.get( i );
	}
	
	/**
     * @return  Returns the fitness.
     * @uml.property  name="fitness"
     */
	public int getFitness() {
		return fitness;
	}
	
	/**
     * @param fitness  The fitness to set.
     * @uml.property  name="fitness"
     */
	public void setFitness( int fitness ) {
		this.fitness = fitness;
	}
	
	public void addFitness( int fit ) {
		fitness += fit;
	}
	
	public void print( ) {
		System.out.println("Printing Neural Network: " + id );
		for( int i = 0; i < layers.size(); i++ ) {
			System.out.println("Layer " + i + ":");
			layers.get( i ).print();
		}
	}

	public void setActivationResponse(double activationResponse) {
		this.activationResponse = activationResponse;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	public void setHiddenLayersNumber(int hiddenLayersNumber) {
		this.hiddenLayersNumber = hiddenLayersNumber;
	}

	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}

	public void setNeuronsPerLayer(int neuronsPerLayer) {
		this.neuronsPerLayer = neuronsPerLayer;
	}

	public void setOutputNumber(int outputNumber) {
		this.outputNumber = outputNumber;
	}

	public void setMutationRate(float mutationRate) {
		this.mutationRate = mutationRate;
	}

	public void setMutationSize(float mutationSize) {
		this.mutationSize = mutationSize;
	}

	public float getMutationRate() {
		return mutationRate;
	}

	public float getMutationSize() {
		return mutationSize;
	}

	public float getCrossoverRate() {
		return crossoverRate;
	}

	public void setCrossoverRate(float crossoverRate) {
		this.crossoverRate = crossoverRate;
	}

}