package view;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

public class UI extends JFrame implements Observer{

  static private UI ui = null;
  private JEditorPane editorPanel;

  private UI(){
    // Panel Init
    this.editorPanel = new JEditorPane();
    this.setContentPane(this.editorPanel);
    // Windows init
    this.setTitle("Editeur");
    this.setSize(500,450);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);

    editorPanel.addMouseListener(new SelectionListener(this.editorPanel));
  }


  /**
  * Singleton Pattern
  *
  * @return UI  return an instance of the windows
  **/
  public static UI getInstance(){
    if(ui == null){
      ui = new UI();
    }
    return ui;
  }


  /**
  * Singleton Pattern
  *
  * @param o  The Observable
  * @param arg woui
  **/
  public void update(Observable o, Object arg){

  }

  // Test de l'interface utilisateur
  /*public static void main(String[] args) {
    UI u = UI.getInstance();
  }*/

  class SelectionListener implements MouseListener{

    private JEditorPane panel;

    public SelectionListener(JEditorPane p){
      this.panel = p;
    }

    public void mouseClicked(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){}

    public void 	mouseExited(MouseEvent e){}

    public void 	mousePressed(MouseEvent e){}

    public void 	mouseReleased(MouseEvent e){
      System.out.println("Hey");
      System.out.println(panel.getSelectionStart());
      System.out.println(panel.getSelectionEnd());
      
    }
  }
}
