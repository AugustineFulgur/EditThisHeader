package burp.indi.augusttheodor.helper;

import javax.swing.*;
import java.awt.*;

public class ATextField extends JTextField { //点击弹窗的TextField

    public String value;

    public ATextField(String value){
        this.setText(value);
        this.setEditable(false);
        this.addActionListener(e->{
            if(e.getSource()==this){
                JDialog dialog=new JDialog();
                JTextArea textArea=new JTextArea();
                JButton btn=new JButton();
                btn.setText(L18n.getInstance().okTXT);
                btn.addActionListener(l->{
                    if(l.getSource()==btn){
                        this.value=textArea.getText(); //点击退出时更新值
                        this.setText(this.value); //刷新值
                        //话说这是匿名类中类 也可以这么写吗？之前写安卓的时候好像不是这样啊^ ^
                    }
                });
                textArea.setSize(500,300);
                dialog.add(textArea);
                dialog.add(btn);
                dialog.setLayout(new GridLayout(0,1));
                dialog.setVisible(true);
            }
        });
    }


}
