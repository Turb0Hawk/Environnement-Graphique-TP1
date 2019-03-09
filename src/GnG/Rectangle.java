package GnG;

import java.awt.BasicStroke;
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

		if (x1 > x2) {
			this.setX1(x2);
			this.setX2(x1);
		} else {
			this.setX1( x1 );
			this.setX2(x2);
		}
		
		if (y1 > y2) {
			this.setY1(y2);
			this.setY2(y1);
		} else {
			this.setY1( y1 );
			this.setY2( y2 );
		}
		this.setHauteur( (int) Math.abs( (double) this.y2 - this.y1 ) );
		this.setLargeur( (int) Math.abs( (double) this.x2 - this.x1 ) );
		

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
		g2d.drawRect( x1, y1, largeur, hauteur );

		/*selon ce que le prof veut ????????
		 * if ( remplissage != null ) {
		 * 		g2d.setColor( remplissage );
				g2d.fillRect( x1, y1, largeur, hauteur );
			} else {
				g2d.setColor( contour );
				g2d.drawRect( x1, y1, largeur, hauteur );
			}
		 */
	}

}
