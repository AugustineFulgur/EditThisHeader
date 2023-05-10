package burp.indi.augusttheodor.helper;

import burp.BurpExtender;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AConfigPanel extends JPanel implements ActionListener {
    public static AConfigPanel singleton;
    private JCheckBox settingDisable;
    private JCheckBox settingDisableGlobal; //ȫ������
    private JComboBox settingLanguage;

    public AConfigPanel(){
        AConfigPanel.singleton=this;
        String[] text=L18n.getInstance().settingsDesc;
        this.setLayout(new GridLayout(0,1));
        JPanel settingsPanel=new JPanel();
        //���ù�����ʲô�����ˣ������ǲ�Ҫд����
        settingsPanel.setBorder(new TitledBorder(text[0]));
        this.settingDisable=new JCheckBox();
        this.settingDisable.setText(text[1]);
        this.settingDisable.addActionListener(l->{
            BurpExtender.config.isSettingDisable=this.settingDisable.isSelected();
        });
        settingsPanel.add(this.settingDisable); //�������й���
        this.settingDisableGlobal = new JCheckBox();
        this.settingDisableGlobal.setText(text[2]);
        this.settingDisableGlobal.addActionListener(l->{
            BurpExtender.config.isSettingDisableGlobal=this.settingDisableGlobal.isSelected();
        });
        settingsPanel.add(this.settingDisableGlobal); //����ȫ�ֹ���
        this.add(settingsPanel);
        this.settingLanguage=new JComboBox<>(new String[]{"����","English"}); //����
        this.settingLanguage.addActionListener(l->{
            BurpExtender.config.language=this.settingLanguage.getSelectedIndex();
        });
        settingsPanel.add(new JLabel(text[3]));
        settingsPanel.add(this.settingLanguage);
        JButton save=new JButton(L18n.getInstance().okTXT);
        save.addActionListener(l->{
            ConfigFileUtil.saveConfig();
        });
        settingsPanel.add(save);
        AConfigPanel.singleton=this;
        AConfigPanel.singleton.settingDisable.setSelected(BurpExtender.config.isSettingDisable);
        AConfigPanel.singleton.settingDisableGlobal.setSelected(BurpExtender.config.isSettingDisableGlobal);
        AConfigPanel.singleton.settingLanguage.setSelectedIndex(BurpExtender.config.language); //�������ֵ��±���ַ�������һ��
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
