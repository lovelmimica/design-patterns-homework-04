/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.gear;

import java.util.ArrayList;
import java.util.List;
import lovelmimica.diving.Diver;

/**
 *
 * @author lovel_mimica
 */
public class GearStorage {
    private static GearStorage instance = null;
    private List<Gear> gearList;

    private GearStorage() {
        gearList = new ArrayList<Gear>();
    }

    public static GearStorage getInstance() {
        if(instance == null) instance = new GearStorage();
        return instance;
    }
    
    public void addGear(Gear gear){
        gearList.add(gear);
    }
    
    public void giveGear(Diver diver, Gear gear) throws Exception{
        if(diver.hasGear(gear) == true) return;
        gear.decrementCount();
        diver.addGear(gear);
    }
    public void takeGear(Diver diver, Gear gear){
        diver.removeGear(gear);
        gear.incrementCount();
    }
    public void takeAllGear(Diver diver){
        List<Gear> temp = new ArrayList<Gear>();
        temp.addAll(diver.getGearList());
        for(Gear g : temp){
            takeGear(diver, g);
        }
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        sb.append("\nGearStorage: \n{");
        for(Gear g : gearList){
            sb.append("\n   " + g.toString());
        }
        sb.append("\n}");
        return new String(sb);

    }

    public List<Gear> getGearList() {
        return gearList;
    }
    
    
    
}
