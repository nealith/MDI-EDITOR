package test;

import org.junit.*;
import org.junit.runner.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;
import model.*;


public class Sprint1Test{

  public static void main ( String[] args ) {
      JUnitCore.main("test.Sprint1Test");
  }

  /**
   * On voit si le texte que l'on souhaite insérer est bien présent dans le buffer
   */
  @Test
  public void tryInsertText(){
    Text txt = Text.getInstance();
    String s = "Salut c'est MDI Man, aujourd'hui on fait un éditeur.";
    for(int i = 0; i < s.length(); i++){
      txt.append(s.charAt(i),i);
    }
    assertTrue(txt.getText().equals(s));
  }

  // @Test
  // public void trySelectText(){
  //   Text txt = Text.getInstance();
  //   String selectedText = "Je suis un petit texte qui sera selectionné";
  //   String s = "Salut c'est MDI Man, aujourd'hui on fait un éditeur. "+selectedText;
  //   for(int i = 0; i < s.length(); i++){
  //     txt.append(s.charAt(i),i);
  //   }
  //
  // }

  /**
   * On vérifie si une selection vide est bien supprimé
   */
  // @Test
  // public void tryDeleteEmptySelection(){
  //   Text txt = Text.getInstance();
  //   String s = "Salut c'est MDI Man, aujourd'hui on fait un éditeur. Ce texte ne servira absolument à rien car il ne doit pas être selectionné.";
  //   for(int i = 0; i < s.length(); i++){
  //     txt.append(s.charAt(i),i);
  //   }
  // }

  /**
   * Test pour voir si le texte est bien copié et présent dans le clipBoard
   */
  @Test
  public void tryCopyText(){
    Text txt = Text.getInstance();
    String cpTxt = "Je suis un texte qui sera copié.";
    String s = "Salut c'est MDI Man, aujourd'hui on fait un éditeur. "+cpTxt;
    for(int i = 0; i < s.length(); i++){
      txt.append(s.charAt(i),i);
    }
    Selection sl = new Selection(s.length()-cpTxt.length(), cpTxt.length());
    txt.copy(sl);
    assertTrue(cpTxt.equals(txt.getClipboardForText()));
  }

  /**
   * Copie et verification d'un texte vide
   */
  @Test
  public void tryCopyEmptyText(){
    Text txt = Text.getInstance();
    String cpTxt = "";
    for(int i = 0; i < cpTxt.length(); i++){
      txt.append(cpTxt.charAt(i),i);
    }
    Selection sl = new Selection(0, cpTxt.length());
    txt.copy(sl);
    assertTrue(txt.getClipboardForText().length()==0);
  }

  @Test
  public void verifySelectionLength(){
      Text txt = Text.getInstance();
      String cpTxt = "Tu va travailler avec Péteur et Stéveune!";
      for(int i = 0; i < cpTxt.length(); i++){
        txt.append(cpTxt.charAt(i),i);
      }
      Selection sl = new Selection(0, cpTxt.length());
      txt.copy(sl);
      assertTrue(txt.getClipboardForText().length()==cpTxt.length());
  }

  /**
   * On vérifie que removeAt supprime bien le bon nombre de caractères en fin de texte et que le ttexte est bien égal
   */
  @Test
  public void tryRemoveAtEnd(){
    Text txt = Text.getInstance();
    String txtToModif = "On va manger des chips!";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    for(int j = 0; j < 11; j++){
      txt.removeAt(txtToModif.length()-j);
    }
    assertTrue(txt.getText().equals("On va manger"));
  }

  @Test
  public void tryRemoveAtMiddle(){
    Text txt = Text.getInstance();
    String txtToModif = "On va manger avec les enfants!";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    for(int j = 18; j > 12; j--){
      txt.removeAt(j);
    }
    assertTrue(txt.getText().equals("On va manger les enfants!"));
  }

  @Test
  public void tryRemoveAtBegin(){
    Text txt = Text.getInstance();
    String txtToModif = "Georges! Georges est un faschiste de merde!";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    for(int j = 10; j > 0; j--){
      txt.removeAt(j);
    }
    assertTrue(txt.getText().equals("Georges est un faschiste de merde!"));
  }

  @Test
  public void verifyCopied(){
    Text txt = Text.getInstance();
    String txtToModif = "Il s'appelle Juste LeBlanc! Ah bon il a pas de prénom? Non, ";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    Selection sl = new Selection(0, 27);
    Selection endLine = new Selection(txtToModif.length(), 0);
    txt.copy(sl); //on met la séléction dans le clipboard
    txt.paste(endLine); //et on la colle à la fin
    assertTrue(txt.getText().equals("Il s'appelle Juste LeBlanc! Ah bon il a pas de prénom? Non, Il s'appelle Juste LeBlanc!"));
  }

  @Test
  public void verifyEraseTextAfterPast(){
    Text txt = Text.getInstance();
    String txtToModif = "je ne sais pas quoi écrire mais je sais quoi remplacer";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    Selection sl = new Selection(20, 26);
    Selection endLine = new Selection(45, 9);
    txt.copy(sl); //on met la séléction dans le clipboard
    txt.paste(endLine); //et on la colle à la fin
    assertTrue(txt.getText().equals("je ne sais pas quoi écrire mais je sais quoi écrire"));
  }

  @Test
  public void verifyCutText(){
    Text txt = Text.getInstance();
    String txtToModif = "Je suis un texte, je vais être coupé.";
    for (int i = 0; i<txtToModif.length(); i++) {
      txt.append(txtToModif.charAt(i),i);
    }
    Selection sl = new Selection(18, 18);
    Selection beginLine = new Selection(0, 0);
    txt.cut(sl); //on met la séléction dans le clipboard
    txt.paste(beginLine); //et on la colle à la fin
    assertTrue(txt.getText().equals("je vais être coupéJe suis un texte, ."));
  }
}
