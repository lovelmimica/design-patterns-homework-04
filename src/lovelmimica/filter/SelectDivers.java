/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lovelmimica.app.Data;
import lovelmimica.diving.Dive;
import lovelmimica.diving.Diver;
import lovelmimica.comparator.DiverComparator;
import lovelmimica.skills.UnderwatherCamerman;

/**
 *
 * @author lovel_mimica
 */
public class SelectDivers extends FilterAbstract{
    @Override
    public void concreteProcess(Data data) {
        try {
            select(data);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            data.setError(true);
            return;
        }
    }
    
    private void select(Data data) throws Exception{
        Dive dive = data.getCurrentDive();
        List<Diver> preselectedDivers = data.getSelectedDivers();

        if(dive.getDiverCount() > preselectedDivers.size()) throw new Exception("Nemoguce formirati uron. Premalo ronioca");
        
        preselectedDivers.sort(new DiverComparator());
        /*{
            @Override
            public int compare(Diver d1, Diver d2) {
                if(d1.getDiveCount() > d2.getDiveCount()) return 1;
                else if(d1.getDiveCount() == d2.getDiveCount()){
                    if(d1.getOldesDive().after(d2.getOldesDive()))return 1;
                    else if(d1.getOldesDive().equals(d2.getOldesDive())){
                        if(d1.getLevel() > d2.getLevel()) return 1;
                    }
                }
                return -1;
            } 
        });*/
        
        List<Diver> selectedDivers = new ArrayList<Diver>();
        //prvo dodaj fotografe
        Integer photoCounter = 0;
        for(Diver d : preselectedDivers){
            if(photoCounter >= data.getCurrentDive().getCamermanCount()) break;
            if(d.hasSkill(UnderwatherCamerman.class)) {
                selectedDivers.add(d);
                photoCounter++;
            }
        }
        
        //dalje dodaj po listi
        
        for(int i = 0; i<(dive.getDiverCount() - photoCounter); i++){
            selectedDivers.add(preselectedDivers.get(i));
        }
        data.setSelectedDivers(selectedDivers);
    }
}
