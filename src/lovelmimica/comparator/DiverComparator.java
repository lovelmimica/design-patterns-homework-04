/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.comparator;

import lovelmimica.diving.Diver;

/**
 *
 * @author lovel_mimica
 */
public class DiverComparator  extends ComparatorAbstract{

    @Override
    protected int specialCompare(Object o1, Object o2) {
        Diver d1 = (Diver) o1;
        Diver d2 = (Diver) o2;

        if (d1.getDiveCount() > d2.getDiveCount()) {
            return 1;
        } else if (d1.getDiveCount().equals(d2.getDiveCount())) {
            if (d1.getOldesDive().after(d2.getOldesDive())) {
                return 1;
            } else if (d1.getOldesDive().equals(d2.getOldesDive())) {
                if (d1.getLevel() > d2.getLevel()) {
                    return 1;
                }
            }
        }
        return -1;
    }

}
