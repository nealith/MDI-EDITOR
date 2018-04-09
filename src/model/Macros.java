package model;

import java.util.Map;
import command.Macro;
import command.RecordableCommand;

public class Macros {

  private Map<String,Macro> macros;
  private boolean recording;

  public Macros(){
    this.macros = new Map<String,Macro>();
    this.recording = false;
  }

  public void beginRecording(){
    this.recording = true;
    this.macros.put(String.valueOf(macros.size()),new Macro)
  }

  public void endRecording(){
    this.recording = false;
  }

  public void record(RecordableCommand command){
    if (this.recording) {
      this.macros.get(String.valueOf(macros.size()-1)).addCommand(command);
    }
  }




}
