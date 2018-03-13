package model;

public class Text{

  static private Text texte = null;

  private Text(){

  }

  static public Text getInstance(){
    if(texte == null){
      texte = new Text();
    }
    return texte;
  }

  public void append(char c, int pos){

  }

  public void copy(Selection s){
    
  }

}
