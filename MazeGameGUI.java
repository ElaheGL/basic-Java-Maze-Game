package mazeGamme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MazeGameGUI extends JFrame implements ActionListener{
	
	private JPanel mazePanel;
	private JPanel keepbuttons;
	private JButton[][] mazecells;
	private MazeSolver MazeSolver;
	private JComboBox<String> speedBox;
	

	private boolean startpoint,finishpoint,wallspoint,autoWall;
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
		JButton autowalls = new InfoButton("Atomatic WALLS",this,Color.cyan);
		JButton reset = new InfoButton("RESET",this,Color.cyan);
	    speedBox = new JComboBox<>(new String[] {"Fast Solve","Normal Solve"
				,"Slow Solve"});
		speedBox.setBackground(Color.cyan);
		speedBox.addActionListener(new ComboBoxListener());
		
		keepbuttons = new JPanel();
		keepbuttons.add(start);
		keepbuttons.add(finish);
		keepbuttons.add(walls);
		keepbuttons.add(autowalls);
		keepbuttons.add(speedBox);
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
		
	 //////////////////////////////////////////////////////////////////////////////  
		if (((JButton) e.getSource()).getText().equals("Atomatic WALLS")) {
			autoWall = true;
			int numWalls = 20; 
		    MazeSolver.createRandomWalls(mazecells, mazeSize, numWalls);
		    autoWall = false;
		    }
		
	    }

    ///////////////////////////////////////////////////////////////////////////////////////	
	private void solveMazeWithDelay(int delay) {
		List<Point> path = MazeSolver.solveMaze(mazecells, mazeSize); // Solve the maze
		if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There is no path from the start point"
            		+ " to the finish point.", "No Path", JOptionPane.WARNING_MESSAGE);
        }
			else {
				highlightPath(path,delay);} // Highlight the path
	}
		
	
	
	private void highlightPath(List<Point> path, int delay) {
	    List<Point> pathCopy = new ArrayList<>(path);

	    javax.swing.Timer timer = new javax.swing.Timer(delay, new ActionListener() {
	        int currentIndex = 0;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (currentIndex < pathCopy.size()) {
	                Point point = pathCopy.get(currentIndex);
	                mazecells[point.x][point.y].setBackground(Color.yellow);
	                currentIndex++;
	            } else {
	                ((javax.swing.Timer) e.getSource()).stop();
	            }
	        }
	    });

	    timer.start();
		    
	
	startpoint = false;
	finishpoint = false;
	wallspoint = false; 
	autoWall = false;
	
	
	}
	 private class ComboBoxListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
	            String selectedItem = (String) comboBox.getSelectedItem();
	            
	            switch (selectedItem) {
	                case "Fast Solve":
	                    solveMazeWithDelay(200);
	                    break;
	                case "Normal Solve":
	                    solveMazeWithDelay(800);
	                    break;
	                case "Slow Solve":
	                    solveMazeWithDelay(1000);
	                    break;
	                default:
	                    break;
	            }
	        }
	    }
	
}