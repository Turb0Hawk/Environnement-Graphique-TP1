package GnG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Fichier BarreOutils.java 
 * Description de la classe: Classe pour construire la barre d'outils
 * @author Dave Nicolas Parr, David Ringuet
 * @Date: 15/02/2019
 * @version 3 
 */
public class BarreOutils extends JToolBar implements ActionListener {
	private static final long serialVersionUID = 1407475899641651758L;

	private JToggleButton btnOvale = new JToggleButton();
	private JToggleButton btnRectangle = new JToggleButton();
	private JToggleButton btnTrait = new JToggleButton();
	private JToggleButton btnVide = new JToggleButton();

	private ImageIcon iconeOvale = new ImageIcon( BarreOutils.class.getResource( "Images\\ovale.png" ) );
	private ImageIcon iconeRectangle = new ImageIcon( BarreOutils.class.getResource( "Images\\rectangle.png" ) );
	private ImageIcon iconeTrait = new ImageIcon( BarreOutils.class.getResource( "Images\\trait.png" ) );
	private ImageIcon iconeVide = new ImageIcon( BarreOutils.class.getResource( "Images\\none.png" ) );
	private ImageIcon iconeVideSel = new ImageIcon( BarreOutils.class.getResource( "Images\\nonePressed.png" ) );

	private JPanel panelRemplissage = new JPanel();
	private JPanel panelTrait = new JPanel();

	private ButtonGroup groupeForme = new ButtonGroup();
	private ButtonGroup groupeRemplissage = new ButtonGroup();
	private ButtonGroup groupeTrait = new ButtonGroup();

	private JToggleButton[] btnsCouleurRemplissage = new JToggleButton[6];
	private JToggleButton[] btnsCouleurTrait = new JToggleButton[6];

	private InterfacePrincipale frame;
	
	public static final String[] couleurs = { "#000000", "#ffcfea", "#feffbe", "#cbffe6", "#afe9ff", "#bfb9ff" };
	public static final String[] nomCouleurs = { "noir noirci", "rose songes d'�t�", "jaune canari", "vaporGreen TM",
			"blue A E S T H E T I C", "fushia d�pressif" };

	public BarreOutils(InterfacePrincipale frame) {
		super();
		setFloatable( false );
		setOrientation( SwingConstants.HORIZONTAL );
		this.frame = frame;

		btnOvale.setIcon( iconeOvale );
		btnOvale.setToolTipText( "Outil pour dessiner des Ovales et des cercles" );

		btnRectangle.setIcon( iconeRectangle );
		btnRectangle.setToolTipText( "Outil pour dessiner des rectangles et des carr�s" );

		btnTrait.setIcon( iconeTrait );
		btnTrait.setToolTipText( "Outil pour dessiner des traits" );

		groupeForme.add( btnOvale );
		groupeForme.add( btnRectangle );
		groupeForme.add( btnTrait );

		btnVide.setIcon( iconeVide );
		btnVide.setSelectedIcon( iconeVideSel );
		btnVide.setToolTipText( "Aucun Remplissage" );
		btnVide.setPreferredSize( new Dimension( 32, 32 ) );

		panelRemplissage.add( new JLabel( "Remplissage" ) );
		panelRemplissage.setSize( new Dimension( 300, 40 ) );

		panelTrait.add( new JLabel( "Trait" ) );
		panelTrait.setSize( new Dimension( 300, 40 ) );

		for ( int j = 0; j < couleurs.length; ++j ) {
			btnsCouleurRemplissage[j] = new JToggleButton();
			btnsCouleurRemplissage[j].setToolTipText( nomCouleurs[j] );
			btnsCouleurRemplissage[j].setBackground( Color.decode( couleurs[j] ) );
			btnsCouleurRemplissage[j].setPreferredSize( new Dimension( 32, 32 ) );
			groupeRemplissage.add( btnsCouleurRemplissage[j] );
			panelRemplissage.add( btnsCouleurRemplissage[j] );
			btnsCouleurRemplissage[j].addActionListener( this );

			btnsCouleurTrait[j] = new JToggleButton();
			btnsCouleurTrait[j].setToolTipText( nomCouleurs[j] );
			btnsCouleurTrait[j].setBackground( Color.decode( couleurs[j] ) );
			btnsCouleurTrait[j].setPreferredSize( new Dimension( 32, 32 ) );
			groupeTrait.add( btnsCouleurTrait[j] );
			panelTrait.add( btnsCouleurTrait[j] );
			btnsCouleurTrait[j].addActionListener( this );
		}
		groupeRemplissage.add( btnVide );
		panelRemplissage.add( btnVide );

		btnTrait.setSelected( true );
		btnsCouleurTrait[0].setSelected( true );
		btnVide.setSelected( true );

		add( btnTrait );
		add( btnRectangle );
		add( btnOvale );
		addSeparator();
		add( panelTrait, BorderLayout.WEST );
		addSeparator();
		add( panelRemplissage, BorderLayout.WEST );

		btnOvale.addActionListener( this );
		btnRectangle.addActionListener( this );
		btnTrait.addActionListener( this );
		btnVide.addActionListener( this );

	}

	/**
	 * m�thode interne pour  g�rer les �v�nements de la barre d'outils
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnTrait ) {
			frame.setFormeCourrante( 0 );
		}

		if ( e.getSource() == btnRectangle ) {
			frame.setFormeCourrante( 2 );
		}

		if ( e.getSource() == btnOvale ) {
			frame.setFormeCourrante( 1 );
		}

		if ( e.getSource() == btnVide ) {
			frame.setRemplissageCourrant( -1 );
		}

		for ( int i = 0; i < btnsCouleurRemplissage.length; ++i ) {
			if ( e.getSource() == btnsCouleurRemplissage[i] ) {
				frame.setRemplissageCourrant( i );
				break;
			}
		}

		for ( int i = 0; i < btnsCouleurTrait.length; ++i ) {
			if ( e.getSource() == btnsCouleurTrait[i] ) {
				frame.setContourCourrant( i );
				break;
			}
		}
	}
}