package org.areska;
import java.util.ArrayList;
import java.util.List;


public class CreatureRunner extends Thread {

	private boolean isAlive = true;
	private final List<Creature> creatures = new ArrayList<Creature>();
	private final List<Creature> creaturesToRemove = new ArrayList<Creature>();
	
	@Override
    public void run() {
		for( ;isAlive; ) {
			try {
				sleep( App.TICK );
				onTick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void onTick() {
		synchronized( creatures ) {
			for( Creature creature : creatures ) {
				creature.onTick();
			}
			creatures.removeAll( creaturesToRemove );
		}
	}
	
	public void addCreature( Creature c ) {
		synchronized( creatures ) {
			creatures.add( c );
		}
	}
	
	public void removeCreature( Creature c ) {
		creaturesToRemove.add( c );
	}
	
	public void kill() {
		isAlive = false;
	}
}