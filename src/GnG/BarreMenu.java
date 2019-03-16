package GnG;

import java.awt.event.*;
import javax.swing.*;

/**
 * @author Dave Nicolas Parr, David Ringuet 
 * @Date: 15/02/2019 
 * @version 3
 * Fichier BarreMenu.java
 * Description de la classe: Classe qui construit notre barre de menu
 */
public class BarreMenu extends JMenuBar {

	private static final long serialVersionUID = 2L;

	public BarreMenu() {
		super();

		JMenu menuFichier = new JMenu( "Fichier" );
		JMenu menuPropos = new JMenu( "� propos" );

		JMenuItem itemNouv = new JMenuItem( "Nouvelle image", UIManager.getIcon( "FileView.fileIcon" ) );
		itemNouv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK ) );
		JMenuItem itemSauv = new JMenuItem( "Enregister", UIManager.getIcon( "FileView.floppyDriveIcon" ) );
		itemSauv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK ) );
		JMenuItem itemSauvSous = new JMenuItem( "Enregistrer sous...",
				UIManager.getIcon( "FileView.floppyDriveIcon" ) );
		JMenuItem itemOuv = new JMenuItem( "Ouvrir fichier...", UIManager.getIcon( "FileView.directoryIcon" ) );
		itemOuv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK ) );
		JMenuItem itemQuit = new JMenuItem( "Quitter" );

		menuFichier.add( itemNouv );
		menuFichier.add( itemSauv );
		menuFichier.add( itemSauvSous );
		menuFichier.add( itemOuv );
		menuFichier.addSeparator();
		menuFichier.add( itemQuit );

		this.add( menuFichier );
		this.add( menuPropos );
	}
}