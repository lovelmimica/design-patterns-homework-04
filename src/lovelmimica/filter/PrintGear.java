/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import lovelmimica.app.Data;
import lovelmimica.gear.GearStorage;

/**
 *
 * @author lovel_mimica
 */
public class PrintGear extends FilterAbstract{
    @Override
    public void concreteProcess(Data data) {
        printStorageStatus(data);
    }
    
    private void printStorageStatus(Data data){
        System.out.println("\nStanje skladista opreme na datum: " + data.getCurrentDive().getDate().toString() + GearStorage.getInstance().toString());
    }
}
