package view;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import controller.*;
import model.*;
import commands.*;

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
    editorPanel.addKeyListener(new CommandListener(this.editorPanel));
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
  * Observer Pattern
  *
  * @param o  The Observable
  * @param arg woui
  **/
  public void update(Observable o, Object arg){
    System.out.println("Hey");
    editorPanel.setText(Text.getInstance().getText());
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
      int deb = panel.getSelectionStart();
      int len = panel.getSelectionEnd()-deb;
      //if(len>0){
        Editor.getInstance().setSelection(new Selection(deb,len));
      //}

    }
  }

  class CommandListener implements KeyListener{

    private boolean isCommand;
    private JEditorPane panel;

    public CommandListener(JEditorPane p){
      isCommand = false;
      panel = p;
    }

    public void keyPressed(KeyEvent e){
      if(e.getKeyCode()==KeyEvent.VK_CONTROL){
        isCommand = true;
      }
      if(isCommand){
        switch(e.getKeyCode()){
            case KeyEvent.VK_I : Cut c = new Cut(Editor.getInstance().getSelection());c.execute();
            break;
            case KeyEvent.VK_O : Copy co = new Copy(Editor.getInstance().getSelection());co.execute();
            break;
            case KeyEvent.VK_P : Paste p = new Paste(Editor.getInstance().getSelection());p.execute();
            break;
            default:;
        }
      }
    }
    public void keyReleased(KeyEvent e){
      if(e.getKeyCode()==KeyEvent.VK_CONTROL){
        isCommand = false;
      }
      Text.getInstance().setText(panel.getText());
    }
    public void keyTyped(KeyEvent e){

    }
  }
}
