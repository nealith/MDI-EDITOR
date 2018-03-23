package model;

public class ClipBoard
{

  private String content;


  public ClipBoard(){
      content = new String();
  }


  public void setContent(String s){
    content = s;
  }

  public String getContent(){
    return content;
  }

}
