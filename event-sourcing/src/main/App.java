package main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

import main.event.AccountCreateEvent;
import main.event.DomainEvent;
import main.event.MoneyDepositEvent;
import main.event.MoneyTransferEvent;
import main.processor.DomainEventProcessor;
import main.state.AccountAggregate;

public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	public static final int ACCOUNT_OF_DAENERYS = 1;
	  
	public static final int ACCOUNT_OF_JON = 2;

	public static void main(String[] args) {

		DomainEventProcessor eventProcessor = new DomainEventProcessor();


	    LOGGER.info("Running the system first time............");
	    eventProcessor.reset();

	    LOGGER.info("Creating the accounts............");

	    eventProcessor.process(new AccountCreateEvent(
	        0, new Date().getTime(), ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"));

	    eventProcessor.process(new AccountCreateEvent(
	        1, new Date().getTime(), ACCOUNT_OF_JON, "Jon Snow"));

	    LOGGER.info("Do some money operations............");

	    eventProcessor.process(new MoneyDepositEvent(
	        2, new Date().getTime(), ACCOUNT_OF_DAENERYS, new BigDecimal("100000")));

	    eventProcessor.process(new MoneyDepositEvent(
	        3, new Date().getTime(), ACCOUNT_OF_JON, new BigDecimal("100")));

	    eventProcessor.process(new MoneyTransferEvent(
	        4, new Date().getTime(), new BigDecimal("10000"), ACCOUNT_OF_DAENERYS,
	        ACCOUNT_OF_JON));

	    LOGGER.info("...............State:............");
	    LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
	    LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());

	    LOGGER.info("At that point system had a shut down, state in memory is cleared............");
	    AccountAggregate.resetState();

	    LOGGER.info("Recover the system by the events in journal file............");

	    eventProcessor = new DomainEventProcessor();
	    eventProcessor.recover();

	    LOGGER.info("...............Recovered State:............");
	    LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
	    LOGGER.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());
	}


}
