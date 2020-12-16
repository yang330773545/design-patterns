package main.domain;

import java.math.BigDecimal;

import main.event.AccountCreateEvent;
import main.event.MoneyDepositEvent;
import main.event.MoneyTransferEvent;
import main.state.AccountAggregate;

/**
 * 保存账户信息
 */
public class Account {

	private final int accountNo;
	private final String owner;
	private BigDecimal money;
	private static final String MSG = "Some external api for only realtime execution could be called here.";
	
	public Account(int accountNo, String owner) {
		this.accountNo = accountNo;
		this.owner = owner;
		this.money = BigDecimal.ZERO;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public String getOwner() {
		return owner;
	}

	public static String getMsg() {
		return MSG;
	}
	
	// 复制
	public Account copy() {
		Account account = new Account(accountNo, owner);
	    account.setMoney(money);
	    return account;
	}
	
	@Override
	public String toString() {
	    return "Account{"
	        + "accountNo=" + accountNo
	        + ", owner='" + owner + '\''
	        + ", money=" + money
	        + '}';
	}
	
	// 存款
	private void depositMoney(BigDecimal money) {
	    this.money = this.money.add(money);
	}
	
	// 提款
	private void withdrawMoney(BigDecimal money) {
	    this.money = this.money.subtract(money);
	}
	
	// 存款处理
	private void handleDeposit(BigDecimal money, boolean realTime) {
	    depositMoney(money);
	    AccountAggregate.putAccount(this);
	    if (realTime) {
	        System.out.println(MSG);
	    }
	}
	
	// 提款处理
	private void handleWithdrawal(BigDecimal money, boolean realTime) {
	    if (this.money.compareTo(money) < 0) {
	        throw new RuntimeException("Insufficient Account Balance");
	    }

	    withdrawMoney(money);
	    AccountAggregate.putAccount(this);
	    if (realTime) {
	    	System.out.println(MSG);
	    }
	}
	
	// 处理事件方法
	public void handleEvent(MoneyDepositEvent moneyDepositEvent) {
		handleDeposit(moneyDepositEvent.getMoney(), moneyDepositEvent.isRealTime());
	}

	public void handleEvent(AccountCreateEvent accountCreateEvent) {
	    AccountAggregate.putAccount(this);
	    if (accountCreateEvent.isRealTime()) {
	    	System.out.println(MSG);
	    }
	}
	
	// 处理提款事件
	public void handleTransferFromEvent(MoneyTransferEvent moneyTransferEvent) {
	    handleWithdrawal(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
	}
	
	// 处理存款事件
	public void handleTransferToEvent(MoneyTransferEvent moneyTransferEvent) {
	    handleDeposit(moneyTransferEvent.getMoney(), moneyTransferEvent.isRealTime());
	}
}
