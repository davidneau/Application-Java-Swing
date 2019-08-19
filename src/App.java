import javax.swing.JFrame;
import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Panneau extends JPanel {
	  public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("C:\\Users\\david\\Documents\\Franck.png"));
	      //g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
	  }               
	}

class Fenetre extends JFrame {
	  public Fenetre(){
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel pan = new JPanel();
	    pan.setBackground(Color.BLUE);
	    this.setContentPane(new Panneau());               
	    this.setVisible(true);
	  }
	}

public class App {
  public static void main(String[] args){
    Fenetre fenetre = new Fenetre();
  }       
}

