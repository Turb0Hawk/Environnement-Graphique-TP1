package GnG;

import java.awt.Graphics;

public abstract class Forme {

	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected int xInit;
	protected int yInit;

	protected float strokeWidth = 5.0f;

	protected int contour;
	protected int remplissage;

	public Forme() {
		super();
	}

	public Forme( int x, int y ) {
		super();
		this.setXInit( x );
		this.setYInit( y );
		this.setX1( x );
		this.setY1( y );
	}

	public int getX2() {
		return x2;
	}

	public void setX2( int x2 ) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2( int y2 ) {
		this.y2 = y2;
	}

	public int getyInit() {
		return yInit;
	}

	public void setyInit( int yInit ) {
		this.yInit = yInit;
	}

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

	public int getContour() {
		return contour;
	}

	public void setContour( int contour ) {
		this.contour = contour;
	}

	public int getRemplissage() {
		return remplissage;
	}

	public void setRemplissage( int remplissage ) {
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
