package Trabalho.SystemUtil;

import java.io.IOException;

public class Clear {
    public static void clearDisplay(){
        final String os = System.getProperty("os.name").toLowerCase();
        try{
            if(os.contains("windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
