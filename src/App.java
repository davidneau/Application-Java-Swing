import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

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
  private int drawSize = 50;
  //Un booléen pour le mode morphing 
  //Un autre pour savoir si la taille doit être réduite
  private boolean morph = false, reduce = false;
  private String forme = "ROND";
  //Le compteur de rafraîchissements
  private int increment = 0;
  
  public void paintComponent(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(Color.red);
    //Si le mode morphing est activé, on peint le morphing
    if(this.morph)
      drawMorph(g);
    //Sinon, on peint le mode normal
    else
      draw(g);    
  }
 
  private void draw(Graphics g){
    if(this.forme.equals("ROND")){
      g.fillOval(posX, posY, 50, 50); 
    }
    if(this.forme.equals("CARRE")){
      g.fillRect(posX, posY, 50, 50);
    }
    if(this.forme.equals("TRIANGLE")){       
      int s1X = posX + 50/2;
      int s1Y = posY;
      int s2X = posX + 50;
      int s2Y = posY + 50;
      int s3X = posX;
      int s3Y = posY + 50;      
      int[] ptsX = {s1X, s2X, s3X};
      int[] ptsY = {s1Y, s2Y, s3Y};      
      g.fillPolygon(ptsX, ptsY, 3);
    }
    if(this.forme.equals("ETOILE")){      
      int s1X = posX + 50/2;
      int s1Y = posY;
      int s2X = posX + 50;
      int s2Y = posY + 50;        
      g.drawLine(s1X, s1Y, s2X, s2Y);      
      int s3X = posX;
      int s3Y = posY + 50/3;
      g.drawLine(s2X, s2Y, s3X, s3Y);      
      int s4X = posX + 50;
      int s4Y = posY + 50/3;
      g.drawLine(s3X, s3Y, s4X, s4Y);                   
      int s5X = posX;
      int s5Y = posY + 50;
      g.drawLine(s4X, s4Y, s5X, s5Y);       
      g.drawLine(s5X, s5Y, s1X, s1Y);  
    }    
  }
  
  //Méthode qui peint le morphing
  private void drawMorph(Graphics g){
    //On incrémente
    increment++;
    //On regarde si on doit réduire ou non
    if(drawSize >= 50) reduce = true;
    if(drawSize <= 10) reduce = false;    
    if(reduce)
      drawSize = drawSize - getUsedSize();
    else
      drawSize = drawSize + getUsedSize();
    
    if(this.forme.equals("ROND")){
      g.fillOval(posX, posY, drawSize, drawSize); 
    }
    if(this.forme.equals("CARRE")){
      g.fillRect(posX, posY, drawSize, drawSize);
    }
    if(this.forme.equals("TRIANGLE")){        
      int s1X = posX + drawSize/2;
      int s1Y = posY;
      int s2X = posX + drawSize;
      int s2Y = posY + drawSize;
      int s3X = posX;
      int s3Y = posY + drawSize;      
      int[] ptsX = {s1X, s2X, s3X};
      int[] ptsY = {s1Y, s2Y, s3Y};      
      g.fillPolygon(ptsX, ptsY, 3);
    }
    if(this.forme.equals("ETOILE")){      
      int s1X = posX + drawSize/2;
      int s1Y = posY;
      int s2X = posX + drawSize;
      int s2Y = posY + drawSize;      
      g.drawLine(s1X, s1Y, s2X, s2Y);      
      int s3X = posX;
      int s3Y = posY + drawSize/3;
      g.drawLine(s2X, s2Y, s3X, s3Y);      
      int s4X = posX + drawSize;
      int s4Y = posY + drawSize/3;
      g.drawLine(s3X, s3Y, s4X, s4Y);                   
      int s5X = posX;
      int s5Y = posY + drawSize;
      g.drawLine(s4X, s4Y, s5X, s5Y);       
      g.drawLine(s5X, s5Y, s1X, s1Y);  
    }    
  }
  
  //Retourne le nombre à retrancher ou à ajouter pour le morphing
  private int getUsedSize(){
    int res = 0;
    //Si le nombre de tours est de dix, on réinitialise l'incrément et on retourne 1
    if(increment / 10 == 1){
      increment = 0;
      res = 1;
    }    
    return res;
  }
  
  public int getDrawSize(){
    return drawSize;
  }
  
  public boolean isMorph(){
    return morph;
  }
  
  public void setMorph(boolean bool){
    this.morph = bool;
    //On réinitialise la taille
    drawSize = 50;
  }
  
  public void setForme(String form){
    this.forme = form;
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
	private JLabel label = new JLabel("Choix de la forme");
	private int compteur = 0;
	private boolean animated = true;
	private boolean backX, backY;
	private int x,y ;
	private Thread t;
	private JComboBox combo = new JComboBox();
	  
	private JCheckBox morph = new JCheckBox("Morphing");
  
  public Fenetre(){
    this.setTitle("Animation");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null); 
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);    
    bouton.addActionListener(new BoutonListener());     
    bouton2.addActionListener(new Bouton2Listener());
    bouton2.setEnabled(false);    
    JPanel south = new JPanel();
    south.add(bouton);
    south.add(bouton2);
    container.add(south, BorderLayout.SOUTH);    
    combo.addItem("ROND");
    combo.addItem("CARRE");
    combo.addItem("TRIANGLE");
    combo.addItem("ETOILE");    
    combo.addActionListener(new FormeListener());    
    morph.addActionListener(new MorphListener());
     
    JPanel top = new JPanel();
    top.add(label);
    top.add(combo);
    top.add(morph);    
    container.add(top, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);         
  }
      
  private void go(){
    x = pan.getPosX();
    y = pan.getPosY();
    while(this.animated){
    
    //Si le mode morphing est activé, on utilise la taille actuelle de la forme
      if(pan.isMorph()){
        if(x < 1)backX = false;
        if(x > pan.getWidth() - pan.getDrawSize()) backX = true;   
        if(y < 1)backY = false;
        if(y > pan.getHeight() - pan.getDrawSize()) backY = true;
      }
    //Sinon, on fait comme d'habitude
      else{
        if(x < 1)backX = false;
        if(x > pan.getWidth()-50) backX = true;    
        if(y < 1)backY = false;
        if(y > pan.getHeight()-50) backY = true;
      }  

      if(!backX) pan.setPosX(++x);
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

  public class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent arg0) {    	
	      JOptionPane jop = new JOptionPane();    	
	      int option = jop.showConfirmDialog(null, 
	        "Voulez-vous lancer l'animation ?", 
	        "Lancement de l'animation", 
	        JOptionPane.YES_NO_OPTION, 
	        JOptionPane.QUESTION_MESSAGE);

	      if(option == JOptionPane.OK_OPTION){
	        animated = true;
	        t = new Thread(new PlayAnimation());
	        t.start();
	        bouton.setEnabled(false);
	        bouton2.setEnabled(true);    	
	      }
	    }    
	  }

	  class Bouton2Listener  implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      JOptionPane jop = new JOptionPane();    	
	      int option = jop.showConfirmDialog(null, 
	        "Voulez-vous arrêter l'animation ?",
	        "Arrêt de l'animation", 
	        JOptionPane.YES_NO_CANCEL_OPTION, 
	        JOptionPane.QUESTION_MESSAGE);

	      if(option != JOptionPane.NO_OPTION && 
	      option != JOptionPane.CANCEL_OPTION && 
	      option != JOptionPane.CLOSED_OPTION){
	        animated = false;	
	        bouton.setEnabled(true);
	        bouton2.setEnabled(false);
	      }
	    }    
	  }	

	  class PlayAnimation implements Runnable{
	    public void run() {
	      go();    	
	    }    
	  }
    
  class FormeListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      pan.setForme(combo.getSelectedItem().toString());
    }    
  }
    
  class MorphListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      //Si la case est cochée, on active le mode morphing
      if(morph.isSelected())pan.setMorph(true);
      //Sinon, on ne fait rien
      else pan.setMorph(false);
    }
  }    
}

public class App {
  public static void main(String[] args){
    Fenetre fenetre = new Fenetre();
  }       
}

