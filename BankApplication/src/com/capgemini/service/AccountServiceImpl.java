package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	@Override
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialBalanceException
	{
		if(amount<500)
		{
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
		
	}
	@Override
	public int showBalance(int accountNumber) throws InvalidAccountNumberException {
		int balance = 0;
		Account account = accountRepository.searchAccount(accountNumber);
		if(account!= null){
			balance = account.getAmount();
		}else{
			throw new InvalidAccountNumberException();
		}
		return balance;
	}
	
	
	@Override
	public boolean withdrawBalance(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		
		Account account = accountRepository.searchAccount(accountNumber);
		
		if(account==null){
			
			throw new InvalidAccountNumberException();
			
		}
		else if(account.getAmount()<amount){
			
			throw new InsufficientBalanceException();
			
		}
		else{
			int balance = account.getAmount() - amount;
			account.setAmount(balance);
			
		}
		return true;
	}
	
	
	@Override
	public String fundTransfer(int accountNumberFrom, int accountNumberTo, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		
		Account accountFrom = accountRepository.searchAccount(accountNumberFrom);
		Account accountTo = accountRepository.searchAccount(accountNumberTo);
		
		if(accountFrom==null && accountTo==null){
			
			throw new InvalidAccountNumberException();
		}
		else if(accountFrom.getAmount()<amount){
			
			throw new InsufficientBalanceException();
		}
		else{
			
		}
		
		return null;
	}

}
