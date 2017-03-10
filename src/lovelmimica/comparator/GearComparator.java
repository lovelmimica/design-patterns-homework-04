/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.comparator;

import lovelmimica.gear.Gear;

/**
 *
 * @author lovel_mimica
 */
public class GearComparator extends ComparatorAbstract{

    @Override
    protected int specialCompare(Object o1, Object o2) {
        Gear g1 = (Gear)o1;
        Gear g2 = (Gear)o2;
        if (g1.getMinTemperature() > g2.getMinTemperature()) {
            return -1;
        }
        if (g1.getMinTemperature().equals(g2.getMinTemperature())) {
            return 0;
        }
        if (g1.getMinTemperature() < g2.getMinTemperature()) {
            return 1;
        }
        return 0;
    }

}
