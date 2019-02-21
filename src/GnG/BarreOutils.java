package GnG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class BarreOutils extends JToolBar {
	private static final long serialVersionUID = 1407475899641651758L;

	JButton btnOvale = new JButton();
	JButton btnRectangle = new JButton();
	JButton btnTrait = new JButton();
	JToggleButton btnVide = new JToggleButton();

	ImageIcon iconeOvale = new ImageIcon( BarreOutils.class.getResource( "Images\\ovale.png" ) );
	ImageIcon iconeRectangle = new ImageIcon( BarreOutils.class.getResource( "Images\\rectangle.png" ) );
	ImageIcon iconeTrait = new ImageIcon( BarreOutils.class.getResource( "Images\\trait.png" ) );
	ImageIcon iconeVide = new ImageIcon( BarreOutils.class.getResource( "Images\\none.png" ) );

	JPanel panelRemplissage = new JPanel();
	JPanel panelTrait = new JPanel();

	ButtonGroup groupeRemplissage = new ButtonGroup();
	ButtonGroup groupeTrait = new ButtonGroup();

	JToggleButton[] btnsRemplissage = new JToggleButton[6];
	JToggleButton[] btnsTrait = new JToggleButton[6];
	String[] couleurs = { "#000000", "#ffcfea", "#feffbe", "#cbffe6", "#afe9ff", "#bfb9ff" };
	String[] nomCouleurs = { "noir noirci", "rose songes d'été", "jaune canari", "vaporGreen TM",
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

		btnVide.setIcon( iconeVide );
		btnVide.setToolTipText( "Aucun Remplissage" );
		btnVide.setPreferredSize( new Dimension( 32, 32 ) );

		panelRemplissage.add( new JLabel( "Remplissage" ) );
		panelRemplissage.setSize( new Dimension( 300, 40 ) );

		panelTrait.add( new JLabel( "Trait" ) );
		panelTrait.setSize( new Dimension( 300, 40 ) );

		for ( int i = 0; i < couleurs.length; ++i ) {
			btnsRemplissage[i] = new JToggleButton();
			btnsRemplissage[i].setToolTipText( nomCouleurs[i] );
			btnsRemplissage[i].setBackground( Color.decode( couleurs[i] ) );
			btnsRemplissage[i].setPreferredSize( new Dimension( 32, 32 ) );
			groupeRemplissage.add( btnsRemplissage[i] );
			panelRemplissage.add( btnsRemplissage[i] );

			btnsTrait[i] = new JToggleButton();
			btnsTrait[i].setToolTipText( nomCouleurs[i] );
			btnsTrait[i].setBackground( Color.decode( couleurs[i] ) );
			btnsTrait[i].setPreferredSize( new Dimension( 32, 32 ) );
			groupeTrait.add( btnsTrait[i] );
			panelTrait.add( btnsTrait[i] );
		}
		groupeRemplissage.add( btnVide );
		panelRemplissage.add( btnVide );

		btnsRemplissage[1].setSelected( true );
		btnsTrait[1].setSelected( true );

		add( btnTrait );
		add( btnRectangle );
		add( btnOvale );
		addSeparator();
		add( panelTrait, BorderLayout.WEST );
		addSeparator();
		add( panelRemplissage, BorderLayout.WEST );

	}
}
