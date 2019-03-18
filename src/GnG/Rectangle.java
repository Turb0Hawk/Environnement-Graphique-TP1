package GnG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

/**
 * Fichier Ovale.java
 * Description de la classe: classe qui crée la forme Rectangle
 * @author Dave Nicolas Parr, David Ringuet 
 * @Date: 15/02/2019 
 * @version 3
 * 
 */
public class Rectangle extends Forme {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Graphics2D g2d;
	/**
	 * la largeur de la forme à dessiner
	 */
	protected int largeur;
	/**
	 * la hauteur de la forme à dessiner
	 */
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
	
	/**
	 * @see GnG.Forme#setParametres(int, int, int, int)
	 */
	@Override
	public void setParametres( int x1, int y1, int x2, int y2 ) {

		if ( x1 > x2 ) {
			this.setX1( x2 );
			this.setX2( x1 );
		} else {
			this.setX1( x1 );
			this.setX2( x2 );
		}

		if ( y1 > y2 ) {
			this.setY1( y2 );
			this.setY2( y1 );
		} else {
			this.setY1( y1 );
			this.setY2( y2 );
		}
		this.setHauteur( (int) Math.abs( (double) this.y2 - this.y1 ) );
		this.setLargeur( (int) Math.abs( (double) this.x2 - this.x1 ) );

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
			g2d.drawRect( x1, y1, largeur, hauteur );
			g2d.setColor( Color.decode( BarreOutils.couleurs[remplissage] ) );
			g2d.fillRect( x1, y1, largeur, hauteur );
		} else {
			g2d.drawRect( x1, y1, largeur, hauteur );
		}
	}

	@Override
	public void writeObject( ObjectOutputStream out ) throws IOException {
		out.writeInt( 1 );
		out.writeInt( x1 );
		out.writeInt( x2 );
		out.writeInt( y1 );
		out.writeInt( y2 );
		out.writeInt( xInit );
		out.writeInt( yInit );
		out.writeInt( contour );
		out.writeInt( remplissage );
		
	}

	@Override
	public void readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException {
		setX1(in.readInt());
		setX2(in.readInt());
		setY1(in.readInt());
		setY2( in.readInt() );
		setXInit( in.readInt() );
		setYInit( in.readInt() );
		setContour( in.readInt() );
		setRemplissage( in.readInt() );
		setParametres( x1, y1, x2, y2 );
		
	}

	@Override
	public void readObjectNoData() throws ObjectStreamException {
		// Auto-generated method stub
		
	}

}
