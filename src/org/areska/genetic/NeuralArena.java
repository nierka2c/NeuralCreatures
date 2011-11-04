/*
 * Created on 2006.11.11
 */
package org.areska.genetic;

import java.util.List;

import org.areska.neural.NeuralNetwork;

public interface NeuralArena {

    void playRound(int i);

    List<NeuralNetwork> getAllNetworks();

    void setNetworks(List<NeuralNetwork> networks, int a);
    
    public NeuralNetwork provideNewNeuralNetwork();

}
