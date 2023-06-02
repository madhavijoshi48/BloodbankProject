package com.kavya;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;



//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import com.kavya.exception.DonorNotFoundException;





@Service
public class BloodbankService {
	
	
	final Logger LOGGER =LogManager.getLogger(BloodbankService.class);

	
	@Autowired
	private BloodbankRepository repository;
	
	
	
//	@PostConstruct    //init method of bean life cycle
//	private void initDB()
//	{
//		repository.saveAll(Stream.of(new Donor("Gautam","B","1212121212"),
//				new Donor("Nayan","G","656547657"),
//				new Donor("Sagar","H","424680881"),
//				new Donor("Akshat","g","1787979878")
//				).collect(Collectors.toList()));
//		
//	}
	
	public java.util.List<Donor> getDonor() throws DonorNotFoundException    //Show all records  select query
	{
		List<Donor> donors = repository.findAll();
		if(donors.size()==0)
		{
			throw new DonorNotFoundException("Thrie is no record avilable or enter correct url");
			
		}
		else
		{
			LOGGER.info("Inside get Donner Service Method");
			return repository.findAll();
		}
		
	}
	
	public Donor getDonorByNameandbloodgroup(String name,String bg) throws DonorNotFoundException //Get data by its name and blood group
	{
			if(repository.findByNameAndBg(name,bg)!=null)
			{	
				LOGGER.info("Inside getDonnerByName Service Method");
				
				
				return repository.findByNameAndBg(name,bg);
			}
			else
			{
				LOGGER.info("Inside getDonnerByName Service Method");
				throw new DonorNotFoundException("Donor not found with this name and Blood group");
			}
			
	}
	
	
	public Donor getDonorByName(String name) throws DonorNotFoundException    //Show product by it's name
	{
		if(repository.findByName(name)!=null)
		{
			LOGGER.info("Inside getDonnerByName Service Method");
			return repository.findByName(name);
		}
		else
		{
			throw new DonorNotFoundException("Donor not found with this name"+" "+name);
		}
		
	}
	public java.util.List<Donor> findByBloodGroup(String bg) throws DonorNotFoundException
	{
		List<Donor> donors = repository.findBybg(bg);
		if(!donors.isEmpty())
		{
			LOGGER.info("Inside findByBloodgroup Service Method");
			return repository.findBybg(bg);
		}
		else
		{
				System.out.println("Exception throw");
				throw new DonorNotFoundException("Thier is no such record with this blood group" +bg);
//				throw new Exception("Thier is no records for this blood group" + bg);
		}
		
			
	}
	public Donor storeDonor(Donor donor)  throws DonorNotFoundException  // it stored the product like insert query
	{
		
		Donor donor2 = repository.findById(donor.getId());
		if(donor2==null)
		{
//			LOGGER.info("Inside StoreDonor Service Method");
			return repository.save(donor);
		}
		else
		{
			throw new DonorNotFoundException("This donor record is already  exist in database you can't repeat" );
		}
	}
	public java.util.List<Donor> storeMultipleDonor(List<Donor> donor)    // it stored the product like insert query multiple query
	{
//		LOGGER.info("Inside StoreMultipleDonor Service Method");
		return repository.saveAll(donor);
	}
	
	
	
	
	public java.util.List<Donor> donorsort()    //Show product after sorting
	{
//		LOGGER.info("Inside Donnorsort Service Method");
		return repository.findAll(Sort.by("name"));
	}
	public Donor updatedonor(Donor donor) throws DonorNotFoundException
	{
		Donor existingDonor = repository.findById(donor.getId());
		if(existingDonor != null)
		{
			existingDonor.setName(donor.getName());
			existingDonor.setBg(donor.getBg());
			existingDonor.setMobileno(donor.getMobileno());
			LOGGER.info("Inside Updatedonor Service Method");
			return repository.save(existingDonor);
		}
		else
		{
			throw new DonorNotFoundException("Donor with this id can not be updated"+donor.getId() );
		}
	}
	
//	
	public String deleteDonor(int id)  throws DonorNotFoundException
	{
			if(repository.findById(id)!=null)
			{
				LOGGER.info("Inside Delete Service Method");
				repository.deleteById(id);
			
				return "Donor data get deleted sucessfully";
			}
			else
			{
				throw new DonorNotFoundException("Donor with this id delte not found"+ " "+id);
			}
			
		
		
	}
	public String deleteDonorByObject(Donor donor)
	{
		LOGGER.info("Inside DeleteDonorbyobject Service Method");
		repository.delete(donor);
		
		return "Donor data get deleted sucessfully";
	}
	public Donor DonorById(int id) throws DonorNotFoundException
	{
		if(repository.findById(id)!=null)
		{
//			LOGGER.info("Inside DonorById Service Method");
			return repository.findById(id);
		}
		else
			throw new DonorNotFoundException("Donor not found with this id"+" " + id);
	}
	
	
//  Native Query---------------------
	
	
	public List<Donor> searchcombinationone(String query) {
		List<Donor> donor=repository.searchCombinationek(query);
//		LOGGER.info("Inside Searchcombination Service Method");
		return donor;
	}

	public List<Donor> searchcombinatontwo(String query1, String query2) {
		List<Donor> donor=repository.searchCombinationbe(query1,query2);
//		LOGGER.info("Inside SearchCombinationtwo Service Method");
		return donor;
	}

	public List<Donor> searchcombinationthree(String query1, String query2, String query3) {
		List<Donor> donor=repository.searchCombinationthin(query1,query2,query3);
//		LOGGER.info("Inside SearchCombinationthree Service Method");
		return donor;
	}

	public List<Donor> searchcombinationall(String query1, String query2, String query3, String query4) {
		List<Donor> donor=repository.searchCombinationchar(query1,query2,query3,query4);
//		LOGGER.info("Inside SearchCombinationall Service Method");
		return donor;
	}


	
	
	
	


}
