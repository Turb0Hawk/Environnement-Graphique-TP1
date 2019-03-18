package GnG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Fichier Ovale.java
 * Description de la classe: classe qui crée la forme Ovale
 * @author Dave Nicolas Parr, David Ringuet 
 * @Date: 15/02/2019 
 * @version 3
 * 
 */
public class Ovale extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ovale() {
		super();
	}

	public Ovale( int x, int y ) {
		super( x, y );
	}
	
	/**
	 * @see GnG.Forme#tracer(java.awt.Graphics)
	 */
	@Override
	public void tracer( Graphics g ) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2d.setStroke( new BasicStroke( strokeWidth ) );

		g2d.setColor( Color.decode( BarreOutils.couleurs[contour] ) );
		if ( remplissage >= 0 ) {
			g2d.drawOval( x1, y1, largeur, hauteur );
			g2d.setColor( Color.decode( BarreOutils.couleurs[remplissage] ) );
			g2d.fillOval( x1, y1, largeur, hauteur );
		} else {

			g2d.drawOval( x1, y1, largeur, hauteur );
		}
	}
	@Override
	public void writeObject( ObjectOutputStream out ) throws IOException {
		out.writeInt( 2 );
		out.writeInt( x1 );
		out.writeInt( x2 );
		out.writeInt( y1 );
		out.writeInt( y2 );
		out.writeInt( xInit );
		out.writeInt( yInit );
		out.writeInt( contour );
		out.writeInt( remplissage );		
	}
	
}