package burp.indi.augusttheodor.helper;

import javax.swing.*;
import java.io.Serializable;

public class RuleObject implements Serializable {

    public  boolean enable;
    public String operator;
    public String includeHost;
    public String includeReg;
    public String replaceHeader;
    public String value;

    public RuleObject(JTable table,int i){
        this.enable=(boolean) table.getValueAt(i,0);
        this.operator=(String) table.getValueAt(i,1);
        this.includeHost=(String) table.getValueAt(i,2);
        this.includeReg= (String) table.getValueAt(i,3);
        this.replaceHeader=(String) table.getValueAt(i,4);
        this.value=(String) table.getValueAt(i,5);
    }

}
