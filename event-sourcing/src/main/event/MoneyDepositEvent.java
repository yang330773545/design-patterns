package main.event;

import java.math.BigDecimal;
import java.util.Optional;

import main.domain.Account;
import main.state.AccountAggregate;

// 存款事件类
public class MoneyDepositEvent extends DomainEvent {
	
	private final BigDecimal money;
	private final int accountNo;

	public MoneyDepositEvent(long sequenceId, long createdTime, int accountNo, BigDecimal money) {
	    super(sequenceId, createdTime, "MoneyDepositEvent");
	    this.money = money;
	    this.accountNo = accountNo;
	}
	
	@Override
	public void process() {
		Account account = Optional.ofNullable(AccountAggregate.getAccount(accountNo))
		        .orElseThrow(() -> new RuntimeException("Account not found"));
		    account.handleEvent(this);
	}

	public BigDecimal getMoney() {
		return money;
	}

	public int getAccountNo() {
		return accountNo;
	}

	
}
