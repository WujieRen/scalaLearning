package com.lemon.kylin;

import java.io.*;

/**
 * Created by renwujie on 2018/01/30 at 9:50
 */
public class WordReader {
    public static void main(String[] args){
        String path = "T:\\lemon\\20180130\\kylin-lemonCharge-ziduan.txt";
        InputStreamReader reader = null;
        BufferedReader br = null;
        File file = null;
        FileWriter writer = null;

        try {
            reader = new InputStreamReader(new FileInputStream(path));
            br = new BufferedReader(reader);
            String line;

            file = new File("T:\\lemon\\20180130\\kylin-lemonCharge-ziduan-lowercase.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file, false);

            while((line = br.readLine()) != null) {
                writer.flush();
                writer.write(line.trim().toLowerCase() + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }

                if(br != null) {
                    br.close();
                }

                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
