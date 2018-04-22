package commands;

import model.Selection;

public class SelectionCommand extends RecordableCommand{

  protected Selection selection;

  public SelectionCommand(Selection selection){
    this.selection = selection;
  }

  public void execute(){
    super.execute();
  }


}
