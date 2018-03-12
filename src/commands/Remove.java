package commands;

public class Remove extends SelectionCommand{

  public Remove(Selection selection){
    super(selection);
  }

  public void execute(){
    Text.getInstance().remove(this.selection);
  }

}
