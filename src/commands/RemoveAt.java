package commands;

public class RemoveAt implements Command{

  private int pos;

  public RemoveAt(int pos){
    this.pos = pos;
  }

  public void execute(){
    Text.getInstance().removeAt(pos);
  }


}
