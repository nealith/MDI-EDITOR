package commands;

import model.Macros;

public class BeginRecording implements Command {

  private String name;

  public BeginRecording(String name){
    this.name = name;
  }

  public void execute(){
    Macros.getInstance().beginRecording(name);
  }

}
