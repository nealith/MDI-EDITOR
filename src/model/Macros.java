package model;

import java.util.*;
import commands.*;

public class Macros {

  static private Macros instance;
  private Map<String,Macro> macrosList;
  private boolean recording;

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

  public void beginRecording(){
    this.recording = true;
    this.macrosList.put(String.valueOf(macrosList.size()),new Macro());
  }

  public void endRecording(){
    this.recording = false;
  }

  public void record(RecordableCommand command){
    if (this.recording) {
      this.macrosList.get(String.valueOf(macrosList.size()-1)).addCommand(command);
    }
  }

  public void execute(String name){
    macrosList.get(name).execute();
  }

  public Set<String> getListOfMacros(){
    return macrosList.keySet();
  }
}
