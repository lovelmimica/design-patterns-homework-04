/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.gear;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lovelmimica.comparator.GearComparator;
import lovelmimica.iterator.Iterator;


/**
 *
 * @author lovel_mimica
 */
public class GearIterator implements Iterator<Gear>{
    private List<Gear> gearList;
    private Class gearType;
    private int currentIndex;

    @Override
    public Gear next() {
        do {
            if (hasMore()) {
                currentIndex++;
            } else {
                return null;
            }
        } while (current().getCount()== 0);

        return current();
    }

    @Override
    public Gear first() {
        currentIndex = -1;
        return next();
    }

    @Override
    public boolean hasMore() {
        if ((currentIndex + 1) >= this.gearList.size()) {
            return false;
        }
        return true;
    }

    @Override
    public Gear current() {
        return this.gearList.get(currentIndex);
    }
 
    /*private class GearComparator implements Comparator<Gear>{

        @Override
        public int compare(Gear g1, Gear g2) {
            if(g1.getMinTemperature() > g2.getMinTemperature()) return -1;
            if(g1.getMinTemperature().equals(g2.getMinTemperature())) return 0;
            if(g1.getMinTemperature() < g2.getMinTemperature()) return 1;
            return 0;
        }
        
    }*/

    public GearIterator(Class gearType) {
        this.gearType = gearType;
        this.gearList = new ArrayList<Gear>(); 
        this.currentIndex = 0;
        
        loadCorrespondingGear();
        sortGearList();
    }
    
    //privatne metode
    private void loadCorrespondingGear(){
        List<Gear> temp = new ArrayList<Gear>();
        temp.addAll(GearStorage.getInstance().getGearList());
        for(Gear g : temp){
            if(g.getClass().equals(gearType)) this.gearList.add(g);
        }
    }
    private void sortGearList(){
        this.gearList.sort(new GearComparator());
    }
    
    
    
}
