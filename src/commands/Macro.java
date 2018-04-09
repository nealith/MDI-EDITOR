package command;

import java.util.List;
import java.util.LinkedList;

public class Macro implements Command {
  private List<RecordableCommand> commands;

  Macro(){
    commands = new LinkedList<RecordableCommand>();
  }

  public addCommand(RecordableCommand command){
    commands.add(command);
  }
}
