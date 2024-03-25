package mazeGamme;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class MazeCellActionHandler {
	public MazeCellActionHandler(JButton[][] mazecells,ActionEvent e,boolean point,Color color) {
		int mazeSize = mazecells.length;
		if(point) {
			for(int i = 0;i < mazeSize; i++) {
				for(int j= 0 ;j < mazeSize;j++) {
					if((e.getSource() == mazecells[i][j])) {
						((JButton) e.getSource()).setBackground(color);
						point = false;
					}
					
					
					
				}
				
				
			}
			
		
	}
	}
	

}
