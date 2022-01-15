package com.cg.app.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.app.entity.Bill;
import com.cg.app.exceptions.BillException;

public interface IBillService {
	public Bill addBill(Bill bill);
	public Bill updateBill(Bill bill) throws BillException;
	public Bill removeBill(Integer billId) throws BillException;
	public Bill viewBill(Integer billId) throws BillException;
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException;
	public List<Bill> viewBills(String custId) throws BillException;
	public double calculateTotalCost(Bill bill) throws BillException;

}