//	This Class haave logics and i like to work like this !! hell yeahahhh!!!!! 
// 
public class ClearClass{
	
	ClearClass(){
	
	}
	
	
	
	public int unitSize(int value){
		if(value == 1)
			return 10;
		else if(value == 2)
			return 15;
		else if(value == 3)
			return 20;
		else if(value == 4)
			return 25;
		else if(value == 5)
			return 50;
		else 
			return value*10 ;
	}
	
	public int returnXCoordinate(int x,int unit ,int gameUnit){
		for(int i=0;i<gameUnit;i++){
			if( x>=0 && x<=600 && i*unit > x)
				return i-1;
		}
		return 0;
	}	
	
	public int returnYCoordinate(int y,int unit ,int gameUnit){
		for(int i=0;i<gameUnit;i++){
			if( y>=0 && y<=600 && i*unit > y)
				return i-1;
		}
		return 0;
	}	
	
	public void clearMap(int grid[][], int gameUnit,int maze[][]){
		for(int i=0;i<gameUnit;i++){
			for(int j=0; j<gameUnit; j++){
				grid[j][i] = 0;
				maze[j][i] = 0;
			}
		}	
	}
	
	public void checkCollision(Box node){
		if(node.j==endingIndexX && node.i==endingIndexY){
// 			game.stopTimer();
			System.out.println("esa");
		}
	}
	
	public void setEndingIndex(int x,int y){
		endingIndexX = x;
		endingIndexY = y;
	}
	
	public void setStartingIndex(int x,int y){
		startingIndexX = x;
		startingIndexY = y;
	}	
		
//-----------------------VARIABLES-----------------------------------
	public int endingIndexX ,endingIndexY;
	public int startingIndexX ,startingIndexY;
	
}