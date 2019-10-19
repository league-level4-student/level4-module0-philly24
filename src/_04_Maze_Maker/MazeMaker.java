package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random rand = new Random();
		Cell start = maze.cells[rand.nextInt(maze.cells.length)][rand.nextInt(maze.cells.length)];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(start);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
			ArrayList<Cell>neighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if (neighbors.size() > 0) {
			
		
			//C1. select one at random.
			Cell cellRand = neighbors.get(randGen.nextInt(neighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(cellRand);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, cellRand);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = cellRand;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(!uncheckedCells.isEmpty()) {
			//D1. if the stack is not empty
			Cell C = uncheckedCells.pop();
			currentCell = C;
				// D1a. pop a cell from the stack
			selectNextPath(C);

				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
	
		}	
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() < c2.getY()) {
				// c1 higher than c2
				c1.setSouthWall(false);
				c2.setNorthWall(false);
				// System.out.println("c1 south remove");
			} else if (c1.getY() > c2.getY()) {
				// c1 below c2
				c1.setNorthWall(false);
				c2.setSouthWall(false);
				// System.out.println("c1 north remove");
			}
		} else if (c1.getX() < c2.getX()) {
			// c1 left of c2
			if (c1.getY() == c2.getY()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
				// System.out.println("c1 east remove");
			}
		} else if (c1.getX() > c2.getX()) {
			// c1 right of c2
			if (c1.getY() == c2.getY()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
				// System.out.println("c1 west remove");
			}
		}
		maze.cells[0][1].setWestWall(false);
		maze.cells[4][4].setSouthWall(false);
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		if (c.getX() != 0) {
			if (maze.cells[c.getX() - 1][c.getY()].hasBeenVisited() == false) {
				list.add(maze.cells[c.getX() - 1][c.getY()]);
			}
		}

		if (c.getX() != 4) {
			if (maze.cells[c.getX() + 1][c.getY()].hasBeenVisited() == false) {
				list.add(maze.cells[c.getX() + 1][c.getY()]);
			}
		}

		if (c.getY() != 0) {
			if (maze.cells[c.getX()][c.getY() - 1].hasBeenVisited() == false) {
				list.add(maze.cells[c.getX()][c.getY() - 1]);
			}
		}

		if (c.getY() != 4) {
			if (maze.cells[c.getX()][c.getY() + 1].hasBeenVisited() == false) {
				list.add(maze.cells[c.getX()][c.getY() + 1]);
			}
		}
		return list;
	}
}
