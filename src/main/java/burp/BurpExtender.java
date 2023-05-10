//#- encoding utf-8
// ./gradlew build
package burp;

import burp.indi.augusttheodor.helper.*;

import java.awt.*;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BurpExtender implements IBurpExtender,ITab,IHttpListener,IProxyListener {

    public static IExtensionHelpers helpers;
    public static ConfigObject config;
    private IBurpExtenderCallbacks call;
    private static final String VERSION = "0.1.0";
    public static PrintWriter so;
    public static AMainPanel panel;

    public BurpExtender(){} //无参构造函数

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks call) {
        ConfigFileUtil.getInstance(); //读取配置文件
        BurpExtender.panel=new AMainPanel();
        this.call=call;
        this.call.setExtensionName("EditThisHeader "+BurpExtender.VERSION);
        BurpExtender.helpers=this.call.getHelpers();
        this.call.registerHttpListener(this); //register the listener
        //this.call.registerProxyListener(this);
        BurpExtender.so = new PrintWriter(call.getStdout(), true);
        BurpExtender.so.println("@Author AugustTheodor \n"
                +"@Github https://github.com/AugustineFulgur/ \n"
                +"@Use Modify HTTP Request Headers");
        this.call.addSuiteTab(this);
    }

    @Override
    public String getTabCaption() {
        return "EditTH";
    }

    @Override
    public Component getUiComponent() {
        return this.panel;
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        byte[] byteMessage;
        String host;
        String message;
        String header;
        String body;
        if(messageIsRequest){
            byteMessage=messageInfo.getRequest();
            IRequestInfo info=BurpExtender.helpers.analyzeRequest(messageInfo);
            host=info.getUrl().getHost();
            message=new String(byteMessage, StandardCharsets.UTF_8).intern();
            header=new String(Arrays.copyOfRange(byteMessage,0,info.getBodyOffset()),StandardCharsets.UTF_8).intern();
            body=new String(Arrays.copyOfRange(byteMessage,info.getBodyOffset(),byteMessage.length),StandardCharsets.UTF_8).intern();
        }else{
            byteMessage=messageInfo.getResponse();
            IRequestInfo reqInfo=BurpExtender.helpers.analyzeRequest(messageInfo);
            IResponseInfo info=BurpExtender.helpers.analyzeResponse(messageInfo.getResponse());
            host=reqInfo.getUrl().getHost();
            message=new String(byteMessage, StandardCharsets.UTF_8).intern();
            header=new String(Arrays.copyOfRange(byteMessage,0,info.getBodyOffset()),StandardCharsets.UTF_8).intern();
            body=new String(Arrays.copyOfRange(byteMessage,info.getBodyOffset(),byteMessage.length),StandardCharsets.UTF_8).intern();
        }
        HttpProcessor processor=new HttpProcessor(header,message,host,messageIsRequest,messageInfo,body);
        if(!BurpExtender.config.isSettingDisable){
            if(!BurpExtender.config.isSettingDisableGlobal){
                processor.executeTableRules(BurpExtender.panel.rule.globalTable);
            }
            processor.executeTableRules(BurpExtender.panel.rule.tmpTable); //依次执行两种规则
        }
    }

    @Override
    public void processProxyMessage(boolean messageIsRequest, IInterceptedProxyMessage messageProxy) {
        IHttpRequestResponse messageInfo=messageProxy.getMessageInfo();
        byte[] byteMessage;
        String host;
        String message;
        String header;
        String body;
        if(messageIsRequest){
            byteMessage=messageInfo.getRequest();
            IRequestInfo info=BurpExtender.helpers.analyzeRequest(messageInfo);
            host=info.getUrl().getHost();
            message=new String(byteMessage, StandardCharsets.UTF_8).intern();
            header=new String(Arrays.copyOfRange(byteMessage,0,info.getBodyOffset()),StandardCharsets.UTF_8).intern();
            body=new String(Arrays.copyOfRange(byteMessage,info.getBodyOffset(),byteMessage.length),StandardCharsets.UTF_8).intern();
        }else{
            byteMessage=messageInfo.getResponse();
            IRequestInfo reqInfo=BurpExtender.helpers.analyzeRequest(messageInfo);
            IResponseInfo info=BurpExtender.helpers.analyzeResponse(messageInfo.getResponse());
            host=reqInfo.getUrl().getHost();
            message=new String(byteMessage, StandardCharsets.UTF_8).intern();
            header=new String(Arrays.copyOfRange(byteMessage,0,info.getBodyOffset()),StandardCharsets.UTF_8).intern();
            body=new String(Arrays.copyOfRange(byteMessage,info.getBodyOffset(),byteMessage.length),StandardCharsets.UTF_8).intern();
        }
        HttpProcessor processor=new HttpProcessor(header,message,host,messageIsRequest,messageInfo,body);
        if(!BurpExtender.config.isSettingDisable){
            if(!BurpExtender.config.isSettingDisableGlobal){
                processor.executeTableRules(BurpExtender.panel.rule.globalTable);
            }
            processor.executeTableRules(BurpExtender.panel.rule.tmpTable); //依次执行两种规则
        }
    }
}
