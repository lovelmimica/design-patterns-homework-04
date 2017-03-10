/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.comparator;

import java.util.Comparator;

/**
 *
 * @author lovel_mimica
 */
public abstract class ComparatorAbstract implements Comparator{
    protected abstract int specialCompare(Object o1, Object o2);
    
    @Override
    public int compare(Object o1, Object o2) {
        return specialCompare(o1, o2);
    }
    
}
