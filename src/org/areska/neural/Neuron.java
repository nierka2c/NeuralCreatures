package org.areska.neural;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Neuron implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public List<Double> inputs;
	private int size = 0;
	private NeuronLayer layer;
	
	public Neuron( NeuronLayer layer ) {
		this.layer = layer;
		inputs = new ArrayList<Double>();
	}
	
	public Neuron( NeuronLayer layer, int size ) {
		this.layer = layer;
		this.size = size;
		inputs = new ArrayList<Double>();
		// + 1 todel, kad _bias_ taip pat traktuosim kaip input'a
		for( int i = 0; i < size + 1; i++ ) {
//			 random bus stilium -1 < x < 1
			double thresh = Math.random();
			double input = Math.random();
			if( thresh > 0.5)
				inputs.add( input );
			else inputs.add( -input );
		}
	}
	
	/**
     * @param inputs  The inputs to set.
     * @uml.property  name="inputs"
     */
	public void setInputs( List<Double> inputs ) {
		this.inputs = inputs;
	}
	
	public Neuron mutate() {
		Neuron neuron = new Neuron( layer, size );
		List<Double> mutatedInputs = new ArrayList<Double>();
		for( int i = 0; i < size + 1; i++ ) {
			double value = inputs.get( i );
			double ran = Math.random();
			if( ran > layer.getNetwork().getMutationRate() ) {
				mutatedInputs.add( new Double( value ) );
				continue;
			}
			ran = Math.random();
			boolean plus = false;
			if( ran > 0.5 ) plus = true;
			ran = Math.random();
			ran *= layer.getNetwork().getMutationSize(); // jei mut_size = 0.1, tai equ /= 10
			if( plus ) value += ran;
			else value -= ran;
			mutatedInputs.add( new Double( value ) );
		}
		neuron.setInputs( mutatedInputs );
		return neuron;
	}
	
	public Neuron copy() {
		Neuron neuron = new Neuron( layer, size );
		List<Double> newInputs = new ArrayList<Double>();
		for( int i = 0; i < size + 1; i++ ) {
			newInputs.add( inputs.get( i ) );
		}	
		neuron.setInputs( newInputs );	
		return neuron;
	}
	
	
	public void print() {
		for( int i = 0; i < inputs.size(); i++ ) {
			System.out.print( " " + (float)(inputs.get( i ) + 0 ) );
		}
		System.out.println("");
	}
	
}
