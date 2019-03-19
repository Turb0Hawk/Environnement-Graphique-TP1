package GnG;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import outilsjava.OutilsLecture;

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

	public void actionPerformed( ActionEvent e ) {
		JFileChooser choixFichier = new JFileChooser();
		FileNameExtensionFilter filtreGng = new FileNameExtensionFilter( "Not Gimp files *.gng", "gng" );
		choixFichier.addChoosableFileFilter( filtreGng );
		choixFichier.setFileFilter( filtreGng );

		if ( e.getSource() == itemNouv ) {
			panneau.getFormes().clear();
			panneau.paintComponent( panneau.getGraphics() );
			panneau.setFichierCourant( "" );
			panneau.getFrame().changerTitre( "Untitled" );

		} else if ( e.getSource() == itemSauvSous
				|| ( e.getSource() == itemSauv && panneau.getFichierCourant() == "" ) ) {

			ObjectOutputStream fic;
			if ( choixFichier.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ) {

				if ( ( fic = ouvrirFileWrite( choixFichier.getSelectedFile().getAbsolutePath() + ".gng",
						true ) ) != null ) {

					try {
						fic.writeInt( panneau.getFormes().size() );
						for ( Forme forme : panneau.getFormes() ) {
							forme.writeObject( fic );
						}
						fermerFile( fic, choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
						panneau.getFrame().changerTitre( choixFichier.getSelectedFile().getName() );
						panneau.setFichierCourant( choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
					} catch ( IOException e1 ) {
						System.out.println(
								"Problème d'écriture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur de sauvegarde est survenue",
								"Erreur de sauvegarde", JOptionPane.ERROR_MESSAGE );
					}
				}
			}

		} else if ( e.getSource() == itemSauv ) {

			ObjectOutputStream fic;
			if ( ( fic = ouvrirFileWrite( panneau.getFichierCourant(), false ) ) != null ) {

				try {
					for ( Forme forme : panneau.getFormes() ) {
						forme.writeObject( fic );
					}
					fermerFile( fic, panneau.getFichierCourant() );
				} catch ( IOException e1 ) {
					System.out.println( "Probléme d'ècriture du fichier " + panneau.getFrame().getName() );
					JOptionPane.showMessageDialog( this, "Une Erreur de sauvegarde est survenue",
							"Erreur de sauvegarde", JOptionPane.ERROR_MESSAGE );
				}
			}

		} else if ( e.getSource() == itemOuv ) {

			ObjectInputStream fic;
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {

				if ( ( fic = ouvrirFileRead( choixFichier.getSelectedFile().getAbsolutePath() ) ) != null ) {
					try {
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
						fermerFile( fic, choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );
						panneau.getFrame().changerTitre( choixFichier.getSelectedFile().getName() );
						panneau.setFichierCourant( choixFichier.getSelectedFile().getAbsolutePath() + ".gng" );

					} catch ( IOException e2 ) {

						System.out.println(
								"Probléme de lecture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur  est survenue lors de l'ouverture du fichier",
								"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE );
					} catch ( ClassNotFoundException e1 ) {

						System.out.println(
								"Problème de lecture du fichier " + choixFichier.getSelectedFile().getName() );
						JOptionPane.showMessageDialog( this, "Une Erreur  est survenue lors de l'ouverture du fichier",
								"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE );
					}
				}
			}

		} else if ( e.getSource() == itemQuit ) {
			System.exit( 0 );
		} else if ( e.getSource() == menuPropos ) {
			JOptionPane.showMessageDialog( this,
					"GnG : GnG not Gimp \nCréé par: Nicolas Parr & David Ringuet\n Version: 3.0", "À propos",
					JOptionPane.INFORMATION_MESSAGE );
		}
	}

	private void fermerFile( ObjectOutputStream fic, String fichierCourant ) {
		try {
			fic.close();
		} catch ( IOException erreur ) {
			JOptionPane.showMessageDialog( this,
					"Une Erreur est survenue lors de la fermeture du fichier" + fichierCourant, "Erreur de fermeture",
					JOptionPane.ERROR_MESSAGE );
		}

	}

	private void fermerFile( ObjectInputStream fic, String fichierCourant ) {
		try {
			fic.close();
		} catch ( IOException erreur ) {
			JOptionPane.showMessageDialog( this,
					"Une Erreur est survenue lors de la fermeture du fichier" + fichierCourant, "Erreur de fermeture",
					JOptionPane.ERROR_MESSAGE );
		}
	}

	private ObjectOutputStream ouvrirFileWrite( String nomFile, boolean saveAs ) {
		boolean valide = true;
		Path chemin = null;
		String absoluteChemin = "";// not drinkable lemayo
		ObjectOutputStream file = null;

		try {
			chemin = Paths.get( nomFile );
		} catch ( InvalidPathException errNomFichier ) {
			JOptionPane.showMessageDialog( this, "Erreur le nom de fichier contient des caractères invalides",
					"Erreur d'écriture", JOptionPane.ERROR_MESSAGE );
			valide = false;
		}

		// plusieurs checks pour être vraiment suer que sa foire pas
		if ( valide ) {
			if ( valide ) {
				absoluteChemin = chemin.toAbsolutePath().toString();

				if ( Files.notExists( chemin ) ) {
					valide = true;

				} else if ( Files.exists( chemin ) ) {
					if ( !Files.isRegularFile( chemin ) ) {
						JOptionPane.showMessageDialog( this,
								"\nErreur, le fichier " + absoluteChemin + " n'est pas un fichier supporté par GnG .",
								"Erreur d'écriture", JOptionPane.ERROR_MESSAGE );
						valide = false;
					} else {
						if ( !Files.isWritable( chemin ) ) {
							valide = false;
						} else {

							if ( saveAs ) {
								valide = ( JOptionPane.showConfirmDialog( this,
										"Le fichier  \"" + nomFile
												+ "\" contient déjà des données, voulez-vous l'écraser ?",
										"Confirmation pour écraser",
										JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION );
							}
						}
					}
				} else {
					valide = false;
				}
			}

			if ( valide ) {
				try {
					file = new ObjectOutputStream( new FileOutputStream( nomFile ) );
				} catch ( IOException errIO ) {
					JOptionPane.showMessageDialog( this,
							"Une Erreur est survenue lors de l'écriture du fichier" + nomFile, "Erreur d'écriture",
							JOptionPane.ERROR_MESSAGE );
					valide = false;
				}
			}
		}
		return file;
	}

	private ObjectInputStream ouvrirFileRead( String cheminFile ) {
		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu;
		ObjectInputStream file = null;

		// Création du chemin.
		try {
			chemin = Paths.get( cheminFile );
		} catch ( InvalidPathException errNomFichier ) {
			JOptionPane.showMessageDialog( this, "Erreur le nom de fichier contient des caractères invalides",
					"Erreur de lecture", JOptionPane.ERROR_MESSAGE );
			valide = false;
		}

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();
			if ( Files.notExists( chemin ) ) {
				System.out.println( "\nErreur, le fichier " + cheminAbsolu + " n'existe pas." );
				valide = false;

			} else if ( Files.exists( chemin ) ) {
				if ( !Files.isRegularFile( chemin ) ) {

					JOptionPane.showMessageDialog( this,
							"\nErreur, le fichier " + cheminAbsolu + " n'est pas un fichier supporté par GnG .",
							"Erreur d'écriture", JOptionPane.ERROR_MESSAGE );
					valide = false;
				} else {

					if ( !Files.isReadable( chemin ) ) {

						System.out.println( "\nErreur, le fichier " + cheminAbsolu + " n'est pas permis en lecture." );
						valide = false;
					} else {

						try {
							file = new ObjectInputStream( new FileInputStream( cheminFile ) );
						} catch ( IOException errIO ) {
							System.out.println( "\nErreur, impossible d'ouvrir " + "le fichier " + cheminAbsolu
									+ " en mode lecture binaire." );
							valide = false;
						}
					}
				}
			} else {
				System.out.println(
						"\nErreur, impossible de vérifier " + "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}
		return file;
	}
}