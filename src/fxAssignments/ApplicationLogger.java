package fxAssignments;

import java.util.logging.*;

public class ApplicationLogger {
	private final static Logger logr = Logger.getLogger( "ApplicationLogger" );
	
	public static Logger createLogger() {
	LogManager.getLogManager().reset();
	logr.setLevel(Level.ALL);
	ConsoleHandler ch = new ConsoleHandler();
	ch.setLevel(Level.ALL); //CHANGE THIS TO ANOTHER LEVEL ONCE DONE
	logr.addHandler(ch);
	
	return logr;
	}

}
