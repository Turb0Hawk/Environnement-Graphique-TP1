package GnG;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ovale extends Rectangle {
	
	public Ovale() {
		super();
	}

	public Ovale( int x, int y ) {
		super( x, y );
	}

	@Override
	public void tracer( Graphics g ) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2d.setStroke( new BasicStroke( strokeWidth ) );
		//following code pas ce que le prof veu mais je crois que sa vas [etre actually mieux je sais pas ?
		g2d.setColor( contour );
		if ( remplissage != null ) {
			g2d.setPaint( remplissage );
		}
		g2d.drawOval( x1, y1, largeur, hauteur );
		/*selon ce que le prof veut ????????
		 * if ( remplissage != null ) {
		 * 		g2d.setColor( remplissage );
				g2d.fillOval( x1, y1, largeur, hauteur );
			} else {
				g2d.setColor( contour );
				g2d.drawOval( x1, y1, largeur, hauteur );
			}
		 */
	}
}
