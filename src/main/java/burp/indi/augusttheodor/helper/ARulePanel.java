package burp.indi.augusttheodor.helper;

import burp.BurpExtender;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ARulePanel extends JPanel implements ActionListener {

    private JButton addGlobalRule;
    private JButton deleteGlobalRule;
    private JButton addTmpRule;
    private JButton deleteTmpRule;
    public JTable globalTable;
    public JTable tmpTable;

    public ARulePanel(){
        this.setLayout(new GridLayout(0,1));
        JScrollPane globalPanel=new JScrollPane();
        this.globalTable=initTable();
        globalPanel.setViewportView(this.globalTable); //设置视口
        globalPanel.setBorder(BorderFactory.createTitledBorder(L18n.getInstance().globalRuleTXT));
        this.add(globalPanel);
        for(int i=0;i<BurpExtender.config.rule.size();i++){
            RuleObject ro=BurpExtender.config.rule.get(i);
            ((DefaultTableModel)this.globalTable.getModel()).addRow(new Object[]{ro.enable,ro.operator,ro.includeHost,ro.includeReg,ro.replaceHeader,ro.value});
        }
        this.addGlobalRule=new JButton(L18n.getInstance().addTXT);
        this.addGlobalRule.addActionListener(this);
        this.deleteGlobalRule=new JButton(L18n.getInstance().deleteTXT);
        this.deleteGlobalRule.addActionListener(this);
        JPanel globalButtonPanel=new JPanel();
        globalButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        addGlobalRule.setSize(200,30);
        deleteGlobalRule.setSize(200,30);
        JButton saveGlobalRule=new JButton(L18n.getInstance().saveTXT);
        saveGlobalRule.addActionListener(l->{
            int index=BurpExtender.panel.rule.globalTable.getRowCount();
            BurpExtender.config.rule=new Vector<>(); //清空规则数组
            for(int i=0;i<index;i++){
                BurpExtender.config.rule.add(new RuleObject(BurpExtender.panel.rule.globalTable,i));
            } //添加记录规则
            ConfigFileUtil.saveConfig(); //保存一次规则
        });
        saveGlobalRule.setSize(200,30);
        globalButtonPanel.add(saveGlobalRule);
        globalButtonPanel.add(addGlobalRule);
        globalButtonPanel.add(deleteGlobalRule);
        this.add(globalButtonPanel);

        JScrollPane tmpPanel=new JScrollPane();;
        this.tmpTable=initTable();
        tmpPanel.setViewportView(this.tmpTable);
        tmpPanel.setBorder(BorderFactory.createTitledBorder(L18n.getInstance().tmpRuleTXT));
        this.add(tmpPanel); //两个选择区域
        this.addTmpRule=new JButton(L18n.getInstance().addTXT);
        this.addTmpRule.addActionListener(this);
        this.deleteTmpRule=new JButton(L18n.getInstance().deleteTXT);
        this.deleteTmpRule.addActionListener(this);
        JPanel tmpButtonPanel=new JPanel();
        tmpButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        addTmpRule.setSize(200,30);
        deleteTmpRule.setSize(200,30);
        tmpButtonPanel.add(addTmpRule);
        tmpButtonPanel.add(deleteTmpRule);
        this.add(tmpButtonPanel);
    }

    private JTable initTable(){
        JTable table=new JTable(new ATableModel());
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<>(L18n.getInstance().operator)));
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        return table;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(addGlobalRule.equals(e.getSource())){
            ((DefaultTableModel)this.globalTable.getModel()).addRow(new Object[]{true,null,null,null,null,null});
        }
        if(deleteGlobalRule.equals(e.getSource())){
            ((DefaultTableModel)this.globalTable.getModel()).removeRow(this.globalTable.getSelectedRow());
        }
        if(addTmpRule.equals(e.getSource())){
            ((DefaultTableModel)this.tmpTable.getModel()).addRow(new Object[]{true,null,null,null,null,null});
        }
        if(deleteTmpRule.equals(e.getSource())){
            ((DefaultTableModel)this.tmpTable.getModel()).removeRow(this.tmpTable.getSelectedRow());
        }
    }
}
