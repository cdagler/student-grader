/* This is the backend for the student_gradaes program
 *
 * @author: Mr. Dagler
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;

public class StudentsGraderData 
{
   final int MAX_NUM_ASSINMENTS = 5;

   private ArrayList<String> assigmentNames;
   private ArrayList<String> assigmentPoints;
   private ArrayList<String> assigmnetDates;
   private ArrayList<String> assigmentTypes;

   private ArrayList<String> studentIds;
   private ArrayList<String> studentLastNames;
   private ArrayList<String> studentFirstNames;
   private String[][] studentScores;

   public StudentsGraderData() 
   {
      String[] data;
      
      assigmentNames = new ArrayList<String>();
      assigmentPoints = new ArrayList<String>();
      assigmnetDates = new ArrayList<String>();
      assigmentTypes = new ArrayList<String>();

      studentIds = new ArrayList<String>();
      studentLastNames = new ArrayList<String>();
      studentFirstNames = new ArrayList<String>();
      
      try 
      {
         BufferedReader br = new BufferedReader(new FileReader("data.sg"));

         while(br.ready()) {
            data = br.readLine().split(",");
            studentIds.add(data[0]);
            studentLastNames.add(data[1]);
            studentFirstNames.add(data[2]);
         }

         studentScores = new String[getNumStudents()][MAX_NUM_ASSINMENTS];

         br.close();
      }
      catch(IOException ioe) 
      {
         System.out.println("Error, could not find data.sg!");
         System.exit(1);
      }
   }

   public void addAssignment(String name, String points, String type, String date)
   {
      assigmentNames.add(name);
      assigmentPoints.add(points);
      assigmnetDates.add(date);
      assigmentTypes.add(type);
   }

   public int getNumStudents()
   {
      return studentIds.size();
   }

   public int getNumAssinments() 
   {
      return assigmentNames.size();
   }

   public String getStudentName(int i)
   {
      return studentLastNames.get(i) + ", " + studentFirstNames.get(i);
   }

   public String getAssmentName(int i)
   {
      return assigmentNames.get(i);
   }

   public String getAssmentPoints(int i)
   {
      return assigmentPoints.get(i);
   }

   public void addScores(int assigment, String[] scores)
   {
      if(assigment<0 || assigment>assigmentNames.size() || assigment>MAX_NUM_ASSINMENTS)
      {
         System.out.println("Error, invalid assigment number");
         System.exit(0);
      }
      for(int i=0; i<getNumStudents(); i++)
      {
         try
         {
            studentScores[i][assigment] = Integer.parseInt(scores[i]) + "";
         } 
         catch (NumberFormatException e) 
         {
            studentScores[i][assigment] = "0";
         }
     } 
   }

   public void writeData()
   {
      String year = "" + Calendar.getInstance().get(Calendar.YEAR);
      String month = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH));
      String day = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
      String hour = String.format("%02d",Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
      String minute = String.format("%02d", Calendar.getInstance().get(Calendar.MINUTE));
      String outFileName = year + month + day + "_" + hour + minute + ".csv";

      try
      {
         FileWriter out = new FileWriter(outFileName);
         out.write("STUDENT_PERM_ID,STUDENT_FIRST_NAME,STUDENT_LAST_NAME,ASSIGNMENT_NAME,OVERALL_SCORE,MAX_SCORE,POINTS,SCORE_TYPE,ASSIGNMENT_TYPE,ASSIGNMENT_DATE,DUE_DATE\n");

         for(int i=0; i<getNumAssinments(); i++)
            for(int j=0; j<getNumStudents(); j++)
               out.write(studentIds.get(j) + "," + studentFirstNames.get(j) + "," + studentLastNames.get(j) + "," + assigmentNames.get(i) + "," + studentScores[j][i] + "," + assigmentPoints.get(i) + "," + assigmentPoints.get(i) + ",Raw Score," + assigmentTypes.get(i) + "," + assigmnetDates.get(i)  + "," + assigmnetDates.get(i) + "\n");

         out.close();
      }
      catch(IOException e)
		{  
         System.out.println("I can not write to: " + outFileName);
      }
   }
}
