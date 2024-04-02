package mazeGamme;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;

public class MazeSolver {
	private int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; 

    public List<Point> solveMaze(JButton[][] mazeCells, int mazeSize) {
        Point start = null, finish = null;

    
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (mazeCells[i][j].getBackground().equals(Color.GREEN)) {
                    start = new Point(i, j);
                } else if (mazeCells[i][j].getBackground().equals(Color.pink)) {
                    finish = new Point(i, j);
                }
            }
        }

    
        return bfs(mazeCells, start, finish, mazeSize);
    }

    private List<Point> bfs(JButton[][] mazeCells, Point start, Point finish, int mazeSize) {
        Queue<Point> queue = new LinkedList<>();
        Point[][] parent = new Point[mazeSize][mazeSize];
        boolean[][] visited = new boolean[mazeSize][mazeSize];

        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(finish)) {
                return reconstructPath(parent, start, finish);
            }

            for (int[] direction : directions) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];
                Point neighbor = new Point(newX, newY);

                if (isValidMove(neighbor, mazeCells, mazeSize) && !visited[newX][newY]) {
                    queue.add(neighbor);
                    visited[newX][newY] = true;
                    parent[newX][newY] = current;
                }
            }
        }

        return new ArrayList<>();
    }

    private boolean isValidMove(Point point, JButton[][] mazeCells, int mazeSize) {
        int x = point.x;
        int y = point.y;
        return x >= 0 && x < mazeSize && y >= 0 && y < mazeSize &&
                !mazeCells[x][y].getBackground().equals(Color.DARK_GRAY); 
    }

    private List<Point> reconstructPath(Point[][] parent, Point start, Point finish) {
        List<Point> path = new ArrayList<>();
        Point current = finish;

        while (!current.equals(start)) {
            path.add(current);
            current = parent[current.x][current.y];
        }

        Collections.reverse(path);
        return path;
    }
    public void createRandomWalls(JButton[][] mazeCells, int mazeSize, int numWalls) {
     
    
        Point start = null, finish = null;
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (mazeCells[i][j].getBackground().equals(Color.GREEN)) {
                    start = new Point(i, j);
                } else if (mazeCells[i][j].getBackground().equals(Color.PINK)) {
                    finish = new Point(i, j);
                }
            }
        }

        
        int minX = Math.min(start.x, finish.x);
        int maxX = Math.max(start.x, finish.x);
        int minY = Math.min(start.y, finish.y);
        int maxY = Math.max(start.y, finish.y);

      
        for (int i = 0; i < numWalls; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(minX + 1, maxX +1);
            int randomY = ThreadLocalRandom.current().nextInt(minY + 1, maxY +1);
            mazeCells[randomX][randomY].setBackground(Color.DARK_GRAY);
        }
    }
}


