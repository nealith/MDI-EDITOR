package commands;

import java.util.List;
import java.util.LinkedList;

public class Macro implements Command {
  private String name;
  private List<RecordableCommand> commands;

  public Macro(){
    commands = new LinkedList<RecordableCommand>();
  }

  public void addCommand(RecordableCommand command){
    commands.add(command);
  }

  public void setName(String newName){
    this.name = newName;
  }

  public String getName(){
    return this.name;
  }

  public void execute(){
    Iterator<RecordableCommand> it = commands.iterator();
    while(it.hasNext()){
      it.next().execute();
    }
  }
}
