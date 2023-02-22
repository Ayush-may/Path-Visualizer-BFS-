import java.awt.*;
import java.util.*;

class BFS{	
	
	BFS(int gameUnit,int biggestGameUnit,int grid[][] ,Queue<Box> shortestNode , int wall[][]){
		this.gameUnit = gameUnit;
		this.grid = grid;
		this.biggestGameUnit = biggestGameUnit;
		this.shortestNode = shortestNode;
		this.wall = wall;
		this.parentNode	= new Box[biggestGameUnit][biggestGameUnit];
		
		visitedBack = new int[biggestGameUnit][biggestGameUnit]; 
		visited = new int[biggestGameUnit][biggestGameUnit];
		
	}
	
	public int bfs(Box node){
		if(visited[node.j][node.i]!=1){
			node.nodeValue = 0;
			visitedNode.add(node);
			visited[node.j][node.i] = 1;
			visitedBack[node.j][node.i] = -1;
			grid[node.j][node.i] = 2;
			parentNode[node.j][node.i] = node; 
		}			
		if(!visitedNode.isEmpty()){
			Box currentNode = visitedNode.remove();
			grid[currentNode.j][currentNode.i] = 2;
			nodeValue = currentNode.nodeValue + 1;
			neighbourNode(currentNode,nodeValue);			
		}
		if(check == 1){
			return 1;
		}
		
		return 0;
	}
	
	public void neighbourNode(Box currentNode,int value){
		Box nextNode;
			
		if( currentNode.j+1 < gameUnit-1 && currentNode.i < gameUnit-1 && visited[currentNode.j+1][currentNode.i]!=1 
				&& wall[currentNode.j+1][currentNode.i]!=1 ){
			
					nextNode = new Box( (currentNode.j+1),currentNode.i,value);
					visitedNode.add(nextNode);
					parentNode[nextNode.j][nextNode.i] = nextNode;		
					grid[nextNode.j][nextNode.i] = 3;   
					visited[nextNode.j][nextNode.i] = 1;
					visitedBack[nextNode.j][nextNode.i] = -1;
					check = checkCollision(nextNode);
		//			nodeAddition(nextNode);
					
		}
		
		if( (currentNode.j-1)>=0 && currentNode.i>=0 && visited[currentNode.j-1][currentNode.i]!=1 
				&& wall[currentNode.j-1][currentNode.i]!=1 ){
			
					nextNode = new Box( (currentNode.j-1),currentNode.i,value);
					visitedNode.add(nextNode);
					parentNode[currentNode.j-1][currentNode.i] = nextNode  ;		
					grid[currentNode.j-1][currentNode.i] = 3;   
					visited[currentNode.j-1][currentNode.i] = 1;
					visitedBack[currentNode.j-1][currentNode.i] = -1;
					check = checkCollision(nextNode);
		//			nodeAddition(nextNode);
					
		}

		if( (currentNode.i-1)>=0 && currentNode.j>=0 && visited[currentNode.j][currentNode.i-1]!=1 
				&& wall[currentNode.j][currentNode.i-1]!=1 ){
					nextNode = new Box( (currentNode.j),currentNode.i-1,value);
					visitedNode.add(nextNode);
					parentNode[currentNode.j][currentNode.i-1] = nextNode;		
					grid[currentNode.j][currentNode.i-1] = 3;   
					visited[currentNode.j][currentNode.i-1] = 1;
					visitedBack[currentNode.j][currentNode.i-1] = -1;		
					check = checkCollision(nextNode);
		//			nodeAddition(nextNode);
		}
		
		if( (currentNode.i+1)<gameUnit-1 && currentNode.j<gameUnit-1 && visited[currentNode.j][currentNode.i+1]!=1 
				&& wall[currentNode.j][currentNode.i+1]!=1 ){
					nextNode = new Box( (currentNode.j),currentNode.i+1,value);
					visitedNode.add(nextNode);
					parentNode[currentNode.j][currentNode.i+1] = nextNode;		
					grid[currentNode.j][currentNode.i+1] = 3;   
					visited[currentNode.j][currentNode.i+1] = 1;
					visitedBack[currentNode.j][currentNode.i+1] = -1;
					check = checkCollision(nextNode);
		//			nodeAddition(nextNode);
		}

	}
	
	
//	public void nodeAddition(Box nextNode){
//		visitedNode.add(nextNode);
//		parentNode[nextNode.j][nextNode.i] = nextNode;		
//		grid[nextNode.j][nextNode.i] = 3;   
//		visited[nextNode.j][nextNode.i] = 1;	
//		check = checkCollision(nextNode);		
//	}	
	

