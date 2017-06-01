# bae
##Behavior Analysis Engine

###BAE Setup Instructions 

The Analysis Engine represents and attempts to solve constraint satisfaction problems modeled on top of Java. It can ingest fUML (SysML 
viewed in activity diagrams) from MagicDraw simulate execution with animation of MagicDraw activity diagrams and data plots. 

This page has mostly old instructions on how to build, run, and set up for development, but they may still work.

####A summary of instructions that may be sufficient: 

1. Install the Java JDK 1.7 (or later). 
2. Install MagicDraw (MD) if you want to use or develop MD plugins for the BAE. 
3. If you want to set up for development install Eclipse JEE and optionally the Eclipse PyDev plugin. 
4. Get BAE code from github.
5. Get gov.nasa.jpl.mbee.util or the mbee_util.jar from github.
5. Get gov.nasa.jpl.mbee.sysml or the sysml.jar from github.
5. If using Eclipse, 
  1. Run `src/gov.nasa.jpl.ae.tests/TestEventXmlToJava.java` as a Java application. 
  2. Refresh the `src/generated` package. 
  3. Run `src/generated/Main.java` as a Java application. 
6. Else, if not using Eclipse, build and run one of these two ways:
  1. Enter

        ```bash
        java -jar AE.jar
        javac -cp mbee_util.jar:sysml.jar:AE.jar -d bin src/generated/*.java  # change the colons to semi-colons for Windows
        java -classpath mbee_util.jar:sysml.jar:AE.jar:bin generated.Main     # change the colons to semi-colons for Windows
        ```
        
  2. Or enter
  
        ```bash
        mvn package
        java -jar target/bae*.jar
        javac -cp mbee_util.jar:sysml.jar:target/bae*.jar -d bin src/generated/*.java  # change the colons to semi-colons for Windows
        java -classpath mbee_util.jar:sysml.jar:target/bae*.jar:bin generated.Main     # change the colons to semi-colons for Windows
        ```

####Detailed Instructions 

#####For non-development 

Follow these instructions if you only want to run it and don't care about adding code to the BAE or integrating code with the BAE. Otherwise, 
skip this step. 

1. Install Java 1.7 or later. 
2. Download AE.jar, mbee_util.jar, and sysml.jar
3. Try one of the two
  1. Enter at a command prompt
  
        ```bash
        java -jar AE.jar
        javac -cp mbee_util.jar:sysml.jar:AE.jar -d bin src/generated/*.java  # change the colons to semi-colons for Windows
        java -classpath mbee_util.jar:sysml.jar:AE.jar:bin generated.Main     # change the colons to semi-colons for Windows
       ```
       
  2. Enter
  
       ```bash
       mvn package
       java -jar target/bae*.jar
       javac -cp mbee_util.jar:sysml.jar:target/bae*.jar -d bin src/generated/*.java  # change the colons to semi-colons for Windows
       java -classpath mbee_util.jar:sysml.jar:target/bae*.jar:bin generated.Main     # change the colons to semi-colons for Windows
       ```
       
5. Skip to instructions on using with MagicDraw. 

Steps for getting and running the Behavior Analysis Engine (BAE) and optionally setting up the development 
environment: 

