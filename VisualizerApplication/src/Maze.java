import java.util.*;

public class Maze {
	Maze(int maze[][],int grid[][],int biggestGameUnit,int gameUnit){
		this.maze = maze;
		this.grid = grid;
		this.gameUnit = gameUnit;
		visited = new int[biggestGameUnit][biggestGameUnit];
	}
	 
	public void colorGrid(){
		for(int i=0;i<gameUnit;i++){
			for(int j=0;j<gameUnit;j++){
				if(j%2!=0 ){					
					maze[j][i] = 1;
				}
				if(i!=0 && i%2==0){
					maze[j][i-1] = 1;					
				}
				grid[j][i] = 1;
			}
		}
	}
	
	public void createMaze(Box node){
		if(visited[node.j][node.i]!=1){
			stack.push(node);
			visited[node.j][node.i] = 1;
			grid[node.j][node.i] = 0;
			maze[node.j][node.i] = 0;
		}

		if(!stack.empty()){
			randomNumber = random.nextInt(4);
			int cnt = 1;
			System.out.println("cowkr");
			
			switch(dir[randomNumber]){
				case "N":
					if(node.j>=0 && node.i-1>=0 && visited[node.j][node.i-1]!=1){
						System.out.println("N");
						visited[node.j][node.i-1] = 1;
						grid[node.j][node.i-1] = 0;
						maze[node.j][node.i-1] = 0;
						node = new Box(node.j,node.i-1);
						stack.push(node);
						cnt = 0;
					}
					break;
//				case "W":
//					if(node.j-1>=0 && node.i>=0 && visited[node.j-1][node.i]!=1){
//						System.out.println("W");
//						node = new Box(node.j-1,node.i);
//						visited[node.j][node.i] = 1;
//						grid[node.j][node.i] = 0;
//						maze[node.j][node.i] = 0;
//						stack.push(node);
//						cnt = 0;
//					}
//					break;
//				case "E":
//					if(node.j+1<gameUnit-1 && node.i<gameUnit-1 && visited[node.j+1][node.i]!=1){
//						System.out.println("E");
//						node = new Box(node.j+1,node.i);
//						visited[node.j][node.i] = 1;
//						grid[node.j][node.i] = 0;
//						maze[node.j][node.i] = 0;
//						stack.push(node);
//						cnt = 0;
//					}
//					break;
//				case "S":
//					if(node.j<gameUnit-1 && node.i+1<gameUnit-1 && visited[node.j][node.i+1]!=1){
//						System.out.println("S");
//						node = new Box(node.j,node.i+1);
//						visited[node.j][node.i] = 1;
//						grid[node.j][node.i] = 0;
//						maze[node.j][node.i] = 0;
//						stack.push(node);
//						cnt = 0;
//					}
//					break;
			}
//			if(cnt==1){
//				node = stack.pop();
//				System.out.println(stack.empty());
//			}
		}
	}
	
	public void neighbourNode(Box node){
		
	}
	
	public void clearScreen(){
		for(int i=0;i<gameUnit;i++){
			for(int j=0;j<gameUnit;j++){
				grid[j][i] = maze[j][i] = 0;
			}
		}
	}
	
//-------------------VARIABLES------------------
	
	int x,y;
	int biggestValue;
	int gameUnit;
	int randomNumber;
	
	int grid[][];
	int maze[][];
	int visited[][];
	
	String dir[] = {"N","S","E","W"};
	Random random = new Random();
	
	Stack<Box> stack = new Stack<>();
}
