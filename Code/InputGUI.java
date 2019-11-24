/* This is how the program reads in the scores for each assinmnets.
 *
 * @author Mr. Dagler
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class InputGUI
{
   JFrame jfInput;
   JTable jTable;
   JPanel jpButtons;
   JButton jbEnter, jbQuit;
   StudentsGraderData studentsGraderData;

   public InputGUI(String[] names, String[] points, String[] types, String[] dates)
   {
      studentsGraderData = new StudentsGraderData();

      for(int i=0; i<names.length; i++)
         studentsGraderData.addAssignment(names[i], points[i], types[i], dates[i]);
         
      jfInput = new JFrame();
      jTable = new JTable(new InputTableModel(studentsGraderData));
      jpButtons = new JPanel();
      jbEnter = new JButton("Enter");
      jbQuit = new JButton("Quit");

      for(int i=0; i<jTable.getColumnCount(); i++)
         jTable.getColumnModel().getColumn(i).setPreferredWidth(250);

      jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.X_AXIS));
      jpButtons.add(Box.createRigidArea(new Dimension(5,0)));
      jpButtons.add(jbQuit);
      jpButtons.add(Box.createHorizontalGlue());
      jpButtons.add(jbEnter);
      jpButtons.add(Box.createRigidArea(new Dimension(5,0)));

      jfInput.add(new JScrollPane(jTable), BorderLayout.CENTER);
      jfInput.add(jpButtons, BorderLayout.PAGE_END);
      jfInput.setSize(250 * jTable.getColumnCount(), 600);
      jfInput.setVisible(true);
      
      jbQuit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
             System.exit(0);
         }
     });

     jbEnter.addActionListener(new ActionListener() 
     {
         public void actionPerformed(ActionEvent ae) 
         {
            if(jTable.getCellEditor() != null)
               jTable.getCellEditor().stopCellEditing();
            jfInput.dispose();

            String[] scores = new String[studentsGraderData.getNumStudents()];

            for(int i=0; i<studentsGraderData.getNumAssinments(); i++)
            {
               for(int j=0; j<studentsGraderData.getNumStudents(); j++)
                  scores[j] = jTable.getValueAt(j, i+1).toString();
                        
               studentsGraderData.addScores(i, scores);
            }
            
            studentsGraderData.writeData();
            
            System.exit(0);
         }
      });
   }
}