/*
 * Created on 2006.11.11
 */
package org.areska.genetic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.areska.neural.NeuralNetwork;

public class SimpleNeuralRunner implements NeuralRunner {
    
    private int tickLength = 0;
    private NeuralArena arena;
    private Integer timeTicks, generationBest, generationNum, generationSize, mutationCount;
    
    public void doTeaching( String fileName, boolean play ) {
        List<NeuralNetwork> networks = new ArrayList<NeuralNetwork>();
        if( play && fileName != null ) networks = getFromFile( fileName );
        int avg = 0;
        Long millis1 = System.currentTimeMillis();
        System.out.println("Initiating teaching mode");
        List<NeuralNetwork> bestNetworks = new ArrayList<NeuralNetwork>();
             
        // einam per generacijas
        for( int a = 0; a < generationNum; a++ ) {
            if( networks != null ) {
                int needMore = generationSize - networks.size();
                for( int i = 0; i < needMore; i++ )
                    networks.add( arena.provideNewNeuralNetwork() );
                arena.setNetworks( networks, a );
            } 
            System.out.println( "Processing Generation " + a );
            for( int i = 0; i < timeTicks; i++ ) {
                try{
                    Thread.sleep( tickLength );
                    arena.playRound( i );
                } catch( InterruptedException ex ) { }
            }
            //networks = arena.getAllNetworks();
            networks = getBest( networks, generationBest );
            System.out.print( "Best networks are: " );
            for( int i = 0; i < networks.size(); i++ ) {
                NeuralNetwork nnn = networks.get( i );
                System.out.print( nnn.getId() + ": " + nnn.getFitness() + ",   " );
                if( i == 0 ) avg += nnn.getFitness();
                nnn.setFitness( 0 );
            }
            System.out.println();
            bestNetworks = networks;
            networks = mutateNetworks( networks, mutationCount );
        }
        Long millis2 = System.currentTimeMillis();
        System.out.println( "Time taken: " + ( ( millis2 - millis1 ) / 1000 ) + " seconds" );
        System.out.println( "Average score: " + (int)( avg / generationNum ) );
        System.out.println( "Best networks: ");
        for( NeuralNetwork n : bestNetworks )
        	n.print();
        if( fileName != null )
            saveToFile( fileName, bestNetworks );
    }
    
    private List<NeuralNetwork> mutateNetworks( List<NeuralNetwork> networks, int amount ) {
        List<NeuralNetwork> newNetworks = new ArrayList<NeuralNetwork>();
        for( int i = 0; i < networks.size(); i++ ) {
            NeuralNetwork nn = networks.get( i );
            //nn.print();
            for( int j = 0; j < amount; j++ ) {
                NeuralNetwork nnn = nn.mutate();
                newNetworks.add( nnn );
                //System.out.println( "Mutated network is: " + nnn.getId() );
            }
            newNetworks.add( nn );
        }
        //System.out.println("");
        return newNetworks;
    }
    
    @SuppressWarnings("unused")
    private List<NeuralNetwork> crossoverNetworks( List<NeuralNetwork> networks ) {
        List<NeuralNetwork> newNetworks = new ArrayList<NeuralNetwork>();
        for( int i = 0; i < networks.size(); i+=2 ) {
            NeuralNetwork nn1 = networks.get( i );
            NeuralNetwork nn2 = networks.get( i + 1 );
            NeuralNetwork nn = nn1.crossover( nn2 );
            newNetworks.add( nn );
            newNetworks.add( nn1 );
            newNetworks.add( nn2 );
            System.out.println( "Crossed network is: " + nn.getId() );
        }
        return newNetworks;
    }
    
    private void saveToFile( String fileName, List<NeuralNetwork> networks ) {
        try {
            FileOutputStream ostream = new FileOutputStream( fileName );
            ObjectOutputStream oos = new ObjectOutputStream( ostream );
            oos.writeObject( networks );
            oos.flush();
            ostream.close();
        } catch( IOException e ) { e.printStackTrace(); }
    }
    
    private List<NeuralNetwork> getBest( List<NeuralNetwork> networks, int count ) {
        List<NeuralNetwork> best = new ArrayList<NeuralNetwork>();
        for( int a = 0; a < count; a++ ) {
            int max = -10000;
            int index = 0;
            for( int i = 0; i < networks.size(); i++ ) {
                NeuralNetwork nn = networks.get( i );
                if( nn.getFitness() > max ) {
                    index = i;
                    max = nn.getFitness();
                }
            }
            best.add( networks.get( index ) );
            networks.remove( index );
        }
        return best;
    }
    
    @SuppressWarnings("unchecked")
    private List<NeuralNetwork> getFromFile( String fileName ) {
        List<NeuralNetwork> l = null;
        try {
            FileInputStream istream = new FileInputStream( fileName );
            ObjectInputStream p = new ObjectInputStream(istream);
            l = (List)p.readObject();
            istream.close();
        } catch( Exception e ) { e.printStackTrace(); System.exit( -1 ); }
        return l;
    }

    /**
     * @return Returns the arena.
     */
    public NeuralArena getArena() {
        return arena;
    }

    /**
     * @param arena The arena to set.
     */
    public void setArena(NeuralArena arena) {
        this.arena = arena;
    }

    /**
     * @return Returns the tickLength.
     */
    public int getTickLength() {
        return tickLength;
    }

    /**
     * @param tickLength The tickLength to set.
     */
    public void setTickLength(int tickLength) {
        this.tickLength = tickLength;
    }

    /**
     * @return Returns the generationNum.
     */
    public Integer getGenerationNum() {
        return generationNum;
    }

    /**
     * @param generationNum The generationNum to set.
     */
    public void setGenerationNum(Integer generationNum) {
        this.generationNum = generationNum;
    }

    /**
     * @return Returns the generationSize.
     */
    public Integer getGenerationSize() {
        return generationSize;
    }

    /**
     * @param generationSize The generationSize to set.
     */
    public void setGenerationSize(Integer generationSize) {
        this.generationSize = generationSize;
    }

    /**
     * @return Returns the mutationCount.
     */
    public Integer getMutationCount() {
        return mutationCount;
    }

    /**
     * @param mutationCount The mutationCount to set.
     */
    public void setMutationCount(Integer mutationCount) {
        this.mutationCount = mutationCount;
    }

    /**
     * @return Returns the timeTicks.
     */
    public Integer getTimeTicks() {
        return timeTicks;
    }

    /**
     * @param timeTicks The timeTicks to set.
     */
    public void setTimeTicks(Integer timeTicks) {
        this.timeTicks = timeTicks;
    }

    /**
     * @return Returns the generationBest.
     */
    public Integer getGenerationBest() {
        return generationBest;
    }

    /**
     * @param generationBest The generationBest to set.
     */
    public void setGenerationBest(Integer generationBest) {
        this.generationBest = generationBest;
    }
}
