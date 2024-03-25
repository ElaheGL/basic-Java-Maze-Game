package mazeGamme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class InfoButton extends JButton {
	public InfoButton(String title,ActionListener action,Color backGround) {
		setText(title);
		addActionListener(action);
		setBackground(backGround);	
		
	}

}
