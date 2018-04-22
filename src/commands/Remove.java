package commands;

import model.Text;
import model.Selection;

public class Remove extends SelectionCommand{

  public Remove(Selection selection){
    super(selection);
  }

  public void execute(){
    super.execute();    
    Text.getInstance().remove(this.selection);
  }

}
