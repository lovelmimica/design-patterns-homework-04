/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.skills;

/**
 *
 * @author lovel_mimica
 */
public abstract class Skill {
    protected String name; 

    public Skill(String name) {
        this.name = name;
    }
       
    
    
    @Override
    public String toString() {
        return "Skill{" + "name=" + name + '}';
    }
    
    
}
