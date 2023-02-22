class Box{
	int i,j;
	int nodeValue;
	boolean visited = false;
	Box(){}
	Box(int j,int i){
		this.j = j;
		this.i = i;
	}
	Box(int j,int i,int nodeValue){
		this.j = j;
		this.i = i;
		this.nodeValue = nodeValue;
	}
}