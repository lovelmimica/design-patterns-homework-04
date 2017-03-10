/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lovelmimica.app.Data;
import lovelmimica.diving.Dive;

/**
 *
 * @author lovel_mimica
 */
public class LoadDives extends LoadAbstract{

    @Override
    protected void parseLines(Data data, String[] lines) {
        List dives = new ArrayList<Dive>();
        for (String line : lines) {
            try {
                String[] diveParams = line.trim().split(";");

                if (diveParams.length == 7) {
                    String dateString = diveParams[0].trim();
                    String hourString = diveParams[1].trim();
                    Date date;

                    date = new SimpleDateFormat("yyyy.MM.dd hh:mm").parse(dateString + " " + hourString);

                    String maxDepthString = diveParams[2].trim();
                    Integer maxDepth = new Integer(maxDepthString);

                    String diverCountString = diveParams[3].trim();
                    Integer diverCount = new Integer(diverCountString);

                    String temperatureString = diveParams[4];
                    Integer temperature = new Integer(temperatureString);

                    String nightString = diveParams[5];
                    boolean night = "1".equals(nightString);

                    String camermanCountString = diveParams[6];
                    Integer camermanCount = new Integer(camermanCountString);

                    Dive dive = new Dive(date, maxDepth, diverCount, temperature, night, camermanCount);
                    dives.add(dive);
                }
            } catch (ParseException ex) {
                System.out.println(ex.toString());
            }
        }
        data.setDives(dives);
    }
    
}
