package commands;

import model.Selection;

public class SelectionCommand implements RecordableCommand{

  protected Selection selection;

  public SelectionCommand(Selection selection){
    this.selection = selection;
  }

  public void execute(){}


}
