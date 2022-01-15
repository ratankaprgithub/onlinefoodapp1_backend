package com.cg.app.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.app.entity.Bill;
import com.cg.app.exceptions.BillException;


@Service
public class IBillServiceImpl implements IBillService{

	
	@Override
	public Bill addBill(Bill bill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill removeBill(Integer billId) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill viewBill(Integer billId) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> viewBills(String custId) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateTotalCost(Bill bill) throws BillException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}