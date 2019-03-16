package GnG;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BarreMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2L;

	private JMenu menuFichier = new JMenu( "Fichier" );
	private JMenu menuPropos = new JMenu( "À propos" );
	private JMenuItem itemNouv = new JMenuItem( "Nouvelle image", UIManager.getIcon( "FileView.fileIcon" ) );
	private JMenuItem itemSauv = new JMenuItem( "Enregister", UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemSauvSous = new JMenuItem( "Enregistrer sous...",
			UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemOuv = new JMenuItem( "Ouvrir fichier...", UIManager.getIcon( "FileView.directoryIcon" ) );
	private JMenuItem itemQuit = new JMenuItem( "Quitter" );
	private PanneauDessin panneau;
	public static JFileChooser choixFichier;

	public BarreMenu( JPanel pan ) {
		super();
		this.panneau = (PanneauDessin) pan;

		itemNouv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK ) );
		itemSauv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK ) );
		itemOuv.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK ) );

		menuFichier.add( itemNouv );
		menuFichier.add( itemSauv );
		menuFichier.add( itemSauvSous );
		menuFichier.add( itemOuv );
		menuFichier.addSeparator();
		menuFichier.add( itemQuit );

		this.add( menuFichier );
		this.add( menuPropos );

		itemNouv.addActionListener( this );
		itemSauv.addActionListener( this );
		itemSauvSous.addActionListener( this );
		itemOuv.addActionListener( this );
		itemQuit.addActionListener( this );
	}

	public void actionPerformed( ActionEvent e ) {
		JFileChooser choixFichier = new JFileChooser();
		FileNameExtensionFilter filtreGng = new FileNameExtensionFilter( "Not Gimp files *.gng", "gng" );
		choixFichier.addChoosableFileFilter( filtreGng );
		choixFichier.setFileFilter( filtreGng );

		if ( e.getSource() == itemNouv ) {
			panneau.getFormes().clear();
			panneau.paintComponent( panneau.getGraphics() );

		} else if ( e.getSource() == itemSauv ) {
			if ( choixFichier.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				// TODO
				// panneau.sauvegarderFic( false, choixFichier.getSelectedFile()
				// );
			}
		} else if ( e.getSource() == itemSauvSous ) {
			// TODO
			// panneau.sauvegarderFic( true, choixFichier.getSelectedFile() );

		} else if ( e.getSource() == itemOuv ) {
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				// TODO
				// panneau.ouvrirFic( choixFichier.getSelectedFile() );
			}

		} else if ( e.getSource() == itemQuit ) {
			System.exit( 0 );
		} else if ( e.getSource() == menuPropos ) {
			// TODO le menu à propos
		}
	}
}