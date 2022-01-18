package com.cg.app.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Bill;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.BillException;
import com.cg.app.repository.IBillRepository;


@Service
public class IBillServiceImpl implements IBillService{

	
	
	@Autowired
	private IBillRepository billRepo;
	
	
	
	@Override
	public Bill addBill(Bill bill) {

		if(bill!=null) {

			Optional<Bill> opt= billRepo.findById(bill.getBillId());
			
			if(!opt.isPresent()){
				
				if(bill.getOrder().getCart().getItem_cartQty().size()>0) {
					
					Integer totalItemCount = bill.getOrder().getCart().getItem_cartQty().size();
					
					bill.getOrder().setOrderStatus("ORDER CONFIRMED");

					bill.setBillDate(LocalDateTime.now());
					bill.setTotalItem(totalItemCount);
					bill.setTotalCost(calculateTotalCost(bill));

					return billRepo.save(bill);	
				}
			
			}
			throw new BillException("Bill already exists with id "+bill.getBillId());
		
		}throw new BillException("Check yout bill id");

	
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		
			if (billRepo.existsById(bill.getBillId())) {
				return billRepo.save(bill);
			}
		
		throw new BillException("Bill Does Not Exists");
	}

	@Override
	public Bill removeBill(Integer billId) throws BillException {
		
		Bill bill= billRepo.findById(billId).orElseThrow(() -> new BillException("Invalid Bill Id"));
		
		billRepo.delete(bill);
		
		return bill;
	}

	@Override
	public Bill viewBill(Integer billId) throws BillException {
		
		Bill bill= billRepo.findById(billId).orElseThrow(() -> new BillException("Invalid Bill Id"));
			
		return bill;
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		
		LocalDateTime startDateB = startDate.atStartOfDay();  //date T00:00:00
		LocalDateTime endDateB = endDate.atStartOfDay();
		
		List<Bill> billListSent = new ArrayList<Bill>();
		
		
		List<Bill> billList = billRepo.findAll();
		
		for (Bill bl: billList) {
			if (bl.getBillDate().isAfter(startDateB) && bl.getBillDate().isBefore(endDateB)) {
				billListSent.add(bl);
			}
		}
		if (billListSent.isEmpty()) {
			throw new BillException("No Bills present between "+startDate+" - "+endDate);
		}
		return billListSent;
	}

	@Override
	public List<Bill> viewBills(Integer custId) throws BillException {
		
		List<Bill> billList = billRepo.findAll();
		List<Bill> custBillList = new ArrayList<Bill>();
		for (Bill b: billList) {
			if(b.getOrder().getCart().getCustomer().getCustomerId() == custId) {
				custBillList.add(b);
			}
		}
		if(custBillList.size()>0)
			return billList;
		throw new BillException("No bill customer "+custId);
		
		
	}

	@Override
	public double calculateTotalCost(Bill bill) throws BillException {
		
		if (bill != null) {
			
			if (billRepo.existsById(bill.getBillId())) {
				
				Bill existingBill = billRepo.findById(bill.getBillId()).get();
				
				Map<Item,Integer> cartItems = existingBill.getOrder().getCart().getItem_cartQty();
			
				Set<Item> items=cartItems.keySet();
				
				Double totalCost = 0.0;
				
				for(Item i:items) {
					
					Integer qty = cartItems.get(i);
					totalCost+=((i.getCost())*qty);
				}
			
				return totalCost;
			}
			throw new BillException("Bill Does Not exists with id "+bill.getBillId());
		}
		throw new BillException("Bill Does Not Exists");
		
		
		
		
		
		
		
	}
	
}