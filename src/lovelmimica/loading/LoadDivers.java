/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import java.util.ArrayList;
import java.util.List;
import lovelmimica.app.Data;
import lovelmimica.diving.Diver;

/**
 *
 * @author lovel_mimica
 */
public class LoadDivers extends LoadAbstract{

    @Override
    protected void parseLines(Data data, String[] lines) {
        List<Diver> divers = new ArrayList<Diver>();
        for (String line : lines) {
            String[] lineArray = line.trim().split(";");
            if (lineArray.length == 4) {
                //naziv
                String name = lineArray[0].trim();

                //agencija
                String agency = lineArray[1].trim();

                //razina
                String levelString = lineArray[2].trim();
                Integer level = determineLevel(levelString);

                //godiste
                String yearBornSrting = lineArray[3].trim();
                Integer yearBorn = new Integer(yearBornSrting);

                Diver diver = new Diver(name, agency, level, yearBorn);

                divers.add(diver);
            }
        }
        data.setDivers(divers);
    
    }
    private Integer determineLevel(String levelString) {
        Integer level = 0;
        Character type = levelString.charAt(0);
        if (type.toString().equalsIgnoreCase("i")) {
            level = 6;
        }
        Integer temp = new Integer(levelString.substring(1, 2));
        level += temp;
        return level;
    }
}
