/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.app;

import lovelmimica.diving.Diver;
import lovelmimica.diving.Dive;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class Data {
    private static Data instance; 
    private List<Diver> divers; 
    private List<Dive> dives = new ArrayList<Dive>();
    private List<Diver> selectedDivers;
    private Dive currentDive;
    
    private boolean error = false;

    private Data() {
    }

    public static Data getInstance() {
        if(instance == null) instance = new Data();
        return instance;
    }

    
    //geteri i seteri
    public List<Dive> getDives() {
        return dives;
    }

    public void setCurrentDive(Dive currentDive) {
        this.currentDive = currentDive;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Diver> getDivers() {
        return divers;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }

    public void setDives(List<Dive> dives) {
        this.dives = dives;
    }

    public Dive getCurrentDive() {
        return currentDive;
    }

    public void setSelectedDivers(List<Diver> selectedDivers) {
        this.selectedDivers = selectedDivers;
    }

    public List<Diver> getSelectedDivers() {
        return selectedDivers;
    }

    
    
    
    
    
}
