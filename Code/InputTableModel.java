
import javax.swing.table.AbstractTableModel;

class InputTableModel extends AbstractTableModel 
{
   private static final long serialVersionUID = 1L;
   StudentsGraderData studentsGraderData;
   int numOfRows, numOfCols;

   String[][] cells;
   String[] columnNames;

   InputTableModel(StudentsGraderData studentsGraderData)
   {
      this.studentsGraderData = studentsGraderData;

      numOfRows = studentsGraderData.getNumStudents();
      numOfCols = studentsGraderData.getNumAssinments() + 1;
      columnNames = new String[numOfCols];

      columnNames[0] = "Student Names";
      for(int i=0; i<studentsGraderData.getNumAssinments(); i++)
         columnNames[i+1] = studentsGraderData.getAssmentName(i) + " [" + studentsGraderData.getAssmentPoints(i) + "]";

      cells = new String[numOfRows][numOfCols];

      for(int i=0; i<numOfRows; i++) 
         for(int j=0; j<numOfCols; j++) 
            cells[i][j] = j == 0 ? studentsGraderData.getStudentName(i) : "";
   }

   @Override
   public int getColumnCount() 
   {
      return numOfCols;
   }

   @Override
   public int getRowCount()
   {
      return numOfRows;
   }

   public String getColumnName(int col) 
   {
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
      return col == 0 ? false : true;
   }
}