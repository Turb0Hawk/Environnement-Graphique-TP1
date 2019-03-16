package GnG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanneauDessin extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1729406830849814133L;

	private ArrayList<Forme> formes = new ArrayList<>();
	private Forme formeTemp;
	private JFrame frame;
	private String fichierCourant = "";

	public PanneauDessin( JFrame frame ) {
		super();
		this.setBackground( Color.LIGHT_GRAY );
		addMouseListener( this );
		addMouseMotionListener( this );
		this.frame = frame;
	}

	public ArrayList<Forme> getFormes() {
		return formes;
	}

	public void setFormes( ArrayList<Forme> formes ) {
		this.formes = formes;
	}

	public Forme getFormeTemp() {
		return formeTemp;
	}

	public void setFormeTemp( Forme formeTemp ) {
		this.formeTemp = formeTemp;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame( JFrame frame ) {
		this.frame = frame;
	}
	
	public String getFichierCourant() {
		return fichierCourant;
	}

	public void setFichierCourant( String fichierCourant ) {
		this.fichierCourant = fichierCourant;
	}

	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent( g );
		if ( !formes.isEmpty() ) {
			for ( Forme forme : formes ) {
				forme.tracer( g );
			}
		}
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
		formeTemp.tracer( getGraphics() );

	}
}