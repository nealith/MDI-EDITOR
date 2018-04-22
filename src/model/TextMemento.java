package model;

import model.Memento;


public class TextMemento implements Memento{
  private StringBuffer buffer;
  private ClipBoard clipBoard;

  public TextMemento(StringBuffer buffer, ClipBoard clipBoard){
    this.buffer = buffer;
    this.clipBoard = clipBoard;
  }

  public ClipBoard getClipBoard(){
    return this.clipBoard;
  }

  public StringBuffer getBuffer(){
    return this.buffer;
  }
}
