package com.example.demo.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PatientTokenController {
	
	    @GetMapping(value = "/abha/account/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> getReports(@PathVariable("id") String id) {
	        return new ResponseEntity<>("GET Reports API triggered", null, HttpStatus.SC_OK);
	    }
	    
	    
	    @DeleteMapping("/reports/{id}")
	    public ResponseEntity<Object> deleteReport(@PathVariable String id) {
	        return new ResponseEntity<>("DELETE Reports API triggered", null, HttpStatus.SC_OK);
	    }
	    
	   
//	    @DeleteMapping(value="/report/delete/{id}")
//	    public ResponseEntity<Object> deleteReport(@PathVariable String id) {
//	        return new ResponseEntity<>("DELETE Reports API triggered"+id, null, HttpStatus.SC_OK);
//	    }
	    
	    @PutMapping(value="/abha/account/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> updateReport(@PathVariable String id) {
	        return new ResponseEntity<>("UPDATE Reports API triggered"+id, null, HttpStatus.SC_OK);
	    }
//	    
	    
//	    @PostMapping(value = "/report/billing/", produces = MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<Object> addReportBill() {
//	        return new ResponseEntity<>("Post Report Billing API triggered", null, HttpStatus.SC_OK);
//	    }
	    
	    
//	    @DeleteMapping(value = "/report/billing/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<Object> deleteReportBill(@PathVariable("id") String id) {
//	        return new ResponseEntity<>("DELETE Report Billing API triggered"+id, null, HttpStatus.SC_OK);
//	    }
//	  
//	    @PutMapping(value="/report/billing/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<Object> updateReportBilling(@PathVariable String id) {
//	        return new ResponseEntity<>("UPDATE Report Billing API triggered"+id, null, HttpStatus.SC_OK);
//	    }
	    
	    @PutMapping("/reports/get/records")
	    public ResponseEntity<Object> getreport()
	    {
	    	return ResponseEntity.ok("reports are fetched");
	    }
	
	
	    
	    @GetMapping("/reports/get/resource")
	    public ResponseEntity<String> getResource() {
	        String message = "Hello";

	        return ResponseEntity.ok(message);
	    }
	   

}
