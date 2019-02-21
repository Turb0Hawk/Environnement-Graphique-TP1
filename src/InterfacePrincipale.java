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
}