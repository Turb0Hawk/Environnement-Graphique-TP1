package GnG;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;;

public class Trait extends Forme{
	protected Graphics2D g2d;
	protected int x2;
	protected int y2;
	
	public Trait() {
		super();
	}

	public Trait( int x, int y ) {
		super( x, y );
	}

	@Override
	public void tracer(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(strokeWidth));
		g2d.setColor( contour );
		if(remplissage != null) {
			 g2d.setPaint( remplissage );
		}
		g2d.drawLine( x1, y1, x2, y2 );
	}

	@Override
	public void setParametres( int x1, int y1, int x2, int y2 ) {
		this.setPosInitiale( x1, y1 );
		this.x2 = x2;
		this.y2 = y2;
	}

}
