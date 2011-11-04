package org.areska;

import java.awt.Frame;


public class App {
	
	public static final int TICK = 100; // in ms
	public static final int DRAW_TICK = 30;
	private static GridPainter gridPainter;
	
	public static void main(String[] args) {
		Grid grid = new Grid(1000, 1000);
		Creature creature = new Creature();
		creature.setX(400);
		creature.setY(400);
		grid.getThings().add(creature);
		
		Tree tree = new Tree();
        tree.setX(700);
        tree.setY(700);
        grid.getThings().add(tree);
        
        Shroom shroom = new Shroom();
        shroom.setX(750);
        shroom.setY(700);
        grid.getThings().add(shroom);
		
		CreatureRunner creatureRunner = new CreatureRunner();
		creatureRunner.addCreature(creature);
		creatureRunner.start();
		
		
		gridPainter = new GridPainter();
		gridPainter.setGrid( grid );
		
		
		Frame frame = new GridFrame( gridPainter );
		frame.setLocation( 100, 100 );
		frame.setVisible( true );
        
        for( ;; ) {
        	try {
				Thread.sleep( DRAW_TICK );
				gridPainter.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
	

	public static GridPainter getGridPainter() {
		return gridPainter;
	}

}
