package GnG;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Fichier InterfacePrincipale.java
 * Description de la classe: Classe contenant les instruction de
 * l'interface du programme GnG
 * @author Dave Nicolas Parr, David Ringuet 
 * @Date: 15/02/2019 
 * @version 3
 */
public class InterfacePrincipale extends JFrame {
	
	
	/**
	 * la forme courante
	 * 0 = trait, 1 = ovale, 2 = rectangle
	 */
	private static int formeCourrante = 0;
	/**
	 * la couleur de contour courante
	 */
	private static int contourCourrant = 0;
	/**
	 * la couleur de remplissage courante
	 */
	private static int remplissageCourrant = -1;
	private static final long serialVersionUID = 1L;
	private static String nomFichier = "Untitled";
	private ImageIcon iconeFenetre = new ImageIcon( InterfacePrincipale.class.getResource( "Images\\icon.png" ) );
	private JPanel panelBarreOutils = new JPanel( new GridBagLayout() );
	private GridBagConstraints constraints = new GridBagConstraints();
	private PanneauDessin pan;

	/**
	 * @return formeCourrante la forme courante:
	 * 0 = trait, 1 = ovale, 2 = rectangle
	 */
	public static int getFormeCourrante() {
		return formeCourrante;
	}

	/**
	 * @param forme 
	 * permet de changer la forme courrante choisis:  
	 * 0 = trait, 1 = ovale, 2 = rectangle
	 */
	public static void setFormeCourrante( int forme ) {
		if ( forme >= 0 && forme <= 2 ) {
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

	/**
	 * @param remplissageCourrant
	 */
	public static void setRemplissageCourrant( int remplissageCourrant ) {
		InterfacePrincipale.remplissageCourrant = remplissageCourrant;
	}

	public InterfacePrincipale() {
		super( nomFichier + " - GnG not Gimp" );

		constraints.gridx = 10;
		constraints.gridy = 3;
		setSize( new Dimension( 1000, 800 ) );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setIconImage( iconeFenetre.getImage() );

		panelBarreOutils.add( new BarreOutils(), constraints );

		add( pan = new PanneauDessin( this ) );
		setJMenuBar( new BarreMenu( pan ) );
		add( panelBarreOutils, BorderLayout.NORTH );

		placerFenetre();
	}

	/**
	 * petite m�thode qui permet de placer la fen�tre dans l'�cran
	 */
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

	/**
	 * @param nom
	 * m�thode publique pour pouvoir cahnger le nom de la fen�tre.
	 */
	public void changerTitre( String nom ) {
		nomFichier = nom;
		this.setTitle( nomFichier + " - GnG not Gimp" );
	}
}