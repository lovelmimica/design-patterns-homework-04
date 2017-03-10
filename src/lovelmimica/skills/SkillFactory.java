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
public class SkillFactory {
    public static Skill createSkill(String name) throws Exception{
        if(name.equalsIgnoreCase("Podvodni fotograf")) return new UnderwatherCamerman(name);
        if(name.equalsIgnoreCase("Noćno ronjenje")) return new NightDiving(name);
        if(name.equalsIgnoreCase("Suho odijelo")) return new WearDrySuit(name);
        
        throw new Exception("Nepostojeca specijalnost");
    }
}
