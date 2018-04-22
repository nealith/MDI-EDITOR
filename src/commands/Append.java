package commands;

import model.Text;

public class Append extends RecordableCommand {

  private char character;
  private int pos;

  public Append(char character,int pos){
    this.character = character;
    this.pos = pos;
  }

  public void execute(){
    super.execute();
    Text.getInstance().append(this.character,this.pos);
  }
}
