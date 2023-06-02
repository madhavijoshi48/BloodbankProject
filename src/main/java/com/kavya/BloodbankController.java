package com.kavya;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.kavya.exception.DonorListNotFoundException;
import com.kavya.exception.DonorNotFoundException;






@RestController
@RequestMapping("donor")
public class BloodbankController {


	private static final Logger LOGGER =LoggerFactory.getLogger(BloodbankController.class);
	
	@Autowired
	private BloodbankService service;
	
	@GetMapping("")
	public java.util.List<Donor> alldonor() throws DonorNotFoundException {
		
		LOGGER.info("Inside  Donor Controller Loggers");
		return service.getDonor();
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("findbyid/{id}") 
	public Donor findDonorById(@PathVariable int id)  throws DonorNotFoundException {
		LOGGER.info("Inside findbyid Controller ");
		return service.DonorById(id);
	}
	@GetMapping("findbyname/{name}")
	public Donor findDonorByName(@PathVariable String name) throws DonorNotFoundException{
		LOGGER.info("Inside findbyname Controller Loggers");
		return service.getDonorByName(name);
	}
	
	
	@GetMapping("findbynameandbg/{name}/{bg}")
	public Donor findDonorByNameandBg(@PathVariable String name,@PathVariable String bg) throws DonorNotFoundException {
		LOGGER.info("Inside findbyname Controller Loggers");
		return service.getDonorByNameandbloodgroup(name,bg);
	}
	
	
	
	
	@GetMapping("findbybg/{bg}")  
	public java.util.List<Donor> findDonorByGroup(@PathVariable String bg) throws DonorNotFoundException{
		LOGGER.info("Inside findbybg Controller loggers");
//		LOGGER.debug("Inside Controller Loggers findbyname loggers debug");
		return service.findByBloodGroup(bg);
	}
	


	
	
	@PostMapping("adddonor")
	public Donor addDonor(@RequestBody Donor donor) throws DonorNotFoundException {
		LOGGER.info("Inside adddonor controller loggers");
		return service.storeDonor(donor);
	}
	@PostMapping("adddonors")
	public List<Donor> addDonors(@RequestBody java.util.List<Donor> donor){
		
		LOGGER.info("Inside adddonors controller loggers");
		return service.storeMultipleDonor(donor);
	}
	
	
	
	
	@GetMapping("sortdonor")
	public java.util.List<Donor> sortingdonor(){
		LOGGER.info("Inside sortdonor controller loggers");
		return service.donorsort();
	}
	@PutMapping("updateDonor")
	public Donor updatedonordetails(@RequestBody Donor donor) throws DonorNotFoundException{
		LOGGER.info("Inside updatedonor controller loggers");
		return service.updatedonor(donor);
	}
	@DeleteMapping("deletedonor/{id}")
	public String deletedonordetails(@PathVariable int id) throws DonorNotFoundException{
		LOGGER.info("Inside deletedonor controller loggers");
		return service.deleteDonor(id);
	}
	
	@DeleteMapping("deletedonorobject")
	public String deletedonordetails(@RequestBody Donor donor)  throws DonorNotFoundException{
		LOGGER.info("Inside deletedonorobject controller loggers");
		return service.deleteDonorByObject(donor);
	}
	
 //	  Native Query-----------------------------
	
	
	@GetMapping("/search")
    public ResponseEntity<List<Donor>> searchCombination1(@RequestParam("query") String query){
        return ResponseEntity.ok(service.searchcombinationone(query));
    }
    
    @GetMapping("/searchtwo")
    public ResponseEntity<List<Donor>> searchCombination2(@RequestParam("query1") String query1, @RequestParam("query2") String query2)
    {
        return ResponseEntity.ok(service.searchcombinatontwo(query1, query2));
    }
    
    @GetMapping("/searchthree")
    public ResponseEntity<List<Donor>> searchCombination3(@RequestParam("query1") String query1, @RequestParam("query2") String query2,@RequestParam("query3") String query3)
    {
        return ResponseEntity.ok(service.searchcombinationthree(query1, query2, query3));
    }
    
    @GetMapping("/searchall")
    public ResponseEntity<List<Donor>> searchCombinationAll(@RequestParam("query1") String query1, @RequestParam("query2") String query2,@RequestParam("query3") String query3,@RequestParam("query4") String query4)
    {
        return ResponseEntity.ok(service.searchcombinationall(query1, query2, query3,query4));
    }
	
	
	
	
	
	
	
	
	
	
	
	

}
