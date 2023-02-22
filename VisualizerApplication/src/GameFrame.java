import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	
	GameFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GamePanel());
		this.setResizable(false);
		this.setSize(750,650);
		this.setLocation(200,50);
		this.setTitle("Path Finding");
		this.setVisible(true);
	}
}