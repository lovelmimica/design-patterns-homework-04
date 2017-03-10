/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import lovelmimica.app.Data;
import lovelmimica.gear.GearLeasingService;

/**
 *
 * @author lovel_mimica
 */
public class CheckLeasing extends FilterAbstract{

    @Override
    protected void concreteProcess(Data data) {
        checkLeasing(data);
    }
    private void checkLeasing(Data data){
                    
        //vrati opremu kojoj je istako rok
        GearLeasingService.getInstance().checkLeaseExpirations();
        //ocisti liste
    }
}
