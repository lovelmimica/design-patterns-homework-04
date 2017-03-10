/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import lovelmimica.app.Data;
import lovelmimica.diving.Diver;
import lovelmimica.gear.GearLeasingService;

/**
 *
 * @author lovel_mimica
 */
public class ApproveDive extends FilterAbstract{

    @Override
    protected void concreteProcess(Data data) {
        addSelectedDivers(data);
        approve(data);
    }
    
    private void addSelectedDivers(Data data){
        data.getCurrentDive().setDivers(data.getSelectedDivers());
    }
    private void approve(Data data){
        if(data.getCurrentDive().checkDiverEquipment() == true){
            System.out.println("\nOdobren uron: \n  :" + data.getCurrentDive().toString() + "\n");
        }else{
            data.setError(true);
            //raspremi sve ronioce
            for(Diver d : data.getCurrentDive().getDivers()){
                GearLeasingService.getInstance().releaseAllGear(d);
            }
            data.getCurrentDive().removeAllDivers();
            System.out.println("\nOdbijen uron: \n  :" +data.getCurrentDive().toString());
        }
    }
    
}
