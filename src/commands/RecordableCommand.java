package commands;

import model.Macros;

public class RecordableCommand implements Command {

  public void execute(){
    Macros.getInstance().record(this);
  }

}
