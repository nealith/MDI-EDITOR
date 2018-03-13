package commands;

import model.Text;
import model.Selection;

public class Cut extends SelectionCommand{

  public Cut(Selection selection){
    super(selection);
  }

  public void execute(){
    Text.getInstance().cut(this.selection);
  }

}
