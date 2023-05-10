package burp.indi.augusttheodor.helper;

import burp.BurpExtender;

import java.io.*;
import java.util.Vector;

public class ConfigFileUtil {
    //配置文件
    private static final String fileName="editThisHeader.config";
    private static File configFile;

    public static void getInstance(){ //读取文件到
        try{
            ConfigFileUtil.configFile=new File(ConfigFileUtil.fileName);
            if(ConfigFileUtil.configFile.exists()){
                InputStream st=new FileInputStream(ConfigFileUtil.configFile);
                ObjectInputStream os=new ObjectInputStream(st);
                BurpExtender.config=(ConfigObject)os.readObject(); //读取文件并反序列化到Object，然后装填
                os.close();
            }else{
                ConfigFileUtil.configFile.createNewFile();
                ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(ConfigFileUtil.configFile));
                ConfigObject co=new ConfigObject();
                co.isSettingDisable=false;
                co.isSettingDisableGlobal=false;
                co.language=0;
                co.rule=new Vector<>();
                oo.writeObject(co);
                oo.close();
                BurpExtender.config=co;
            }
            L18n.language=BurpExtender.config.language;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void saveConfig(){
        ConfigFileUtil.configFile=new File(ConfigFileUtil.fileName);
        try {
            ConfigFileUtil.configFile.delete(); //删除文件
            ConfigFileUtil.configFile.createNewFile();
            ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(ConfigFileUtil.configFile));
            os.writeObject(BurpExtender.config);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
