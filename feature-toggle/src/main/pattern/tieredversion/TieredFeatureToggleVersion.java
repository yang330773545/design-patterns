package main.pattern.tieredversion;

import main.pattern.Service;
import main.user.User;
import main.user.UserGroup;

public class TieredFeatureToggleVersion implements Service {

	@Override
	public String getWelcomeMessage(User user) {
		if (UserGroup.isPaid(user)) {
		      return "You're amazing " + user + ". Thanks for paying for this awesome software.";
		}

	    return "I suppose you can use this software.";
	}

	@Override
	public boolean isEnhanced() {
		return true;
	}

}
