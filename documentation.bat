set CLASSPATH=src;json.jar;junit.jar;C:\JUnit\junit-4.10.jar
javadoc -d doc com.csc.model com.csc.io com.csc.ui
forever stopall & forever start .\.serve.js