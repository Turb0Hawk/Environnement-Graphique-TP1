package GnG;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import outilsjava.*;

/**
 * Fichier BarreMenu.java Description de la classe: Classe qui construit notre
 * barre de menu
 * 
 * @author Nicolas Parr, David Ringuet
 * @Date: 15/02/2019
 * @version 3
 */
public class BarreMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2L;

	private JMenu menuFichier = new JMenu( "Fichier" );
	private JMenuItem menuPropos = new JMenuItem( "ï¿½propos" );
	private JMenuItem itemNouv = new JMenuItem( "Nouvelle image", UIManager.getIcon( "FileView.fileIcon" ) );
	private JMenuItem itemSauv = new JMenuItem( "Enregister", UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemSauvSous = new JMenuItem( "Enregistrer sous...",
			UIManager.getIcon( "FileView.floppyDriveIcon" ) );
	private JMenuItem itemOuv = new JMenuItem( "Ouvrir fichier...", UIManager.getIcon( "FileView.directoryIcon" ) );
	private JMenuItem itemQuit = new JMenuItem( "Quitter" );
	private PanneauDessin panneau;

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

		menuPropos.addActionListener( this );
		menuPropos.setPreferredSize( new Dimension( 200, this.getPreferredSize().height ) );

		this.add( menuFichier );
		this.add( menuPropos );

		itemNouv.addActionListener( this );
		itemSauv.addActionListener( this );
		itemSauvSous.addActionListener( this );
		itemOuv.addActionListener( this );
		itemQuit.addActionListener( this );

	}
//TODO fix gng.gng problem,
//TODO fix quand on overwrite ça le demande dans la console
//TODO fix quand on overwrite c'est vide
	public void actionPerformed( ActionEvent e ) {
		JFileChooser choixFichier = new JFileChooser();
		FileNameExtensionFilter filtreGng = new FileNameExtensionFilter( "Not Gimp files *.gng", "gng" );
		choixFichier.addChoosableFileFilter( filtreGng );
		choixFichier.setFileFilter( filtreGng );

		if ( e.getSource() == itemNouv ) {
			panneau.getFormes().clear();
			panneau.paintComponent( panneau.getGraphics() );
			panneau.setFichierCourant( "" );
			panneau.getFrame().setName( "Untitled" );

		} else if ( e.getSource() == itemSauvSous
				|| ( e.getSource() == itemSauv && panneau.getFichierCourant() == "" ) ) {

			ObjectOutputStream fic;
			if ( choixFichier.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ) {

				if ( ( fic = OutilsFichier.ouvrirFicBinEcriture(
						choixFichier.getSelectedFile().getAbsolutePath() + ".gng" ) ) != null ) {

					try {
						fic.writeInt( panneau.getFormes().size() );
						for ( Forme forme : panneau.getFormes() ) {
							forme.writeObject( fic );
						}
						OutilsFichier.fermerFicBinEcriture( fic,
								choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
						panneau.getFrame().setName( choixFichier.getSelectedFile().getName() );
						panneau.setFichierCourant( choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
					} catch ( IOException e1 ) {
						System.out.println(
								"Problï¿½me d'ï¿½criture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur de sauvegarde est survenue",
								"Erreur de sauvegarde", JOptionPane.ERROR_MESSAGE );
					}
				}
			}

		} else if ( e.getSource() == itemSauv ) {

			ObjectOutputStream fic;
			if ( ( fic = OutilsFichier.ouvrirFicBinEcriture( panneau.getFichierCourant() ) ) != null ) {

				try {
					for ( Forme forme : panneau.getFormes() ) {
						forme.writeObject( fic );
					}
					OutilsFichier.fermerFicBinEcriture( fic, panneau.getFichierCourant() );
				} catch ( IOException e1 ) {
					System.out
							.println( "Problï¿½me d'ï¿½criture du fichier " + panneau.getFrame().getName() );
					JOptionPane.showMessageDialog( this, "Une Erreur de sauvegarde est survenue",
							"Erreur de sauvegarde", JOptionPane.ERROR_MESSAGE );
				}
			}

		} else if ( e.getSource() == itemOuv ) {

			ObjectInputStream fic;
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {

				if ( ( fic = OutilsFichier
						.ouvrirFicBinLecture( choixFichier.getSelectedFile().getAbsolutePath() ) ) != null ) {
					try {
						// panneau.setFormes( (ArrayList) fic.readObject() );
						panneau.getFormes().clear();
						int nbFormes = fic.readInt();
						for ( int i = 0; i < nbFormes; ++i ) {
							Forme temp;
							switch ( fic.readInt() ) {
							case 0:
								temp = new Trait();
								break;
							case 1:
								temp = new Rectangle();
								break;
							case 2:
								temp = new Ovale();
								break;
							default:
								throw new IOException();
							}
							temp.readObject( fic );
							panneau.getFormes().add( temp );
							panneau.repaint();
						}
						/*
						 * for ( Forme forme : panneau.getFormes() ) {
						 * forme.readObject( fic ); }
						 */
						OutilsFichier.fermerFicBinLecture( fic,
								choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
						panneau.getFrame().setName( choixFichier.getSelectedFile().getName() );
						panneau.setFichierCourant( choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );

					} catch ( IOException e2 ) {

						System.out.println(
								"Problï¿½me de lecture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur  est survenue lors de l'ouverture du fichier",
								"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE );
					} catch ( ClassNotFoundException e1 ) {

						System.out.println(
								"Problï¿½me de lecture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur  est survenue lors de l'ouverture du fichier",
								"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE );
					}
				}
			}

		} else if ( e.getSource() == itemQuit ) {
			System.exit( 0 );
		} else if ( e.getSource() == menuPropos ) {
			// TODO le menu ï¿½propos
			JOptionPane.showMessageDialog( this,
					"GnG : GnG not Gimp \nCrï¿½ï¿½ par: Nicolas Parr & David Ringuet\n Version: 3.0", "ï¿½ propos",
					JOptionPane.INFORMATION_MESSAGE );
		}
	}
}