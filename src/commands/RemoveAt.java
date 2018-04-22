package commands;

import model.Text;
import model.Selection;

public class RemoveAt implements RecordableCommand{

  private int pos;

  public RemoveAt(int pos){
    this.pos = pos;
  }

  public void execute(){
    Text.getInstance().removeAt(pos);
  }


}
