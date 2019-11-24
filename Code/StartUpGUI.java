/* This is how the program reads in the name, total points, type,
 * and date for each assinment that is going to be recoreded.
 *
 * @author Mr. Dagler
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartUpGUI
{

   JFrame jfStartUp;
   JTable jTable;
   JPanel jpButtons;
   JButton jbEnter, jbQuit;

   public StartUpGUI()
   {
      
      jfStartUp = new JFrame();
      jTable = new JTable(new StartUpTableModel());
      jpButtons = new JPanel();
      jbEnter = new JButton("Enter");
      jbQuit = new JButton("Quit");

      jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
      jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
      jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
      jTable.getColumnModel().getColumn(3).setPreferredWidth(100);

      jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.X_AXIS));
      jpButtons.add(Box.createRigidArea(new Dimension(5,0)));
      jpButtons.add(jbQuit);
      jpButtons.add(Box.createHorizontalGlue());
      jpButtons.add(jbEnter);
      jpButtons.add(Box.createRigidArea(new Dimension(5,0)));

      jfStartUp.add(new JScrollPane(jTable), BorderLayout.CENTER);
      jfStartUp.add(jpButtons, BorderLayout.PAGE_END);
      jfStartUp.setSize(500, 175);
      jfStartUp.setVisible(true);
      
      jbQuit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
             System.exit(0);
         }
     });

     jbEnter.addActionListener(new ActionListener() 
     {
         public void actionPerformed(ActionEvent ae) 
         {
            int numOfAssinments = 0;

            while(jTable.getValueAt(numOfAssinments, 0).toString().length()>0)
               numOfAssinments++;

            String[] names = new String[numOfAssinments];
            String[] points = new String[numOfAssinments];
            String[] types = new String[numOfAssinments];
            String[] dates = new String[numOfAssinments];
            if(jTable.getCellEditor() != null)
               jTable.getCellEditor().stopCellEditing();
            jfStartUp.dispose();

            for(int i=0; i<numOfAssinments; i++) 
            {
               names[i] = jTable.getValueAt(i,0).toString(); 
               points[i] = jTable.getValueAt(i,1).toString();
               types[i] = jTable.getValueAt(i,2).toString(); 
               dates[i] = jTable.getValueAt(i,3).toString();
            }

            InputGUI inputGui = new InputGUI(names, points, types, dates);

         }
      });
   }
}