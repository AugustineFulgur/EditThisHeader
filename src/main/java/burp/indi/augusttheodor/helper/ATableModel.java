package burp.indi.augusttheodor.helper;

import javax.swing.table.DefaultTableModel;

public class ATableModel extends DefaultTableModel{

    private final Class[] tableClass={Boolean.class,String.class,String.class,String.class,String.class,String.class};
    //需要表现为复选框的列类型为Boolean

    @Override
    public int getColumnCount() {
        return this.tableClass.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return L18n.getInstance().columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.tableClass[columnIndex];
    }

}