	public void searchShortestNeighbour(Box node){
		
		do{
			
			if( node.j+1 < gameUnit-1 && node.i < gameUnit-1  && visitedBack[node.j+1][node.i] == -1
					&& parentNode[node.j+1][node.i].nodeValue < node.nodeValue ){
					
						shortestNode.add(node);
						visitedBack[node.j+1][node.i] = 1;
						node = parentNode[node.j+1][node.i];
						continue;
		}
			if( node.j < gameUnit-1 && node.i+1 < gameUnit-1 && visitedBack[node.j][node.i+1] == -1 
					&& parentNode[node.j][node.i+1].nodeValue < node.nodeValue ){
									
							shortestNode.add(node);
//							visited[node.j][node.i+1] = 0;
							visitedBack[node.j][node.i+1] = 1;					
							node = parentNode[node.j][node.i+1];
							continue;
		}
//			
			
			if( node.j-1 >=0 && node.i >=0	&& visitedBack[node.j-1][node.i] == -1 
					&& parentNode[node.j-1][node.i].nodeValue < node.nodeValue ){
		
							shortestNode.add(node);
//							visited[node.j-1][node.i] = 0;
							visitedBack[node.j-1][node.i] = 1;
							node = parentNode[node.j-1][node.i];
							continue;
		}
			if( node.j >=0 && node.i-1 >=0 && visitedBack[node.j][node.i-1] == -1 
					&& parentNode[node.j][node.i-1].nodeValue < node.nodeValue ){
			
							shortestNode.add(node);
//							visited[node.j-1][node.i-1] = 0;
							visitedBack[node.j][node.i-1] =1;
							node = parentNode[node.j][node.i-1];
							continue;
			}
		}while(node.nodeValue != 0);
	}	
	
	public int checkCollision(Box node){
		if(node.j==endingNodeX && node.i==endingNodeY){
			searchShortestNeighbour(node);
			System.out.println("Fountd it");
			return 1;
		}
		return 0;
	}
	
	public void storeValue(int x,int y){
		endingNodeX = x;
		endingNodeY = y;
	}

	public void reDeclaring(Queue<Box> shortestNode , int wall[][]) {
		
		endingNodeX = -1;
		endingNodeY = -1;
		this.shortestNode = shortestNode;
		this.wall = wall;
		this.parentNode	= new Box[biggestGameUnit][biggestGameUnit];
		
		visitedBack = new int[biggestGameUnit][biggestGameUnit]; 
		visited = new int[biggestGameUnit][biggestGameUnit];
		visitedNode = new LinkedList<>();
	}

	
//-----------------------VARIABLES-----------------------------------
	public int gameUnit;
	int biggestGameUnit;
	int nodeValue = 0;
	int check ;
	int endingNodeX ,endingNodeY;

	int grid[][] ;
	int visited[][];
	int visitedBack[][];
	int wall[][];
	Box parentNode[][];
	
	ClearClass c = new ClearClass();
		
	Queue<Box> visitedNode = new LinkedList<>();
	Queue<Box> shortestNode = new LinkedList<>();	
	
	
//-----------------------VARIABLES----------------------------------
}






//public void searchShortestNeighbour(Box finalNode){
//
//	while(true){
//		if(finalNode.nodeValue == 1)
//			break;
//
//		 if(finalNode.j+1 < gameUnit-1 && finalNode.i < gameUnit-1 && visited[finalNode.j+1][finalNode.i ]==1 
//				 && parentNode[finalNode.j+1][finalNode.i].nodeValue <= finalNode.nodeValue  ){
//			System.out.println("FIRST " + finalNode.nodeValue);
//				finalNode = parentNode[finalNode.j+1][finalNode.i];
//				visited[finalNode.j+1][finalNode.i ] = 0;
//				shortestNode.add(finalNode);
//				continue;
//		}
//		 
//		else if((finalNode.i-1)>=0 && finalNode.j>=0 && visited[finalNode.j][finalNode.i-1] == 1 
//					&& parentNode[finalNode.j][finalNode.i-1].nodeValue <= finalNode.nodeValue){
//					System.out.println("THIRD " + finalNode.nodeValue);
//					finalNode = parentNode[finalNode.j][finalNode.i-1];
//					visited[finalNode.j][finalNode.i-1] = 0;
//					shortestNode.add(finalNode);
//					continue;
//			}
//
//		else if((finalNode.j-1)>=0 && finalNode.i>=0 && visited[finalNode.j-1][finalNode.i]==1 
//				&& parentNode[finalNode.j-1][finalNode.i].nodeValue <= finalNode.nodeValue ){
//				System.out.println("SECOND "+ finalNode.nodeValue);
//				finalNode = parentNode[finalNode.j-1][finalNode.i];
//				visited[finalNode.j-1][finalNode.i] = 0;
//				shortestNode.add(finalNode);
//				continue;
//		}
//		
//		
//		 else if((finalNode.i+1)<gameUnit-1 && finalNode.j<gameUnit-1 && visited[finalNode.j][finalNode.i+1] == 1 
//				 && parentNode[finalNode.j][finalNode.i+1].nodeValue <= finalNode.nodeValue ){
//				System.out.println("FOURTH  "+ finalNode.nodeValue);
//				finalNode = parentNode[finalNode.j][finalNode.i+1];
//				visited[finalNode.j][finalNode.i+1] = 0;
//				shortestNode.add(finalNode);
//				continue;	
//		}
//		
//		else{
////				System.out.println("CANTT");
//		}
//	}
////		System.out.println("Final :"+finalNode.nodeValue);
//}



