/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.app;

import lovelmimica.diving.Dive;
import lovelmimica.filter.ApproveDive;
import lovelmimica.filter.EquipDivers;
import lovelmimica.filter.FilterAbstract;
import lovelmimica.filter.CheckLeasing;
import lovelmimica.filter.LoadData;
import lovelmimica.filter.PreselectDivers;
import lovelmimica.filter.PrintDivers;
import lovelmimica.filter.PrintGear;
import lovelmimica.filter.SelectDivers;

/**
 *
 * @author lovel_mimica
 */
public class Lovmimica_zadaca_04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Params.getInstance().load(args);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return;
        }
        
        Data data = Data.getInstance();
        FilterAbstract currentFilter = new LoadData();
        currentFilter.process(data);
        if(data.isError()) return;
        for(Dive dive : Data.getInstance().getDives()){
            data.setCurrentDive(dive);
            data.setError(false);
            System.out.println("\nPocetak obrade urona: " + data.getCurrentDive() + "\n");
            
            currentFilter = new CheckLeasing();
            currentFilter.process(data);
            
            currentFilter = new PrintGear();
            currentFilter.process(data);
            
            currentFilter = new PreselectDivers();
            currentFilter.process(data);
            
            currentFilter = new SelectDivers();
            currentFilter.process(data);
            
            currentFilter = new PrintDivers();
            currentFilter.process(data);
         
            currentFilter = new EquipDivers();
            currentFilter.process(data);
            
            currentFilter = new ApproveDive();
            currentFilter.process(data);
            
            currentFilter = new PrintGear();
            currentFilter.process(data);
        }
    }
    
}
