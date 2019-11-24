## Student Grader

This is a program where student-TAs can record assignments that are saved to an excel file to be uploaded to _Synegery_ grade book by the teacher.

The basic setup of this program is to have a folder for each class period that you teach. Inside of each folder needs to have the program _student_grader.jar_ and the file _data.sg_. The file _data.sg_ is a CSV file with each Students' ID, First Name, and Last Name.

When the program starts up a window opens where you input information about the assignments: The Assignment Names, Points, Dates, and Types. You can read in up to 5 different assignments for the class.

After you are done with this window, a new window open ups to import the students' scores. After the scores are imputed a new CSV file is created using the date and time it was created [YYYYMMDD_HHMM.csv]. For more information about how to use this program see [Student_Directions.pdf](./Student_Directions.pdf).

As the teacher, convert all of CSV files you get using
~~~
 ssconvert filename.csv filename.xls
~~~

You may need to install gnumeric

~~~
 sudo apt-get install gnumeric
~~~

Now that you have the file in excel format import the scores using _Grade Book Import_.

To build the program type:

~~~
 javac *.java
 jar cmf manifest.mf student_grader.jar *.class
 chmod 777 student_grader.jar
~~~

Note that these program offices any guarantee or warranty. Use it at your own risk.