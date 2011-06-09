package com.fuckingwin.bukkit.rakiru.QuickSeeds;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adds a debug method, and prefixes all log messages with [QuickSeeds]
 */
public class QuickSeedsLogger {

    public static final Logger logger = Logger.getLogger("Minecraft");

    public void debug( String s ) {
        if(QuickSeedsConfig.debugMode) {
	logger.log(Level.INFO, "[QuickSeeds DEBUG] " + s);
	}
    }

    public void info( String s ) {
	logger.log(Level.INFO, "[QuickSeeds] " + s);
    }

    public void severe( String s ) {
	logger.log(Level.SEVERE, "[QuickSeeds] " + s);
    }

    public void warning( String s ) {
	logger.log(Level.WARNING, "[QuickSeeds] " + s);
    }

}