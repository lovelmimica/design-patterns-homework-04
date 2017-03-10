/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.filter;
import lovelmimica.app.Data;
import lovelmimica.app.Params;
import lovelmimica.loading.LoadAbstract;
import lovelmimica.loading.LoadDivers;
import lovelmimica.loading.LoadDives;
import lovelmimica.loading.LoadGear;
import lovelmimica.loading.LoadSkills;

/**
 *
 * @author lovel_mimica
 */
public class LoadData extends FilterAbstract {

    @Override
    public void concreteProcess(Data data) {
        try {
            System.out.println("\nFilter: " + this.getClass().toString() + "\n Metoda: process() ");

            //load divers
            LoadAbstract loader = new LoadDivers();
            loader.load(data, Params.getInstance().getDiversFile());

            //load dives
            loader = new LoadDives();
            loader.load(data, Params.getInstance().getDivesFile());

            //load skills
            loader = new LoadSkills();
            loader.load(data, Params.getInstance().getSkillsFile());

            //load gear
            loader = new LoadGear();
            loader.load(data, Params.getInstance().getGearFile());
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            data.setError(true);
            return;
        }
    }

}
