package mazeGamme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MazeGameGUI extends JFrame implements ActionListener{
	
	private JPanel mazePanel;
	private JPanel keepbuttons;
	private JButton[][] mazecells;
	private MazeSolver MazeSolver;
	

	private boolean startpoint,finishpoint,wallspoint;
	int mazeSize;
	
	public MazeGameGUI(int userEnter) {
		mazeSize = userEnter;
		setTitle("YOUR MAZE GROUND");
		MazeSolver = new MazeSolver();
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//define a panel to add the cells :
		
		mazePanel = new JPanel(new GridLayout(mazeSize,mazeSize));
		createMaze();
		
		//create buttons (start,reset,solve):
		
		JButton start = new InfoButton("START POINT",this,Color.cyan);
		JButton finish = new InfoButton("FINISH POINT",this,Color.cyan);
		JButton walls = new InfoButton("BUILD WALLS",this,Color.cyan);
		JButton solve = new InfoButton("SOLVE",this,Color.cyan);
		JButton reset = new InfoButton("RESET",this,Color.cyan);
		
		keepbuttons = new JPanel();
		keepbuttons.add(start);
		keepbuttons.add(finish);
		keepbuttons.add(walls);
		keepbuttons.add(solve);
		keepbuttons.add(reset);
		getContentPane().add(keepbuttons, BorderLayout.SOUTH);
	
		
		
		setVisible(true);
	}
	private void createMaze() {
		mazecells = new JButton[mazeSize][mazeSize];
		for(int i = 0;i < mazeSize; i++) {
			for(int j= 0 ;j < mazeSize;j++) {
				JButton cell = new JButton();
				cell.setBackground(Color.white);
				cell.setPreferredSize(new Dimension(50,50));
				cell.addActionListener(this);
				mazecells[i][j] = cell;
				mazePanel.add(cell);
				getContentPane().add(mazePanel, BorderLayout.CENTER);
				
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText().equals("START POINT")) {
			startpoint = true;}
		else {
			new MazeCellActionHandler(mazecells, e, startpoint, Color.GREEN);
			startpoint = false;	
		}
		//////////////////////////////////////////////////////////////////
		
		if(((JButton)e.getSource()).getText().equals("FINISH POINT")) {
			finishpoint = true;		
		}
		else {
			new MazeCellActionHandler(mazecells, e, finishpoint, Color.pink);
			finishpoint = false;
					
			}
		
		////////////////////////////////////////////////////////////////////

		if (((JButton) e.getSource()).getText().equals("BUILD WALLS")) {
			wallspoint = true;
		}
		else {
			if(wallspoint) {
				new MazeCellActionHandler(mazecells, e, wallspoint, Color.darkGray);
					
			}
		}
		
		//////////////////////////////////////////////////////////////////////
		if (((JButton) e.getSource()).getText().equals("RESET")) {
			for(int i = 0;i < mazeSize; i++) {
				for(int j= 0 ;j < mazeSize;j++) {
					mazecells[i][j].setBackground(Color.WHITE);}
									        
				}
		
}
		if (((JButton) e.getSource()).getText().equals("SOLVE")) {
			List<Point> path = MazeSolver.solveMaze(mazecells, mazeSize); // Solve the maze
			if (path.isEmpty()) {
                JOptionPane.showMessageDialog(this, "There is no path from the start point"
                		+ " to the finish point.", "No Path", JOptionPane.WARNING_MESSAGE);
            } else {
            highlightPath(path);} // Highlight the path
		   
		}
		
		
	}
	private void highlightPath(List<Point> path) {
		
			for (Point point : path) {
				mazecells[point.x][point.y].setBackground(Color.yellow);}
		
		
        startpoint = false;
		finishpoint = false;
		wallspoint = false;
	
}
}