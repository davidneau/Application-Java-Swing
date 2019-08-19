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
	  private int posX = -50;
	  private int posY = -50;

	  public void paintComponent(Graphics g){
		g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.red);
	    g.setColor(Color.red);
	    g.fillOval(posX, posY, 50, 50);
	  }

	  public int getPosX() {
	    return posX;
	  }

	  public void setPosX(int posX) {
	    this.posX = posX;
	  }

	  public int getPosY() {
	    return posY;
	  }

	  public void setPosY(int posY) {
	    this.posY = posY;
	  }        
	}

class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	
	  public Fenetre(){
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(300, 300);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(pan);               
	    this.setVisible(true);
	    go();
	  }
	  
	  private void go(){
		  //Les coordonnées de départ de notre rond
		  int x = pan.getPosX(), y = pan.getPosY();
		  //Le booléen pour savoir si l'on recule ou non sur l'axe x
		  boolean backX = false;
		  //Le booléen pour savoir si l'on recule ou non sur l'axe y
		  boolean backY = false;

		  //Dans cet exemple, j'utilise une boucle while
		  //Vous verrez qu'elle fonctionne très bien
		  while(true){
		    //Si la coordonnée x est inférieure à 1, on avance
		    if(x < 1)
		      backX = false;

		    //Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
		    if(x > pan.getWidth()-50)
		      backX = true;

		    //Idem pour l'axe y
		    if(y < 1)
		      backY = false;
		    if(y > pan.getHeight()-50)
		      backY = true;

		    //Si on avance, on incrémente la coordonnée
		    //backX est un booléen, donc !backX revient à écrire
		    //if (backX == false)
		    if(!backX)
		      pan.setPosX(++x);

		    //Sinon, on décrémente
		    else
		      pan.setPosX(--x);

		    //Idem pour l'axe Y
		    if(!backY)
		      pan.setPosY(++y);
		    else
		      pan.setPosY(--y);

		    //On redessine notre Panneau
		    pan.repaint();

		    //Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
		    try {
		      Thread.sleep(1);
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		  }
		}   
	}

public class App {
  public static void main(String[] args){
    Fenetre fenetre = new Fenetre();
  }       
}

