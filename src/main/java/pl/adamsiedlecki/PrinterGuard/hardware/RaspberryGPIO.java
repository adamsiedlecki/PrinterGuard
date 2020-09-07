package pl.adamsiedlecki.PrinterGuard.hardware;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class RaspberryGPIO {

    public RaspberryGPIO( ){
    }

    public void setHigh(int address){
        String command = "gpio -g mode "+address+" out ";
        execute(command);
        System.out.println(command);
        String command2 = "gpio -g write "+address+" 0 ";
        execute(command2);
        System.out.println(command2);
    }

    public boolean getStatusByPin(int address){
        String command = "gpio -g read "+address;
        String result = execute(command);
        //System.out.println(command);
        //System.out.println("RESULT OF READ PIN COMMAND: "+result);
        int res = -1;
        if(NumberUtils.isDigits(result)){
            res = org.springframework.util.NumberUtils.parseNumber(result, Integer.class);
        }
        if(res==1){
            return false;
        } else return res == 0;
    }

    public void setLow(int address){
        String command = "gpio -g write "+address+" 1 ";
        execute(command);
        System.out.println(command);
    }

    public boolean isGpioWorking(){
        String command = "gpio -g write 0 1 ";
        String result = execute(command);
        return !result.equals("ERROR");
    }

    private String execute(String command){
        Runtime run = Runtime.getRuntime();

        Process pr;
        try {
            pr = run.exec(command);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line;
            String allText="";
            while ((line=buf.readLine())!=null) {
                allText = allText.concat(line);
            }
            if(allText.length()>1){
                System.out.println(allText);
            }
            return allText;
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
            //VoiceRaport.errorOccurred();
        }
        //VoiceRaport.cannotFindGPIO();
        return "ERROR";
    }

}
