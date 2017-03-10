/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import lovelmimica.app.Data;

/**
 *
 * @author lovel_mimica
 */
public abstract class LoadAbstract {
    public void load(Data data, String path) throws Exception{
        String dataText = TxtFileReader.loadText(path);
        String[] lines = dataText.trim().split("\\n");
        parseLines(data, lines); 
    }
    protected abstract void parseLines(Data data, String[] lines);
    
}
