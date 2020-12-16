package main;

import java.util.Properties;

import main.pattern.Service;
import main.pattern.propertiesversion.PropertiesFeatureToggleVersion;
import main.pattern.tieredversion.TieredFeatureToggleVersion;
import main.user.User;
import main.user.UserGroup;

public class App {

	public static void main(String[] args) {
		final Properties properties = new Properties();
		properties.put("enhancedWelcome", true);
		Service service = new PropertiesFeatureToggleVersion(properties);
		String welcomeMessage = service.getWelcomeMessage(new User("Jamie No Code"));
		System.out.println(welcomeMessage);
		
		final Properties turnedOff = new Properties();
		turnedOff.put("enhancedWelcome", false);
		Service turnedOffService = new PropertiesFeatureToggleVersion(turnedOff);
		final String welcomeMessageturnedOff = turnedOffService.getWelcomeMessage(new User("Jamie No Code"));
		System.out.println(welcomeMessageturnedOff);
		
		Service service2 = new TieredFeatureToggleVersion();
		final User paidUser = new User("Jamie Coder");
	    final User freeUser = new User("Alan Defect");
	    UserGroup.addUserToPaidGroup(paidUser);
	    UserGroup.addUserToFreeGroup(freeUser);
	    final String welcomeMessagePaidUser = service2.getWelcomeMessage(paidUser);
	    final String welcomeMessageFreeUser = service2.getWelcomeMessage(freeUser);
	    System.out.println(welcomeMessagePaidUser);
	    System.out.println(welcomeMessageFreeUser);
	}
}
