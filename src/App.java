import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
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
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JPopupMenu;


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
  
  //Les variables définies auparavant ne changent pas
  //On y ajoute nos deux couleurs
  private Color couleurForme = Color.red;
  private Color couleurFond = Color.white;

  public void paintComponent(Graphics g){
    //Affectation de la couleur de fond   
    g.setColor(couleurFond);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());

    //Affectation de la couleur de la forme
    g.setColor(couleurForme);
    //Si le mode morphing est activé, on peint le morphing
    if(this.morph)
      drawMorph(g);
    //Sinon, mode normal
    else
      draw(g);
  }

  //Méthode qui redéfinit la couleur du fond
  public void setCouleurFond(Color color){
    this.couleurFond = color;
  }

  //Méthode qui redéfinit la couleur de la forme
  public void setCouleurForme(Color color){
    this.couleurForme = color;
  }
 
  private void draw(Graphics g){
    if(this.forme.equals("ROND")){
      g.fillOval(posX, posY, 50, 50); 
    }
  //J'ai ajouté :  || this.forme.equals("CARRÉ")
    if(this.forme.equals("CARRE") || this.forme.equals("CARRÉ")){
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
	  this.forme = form.toUpperCase();
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
	  private JPanel container = new JPanel();
	  private int compteur = 0;
	  private boolean animated = true;
	  private boolean backX, backY;
	  private int x,y ;
	  private Thread t;

	  private JMenuBar menuBar = new JMenuBar();
	  
	  private JMenu animation = new JMenu("Animation"),
	    forme = new JMenu("Forme"),
	    typeForme = new JMenu("Type de forme"),
	    aPropos = new JMenu("À propos");

	  private JMenuItem lancer = new JMenuItem("Lancer l'animation"),
	    arreter = new JMenuItem("Arrêter l'animation"),
	    quitter = new JMenuItem("Quitter"),
	    aProposItem = new JMenuItem("?");

	  private JCheckBoxMenuItem morph = new JCheckBoxMenuItem("Morphing");
	  private JRadioButtonMenuItem carre = new JRadioButtonMenuItem("Carré"),
	    rond = new JRadioButtonMenuItem("Rond"),
	    triangle = new JRadioButtonMenuItem("Triangle"),
	    etoile = new JRadioButtonMenuItem("Etoile");

	  private ButtonGroup bg = new ButtonGroup();
	  
	  //La déclaration pour le menu contextuel 
	  private JPopupMenu jpm = new JPopupMenu();
	  private JMenu background = new JMenu("Couleur de fond");
	  private JMenu couleur = new JMenu("Couleur de la forme");

	  private JMenuItem launch = new JMenuItem("Lancer l'animation");      
	  private JMenuItem stop = new JMenuItem("Arrêter l'animation");
	  private JMenuItem  rouge = new JMenuItem("Rouge"),
	    bleu = new JMenuItem("Bleu"),
	    vert = new JMenuItem("Vert"),
	    blanc = new JMenuItem("Blanc"),
	    rougeBack = new JMenuItem("Rouge"),
	    bleuBack = new JMenuItem("Bleu"),
	    vertBack = new JMenuItem("Vert"),
	    blancBack = new JMenuItem("Blanc");

	  //On crée des listeners globaux
	  private StopAnimationListener stopAnimation=new StopAnimationListener();
	  private StartAnimationListener startAnimation=new StartAnimationListener();
	  
	  //Avec des listeners pour les couleurs
	  private CouleurFondListener bgColor = new CouleurFondListener();
	  private CouleurFormeListener frmColor = new CouleurFormeListener();
	  
	  
	  
	  public Fenetre(){
		    this.setTitle("Animation");
		    this.setSize(300, 300);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);

		    container.setBackground(Color.white);
		    container.setLayout(new BorderLayout());

		    //On initialise le menu stop
		    stop.setEnabled(false);
		    //On affecte les écouteurs
		    stop.addActionListener(stopAnimation);
		    launch.addActionListener(startAnimation);
		    
		    //On affecte les écouteurs aux points de menu
		    rouge.addActionListener(frmColor);
		    bleu.addActionListener(frmColor);
		    vert.addActionListener(frmColor);
		    blanc.addActionListener(frmColor);

		    rougeBack.addActionListener(bgColor);
		    bleuBack.addActionListener(bgColor);
		    vertBack.addActionListener(bgColor);
		    blancBack.addActionListener(bgColor);
		    
		    //On crée et on passe l'écouteur pour afficher le menu contextuel
		    //Création d'une implémentation de MouseAdapter
		    //avec redéfinition de la méthode adéquate
		    pan.addMouseListener(new MouseAdapter(){
		      public void mouseReleased(MouseEvent event){
		        //Seulement s'il s'agit d'un clic droit
		        //if(event.getButton() == MouseEvent.BUTTON3)
		        if(event.isPopupTrigger()){ 
		          background.add(blancBack);
		          background.add(rougeBack);
		          background.add(bleuBack);
		          background.add(vertBack);

		          couleur.add(blanc);
		          couleur.add(rouge);
		          couleur.add(bleu);
		          couleur.add(vert);

		          jpm.add(launch);
		          jpm.add(stop);
		          jpm.add(couleur);
		          jpm.add(background);
		          //La méthode qui va afficher le menu
		          jpm.show(pan, event.getX(), event.getY());
		        }
		      }
		    });
		    
		    container.add(pan, BorderLayout.CENTER);
		    this.setContentPane(container);
		    this.initMenu();
		    this.setVisible(true);    

		  }


	  private void initMenu(){
		//Ajout du listener pour lancer l'animation
		//ATTENTION, LE LISTENER EST GLOBAL !!! 
		lancer.addActionListener(startAnimation);
		//On attribue l'accélerateur c
		lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		animation.add(lancer);
	
		//Ajout du listener pour arrêter l'animation
		//LISTENER A CHANGER ICI AUSSI
		arreter.addActionListener(stopAnimation);
		arreter.setEnabled(false);
		arreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		animation.add(arreter);
		lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		animation.add(lancer);
	
		animation.addSeparator();
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		animation.add(quitter);

	    //Menu forme    
	    bg.add(carre);
	    bg.add(triangle);
	    bg.add(rond);
	    bg.add(etoile);
	    //On crée un nouvel écouteur, inutile de créer 4 instances différentes
	    FormeListener fl = new FormeListener();
	    carre.addActionListener(fl);
	    rond.addActionListener(fl);
	    triangle.addActionListener(fl);
	    etoile.addActionListener(fl);
	    
	    typeForme.add(rond);
	    typeForme.add(carre);    
	    typeForme.add(triangle);
	    typeForme.add(etoile);

	    rond.setSelected(true);

	    forme.add(typeForme);
	  //Ajout du listener pour le morphing
	    morph.addActionListener(new MorphListener());
	    forme.add(morph);

	    //Menu À propos
	    //Ajout du listener pour le morphing
	    morph.addActionListener(new MorphListener());
	    forme.add(morph);

	    //Menu À propos

	    //Ajout de ce que doit faire le "?"
	    aProposItem.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        JOptionPane jop = new JOptionPane();      
	        String mess = "Merci ! \n J'espère que vous vous amusez bien !\n";
	        mess += "Je crois qu'il est temps d'ajouter des accélérateurs et des "+" mnémoniques dans tout ça…\n";
	        mess += "\n Allez, GO les ZérOs !";        
	        jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE);        
	      }            
	    });
	    aPropos.add(aProposItem);

	    //Ajout des menus dans la barre de menus
	    menuBar.add(animation);
	    menuBar.add(forme);
	    menuBar.add(aPropos);
	    
	    //Ajout des menus dans la barre de menus et ajout de mnémoniques !
	    animation.setMnemonic('A');
	    menuBar.add(animation);

	    forme.setMnemonic('F');
	    menuBar.add(forme);

	    aPropos.setMnemonic('P');
	    menuBar.add(aPropos); 
	    
	    //Ajout de la barre de menus sur la fenêtre
	    this.setJMenuBar(menuBar);
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
	   
		  
	  public class StartAnimationListener implements ActionListener{
		    public void actionPerformed(ActionEvent arg0) {
		      JOptionPane jop = new JOptionPane();     
		      int option = jop.showConfirmDialog(null, 
		        "Voulez-vous lancer l'animation ?", 
		        "Lancement de l'animation", 
		        JOptionPane.YES_NO_OPTION, 
		        JOptionPane.QUESTION_MESSAGE);

		      if(option == JOptionPane.OK_OPTION){
		        lancer.setEnabled(false);
		        arreter.setEnabled(true);

		        //On ajoute l'instruction pour le menu contextuel
		        launch.setEnabled(false);
		        stop.setEnabled(true);

		        animated = true;
		        t = new Thread(new PlayAnimation());
		        t.start();     
		      }
		    }    
		  }

		  /**
		  * Écouteur du menu Quitter
		  * @author CHerby
		  */
		  class StopAnimationListener  implements ActionListener{

		    public void actionPerformed(ActionEvent e) {
		      JOptionPane jop = new JOptionPane();     
		      int option = jop.showConfirmDialog(null, 
		        "Voulez-vous arrêter l'animation ?",
		        "Arrêt de l'animation", 
		        JOptionPane.YES_NO_CANCEL_OPTION, 
		        JOptionPane.QUESTION_MESSAGE);

		      if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){
		        animated = false;
		        //On remplace nos boutons par nos JMenuItem
		        lancer.setEnabled(true);
		        arreter.setEnabled(false);

		        //On ajoute l'instruction pour le menu contextuel
		        launch.setEnabled(true);
		        stop.setEnabled(false);
		      }
		    }    
		  }  
	  class PlayAnimation implements Runnable{
	    public void run() {
	      go();      
	    }    
	  }  

	  /**
	   * Écoute les menus Forme
	   * @author CHerby
	   */
	  class FormeListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
	      System.out.println("Forme");
	    }
	  }

	  /**
	   * Écoute le menu Morphing
	   * @author CHerby
	   */
	  class MorphListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      //Si la case est cochée, activation du mode morphing
	      if(morph.isSelected()) pan.setMorph(true);
	      //Sinon rien !
	      else pan.setMorph(false);
	    }    
	  }
	  
	  //Écoute le changement de couleur du fond
	  class CouleurFondListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {

	      if(e.getSource() == vertBack)
	        pan.setCouleurFond(Color.green);
	      else if (e.getSource() == bleuBack)
	        pan.setCouleurFond(Color.blue);
	      else if(e.getSource() == rougeBack)
	        pan.setCouleurFond(Color.red);
	      else
	        pan.setCouleurFond(Color.white);
	    }
	  }

	  //Écoute le changement de couleur du fond
	  class CouleurFormeListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      if(e.getSource() == vert)
	        pan.setCouleurForme(Color.green);
	      else if (e.getSource() == bleu)
	        pan.setCouleurForme(Color.blue);
	      else if(e.getSource() == rouge)
	        pan.setCouleurForme(Color.red);
	      else
	        pan.setCouleurForme(Color.white);
	    }
	  }	
	  
	} 
	

public class App {
  public static void main(String[] args){
    Fenetre fenetre = new Fenetre();
  }       
}

