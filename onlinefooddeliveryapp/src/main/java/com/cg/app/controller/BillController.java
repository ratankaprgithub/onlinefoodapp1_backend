package com.cg.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.Bill;
import com.cg.app.entity.OrderDetails;
import com.cg.app.service.IBillService;
import com.cg.app.service.IOrderService;

@RequestMapping("/bill")
@RestController
public class BillController {

	@Autowired
	private IBillService billService;
	
	@Autowired
	private  IOrderService orderService;
	

	@PostMapping("/add/{orderid}")
	public Bill addBill(@PathVariable ("orderid") Integer orderId)  {
		OrderDetails order =orderService.viewOrder(orderId);
		
		Bill newBill = new Bill();
		
		newBill.setOrder(order);
	
		return billService.addBill(newBill);
	}
	
	@PutMapping("/update")
	public Bill updateBill(@RequestBody Bill bill){
		return billService.updateBill(bill);
		
	}
	
	@DeleteMapping("/remove/{billId}")
	public Bill removeBill(@PathVariable ("billId") Integer billId){
			
			return billService.removeBill(billId);
			
	}
	

	@GetMapping("/view/{billId}")
	public Bill viewBill(@PathVariable("billId") Integer billId) {
		
		return billService.viewBill(billId);
	
	}
	
	
	@GetMapping("/viewwithdate/{sDate}/{eDate}")
	public List<Bill> viewBills(@PathVariable("sDate")  String sDate, @PathVariable("eDate") String  eDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
		LocalDate startDate = LocalDate.parse(sDate, formatter);
		LocalDate endDate = LocalDate.parse(eDate, formatter);
		return billService.viewBills(startDate, endDate);
		
	}
	
	
	@GetMapping("/viewall/{custId}")
	public List<Bill> viewBills(@PathVariable("custId") Integer custId) {
		List<Bill> billList = billService.viewBills(custId);
		return billList;
	}

	@GetMapping("/totalcost/{billid}")
	public double calculateTotalCost(@PathVariable("billid") Integer billId){
		Bill bill= billService.viewBill(billId);
		return billService.calculateTotalCost(bill);
	}

}