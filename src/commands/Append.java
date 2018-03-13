package commands;

import model.Text;

public class Append implements Command {

  private char character;
  private int pos;

  public Append(char character,int pos){
    this.character = character;
    this.pos = pos;
  }

  public void execute(){
    Text.getInstance().append(this.character,this.pos);
  }
}
