package GnG;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.nio.file.*;
import java.io.File;

public class BarreMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2L;

	private JMenu menuFichier = new JMenu( "Fichier" );
	private JMenu menuPropos = new JMenu( "� propos" );
	private JMenuItem itemNouv = new JMenuItem( "Nouvelle image", UIManager.getIcon( "FileView.fileIcon" ) );
	private JMenuItem itemSauv = new JMenuItem( "Enregister", UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemSauvSous = new JMenuItem( "Enregistrer sous...",
			UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemOuv = new JMenuItem( "Ouvrir fichier...", UIManager.getIcon( "FileView.directoryIcon" ) );
	private JMenuItem itemQuit = new JMenuItem( "Quitter" );

	public BarreMenu() {
		super();

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
			InterfacePrincipale.pan.ouvrirFic();
		} else if ( e.getSource() == itemSauv ) {
			InterfacePrincipale.pan.sauvegarderFic( false );
		} else if ( e.getSource() == itemSauvSous ) {
			InterfacePrincipale.pan.sauvegarderFic( true );
		} else if ( e.getSource() == itemOuv ) {
			File fic;
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				fic = choixFichier.getSelectedFile();
				InterfacePrincipale.pan.ouvrirFic( fic );
			}
		} else if ( e.getSource() == itemQuit ) {
			System.exit( 0 );
		}
	}

	/**
	 * @author Farida Classe interne de gestion d'�v�nement du menu fichier
	 *
	 */
	/*private class GestionFichier implements ActionListener {
		JFileChooser choixFichier;

		public GestionFichier() {
			choixFichier = new JFileChooser();
			// choix des filtres
			FileNameExtensionFilter filtreTexte = new FileNameExtensionFilter( "Fichiers textes *.txt", "txt" );
			FileNameExtensionFilter fichierWord = new FileNameExtensionFilter( "Documents Word ", "doc", "docx" );
			// ajouter le filtre aux bo�tes de dialogue
			choixFichier.addChoosableFileFilter( filtreTexte );
			choixFichier.addChoosableFileFilter( fichierWord );
			// choisir le filtre texte par d�faut, au lieu de tous les fichiers
			choixFichier.setFileFilter( filtreTexte );

		}

		@Override
		public void actionPerformed( ActionEvent e ) {
			if ( e.getSource() == optionEnregistrer )
				enregistrer();
			else if ( e.getSource() == optionOuvrir )
				ouvrir();
		}

		private void enregistrer() {
			// Afficher la bo�te de dialogue saveDialog
			if ( choixFichier.showSaveDialog( texte ) == JFileChooser.APPROVE_OPTION ) {// ouvre
																						// la
																						// bo�te
																						// dans
																						// le
																						// composant
																						// texte
				// r�cup�rer le nom du fichier
				File f = choixFichier.getSelectedFile();
				// enregistrer
			}
		}

		private void ouvrir() {
			// Afficher la bo�te de dialogue openDialog
			// JFileChooser choixFichier = new JFileChooser();
			if ( choixFichier.showOpenDialog( texte ) == JFileChooser.APPROVE_OPTION ) {
				// r�cup�rer le nom du fichier
				File f = choixFichier.getSelectedFile();
				// ouvrir
			}
		}

	}*/
}