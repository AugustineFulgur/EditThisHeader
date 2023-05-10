package burp.indi.augusttheodor.helper;

import burp.BurpExtender;

//本地化文件，我好无聊啊- -
public class L18n {

    public static final int LANG_CN = 0;
    public static final int LANG_EN=1;
    public static int language;
    private static L18n singleton;
    public String globalRuleTXT;
    public String tmpRuleTXT;
    public String okTXT;
    public String addTXT;
    public String deleteTXT;
    public String saveTXT;
    public String[] columns;
    public String[] operator;
    public String[] settingsDesc;

    public static L18n getInstance(){
        if(L18n.singleton==null){
            L18n.singleton=new L18n();
        }
        return L18n.singleton;
    }

    private L18n(){
        switch (L18n.language){
            case L18n.LANG_CN:
                iniCN();
            case L18n.LANG_EN:
                iniEN();
            default:
                iniCN();
        }
    }

    private void iniCN(){
        this.globalRuleTXT="全局规则";
        this.tmpRuleTXT="临时规则";
        this.okTXT="确定";
        this.addTXT="添加";
        this.deleteTXT="删除";
        this.saveTXT="保存";
        this.operator=new String[]{"添加","删除","替换"};
        this.columns=new String[]{"启用", "操作类型", "主机", "包括条件", "被操作的头", "替换值"};
        this.settingsDesc=new String[]{"设置","禁用所有规则","禁用全局规则","语言"};
    }

    private void iniEN(){
        this.globalRuleTXT="global rules";
        this.tmpRuleTXT="temporary rules";
        this.okTXT="OK";
        this.addTXT="add";
        this.deleteTXT="delete";
        this.saveTXT="save";
        this.operator=new String[]{"add","delete","replace"};
        this.columns= new String[]{"open","operator","host","include exp","headers","value"};
        this.settingsDesc=new String[]{"settings","disable all rules","disable global rules","language"};
    }

}
