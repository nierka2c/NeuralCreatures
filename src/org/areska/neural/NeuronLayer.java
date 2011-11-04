package org.areska.neural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NeuronLayer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public List<Neuron> neurons;
	private int neuronNumber;
	private int inputsPerNeuron;
	private SimpleNeuralNetwork network;
	
	public NeuronLayer( SimpleNeuralNetwork network, int neuronNumber, int inputsPerNeuron ) {
		this.network = network;
		this.neuronNumber = neuronNumber;
		this.inputsPerNeuron = inputsPerNeuron;
		neurons = new ArrayList<Neuron>();
		for( int i = 0; i < neuronNumber; i++ )
			neurons.add( new Neuron( this, inputsPerNeuron ) );
	}
	
	/**
     * @param neurons  The neurons to set.
     * @uml.property  name="neurons"
     */
	public void setNeurons( List<Neuron> neurons ) {
		this.neurons = neurons;
	}
	
	public NeuronLayer mutate() {
		NeuronLayer layer = new NeuronLayer( network, neuronNumber, inputsPerNeuron );
		List<Neuron> mutadedNeurons = new ArrayList<Neuron>();
		for( Neuron n : neurons )
			mutadedNeurons.add( n.mutate() );
		layer.setNeurons( mutadedNeurons );
		return layer;
	}
	
	public NeuronLayer crossover( NeuronLayer friend ) {
		NeuronLayer layer = new NeuronLayer( network, neuronNumber, inputsPerNeuron );
		List<Neuron> crossedNeurons = new ArrayList<Neuron>();
		for( int i = 0; i < neuronNumber; i++ ) {
			double ran = Math.random();
			if( ran < network.getCrossoverRate() )
				crossedNeurons.add( neurons.get( i ).copy() );
			else crossedNeurons.add( friend.getNeuron( i ).copy() );
		}
		layer.setNeurons( crossedNeurons );
		return layer;
	}
	
	public Neuron getNeuron( int index ) {
		return neurons.get( index );
	}
	
	public void print( ) {
		for( int i = 0; i < neuronNumber; i++ ) {
			System.out.println("  neuron " + i);
			 neurons.get( i ).print();
		}
	}

	public SimpleNeuralNetwork getNetwork() {
		return network;
	}
	
}
