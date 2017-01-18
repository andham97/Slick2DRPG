/**
 * Created by andreashammer on 18/01/2017.
 */
package xyz.hammerprod.RPG;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Iterator;

public class PrepareMaps {
    public static void prepare(){
        Iterator<File> i = FileUtils.iterateFiles(new File("data/maps"), new String[] {"tmx"}, true);
        while(i.hasNext()){
            try {
                File f = (File) i.next();
                // input the file content to the String "input"
                BufferedReader file = new BufferedReader(new FileReader(f));
                String line, input = "";

                while ((line = file.readLine()) != null) input += line + '\n';

                file.close();

                if(!input.contains("<objectgroup width")) {
                    input = input.replace("<objectgroup", "<objectgroup width=\"0\" height=\"0\"");
                }

                // write the new String with the replaced line OVER the same file
                FileOutputStream fileOut = new FileOutputStream(f);
                fileOut.write(input.getBytes());
                fileOut.close();

            } catch (Exception e) {
                System.out.println("Problem reading file.");
            }
        }
    }
}
