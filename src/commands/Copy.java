package commands;

import model.Text;
import model.Selection;

public class Copy extends SelectionCommand{

  public Copy(Selection selection){
    super(selection);
  }

  public void execute(){
    Text.getInstance().copy(this.selection);
  }

}
