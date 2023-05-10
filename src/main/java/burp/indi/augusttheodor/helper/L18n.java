package burp.indi.augusttheodor.helper;

import burp.BurpExtender;

//���ػ��ļ����Һ����İ�- -
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
        this.globalRuleTXT="ȫ�ֹ���";
        this.tmpRuleTXT="��ʱ����";
        this.okTXT="ȷ��";
        this.addTXT="���";
        this.deleteTXT="ɾ��";
        this.saveTXT="����";
        this.operator=new String[]{"���","ɾ��","�滻"};
        this.columns=new String[]{"����", "��������", "����", "��������", "��������ͷ", "�滻ֵ"};
        this.settingsDesc=new String[]{"����","�������й���","����ȫ�ֹ���","����"};
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
