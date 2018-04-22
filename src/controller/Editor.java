package controller;

import commands.*;
import view.*;
import model.*;
import java.util.Stack;
public class Editor{

  private UI main_window;
  private Text texte;
  private Selection selection;
  static private Editor editeur = null;

  private Stack<Memento> undos;
  private Stack<Memento> redos;

  private Editor(){
    main_window = UI.getInstance();
    texte = Text.getInstance();
    selection = new Selection(0,0);
    undos = new Stack<Memento>();
    redos = new Stack<Memento>();
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

  public void undo(){
    if (!undos.empty()) {
      Memento u = undos.pop();
      Text.getInstance().restore(u);
      redos.push(u);
    }
  }

  public void redo(){
    if (!redos.empty()) {
      Memento u = redos.pop();
      Text.getInstance().restore(u);
      undos.push(u);
    }
  }

  public boolean canUndo(){
    return !undos.empty();
  }

  public boolean canRedo(){
    return !redos.empty();
  }

  public void takeCare(){
    undos.push(Text.getInstance().createMemento());
  }

}
