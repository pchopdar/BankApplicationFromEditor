package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException;
	
	int showBalance(int accountNumber)throws InvalidAccountNumberException;
	
	boolean withdrawBalance(int accountNumber,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
	
	String fundTransfer(int accountNumberFrom,int accountNumberfrom,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;

}