package main.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import main.domain.Account;

// 此类保存账户状态
public class AccountAggregate {

	private static Map<Integer, Account> accounts = new HashMap<>();
	
	private AccountAggregate() {}
	
	public static void putAccount(Account account) {
	    accounts.put(account.getAccountNo(), account);
	}
	
	public static Account getAccount(int accountNo) {
	    return Optional.of(accountNo)
	        .map(accounts::get)
	        .map(Account::copy)
	        .orElse(null);
	}
	
	public static void resetState() {
	    accounts = new HashMap<>();
	}
}
