package burp.indi.augusttheodor.helper;

import javax.swing.*;

public class AMainPanel extends JTabbedPane { //��ǩҳ

    public ARulePanel rule;
    public AConfigPanel config;

    private void init(){
        //��ʼ�����
        this.rule=new ARulePanel();
        this.config=new AConfigPanel();
        this.addTab("rule",this.rule);
        this.addTab("config",this.config); //��ӱ�ǩҳ
    }

    public AMainPanel(){
        init();
    }

}
