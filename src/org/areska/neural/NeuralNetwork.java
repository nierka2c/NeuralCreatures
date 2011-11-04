package org.areska.neural;

import java.util.List;

public interface NeuralNetwork {

	public Long getId();
	public void setId( Long id );
	public List<Double> update( List<Double> inputs );
	public NeuralNetwork mutate();
	public NeuralNetwork crossover(NeuralNetwork friend);
	public void print();
	public int getFitness();
	public void setFitness( int fitness );
	public void addFitness( int fit );

}