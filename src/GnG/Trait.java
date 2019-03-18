package GnG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;;

/**
 * Fichier Trait.java Description de la classe: classe qui crée un trait
 * 
 * @author Dave Nicolas Parr, David Ringuet
 * @Date: 15/02/2019
 * @version 3
 *
 */
public class Trait extends Forme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Graphics2D g2d;

	public Trait() {
		super();
	}

	public Trait( int x, int y ) {
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
			g2d.setPaint( Color.decode( BarreOutils.couleurs[remplissage] ) );
		}
		g2d.drawLine( x1, y1, x2, y2 );
	}

	/**
	 * @see GnG.Forme#setParametres(int, int, int, int)
	 */
	@Override
	public void setParametres( int x1, int y1, int x2, int y2 ) {
		this.setPosInitiale( x1, y1 );
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void writeObject( java.io.ObjectOutputStream out ) throws IOException {
		out.writeInt( 0 );
		out.writeInt( x1 );
		out.writeInt( x2 );
		out.writeInt( y1 );
		out.writeInt( y2 );
		out.writeInt( xInit );
		out.writeInt( yInit );
		out.writeInt( contour );
		out.writeInt( remplissage );
	}

	public void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException {
		setX1(in.readInt());
		setX2(in.readInt());
		setY1(in.readInt());
		setY2( in.readInt() );
		setXInit( in.readInt() );
		setYInit( in.readInt() );
		setContour( in.readInt() );
		setRemplissage( in.readInt() );
	}

	public void readObjectNoData() throws ObjectStreamException {

	}
}
