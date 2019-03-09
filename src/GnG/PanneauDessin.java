package GnG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanneauDessin extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1729406830849814133L;

	private ArrayList<Forme> formes = new ArrayList<>();
	private Forme formeTemp;

	public PanneauDessin() {
		super();
		this.setBackground( Color.LIGHT_GRAY );

		addMouseListener( this );
		addMouseMotionListener( this );
	}

	public PanneauDessin( ArrayList<Forme> sauvegarde ) {
		super();
		this.setBackground( Color.LIGHT_GRAY );

		// TODO lire la sauvegarder et dessiner les formes
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		formeTemp.setParametres( formeTemp.getXInit(), formeTemp.getYInit(), e.getX(), e.getY() );
        repaint();
        formeTemp.tracer( getGraphics() );
		
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
	}

	@Override
	public void mouseExited( MouseEvent e ) {
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		if ( InterfacePrincipale.getFormeCourrante() == 1 ) {

			formeTemp = new Ovale( e.getX(), e.getY() );
		} else if ( InterfacePrincipale.getFormeCourrante() == 0 ) {

			formeTemp = new Trait( e.getX(), e.getY() );
		} else {

			formeTemp = new Rectangle( e.getX(), e.getY() );
		}
	}

	@Override
	public void mouseReleased( MouseEvent e ) {

		formeTemp.setParametres( formeTemp.getXInit(), formeTemp.getYInit(), e.getX(), e.getY() );
        formes.add( formeTemp );
        if ( formes.size() > 0 ) {
            for ( Forme forme : formes ) {
                forme.tracer( this.getGraphics() );
            } 
        }
        formeTemp.tracer( getGraphics() );
	}
}