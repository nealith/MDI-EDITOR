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

  private JTextField macro_name;
  private JList<String> macro_list;
  private JButton start_record, end_record;
  private JPanel rgt_panel;

  private Selection selection;


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
    this.getContentPane().add(this.editorPanel,BorderLayout.CENTER);
    // Windows init
    this.setTitle("Editeur");
    this.setSize(500,450);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);

    editorPanel.addMouseListener(new SelectionListener(this.editorPanel));
    editorPanel.addKeyListener(new CommandListener(this.editorPanel));


    // Macro UI

    this.macro_list = new JList<String>();
    this.macro_name = new JTextField();
    this.macro_name.setPreferredSize(new Dimension(20,20));
    this.start_record = new JButton("Creer la macro");
    this.end_record = new JButton("Enregister la macro");
    this.end_record.setEnabled(false);

    this.start_record.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().record();
      }
    });

    this.end_record.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        UI.getInstance().endRecord();
      }
    });

    this.macro_list.addMouseListener(new MouseListener(){
      public void mouseClicked(MouseEvent e){
        UI.getInstance().playMacro();
      }
      public void mouseEntered(MouseEvent e){}

      public void 	mouseExited(MouseEvent e){}

      public void 	mousePressed(MouseEvent e){}

      public void 	mouseReleased(MouseEvent e){}
    });

    this.rgt_panel = new JPanel();
    rgt_panel.setLayout(new BoxLayout(rgt_panel, BoxLayout.Y_AXIS));
    rgt_panel.add(new JLabel("Nom de la macro"));
    rgt_panel.add(this.macro_name);
    rgt_panel.add(Box.createVerticalStrut(25));
    rgt_panel.add(this.start_record);
    rgt_panel.add(Box.createVerticalStrut(25));
    rgt_panel.add(this.end_record);
    rgt_panel.add(Box.createVerticalStrut(25));
    rgt_panel.add(this.macro_list);
    this.getContentPane().add(this.rgt_panel,BorderLayout.EAST);
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
    this.paste.setEnabled(Text.getInstance().clipBoardHasContent());
  }

  //Commands

  public void cut(){
    int caret = editorPanel.getCaretPosition();
    Cut c = new Cut(this.selection);


    c.execute();
    Editor.getInstance().takeCare();
    undo.setEnabled(true);
    Editor.getInstance().clearRedos();
    redo.setEnabled(false);
    editorPanel.moveCaretPosition(caret);
    editorPanel.select(caret,caret);
    this.selection = new Selection(editorPanel.getCaretPosition(),0);
  }

  public void copy(){
    Copy co = new Copy(this.selection);


    co.execute();
    Editor.getInstance().takeCare();
    undo.setEnabled(true);
    Editor.getInstance().clearRedos();
    redo.setEnabled(false);
  }

  public void paste(){
    int caret = editorPanel.getCaretPosition();
    int length = Text.getInstance().getLength();
    Paste p = new Paste(this.selection);


    p.execute();
    Editor.getInstance().takeCare();
    undo.setEnabled(true);
    Editor.getInstance().clearRedos();
    redo.setEnabled(false);
    editorPanel.moveCaretPosition(caret+Text.getInstance().getLength()-length);
    editorPanel.select(editorPanel.getCaretPosition(),editorPanel.getCaretPosition());
    this.selection = new Selection(editorPanel.getCaretPosition(),0);
  }

  public void remove(){
    int caret = editorPanel.getCaretPosition();
    if(this.selection.getLength()>0){
      Remove r = new Remove(this.selection);


      r.execute();
      Editor.getInstance().takeCare();
      undo.setEnabled(true);
      Editor.getInstance().clearRedos();
      redo.setEnabled(false);
      this.selection = new Selection(editorPanel.getCaretPosition(),0);
    }else{
      RemoveAt ra = new RemoveAt(caret-1);


      ra.execute();
      Editor.getInstance().takeCare();
      undo.setEnabled(true);
      Editor.getInstance().clearRedos();
      redo.setEnabled(false);
      caret = caret-1;
    }
    editorPanel.moveCaretPosition(caret);
    editorPanel.select(caret,caret);
  }

  public void undo(){
    Editor.getInstance().undo();
    if (!Editor.getInstance().canUndo()) {
      undo.setEnabled(false);
    }
    if (Editor.getInstance().canRedo()) {
      redo.setEnabled(true);
    }
  }

  public void redo(){
    Editor.getInstance().redo();
    if (Editor.getInstance().canUndo()) {
      undo.setEnabled(true);
    }
    if (!Editor.getInstance().canRedo()) {
      redo.setEnabled(false);
    }
  }

  public void record(){
    if(macro_name.getText().length()>0){
      //RECORD
      BeginRecording br = new BeginRecording(macro_name.getText());
      br.execute();
      this.end_record.setEnabled(true);
      this.start_record.setEnabled(false);
    }
  }

  public void endRecord(){
    //Save record
      EndRecording er = new EndRecording();
      er.execute();
      this.end_record.setEnabled(false);
      this.start_record.setEnabled(true);
      this.macro_list.setListData((String[]) Macros.getInstance().getListOfMacros().toArray(new String[1]));
  }

  public void playMacro(){
    // get name in List
    Macros.getInstance().execute(macro_list.getSelectedValue());
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
        selection = new Selection(deb,len);
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
          Editor.getInstance().takeCare();
          undo.setEnabled(true);
          Editor.getInstance().clearRedos();
          redo.setEnabled(false);
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
