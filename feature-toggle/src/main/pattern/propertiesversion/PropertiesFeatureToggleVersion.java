package main.pattern.propertiesversion;

import java.util.Properties;

import main.pattern.Service;
import main.user.User;

public class PropertiesFeatureToggleVersion implements Service {
	
	private final boolean isEnhanced;
	
	public PropertiesFeatureToggleVersion(final Properties properties) {
		if (properties == null) {
	        throw new IllegalArgumentException("No Properties Provided.");
	    } else {
	        try {
	        	isEnhanced = (boolean) properties.get("enhancedWelcome");
	        } catch (Exception e) {
	        	throw new IllegalArgumentException("Invalid Enhancement Settings Provided.");
	        }
	    }
	}

	@Override
	public boolean isEnhanced() {
		return isEnhanced;
	}

	@Override
	public String getWelcomeMessage(User user) {
		if (isEnhanced()) {
	        return "Welcome " + user + ". You're using the enhanced welcome message.";
	    }
		return "Welcome to the application.";
	}

}
