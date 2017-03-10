/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import lovelmimica.app.Data;
import lovelmimica.diving.Diver;
import lovelmimica.skills.Skill;
import lovelmimica.skills.SkillFactory;

/**
 *
 * @author lovel_mimica
 */
public class LoadSkills extends LoadAbstract{

    @Override
    protected void parseLines(Data data, String[] lines) {
        for (String line : lines) {
            try {
                String[] skillParams = line.trim().split(";");
                if (skillParams.length != 2) {
                    throw new Exception("Neispravan format parametara");
                }
                //naziv
                String diverName = skillParams[0];
                diverName = diverName.trim();
                //specijalnost
                String skillString = skillParams[1];

                Skill skill = SkillFactory.createSkill(skillString);
                Diver d = getDiver(diverName);
                d.addSkill(skill);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
     private Diver getDiver(String name) {
        for (Diver d : Data.getInstance().getDivers()) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }
}
