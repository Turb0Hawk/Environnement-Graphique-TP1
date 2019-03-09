package GnG;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfacePrincipale extends JFrame {


	public static Forme formeCourrante;
	public static Color contourCourrant;
	public static Color remplissageCourrant;
	private static final long serialVersionUID = 1L;
	private static String nomFichier = "Untitled";
	ImageIcon iconeFenetre = new ImageIcon( InterfacePrincipale.class.getResource( "Images\\icon.png" ) );
	JPanel panelBarreOutils = new JPanel(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	
	public static Forme getFormeCourrante() {
		return formeCourrante;
	}

	public static void setFormeCourrante( Forme formeCourrante ) {
		InterfacePrincipale.formeCourrante = formeCourrante;
	}

	public static Color getContourCourrant() {
		return contourCourrant;
	}

	public static void setContourCourrant( Color contourCourrant ) {
		InterfacePrincipale.contourCourrant = contourCourrant;
	}

	public static Color getRemplissageCourrant() {
		return remplissageCourrant;
	}

	public static void setRemplissageCourrant( Color remplissageCourrant ) {
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
		// vérifier la hauteur de la fenêtre par rapport à l'écran
		if ( getHeight() > screenHeight )
			hauteur = screenHeight;
		// vérifier la largeur de la fenêtre par rapport à l'écran
		if ( getWidth() > screenWidth )
			largeur = screenWidth;
		// fixer la taille de la fenêtre
		setSize( largeur, hauteur );
		// positionner la fenêtre au centre de l'écran
		setLocationRelativeTo( null );
	}

	public void changerTitre( String nom ) {
		nomFichier = nom;
		this.setTitle( nomFichier + "- GnG not Gimp" );
	}
}