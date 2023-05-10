package burp.indi.augusttheodor.helper;

import burp.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class HttpProcessor implements IMessageEditor {

    private String host;
    private String header;
    private String body;
    private String message;
    private boolean isRequest; //�Ƿ�Ϊ����
    private IHttpRequestResponse http;
    private  boolean isEdited; //�Ƿ��޸�

    public HttpProcessor(String header,String message,String host,boolean isRequest,IHttpRequestResponse http,String body){
        this.header=header;
        this.message=message;
        this.host=host;
        this.isRequest=isRequest;
        this.http=http;
        this.body=body;
    }

    public void executeTableRules(JTable table){
        int index=table.getRowCount();
        for(int i=0;i<index;i++){
            RuleObject o=new RuleObject(table,i);
            if(!o.enable){continue;}
            filterMessage(o);
        } //���ι���
        //������֮����������
        //���ڲ��޸�BODY�����Բ��ø���Content-Length
        String pack=this.header+this.body;
        if(this.isRequest){
            BurpExtender.so.println(pack);
            this.http.setRequest(pack.getBytes());
            //this.http.setHighlight("pink");
        }else{ //��Ӧ�Ȳ���
            //this.http.setResponse(pack.getBytes());
        }
    }

    private void filterMessage(RuleObject o){
        if(o.includeHost==null){o.includeHost="";}
        if(o.includeReg==null){o.includeReg="";}
        if(!Pattern.compile(o.includeHost).matcher(this.host).find() && !Pattern.compile(o.includeReg).matcher(this.message).find()){
            BurpExtender.so.println("do not match");
            return;
        } //����������������ͷ���
        List<String> headerList=new ArrayList<>(Arrays.asList(this.header.split("\n")));
        if(Objects.equals(o.operator,L18n.getInstance().operator[0])){
            //���
            headerList.add(headerList.size()-1,o.value);
            BurpExtender.so.println("add header");
        }
        if(Objects.equals(o.operator,L18n.getInstance().operator[1])){
            //ɾ��
            headerList.removeIf(i -> Pattern.compile(o.replaceHeader).matcher(i).find());
            BurpExtender.so.println("delete header");
        }
        if(Objects.equals(o.operator,L18n.getInstance().operator[2])){
            //�滻
            for(String h:headerList){
                if(h.contains(o.replaceHeader)){
                    headerList.remove(h);
                    headerList.add(o.value);
                }
            }
            BurpExtender.so.println("replace header");
        }
        StringBuilder message= new StringBuilder();
        for(String h:headerList){
            message.append(h);
            message.append("\n");
        }
        this.header= String.valueOf(message);
    }

    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public void setMessage(byte[] message, boolean isRequest) {

    }

    @Override
    public byte[] getMessage() {
        if(isRequest && this.isEdited){
            return (this.header+this.body).getBytes();
        }
        return new byte[0];
    }

    @Override
    public boolean isMessageModified() {
        return false;
    }

    @Override
    public byte[] getSelectedData() {
        return new byte[0];
    }

    @Override
    public int[] getSelectionBounds() {
        return new int[0];
    }
}
