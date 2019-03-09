package GnG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Rectangle extends Forme {

	protected Graphics2D g2d;
	protected int largeur;
	protected int hauteur;

	public Rectangle() {
		super();
	}

	public Rectangle( int x, int y ) {
		super( x, y );
		setContour( InterfacePrincipale.getContourCourrant() );
		setRemplissage( InterfacePrincipale.getRemplissageCourrant() );
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur( int largeur ) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur( int hauteur ) {
		this.hauteur = hauteur;
	}

	@Override
	public void setParametres( int x1, int y1, int x2, int y2 ) {

		this.setHauteur( (int) Math.abs( (double) y2 - y1 ) );
		this.setLargeur( (int) Math.abs( (double) x2 - x1 ) );
		setPosInitiale( ( y1 > y2 ) ? y2 : y1, ( x1 > x2 ) ? x2 : x1 );

	}

	@Override
	public void tracer( Graphics g ) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2d.setStroke( new BasicStroke( strokeWidth ) );
		g2d.setColor( Color.decode(BarreOutils.couleurs[contour]) );
		if ( remplissage >= 0 ) {
			g2d.drawRect( x1, y1, largeur, hauteur );
			g2d.setColor( Color.decode(BarreOutils.couleurs[remplissage]) );
			g2d.fillRect( x1, y1, largeur, hauteur );
		} else {
			g2d.drawRect( x1, y1, largeur, hauteur );
		}
	}

}
