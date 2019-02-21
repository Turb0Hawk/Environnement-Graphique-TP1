package GnG;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class InterfacePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String nomFichier = "Untitled";
	ImageIcon iconeFenetre = new ImageIcon(InterfacePrincipale.class.getResource("Images\\icon.png"));
	BarreOutils toolbar = new BarreOutils();

	public InterfacePrincipale() {
		super( nomFichier + "- GnG not Gimp" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setIconImage( iconeFenetre.getImage()  );
		this.add( toolbar, BorderLayout.NORTH );
		
		setSize( new Dimension(1000,800) );
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

	public void changerTitre(String nom) {
		this.nomFichier = nom;
		this.setTitle( nomFichier + "- GnG not Gimp" ); 
	}
}
