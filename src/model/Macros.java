package model;

import java.util.*;
import commands.*;

public class Macros {

  static private Macros instance;
  private Map<String,Macro> macrosList;
  private boolean recording;
  private String currentMacro;

  static public Macros getInstance(){
    if (instance == null) {
      instance = new Macros();
    }
    return instance;
  }

  private Macros(){
    this.macrosList = new TreeMap<String,Macro>();
    this.recording = false;
  }

  public void beginRecording(String name){
    this.recording = true;
    this.currentMacro = name;
    this.macrosList.put(name,new Macro());
  }

  public void endRecording(){
    this.recording = false;
  }

  public void record(RecordableCommand command){
    if (this.recording) {
      this.macrosList.get(currentMacro).addCommand(command);
    }
  }

  public void execute(String name){
    macrosList.get(name).execute();
  }

  public Set<String> getListOfMacros(){
    return macrosList.keySet();
  }
}
