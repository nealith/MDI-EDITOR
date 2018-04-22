package model;

import model.Memento;

public class TextMemento implements Memento{
  private BufferString buffer;
  private ClipBoard clipBoard;

  public TextMemento(BufferString buffer, ClipBoard cl){
    this.buffer = buffer;
    this.clipBoard = cl;
  }

  public ClipBoard get_clipBoard(){
    return this.clipBoard;
  }

  public void set_clipBoard(ClipBoard new_clipBoard){
    this.clipBoard = new_clipBoard;
  }

  public BufferString get_buffer(){
    return this.buffer;
  }

  public void set_buffer(BufferString new_buffer){
    this.buffer = new_buffer;
  }
}
