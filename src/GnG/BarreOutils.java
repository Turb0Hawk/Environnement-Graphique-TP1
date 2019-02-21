package GnG;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class BarreOutils extends JToolBar {
	JButton btnOvale = new JButton();
	JButton btnRectangle = new JButton();
	JButton btnTrait = new JButton();
	ImageIcon iconeOvale = new ImageIcon( BarreOutils.class.getResource( "Images\\ovale.png" ) );
	ImageIcon iconeRectangle = new ImageIcon( BarreOutils.class.getResource( "Images\\rectangle.png" ) );
	ImageIcon iconeTrait = new ImageIcon( BarreOutils.class.getResource( "Images\\trait.png" ) );
	
	JButton btnCouleur1 = new JButton();
	JButton btnCouleur2 = new JButton();
	JButton btnCouleur3 = new JButton();
	JButton btnCouleur4 = new JButton();
	JButton btnCouleur5 = new JButton();
	JButton btnCouleur6 = new JButton();
	public BarreOutils() {
		super();
		setFloatable( false );
		setOrientation( SwingConstants.HORIZONTAL );
		btnOvale.setIcon( iconeOvale );
		btnRectangle.setIcon( iconeRectangle );
		btnTrait.setIcon( iconeTrait );
		btnCouleur1.setBackground( Color.decode( "#ffcfea" ) );
		btnCouleur2.setBackground( Color.decode( "#feffbe" ) );
		btnCouleur3.setBackground( Color.decode( "#cbffe6" ) );
		btnCouleur4.setBackground( Color.decode( "#afe9ff" ) );
		btnCouleur5.setBackground( Color.decode( "#bfb9ff" ) );
		btnCouleur6.setBackground( Color.BLACK);
		btnCouleur1.setSize( 32, 32 );
		btnCouleur2.setSize( 32, 32 );
		btnCouleur3.setSize( 32, 32 );
		btnCouleur4.setSize( 32, 32 );
		btnCouleur5.setSize( 32, 32 );
		btnCouleur6.setSize( 32, 32 );
		add( btnTrait );
		add( btnRectangle );
		add( btnOvale );
		addSeparator();
		add( btnCouleur1 );
		add( btnCouleur2 );
		add( btnCouleur3 );
		add( btnCouleur4 );
		add( btnCouleur5 );
		add( btnCouleur6 );
	}
}
