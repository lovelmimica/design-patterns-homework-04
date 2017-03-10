/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import lovelmimica.app.Data;
import lovelmimica.diving.Dive;
import lovelmimica.diving.Diver;

/**
 *
 * @author lovel_mimica
 */
public class PrintDivers extends FilterAbstract{
    @Override
    public void concreteProcess(Data data) {
        print(data);
    }
    private void print(Data data){
        System.out.println("Ronioci koji ispunjavaju uvijete za uron: \n  :" + data.getCurrentDive().toString());
        for(Diver d : data.getSelectedDivers()){
            System.out.println(d.toString());
        }
    }
}
