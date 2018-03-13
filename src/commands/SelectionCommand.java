package commands;

import model.Selection;

public class SelectionCommand implements Command{

  protected Selection selection;

  public SelectionCommand(Selection selection){
    this.selection = selection;
  }

  public void execute(){}


}
