
import javax.swing.table.AbstractTableModel;

class StartUpTableModel extends AbstractTableModel 
{
   private static final long serialVersionUID = 1L;
   final int NUM_OF_ROWS = 5;
   final int NUM_OF_COLS = 4;

   String[][] cells;
   String[] columnNames = {"Assinment Name", "Points", "Type", "Date"};

   StartUpTableModel()
   {
      cells = new String[NUM_OF_ROWS][NUM_OF_COLS];

      for(int i=0; i<NUM_OF_ROWS; i++) 
         for(int j=0; j<NUM_OF_COLS; j++)
            cells[i][j] = "";
   }

   @Override
   public int getColumnCount() 
   {
      return NUM_OF_COLS;
   }

   @Override
   public int getRowCount()
   {
      return NUM_OF_ROWS;
   }

   public String getColumnName(int col) {
      return columnNames[col];
    }

   public String getValueAt(int row, int col) 
   {
      return cells[row][col];
   }

   public void setValueAt(Object val, int row, int col) 
   {
      cells[row][col] = val.toString();
      fireTableDataChanged();
   }

   public boolean isCellEditable(int row, int col)
   {
      return true;
   }

}