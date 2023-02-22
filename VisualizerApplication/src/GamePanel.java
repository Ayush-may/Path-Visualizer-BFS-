import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;


class GamePanel extends JPanel implements ActionListener ,ChangeListener {
		
	GamePanel(){
		this.setLayout(null);

		timer = new Timer(delay , new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int	check = bfsClass.bfs(boxType);
				if(check==1){
					shortestTimer.start();
					timer.stop();
					}
				repaint();
				}
		});						

		shortestTimer = new Timer(shortestDelay , new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				if(!shortestNode.isEmpty()){
					Box forIndex = shortestNode.remove();
					grid[forIndex.j][forIndex.i] = 4;
					repaint();
				}
				else{
					shortestTimer.stop();
				}
				
				
			}
			
		});

		mazeTimer = new Timer(20 , new ActionListener(){
			public void actionPerformed(ActionEvent e){
				maze.createMaze(mazeMaker);
				repaint();
			}
		});
		
		framePlace();
		buttonInit();
		start();
	}
	
	public void buttonInit(){
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		this.add(start);
		this.add(stopMap);
		this.add(reset);
		this.add(clear);
		this.add(mazeButton);
		this.add(algo);
		this.add(cb);
		this.add(slider);
		this.add(gridSize);
		this.add(delaySize);
		this.add(delayDenote);
		this.add(delaySlider);
		this.add(blockCombo);
		this.add(about);
		
		panel.setBackground(Color.BLACK);
		
		start.setBounds(615,15,100,25);
		stopMap.setBounds(615,55,100,25);
		reset.setBounds(615,95,100,25);
		clear.setBounds(615,135,100,25);
		mazeButton.setBounds(615,175,100,25);
		algo.setBounds(635,210,100,25);
		cb.setBounds(615,235,100,30);
		gridSize.setBounds(635,267,130,30);
		slider.setBounds(602,290,130,50);
		delaySize.setBounds(620,345,130,30);
		delayDenote.setBounds(660,350,50,20);
		delaySlider.setBounds(602,370,130,30);
		blockCombo.setBounds(615,410,100,30);
		about.setBounds(615,550,100,30);		
		
		start.addActionListener(this);
		stopMap.addActionListener(this);
		reset.addActionListener(this);
		clear.addActionListener(this);
//		mazeButton.addActionListener(this);
		cb.addActionListener(this);
		slider.addChangeListener(this);
		delaySlider.addChangeListener(this);
		blockCombo.addActionListener(this);
		about.addActionListener(this);
		panel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e){
				if(mouseDraggedBool){
					mouseX = e.getX();
					mouseY = e.getY();
					grid[c.returnXCoordinate(mouseX,unit,gameUnit)][c.returnYCoordinate(mouseY,unit,gameUnit)] = wallAndEraser;
					wall[c.returnXCoordinate(mouseX,unit,gameUnit)][c.returnYCoordinate(mouseY,unit,gameUnit)] = wallAndEraser;
					repaint();
				}
			}
			public void mouseMoved(MouseEvent e){}
		});
		panel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
				
				if(startBool){
					startCount = 1;
					if(cntStart == 0){
						startX = c.returnXCoordinate(mouseX,unit,gameUnit);
						startY = c.returnYCoordinate(mouseY,unit,gameUnit);

						try {
							grid[startX][startY] = wallAndEraser;	
						}
						catch(Exception ex) {}
						
						storePreStartingPoint(startX,startY);
						cntStart++;
					}
					else{
						try {
							grid[preX][preY] = 0;	
						}
						catch(Exception ex) {}
							
						startX = c.returnXCoordinate(mouseX,unit,gameUnit);
						startY = c.returnYCoordinate(mouseY,unit,gameUnit);
						storeCurrentEndingNodeIndex(endX,endY);
						storePreStartingPoint(startX,startY);
						grid[startX][startY] = wallAndEraser;
					}
				}
				if(endBool){
					endCount = 1;
					if(cntEnd == 0){
						endX = c.returnXCoordinate(mouseX,unit,gameUnit);
						endY = c.returnYCoordinate(mouseY,unit,gameUnit); 
						storeCurrentEndingNodeIndex(endX,endY);
						storePreEndingPoint(endX,endY);
						bfsClass.storeValue(endX,endY);
						try {
							grid[endX][endY] = wallAndEraser;									

						}
						catch(Exception ex) {}						
						cntEnd++;
					}
					else{
						
						try {
							grid[preXEnd][preYEnd] = 0;
						}
						catch(Exception ex) {}
						
						endX = c.returnXCoordinate(mouseX,unit,gameUnit);
						endY = c.returnYCoordinate(mouseY,unit,gameUnit);
						bfsClass.storeValue(endX,endY);
						grid[endX][endY] = wallAndEraser;
						storeCurrentEndingNodeIndex(endX,endY);
						storePreEndingPoint(endX,endY);
					}
				}
				repaint();
			}
		});

		delayDenote.setEditable(false);
	}
	
	public void framePlace(){
		
		panel.setBackground(Color.WHITE);
		this.add(panel);
		panel.setBounds(0,0,600,600);
		yellow.setBackground(Color.YELLOW);
		this.add(yellow);
		yellow.setBounds(10,610,30,30);
	}
	
	public void start(){
		wallAndEraser = 8;
	
		for(int i=0 ;i<gameUnit-1 ;i++){
			for(int j=0 ;j<gameUnit-1 ;j++){
				grid[j][i] = 0;
				
			}
		}
	}
	
	public void timerChanged(){
		if(running){
			timer.setDelay(delay);
			timer.restart();
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		draw(g);
	}
	
	public void draw(Graphics g){
		for(int i=0 ;i<gameUnit-1 ;i++){
			for(int j=0 ;j<gameUnit-1 ;j++){
				g.setColor(Color.BLACK);
				g.drawRect(j*unit, i*unit, unit-1, unit-1 );
				
				if(running){
					grid[startX][startY] = 8;
					grid[endX][endY] = 9;
				}
				
				switch(grid[j][i]){
					case 0:
						g.setColor(Color.WHITE);
						break;
					case 1:
						g.setColor(Color.BLACK);
						break;
					case 2:
						g.setColor(Color.GREEN);
						break;
					case 3:
						g.setColor(Color.RED);
						break;
					case 4:
						g.setColor(Color.YELLOW);
						break;
					case 5:
						g.setColor(Color.GRAY);
						break;
					case 8:
						g.setColor(Color.BLUE);
						break;
					case 9:
						g.setColor(Color.CYAN);
						break;
				}
				g.fillRect(j*unit, i*unit, unit-1, unit-1 );
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start){
			if(startButtonBool){
				boxType = new Box(startX,startY,0);
				c.setStartingIndex(startX,startY);
				c.setEndingIndex(endX,endY);
				
				if(startCount == 1 && endCount==1){
					System.out.println("IT WORK");
					running = true;
					timer.restart();
//					timer.start();
				}
			}	
		}
		if(e.getSource()==stopMap){
			timer.stop();
		}
		if(e.getSource()==reset){
 			
			startCount = endCount = 0;
			running = false;
			boxType = null;
			setStartButtonFalse();
			stopTimerAndClear();
			shortestNode = new LinkedList<>();
			wall = new int[biggestGameUnit][biggestGameUnit];
			bfsClass.reDeclaring(shortestNode, wall);
			startButtonBool = true;
			repaint();
			
		}
		if(e.getSource()==clear){

			boxType = null;
			running = false;
			setStartButtonFalse();
			forClear();			
			shortestNode = new LinkedList<>();
			wall = new int[biggestGameUnit][biggestGameUnit];
			bfsClass.reDeclaring(shortestNode, wall);
			try {
				if(startCount == 1 && endCount == 1) {
				
					grid[startX][startY] = 8;
					grid[endX][endY] = 9;	
				}
				else if(startCount == 1 && endCount !=1) {
			
					startX = startY = endX = endY = -1;
					grid[startX][startY] = 8;
					grid[endX][endY] = 9;	
					startCount = endCount = 0;
				}
				else if( endCount == 1 && startCount != 1 ) {
		
					startX = startY = endX = endY = -1;
					grid[startX][startY] = 8;
					grid[endX][endY] = 9;
					startCount = endCount = 0;
				}
				else {
	
				}
				bfsClass.storeValue(endX,endY);
				storeCurrentEndingNodeIndex(endX,endY);
				storePreEndingPoint(endX,endY);
			}
			catch(Exception ex) {}
			startButtonBool = true;
			
			repaint();
		}
		if(e.getSource()==cb){
			if("BFS"==cb.getSelectedItem()){
				bfs = true;
				dfs = false;
			}
			if("DFS"==cb.getSelectedItem()){
				dfs = true;
				bfs = false;
			}
		}
		if(e.getSource()==blockCombo){
			if("Start"==blockCombo.getSelectedItem()){
				wallAndEraser = 8;
				startBool = true;
				endBool = mouseDraggedBool = false;
			}
			if("End"==blockCombo.getSelectedItem()){
				wallAndEraser = 9;
				endBool = true;
				startBool = mouseDraggedBool = false;
			}
			if("Wall"==blockCombo.getSelectedItem()){
				wallAndEraser = 1;			
				mouseDraggedBool = true;
				startBool = endBool = false;
			}
			if("Eraser"==blockCombo.getSelectedItem()){
				wallAndEraser = 0;
				mouseDraggedBool = true;
			}
		}
		if(e.getSource()==about){			
			String info = "Coded By Ayush Sharrma \njan 2023 - march 2023 \n2nd year";
			JOptionPane.showMessageDialog(this,info);  
		}
		if(e.getSource()==mazeButton){
			maze.colorGrid();
			mazeX = random.nextInt(1000)/gameUnit;
			mazeY = random.nextInt(1000)/gameUnit;
			mazeMaker = new Box(mazeX,mazeY);
			mazeTimer.start();
			repaint();
			 
		}
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==slider){
		if(running){
			startX = startY = endX = endY = -1;
			forClear();			
			shortestNode = new LinkedList<>();
			wall = new int[biggestGameUnit][biggestGameUnit];
			bfsClass.reDeclaring(shortestNode, wall);
		}
		else{
			startX = startY = endX = endY = -1;
			forClear();			
			shortestNode = new LinkedList<>();
			wall = new int[biggestGameUnit][biggestGameUnit];
			bfsClass.reDeclaring(shortestNode, wall);
			gameUnit = size/ ( c.unitSize( ((JSlider)e.getSource()).getValue()) ) + 1;
			bfsClass.gameUnit = gameUnit;
			unit = c.unitSize( ((JSlider)e.getSource()).getValue() );
			repaint();	
		}
	
	}

	if(e.getSource()==delaySlider){
		delay = delaySlider.getValue();
		timer.setDelay(delay);
		if(running){
			timerChanged();
		}
		delayDenote.setText( delay + "ms");

		}	
	}
	
	public void stopTimerAndClear(){
		timer.stop();
		running = false;
		startCount = endCount = 0;
		endX = endY = startX = startY = -1;
		c.clearMap(grid,gameUnit,wall);
	}
	
	public void forClear() {
		timer.stop();
		running = false;
		c.clearMap(grid,gameUnit,wall);		
	}
	
	public void storePreStartingPoint(int x,int y){
		preX = x;
		preY = y;
	}
	
	public void storePreEndingPoint(int x,int y){
		preXEnd = x;
		preYEnd = y;
	}
	
	public void storeCurrentEndingNodeIndex(int x,int y){
		bfsClass.endingNodeX = x;
		bfsClass.endingNodeY = y;
		}
	
	public void setStartButtonFalse(){
		startButtonBool = false;
	}
	
//-----------------------VARIABLES-----------------------------------
	int size = 600;
	int unit = 20;
	int delay = 10;
	int shortestDelay = delay + 20;
	int mouseX , mouseY;
	int wallAndEraser;
	int startX , startY; int preX ,preY;
	int endX, endY;	     int preXEnd , preYEnd;
	int cntStart , cntEnd;
	int nodeValue;
	int biggestGameUnit = size/10;
	int gameUnit = size/unit + 1;
	int startCount,endCount;
	int mazeX,mazeY;

	boolean bfs = true;
	boolean dfs = false;
	boolean startBool = true;
	boolean endBool = false;
	boolean startButtonBool = true;
	boolean endButtonBool = false;
	boolean mouseDraggedBool = false;
	boolean running = false;
	
	String idea[] = {"BFS"};
	String blockString[] = {"Start","End","Wall","Eraser"};
	int grid[][] = new int[biggestGameUnit][biggestGameUnit];
	int wall[][] = new int[biggestGameUnit][biggestGameUnit];
	
	Timer timer ,shortestTimer,mazeTimer;
	Box boxType ;
	Box mazeMaker;
	ClearClass c = new ClearClass();
	Random random  = new Random();
	Queue<Box> shortestNode = new LinkedList<>();
	BFS bfsClass = new BFS(gameUnit, biggestGameUnit, grid ,shortestNode,wall);
	Maze maze = new Maze(wall,grid,biggestGameUnit,gameUnit);
	
	JPanel panel = new JPanel();
	JPanel yellow = new JPanel();
	JPanel red = new JPanel();
	JPanel blue = new JPanel();
	JPanel CYAN = new JPanel();
	
	JButton start =  new JButton("Start");
	JButton stopMap =  new JButton("Stop");
	JButton reset =  new JButton("Reset");
	JButton mazeButton = new JButton("Maze");
	JButton clear =  new JButton("Clear");
	JButton about =  new JButton("About");
	
	JTextField delayDenote = new JTextField( delay + "ms");
	
	JLabel yellowName = new JLabel("Shotest Path");
	JLabel redName = new JLabel("Visiting Nodes");
	JLabel blueName = new JLabel("Starting Node");
	JLabel cyanName = new JLabel("Destination Node");
	JLabel gridSize = new JLabel("Grid Size");
	JLabel delaySize = new JLabel("Delay");	
	JLabel algo = new JLabel("Algorithm");	
	
	JComboBox<String> cb = new JComboBox<String>(idea);
	JComboBox<String> blockCombo = new JComboBox<String>(blockString);
	JSlider slider = new JSlider(1,5,3);
	JSlider delaySlider = new JSlider(0,50,delay);


}
