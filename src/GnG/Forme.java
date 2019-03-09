package GnG;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Forme {

	public int getXInit() {
		return xInit;
	}

	public void setXInit( int xInit ) {
		this.xInit = xInit;
	}

	public int getYInit() {
		return yInit;
	}

	public void setYInit( int yInit ) {
		this.yInit = yInit;
	}

	protected int x1;
	protected int y1;
	protected int xInit;
	protected int yInit;
	
	protected float strokeWidth = 5.0f;

	protected Color contour;
	protected Color remplissage;
	
	public Forme() {
		super();
	}

	public Forme( int x, int y ) {
		super();
		this.setX1( x );
		this.setY1( y );
	}
	
		public Color getContour() {
		return contour;
	}

	public void setContour( Color contour ) {
		this.contour = contour;
	}

	public Color getRemplissage() {
		return remplissage;
	}

	public void setRemplissage( Color remplissage ) {
		this.remplissage = remplissage;
	}
	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth( float strokeWidth ) {
		this.strokeWidth = strokeWidth;
	}

	public void setX1( int x1 ) {
		this.x1 = x1;
	}

	public void setY1( int y1 ) {
		this.y1 = y1;
	}

	public void setPosInitiale( int x, int y ) {
		this.xInit = x;
		this.yInit = y;
	}


	public int getX1() {
		return x1;

	}


	public int getY1() {
		return y1;

	}


	public abstract void setParametres( int x1, int y1, int x2, int y2 );

	public abstract void tracer( Graphics g );
}
