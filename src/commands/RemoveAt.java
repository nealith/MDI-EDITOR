package commands;

import model.Text;
import model.Selection;

public class RemoveAt extends RecordableCommand{

  private int pos;

  public RemoveAt(int pos){
    this.pos = pos;
  }

  public void execute(){
    super.execute();
    Text.getInstance().removeAt(pos);
  }


}
