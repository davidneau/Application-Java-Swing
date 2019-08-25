import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Bouton extends JButton implements MouseListener{
	  private String name;
	  private Image img;
	  public Bouton(String str){
	    super(str);
	    this.name = str;
	    try {
	      img = ImageIO.read(new File("Moi.jpg"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    //Grâce à cette instruction, notre objet va s'écouter
	    //Dès qu'un événement de la souris sera intercepté, il en sera averti
	    this.addMouseListener(this);
	  }

	  public void paintComponent(Graphics g){
	    Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    g2d.setColor(Color.black);
	    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() /  2 /4), (this.getHeight() / 2) + 5);
	  }

	  //Méthode appelée lors du clic de souris
	  public void mouseClicked(MouseEvent event) { }

	  public void mouseEntered(MouseEvent event) {
		    //Nous changeons le fond de notre image pour le jaune lors du survol, avec le fichier fondBoutonHover.png
		    try {
		      img = ImageIO.read(new File("FW.jpg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

	  public void mouseExited(MouseEvent event) {
		  //Nous changeons le fond de notre image pour le vert lorsque nous quittons le bouton, avec le fichier fondBouton.png
		    try {
		      img = ImageIO.read(new File("Moi.jpg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		  public void mousePressed(MouseEvent event) {
		    //Nous changeons le fond de notre image pour le jaune lors du clic gauche, avec le fichier fondBoutonClic.png
		    try {
		      img = ImageIO.read(new File("gs.jpg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }
		 
		  public void mouseReleased(MouseEvent event) {
			  //Nous changeons le fond de notre image pour le orange lorsque nous relâchons le clic avec le fichier fondBoutonHover.png si la souris est toujours sur le bouton
			  if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth())){
			    try {
			      img = ImageIO.read(new File("FW.jpg"));
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			  }
			  //Si on se trouve à l'extérieur, on dessine le fond par défaut
			  else{
			    try {
			      img = ImageIO.read(new File("Moi.jpg"));
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			  }               
			}       
		}

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

class Fenetre extends JFrame{

	  private Panneau pan = new Panneau();
	  private JButton bouton = new JButton("Go");
	  private JButton bouton2 = new JButton("Stop");
	  private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Le JLabel");
	  private int compteur = 0;
	  private boolean animated = true;
	  private boolean backX, backY;
	  private int x, y;

	  public Fenetre(){
	    this.setTitle("Animation");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    container.add(pan, BorderLayout.CENTER);
	    bouton.addActionListener(new BoutonListener()); 
	    bouton.setEnabled(false);
	    bouton2.addActionListener(new Bouton2Listener());

	    JPanel south = new JPanel();
	    south.add(bouton);
	    south.add(bouton2);
	    container.add(south, BorderLayout.SOUTH);
	    Font police = new Font("Tahoma", Font.BOLD, 16);
	    label.setFont(police);
	    label.setForeground(Color.blue);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    container.add(label, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	    go();
	  }

	  private void go(){
	    //Les coordonnées de départ de notre rond
	    x = pan.getPosX();
	    y = pan.getPosY();
	    //Dans cet exemple, j'utilise une boucle while
	    //Vous verrez qu'elle fonctionne très bien
	    while(this.animated){
	      if(x < 1)backX = false;
	      if(x > pan.getWidth()-50)backX = true;          
	      if(y < 1)backY = false;
	      if(y > pan.getHeight()-50)backY = true;
	      if(!backX)pan.setPosX(++x);
	      else pan.setPosX(--x);
	      if(!backY) pan.setPosY(++y);
	      else pan.setPosY(--y);
	      pan.repaint();

	      try {
	        Thread.sleep(3);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }     
	  }

	  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {
	      animated = true;
	      bouton.setEnabled(false);
	      bouton2.setEnabled(true);
	      go();
	    }
	  }

	  class Bouton2Listener implements ActionListener{
	     public void actionPerformed(ActionEvent e) {
	      animated = false;     
	      bouton.setEnabled(true);
	      bouton2.setEnabled(false);
	    }
	  }     
	}

public class App {
  public static void main(String[] args){
    Fenetre fenetre = new Fenetre();
  }       
}

