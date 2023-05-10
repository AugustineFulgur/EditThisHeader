package burp.indi.augusttheodor.helper;

import javax.swing.*;

public class AMainPanel extends JTabbedPane { //标签页

    public ARulePanel rule;
    public AConfigPanel config;

    private void init(){
        //初始化组件
        this.rule=new ARulePanel();
        this.config=new AConfigPanel();
        this.addTab("rule",this.rule);
        this.addTab("config",this.config); //添加标签页
    }

    public AMainPanel(){
        init();
    }

}
