package main.event;

import java.math.BigDecimal;
import java.util.Optional;

import main.domain.Account;
import main.state.AccountAggregate;

// 提款事件类
public class MoneyTransferEvent extends DomainEvent {
	 
	private final BigDecimal money;
	private final int accountNoFrom;
	private final int accountNoTo;

	public MoneyTransferEvent(long sequenceId, long createdTime, BigDecimal money, int accountNoFrom, int accountNoTo) {
		super(sequenceId, createdTime, "MoneyTransferEvent");
		this.money = money;
		this.accountNoFrom = accountNoFrom;
		this.accountNoTo = accountNoTo;
	}
	  
	@Override
	public void process() {
		Account accountFrom = Optional.ofNullable(AccountAggregate.getAccount(accountNoFrom))
				.orElseThrow(() -> new RuntimeException("Account not found " + accountNoFrom));
		Account accountTo = Optional.ofNullable(AccountAggregate.getAccount(accountNoTo))
		        .orElseThrow(() -> new RuntimeException("Account not found " + accountNoTo));
		accountFrom.handleTransferFromEvent(this);
		accountTo.handleTransferToEvent(this);
	}

	public BigDecimal getMoney() {
		return money;
	}

	public int getAccountNoFrom() {
		return accountNoFrom;
	}

	public int getAccountNoTo() {
		return accountNoTo;
	}

	
}
