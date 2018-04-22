package commands;

import model.Macros;

public class BeginRecording implements Command {

  public BeginRecording(){

  }

  public void execute(){
    Macros.getInstance().beginRecording();
  }

}
