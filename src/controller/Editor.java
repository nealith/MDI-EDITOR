package controller;

import commands.*;
import view.*;
import model.*;

public class Editor{

  private UI main_window;
  //private Text texte;
  static private Editor editeur = null;

  private Editor(){
    main_window = UI.getInstance();
    //texte = new Text();
  }

  static public Editor getInstance(){
    if(editeur==null){
      editeur = new Editor();
    }
    return editeur;
  }

/*  public Text getModel(){
    return this.texte;
  }*/

  public UI getUI(){
    return this.main_window;
  }

  public static void main(String[] args) {
    Editor e = Editor.getInstance();
  }

}
