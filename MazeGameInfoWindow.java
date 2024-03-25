package mazeGamme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MazeGameInfoWindow extends JFrame implements ActionListener {
	private JLabel welcome;
	private JPanel btns,labels;
	

	public MazeGameInfoWindow() {
		setLayout(new BorderLayout());
		setTitle("Maze Game Info");
		setSize(400,180);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Info Buttons:
		JButton instructionBtn = new InfoButton("instruction",this,Color.magenta);
		add(instructionBtn);
		JButton chooseMazeSizeBtn = new InfoButton("Choose Maze Size",this,Color.orange);
		add(chooseMazeSizeBtn); 
		btns = new JPanel();
		btns.add(instructionBtn);
		btns.add(chooseMazeSizeBtn);
		add(btns,BorderLayout.SOUTH);
		labels = new JPanel();
		welcome = new JLabel("Wlcome to Maze Game");
		welcome.setFont(new Font("Arial", Font.BOLD,30));
		
		
		labels.add(welcome);
		add(labels,BorderLayout.NORTH);
		
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		MazeGameInfoWindow form = new MazeGameInfoWindow();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int userEnter;
		if(((InfoButton)e.getSource()).getText().equals("instruction")) 
			JOptionPane.showMessageDialog(this, "1: maximize your maze ground window \n2: choose the start point"
					+ "\n3:choose the finish point\n4: Build your walls and click on solve Button",
					"Info Page",JOptionPane.INFORMATION_MESSAGE);
		if(((InfoButton)e.getSource()).getText().equals("Choose Maze Size")) {
			try {
				userEnter = Integer.parseInt(JOptionPane.showInputDialog
						(null,"Enter your size \ninfo: 10 means 10 * 10","selection Size",JOptionPane.DEFAULT_OPTION));
				new MazeGameGUI(userEnter);
			}
			catch (Exception e2) {
				JOptionPane.showMessageDialog
				(this, "Please enter a valid number","Invalid Number",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	

}
}
