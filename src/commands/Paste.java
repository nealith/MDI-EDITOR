package commands;

import model.Text;
import model.Selection;

public class Paste extends SelectionCommand{

  public Paste(Selection selection){
    super(selection);
  }

  public void execute(){
    Text.getInstance().paste(this.selection);
  }

}
