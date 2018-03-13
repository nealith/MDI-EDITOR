package commands;

import model.Selection;

public class SelectionCommand implements Command{

  protected Selection selection;

  public SlectionCommand(Selection selection){
    this.selection = selection;
  }

  public abstract void execute();


}
