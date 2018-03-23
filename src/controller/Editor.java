package controller;

import commands.*;
import view.*;
import model.*;

public class Editor{

  private UI main_window;
  private Text texte;
  private Selection selection;
  static private Editor editeur = null;

  private Editor(){
    main_window = UI.getInstance();
    texte = Text.getInstance();
    selection = new Selection(0,0);
  }

  static public Editor getInstance(){
    if(editeur==null){
      editeur = new Editor();
    }
    return editeur;
  }

  public Text getModel(){
    return this.texte;
  }

  public UI getUI(){
    return this.main_window;
  }

  public void setSelection(Selection sel){
    selection = sel;
  }

  public Selection getSelection(){
    return selection;
  }

  public static void main(String[] args) {
    Editor e = Editor.getInstance();
  }

}
