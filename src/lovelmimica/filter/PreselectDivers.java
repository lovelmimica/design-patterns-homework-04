/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import java.util.ArrayList;
import java.util.List;
import lovelmimica.app.Data;
import lovelmimica.diving.Dive;
import lovelmimica.diving.Diver;

/**
 *
 * @author lovel_mimica
 */
public class PreselectDivers extends FilterAbstract{

    @Override
    protected void concreteProcess(Data data) {
        preselect(data);
    }
    
    private void preselect(Data data){
        List<Diver> selectedDivers = new ArrayList<Diver>();
        Dive currentDive = data.getCurrentDive();
        for(Diver diver : data.getDivers()){
           if(currentDive.isDiverSuited(diver)) selectedDivers.add(diver);
        }
        data.setSelectedDivers(selectedDivers);
        
    }
}
