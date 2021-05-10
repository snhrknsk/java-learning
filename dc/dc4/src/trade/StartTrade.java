package src.trade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.trade.manager.AccountInfo;
import src.ui.HomeUI;

/**
 * Coincheck official API<br>
 * https://coincheck.com/ja/documents/exchange/api
 */
public class StartTrade {

    private static final String OUTPUT_PATH = "./dc4/configuration/Configuration.json";
    private static final Logger log = Logger.getLogger(StartTrade.class);

    public static void main(String[] args) {
    	String path = new File(".").getAbsoluteFile().getParent();
        System.out.println(path);
        log.info("Start Trade");
        initialize();
        SwingUtilities.invokeLater(HomeUI::new);
    }

    private static void initialize(){
        log.info("Initialize configuration.");
        String configJSON = readFile();
        if (configJSON == null){
            log.error("Fail to initialize. End.");
            System.exit(0);
        }
        JSONObject config = new JSONObject(configJSON);
        AccountInfo.getInstance().setAccessKey(config.getString("access-key"));
        AccountInfo.getInstance().setSecretKey(config.getString("security-key"));
        AccountInfo.getInstance().setOutputFilePath(config.getString("file-path"));
    }

    private static String readFile(){
        try{
            File file = new File(OUTPUT_PATH);
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder data = new StringBuilder();
            String str = br.readLine();
            while(str != null){
                data.append(str);
                str = br.readLine();
            }
            br.close();
            return data.toString();
        } catch(IOException e){
            log.error(e);
            return null;
        }
    }
}
