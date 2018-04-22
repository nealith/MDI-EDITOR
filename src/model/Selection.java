package model;

public class Selection{

  private int beginning;
  private int length;
  private int reference;

  /**
  *
  * @param b Beginning Index of the Selection
  * @param l length of the Selection
  **/
  public Selection(int b, int l){
    beginning = b;
    length = l;
  }

  /**
  * Access to the beginning index
  *
  * @return int  Index of the beginning
  **/
  public int getBeginning(){
    return this.beginning;
  }
  /**
  * Access to the beginning index
  *
  * @param b  Index of the beginning
  **/
  public void setBeginning(int b){
    this.beginning = b;
  }

  /**
  * Access to the length
  *
  * @return int  Length of the selection
  **/
  public int getLength(){
    return this.length;
  }
  /**
  * Access to the beginning index
  *
  * @param l  length of the selection
  **/
  public void setLength(int l){
    if(l>0){this.length = l;}
  }

  /**
   * Accessor to get reference value
   * @return int the reference value
   */
  public int getReference(){
    return this.reference;
  }

  /**
   * Accessor to set the selection refernence value
   * @param newRefValue the new refernce value.
   */
  public void setReference(int newRefValue){
    this.reference = newRefValue;
  }
}
