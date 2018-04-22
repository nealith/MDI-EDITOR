package model;

import java.util.Map;
import java.util.TreeMap;
import command.Macro;
import command.RecordableCommand;

public class Macros {

  private Map<String,Macro> macros;
  private boolean recording;

  public Macros(){
    this.macros = new TreeMap<String,Macro>();
    this.recording = false;
  }

  public void beginRecording(){
    this.recording = true;
    this.macros.put(String.valueOf(macros.size()),new Macro());
  }

  public void endRecording(){
    this.recording = false;
  }

  public void record(RecordableCommand command){
    if (this.recording) {
      this.macros.get(String.valueOf(macros.size()-1)).addCommand(command);
    }
  }

  public void execute(String name){
    macros.get(name).execute();
  }

  public Set<String> void getListOfMacros(){
    return macros.keySet();
  }
}
