package model;

import model.Memento;

public class TextMemento implements Memento{
  private BufferString buffer;
  private ClipBoard clipBoard;

  public TextMemento(BufferString buffer, ClipBoard clipBoard){
    this.buffer = buffer;
    this.clipBoard = clipBoard;
  }

  public ClipBoard getClipBoard(){
    return this.clipBoard;
  }

  public BufferString getBuffer(){
    return this.buffer;
  }
}
