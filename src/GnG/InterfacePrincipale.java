package GnG;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfacePrincipale extends JFrame {


	private static int formeCourrante = 0;
	private static int contourCourrant = 0;
	private static int remplissageCourrant = -1;
	private static final long serialVersionUID = 1L;
	private static String nomFichier = "Untitled";
	ImageIcon iconeFenetre = new ImageIcon( InterfacePrincipale.class.getResource( "Images\\icon.png" ) );
	JPanel panelBarreOutils = new JPanel(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	
	public static int getFormeCourrante() {
		return formeCourrante;
	}

	/**
	 * @param forme
	 * 0 = trait,
	 * 1 = ovale,
	 * 2 = rectange
	 */
	public static void setFormeCourrante( int forme ) {
		if (forme >= 0 && forme <= 2) {
			InterfacePrincipale.formeCourrante = forme;
		}
	}

	public static int getContourCourrant() {
		return contourCourrant;
	}

	public static void setContourCourrant( int contourCourrant ) {
		InterfacePrincipale.contourCourrant = contourCourrant;
	}

	public static int getRemplissageCourrant() {
		return remplissageCourrant;
	}

	public static void setRemplissageCourrant( int remplissageCourrant ) {
		InterfacePrincipale.remplissageCourrant = remplissageCourrant;
	}
	
	public InterfacePrincipale() {
		super( nomFichier + "- GnG not Gimp" );

		constraints.gridx = 10;
		constraints.gridy = 3;
		setSize( new Dimension( 1000, 800 ) );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setIconImage( iconeFenetre.getImage() );
		
		panelBarreOutils.add(new BarreOutils(), constraints );

		setJMenuBar( new BarreMenu() );
		add( new PanneauDessin());
		add( panelBarreOutils, BorderLayout.NORTH);

		placerFenetre();
	}

	private void placerFenetre() {
		int hauteur = getHeight();
		int largeur = getWidth();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		// v�rifier la hauteur de la fen�tre par rapport � l'�cran
		if ( getHeight() > screenHeight )
			hauteur = screenHeight;
		// v�rifier la largeur de la fen�tre par rapport � l'�cran
		if ( getWidth() > screenWidth )
			largeur = screenWidth;
		// fixer la taille de la fen�tre
		setSize( largeur, hauteur );
		// positionner la fen�tre au centre de l'�cran
		setLocationRelativeTo( null );
	}

	public void changerTitre( String nom ) {
		nomFichier = nom;
		this.setTitle( nomFichier + "- GnG not Gimp" );
	}
}