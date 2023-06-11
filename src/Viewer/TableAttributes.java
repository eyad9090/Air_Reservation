package Viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableAttributes {
    String []columns;
    String tableDepartment;

    DefaultTableModel searchModel;
    JTable searchTable;
    JScrollPane scrollTable;
    public TableAttributes(String[]columns,String tableDepartment)
    {
        this.columns=columns;
        this.tableDepartment=tableDepartment;
    }
}
