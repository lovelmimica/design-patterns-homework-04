/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovelmimica.loading;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author lovel_mimica
 */
public class TxtFileReader {
    public static String loadText(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder builder = new StringBuilder();
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            builder.append("\n" + line);
        }
        String text = new String(builder);
        return text;
    }
}
