package main.event;

import main.domain.Account;
import main.state.AccountAggregate;

// 账户创建必要事件
public class AccountCreateEvent extends DomainEvent {
	
	private final int accountNo;
	private final String owner;
	
	public AccountCreateEvent(long sequenceId, long createdTime, int accountNo, String owner) {
	    super(sequenceId, createdTime, "AccountCreateEvent");
	    this.accountNo = accountNo;
	    this.owner = owner;
	}

	@Override
	public void process() {
		Account account = AccountAggregate.getAccount(accountNo);
	    if (account != null) {
	        throw new RuntimeException("Account already exists");
	    }
	    account = new Account(accountNo, owner);
	    account.handleEvent(this);
	}

	public int getAccountNo() {
		return accountNo;
	}

	public String getOwner() {
		return owner;
	}
	
}
