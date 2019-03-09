package GnG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ovale extends Rectangle {

	public Ovale() {
		super();
	}

	public Ovale( int x, int y ) {
		super( x, y );
		setContour( InterfacePrincipale.getContourCourrant() );
		setRemplissage( InterfacePrincipale.getRemplissageCourrant() );
	}

	@Override
	public void tracer( Graphics g ) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2d.setStroke( new BasicStroke( strokeWidth ) );
		
		g2d.setColor( Color.decode(BarreOutils.couleurs[contour]) );	
		if ( remplissage >=0 ) {
			g2d.drawOval( x1, y1, largeur, hauteur );
			g2d.setColor(  Color.decode(BarreOutils.couleurs[remplissage]) );
			g2d.fillOval( x1, y1, largeur, hauteur );
		} else {
			
			g2d.drawOval( x1, y1, largeur, hauteur );
		}
	}
}
