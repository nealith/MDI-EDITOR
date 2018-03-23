
/**
 * @author Tristan Laurent
 * @desc class that contains text
 * @extends Observable
 */
package model;

import model.Selection;
import java.util.Observable;
import model.ClipBoard;
import view.UI;

public class Text extends Observable {

  static private Text texte = null;
  /**
	 * The object that contains the
	 */
	private StringBuffer buffer;
	/**
	 //* the clipboard object which contains the copied text
	 */
	private ClipBoard clipboard;

	private Text(){
    buffer = new StringBuffer();
    clipboard = new ClipBoard();
    this.addObserver(UI.getInstance());
	}

  static public Text getInstance(){
    if(texte==null){
      texte = new Text();
    }
    return texte;
  }

	/**
	 * add the typed character at the current index (where the cursor is). This function will be called at every character typed
	 * @param c The character to add
	 * @param i the current index
	 */
	public void append(char c, int i){
		//assert(i<buffer.getLength(), "The character can not be inserted at buffer offset");
		buffer.insert(i,c);
	}

	/**
	 * Remove single character at a given position
	 * @param i the position of removed char
	 */
	public void removeAt(int i){
		//assert(i<buffer.getLength(), "The character can not be inserted at buffer offset");
		buffer.deleteCharAt(i);
	}

	/**
	 //* Save the selection in the clipboard
	 //* @param selection the text that is copied in the clipboard
	 */
	public void copy(Selection selection){
		//assert((selection.getBeginning()+selection.getLength())<buffer.getLength(), "The selection end is at an offset of buffer");
		//assert((selection.getBeginning())>=0, "The selection getBeginning() is under 0");
    if(selection.getLength()>0){

    String s = new String();
		s = buffer.substring(selection.getBeginning(), selection.getBeginning()+selection.getLength());
		clipboard.setContent(s);
    }
	}

	/**
	 //* Copy the selection on the clipboard and remove it from the texte
	 * @param selection The text to cut
	 */
	public void cut(Selection selection){
		//assert((selection.getBeginning()+selection.getLength())<buffer.getLength(), "The selection end is at an offset of buffer");
		//assert((selection.getBeginning())>=0, "The selection getBeginning() is under 0");
		this.copy(selection);
		this.remove(selection);
	}


	/**
	 //* Erase the selected text with the clipboard content
	 * @param selection the text that will be erased. Can be empty
	 */
	public void paste(Selection selection){
		buffer.delete(selection.getBeginning(),selection.getBeginning()+selection.getLength());
		String s = clipboard.getContent();
		buffer.insert(selection.getBeginning(),s);
    System.out.println(buffer.toString());
    setChanged();
    this.notifyObservers(UI.getInstance());
	}

	/**
	 * Remove the current selection from the text
	 * @param selection The selected text to remove
	 */
	public void remove(Selection selection){
		//assert((selection.getBeginning()+selection.getLength())<buffer.getLength(), "The selection end is at an offset of buffer");
		//assert((selection.getBeginning())>=0, "The selection getBeginning() is under 0");
	}

  public void setText(String s){
    buffer.replace(0,buffer.length(),s);
    System.out.println(buffer.toString());
  }

  public String getText(){
    return buffer.toString();
  }
}
