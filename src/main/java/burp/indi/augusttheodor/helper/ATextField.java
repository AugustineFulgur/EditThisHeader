package burp.indi.augusttheodor.helper;

import javax.swing.*;
import java.awt.*;

public class ATextField extends JTextField { //���������TextField

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
                        this.value=textArea.getText(); //����˳�ʱ����ֵ
                        this.setText(this.value); //ˢ��ֵ
                        //��˵�������������� Ҳ������ôд��֮ǰд��׿��ʱ�������������^ ^
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
