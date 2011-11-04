package org.areska;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridFrame extends Frame {
    private static final long serialVersionUID = 1L;

    public GridFrame( Canvas arena ) {
		super( "Creatures" );
		setLayout( null );
		setSize( 1000, 1000 );
		arena.setBounds( 25, 40, 1000, 1000 );
		add( arena );
		
		this.addWindowListener ( new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
						 System.exit( -1 );
				}
		} );
	}

}