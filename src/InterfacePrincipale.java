import java.awt.*;
import javax.swing.JFrame;

public class InterfacePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String nomFichier = "Untitled";

	public InterfacePrincipale() {
		super( nomFichier + "- GnG not Gimp" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		this.add( new BarreMenu(), BorderLayout.NORTH );
		
		this.add( new PanneauDessin() );
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
}