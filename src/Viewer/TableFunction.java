package Viewer;

public class TableFunction {
    public void addRow(TableAttributes tableAttributes,String[] row) {
        tableAttributes.searchModel.addRow(row);
    }
    public void clearTable(TableAttributes tableAttributes){
        tableAttributes.searchModel.setRowCount(0);
    }
}
