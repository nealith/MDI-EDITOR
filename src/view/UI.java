package view;

import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import java.awt.*;
import controller.*;
import model.*;
import commands.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI extends JFrame implements Observer{

  static private UI ui = null;
  private JTextArea editorPanel;
  private JToolBar toolbar;
  private JButton cut,copy,paste,redo,undo,remove;

  private UI(){

    //Toolbar Init
    this.toolbar = new JToolBar("outils");
    //CUT
    this.cut = new JButton("Cut");
    this.cut.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().cut();
      }
    });
    this.toolbar.add(this.cut);

    //COPY
    this.copy = new JButton("Copy");
    this.copy.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().copy();
      }
    });
    this.toolbar.add(this.copy);

    //PASTE
    this.paste = new JButton("Paste");
    this.paste.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().paste();
      }
    });
    this.toolbar.add(this.paste);
    this.paste.setEnabled(false);

    //REMOVE
    this.remove = new JButton("Remove");
    this.remove.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().remove();
      }
    });
    this.toolbar.add(this.remove);

    //UNDO
    this.undo = new JButton("Undo");
    this.undo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().undo();
      }
    });
    this.toolbar.add(this.undo);

    //REDO
    this.redo = new JButton("Redo");
    this.redo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().redo();
      }
    });
    this.toolbar.add(this.redo);

    // Panel Init
    this.editorPanel = new JTextArea();
    this.editorPanel.setEditable(false);
    this.editorPanel.getCaret().setVisible(true);
    this.editorPanel.setLineWrap(true);
    this.editorPanel.setWrapStyleWord(true);

    this.setLayout(new BorderLayout());
    this.getContentPane().add(toolbar, BorderLayout.PAGE_START);
    this.getContentPane().add(this.editorPanel);
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
  * @param arg
  **/
  public void update(Observable o, Object arg){
    editorPanel.setText(Text.getInstance().getText());
    editorPanel.getCaret().setVisible(true);
    this.paste.setEnabled(Text.getInstance().ClipBoardHasContent());
  }

  //Commands

  public void cut(){
    Selection s = Editor.getInstance().getSelection();
    int caret = editorPanel.getCaretPosition();
    Cut c = new Cut(s);
    c.execute();
    editorPanel.moveCaretPosition(caret);
    editorPanel.select(caret,caret);
    Editor.getInstance().setSelection(new Selection(editorPanel.getCaretPosition(),0));
  }

  public void copy(){
    Selection s = Editor.getInstance().getSelection();
    Copy co = new Copy(s);co.execute();
  }

  public void paste(){
    Selection s = Editor.getInstance().getSelection();
    int caret = editorPanel.getCaretPosition();
    int length = Text.getInstance().getLength();
    Paste p = new Paste(s);
    p.execute();
    editorPanel.moveCaretPosition(caret+Text.getInstance().getLength()-length);
    editorPanel.select(editorPanel.getCaretPosition(),editorPanel.getCaretPosition());
    Editor.getInstance().setSelection(new Selection(editorPanel.getCaretPosition(),0));
  }

  public void remove(){
    Selection s = Editor.getInstance().getSelection();
    int caret = editorPanel.getCaretPosition();
    if(s.getLength()>0){
      Remove r = new Remove(s);
      r.execute();
      Editor.getInstance().setSelection(new Selection(editorPanel.getCaretPosition(),0));
    }else{
      RemoveAt ra = new RemoveAt(caret-1);
      ra.execute();
      caret = caret-1;
    }
    editorPanel.moveCaretPosition(caret);
    editorPanel.select(caret,caret);
  }

  public void undo(){
    Selection s = Editor.getInstance().getSelection();
    int caret = editorPanel.getCaretPosition();

  }

  public void redo(){
    Selection s = Editor.getInstance().getSelection();
    int caret = editorPanel.getCaretPosition();

  }


  class SelectionListener implements MouseListener{

    private JTextArea panel;

    public SelectionListener(JTextArea p){
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
    private JTextArea panel;

    public CommandListener(JTextArea p){
      isCommand = false;
      panel = p;
    }

    public void keyPressed(KeyEvent e){
      if(e.getKeyCode()==KeyEvent.VK_CONTROL){
        isCommand = true;
      }
      if(isCommand){
        Selection s = Editor.getInstance().getSelection();
        int caret = panel.getCaretPosition();
        switch(e.getKeyCode()){
            case KeyEvent.VK_I : UI.getInstance().cut();
            break;
            case KeyEvent.VK_O : UI.getInstance().copy();
            break;
            case KeyEvent.VK_P :UI.getInstance().paste();
            break;
            case KeyEvent.VK_Z :UI.getInstance().undo();
            break;
            case KeyEvent.VK_E :UI.getInstance().redo();
            break;

            default:;
        }
      }else{
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
          UI.getInstance().remove();
        }else if(!e.isActionKey()
        ){
          int caret = panel.getCaretPosition();
          Append a = new Append(e.getKeyChar(),caret);
          a.execute();
          panel.setCaretPosition(caret+1);
        }
      }
    }
    public void keyReleased(KeyEvent e){
      if(e.getKeyCode()==KeyEvent.VK_CONTROL){
        isCommand = false;
      }
      //Text.getInstance().setText(panel.getText());
    }
    public void keyTyped(KeyEvent e){

    }
  }
}
