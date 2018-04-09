package command;

import model.Macros;

public class EndRecording implements Command {

  public EndRecording(){

  }

  public void execute(){
    Macros.getInstance().endRecording();
  }

}