1. Download and install Java JDK 1.7 (unfortunately 1.6 isn't good enough). 
  1. Just do a web search on "jdk" 
  2. This link probably does not work, JDK: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1637583.html 
2. For participating in Java, python, or jython development, Eclipse is recommended, but this is optional. Skip to step 3 if not using 
Eclipse. 
  1. Download and install eclipse: http://www.eclipse.org/downloads/### I recommend "for Java EE Developers" 
    1. If on Windows, installing in c:\Program Files\eclipse may avoid having to change the classpath.
  2. Run Eclipse
  3. If you want to do python or jython development, similarly install the PyDev plugin using the update site: http://pydev.org/updates 
  4. Clone the project from github (look for open-mbee/bae).  Sorry, no detailed instructions here.
  5. Fix project build if you have compile errors (red x markers) on folders or files. (Warning: this may be outdated)
    1. If there are errors involving calls to the junit.framework.Assert class, you need to make sure the JUnit4 library is 
added to the build. 
      1. Try right-clicking on an Assert error, choosing Fix project setup, and selecting JUnit4 or the junit.jar for 
org.junit_4xxx. 
      2. If that doesn't work, 
        1. tryadding it from the Libraries tab of Project-> Properties -> Java Build Path. 
        2. It may be located in the Eclipse installation directory in eclipse/plugins/org.junit_4xxx. 
      3. If that doesn't work, you could try to do a web search on "junit" and download and install it. 
    2. This shouldn't happen, but if you have errors on calls to japa.parser.* or org.json.*, then the thirdParty source code 
probably needs to be added to the build. 
      1. Select from the menu Project -> Properties 
      2. Choose Java Build Path from the left pane. 
      3. On the Source tab, choose Add Folder. 
      4. Select the folders 
        1. thirdParty/douglascrockford-JSON-java-xxx and 
        2. thirdParty/javaparser-xxx 
  6. If editing Java code, in order to keep a consistent style, please import the projectFormatterOptions.xml from your git 
checkout into Eclipse. 
    1. You may be able to simply do this as a global import. 
      1. From the menu, File -> Import 
      2. In the outline, General -> Preferences 
      3. Click Browse to find and select the file projectFormatterOptions.xml. 
      4. Click Finish. 
    2. If this doesn't work, or if you want to import these setting just for this project and not others, 
      1. From the menu: Window -> Preferences 
      2. In the outline on the left, Java -> Code Style -> Formatter 
      3. On the right, click Import to find and select the file projectFormatterOptions.xml. 
      4. Click Apply & Ok. 
  7. Run the behavior simulator. 
    1. Convert event/behavior XML into Java behavior analysis code. 
      1. From Eclipse's Package Explorer view of the Java perspective, right-click on the src -> tests -> 
TestEventXmlToJava.java file 
      2. Select Run As -> Java Application 
      3. It will read in a default XML file, and generate Java code in src -> generated. 
    2. Simulate the scenario from the generate Java code. 
      1. Right-click on the src -> generated -> Main.java file 
      2. Select Run As -> Java Application 
      3. A simulation of the scenario and any constraint violations will be printed in the console window (maybe 
after a lot of debug prints). 
  8. You're done! You can skip step 3. 
3. If not using Eclipse, you'll need to build and run it some other way, such as from the command line. 
  1. Get git using homebrew, for example. 
  2. Clone from github (open-mbee/bae)
    1. You can do this from the command line; this example checks out into a local directory named bae
    
        ```bash
        git clone https://github.com/Open-MBEE/bae.git
        ```
            
  3. If you have ant installed, you can run it or compile it from a command line (terminal on MacOS; Command Prompt or 
cmd.exe on Windows). 
    1. In a command prompt/terminal window while in the top-level project directory (e.g., bae) where the AE.jar file 
(binary) lives, 
      1. enter
      
            ```bash
            ant run-generated 
            ```
            
        * to run the default scenario in src/gov/nasa/jpl/ae/xml/exampleScenario.xml,
      2. enter
      
            ```bash
            ant run-generated -DxmlFile=myScenario.xml 
            ```
            
        * to translate myScenario.xml to Java in src/generated, 
        * to compile the generated Java, and 
        * to run the compiled scenario, 
      3. enter
      
            ```bash
            ant xml2java -DxmlFile=myScenario.xml 
            ```
            
        * to translate myScenario.xml to Java, 
      4. enter
      
            ```bash
            ant compile-generated -DxmlFile=myScenario.xml 
            ```
               
        * to translate myScenario.xml to Java, and 
        * to compile the generated java 
  4. If you do not have ant installed, 
    1. In a command prompt/terminal window while in the top-level project directory (e.g., bae) where the AE.jar file 
lives,
      1. enter

            ```bash
            java -jar AE.jar 
            ```
               
        * to translate the default src/xml/exampleScenario.xml into Java code in src/generated, 
      2. enter

            ```bash
            java -jar AE.jar myScenario.xml 
            ```
               
        * to translate myScenario.xml to Java in src/generated, 
      3. enter

            ```bash
            javac -cp AE.jar -d bin src/generated/*.java 
            ```
               
        * to compile the generated Java, 
      4. enter

            ```bash
            java -classpath AE.jar;bin generated.Main 
            ```
               
        * to run the compiled scenario on Windows, 
      5. enter
      
            ```bash
            java -classpath AE.jar:bin generated.Main 
            ```
              
        * to run the compiled scenario on Unix platforms (like MacOS and Linux). 


