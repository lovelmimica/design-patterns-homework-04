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
public class EquipDivers extends FilterAbstract{

    @Override
    public void concreteProcess(Data data) {
        equip(data);
    }
    private void equip(Data data){
        for(Diver diver : data.getSelectedDivers()){
            diver.equipSelf(data.getCurrentDive(), GearLeasingService.getInstance());
        }
    }
}
