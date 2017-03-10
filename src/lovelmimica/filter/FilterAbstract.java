/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;

import lovelmimica.app.Data;

/**
 *
 * @author lovel_mimica
 */
public abstract class FilterAbstract {

    public void process(Data data) {
        if (data.isError()) {
            return;
        }
        concreteProcess(data);
        
    }

    ;
    protected abstract void concreteProcess(Data data);
}
