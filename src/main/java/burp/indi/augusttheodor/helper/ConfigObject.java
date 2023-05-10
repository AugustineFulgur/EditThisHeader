package burp.indi.augusttheodor.helper;

import java.io.Serializable;
import java.util.Vector;

public class ConfigObject implements Serializable {

    public int language; //语言
    public boolean isSettingDisable; //本地规则是否打开
    public boolean isSettingDisableGlobal; //全局规则是否打开
    public Vector<RuleObject> rule;

}
