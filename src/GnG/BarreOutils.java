package GnG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BarreOutils extends JToolBar implements ActionListener {
	private static final long serialVersionUID = 1407475899641651758L;

	JToggleButton btnOvale = new JToggleButton();
	JToggleButton btnRectangle = new JToggleButton();
	JToggleButton btnTrait = new JToggleButton();
	JToggleButton btnVide = new JToggleButton();

	ImageIcon iconeOvale = new ImageIcon( BarreOutils.class.getResource( "Images\\ovale.png" ) );
	ImageIcon iconeRectangle = new ImageIcon( BarreOutils.class.getResource( "Images\\rectangle.png" ) );
	ImageIcon iconeTrait = new ImageIcon( BarreOutils.class.getResource( "Images\\trait.png" ) );
	ImageIcon iconeVide = new ImageIcon( BarreOutils.class.getResource( "Images\\none.png" ) );
	ImageIcon iconeVideSel = new ImageIcon( BarreOutils.class.getResource( "Images\\nonePressed.png" ) );

	JPanel panelRemplissage = new JPanel();
	JPanel panelTrait = new JPanel();

	ButtonGroup groupeForme = new ButtonGroup();
	ButtonGroup groupeRemplissage = new ButtonGroup();
	ButtonGroup groupeTrait = new ButtonGroup();

	JToggleButton[] btnsCouleurRemplissage = new JToggleButton[6];
	JToggleButton[] btnsCouleurTrait = new JToggleButton[6];
	public static String[] couleurs = { "#000000", "#ffcfea", "#feffbe", "#cbffe6", "#afe9ff", "#bfb9ff" };
	public static String[] nomCouleurs = { "noir noirci", "rose songes d'été", "jaune canari", "vaporGreen TM",
			"blue A E S T H E T I C", "fushia dépressif" };

	public BarreOutils() {
		super();
		setFloatable( false );
		setOrientation( SwingConstants.HORIZONTAL );

		btnOvale.setIcon( iconeOvale );
		btnOvale.setToolTipText( "Outil pour dessiner des Ovales et des cercles" );

		btnRectangle.setIcon( iconeRectangle );
		btnRectangle.setToolTipText( "Outil pour dessiner des rectangles et des carrés" );

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

	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnTrait ) {
			InterfacePrincipale.setFormeCourrante( 0 );
		}

		if ( e.getSource() == btnRectangle ) {
			InterfacePrincipale.setFormeCourrante( 2 );
		}

		if ( e.getSource() == btnOvale ) {
			InterfacePrincipale.setFormeCourrante( 1 );
		}

		if ( e.getSource() == btnVide ) {
			InterfacePrincipale.setRemplissageCourrant( -1 );
		}

		for ( int i = 0; i < btnsCouleurRemplissage.length; ++i ) {
			if ( e.getSource() == btnsCouleurRemplissage[i] ) {
				InterfacePrincipale.setRemplissageCourrant( i );
				break;
			}
		}

		for ( int i = 0; i < btnsCouleurTrait.length; ++i ) {
			if ( e.getSource() == btnsCouleurTrait[i] ) {
				InterfacePrincipale.setContourCourrant( i );
				break;
			}
		}
	}
}