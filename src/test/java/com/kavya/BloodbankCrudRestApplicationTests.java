package com.kavya;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;




import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import com.kavya.exception.DonorNotFoundException;






@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BloodbankCrudRestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@InjectMocks
	private BloodbankService service;
	
	@Mock
	private BloodbankRepository repository;
	

	
	
//	Test Case For Select Query Fetching all records
	
	@Test
	@Order(1)
	public void getUser() throws DonorNotFoundException
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890809090"));
		myuser.add(new  Donor(2,"Kavya" ,"B+","9890809878"));
		
		
		when (repository.findAll()).thenReturn(myuser);
		assertEquals(2,service.getDonor().size());
//		assertEquals(Expected output, actual output)
	}
//  Test Case For Fetching Sorting data
	@Test
	@Order(2)
	public void getUserSort()
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890809878"));
		myuser.add(new  Donor(2,"Prisha" ,"O+","9890809878"));
		
		when (repository.findAll(Sort.by("name"))).thenReturn(myuser);
		assertEquals(2,service.donorsort().size());
		
	}
	

//  Test Case for Select query data fetch by its name
	@Test
	@Order(3)
	public void getUserByName() throws DonorNotFoundException
	{

		String dname = "Madhavi";
		Donor dnd = new Donor(1,"Madhavi","B+","9089786756");
		when (repository.findByName(dname)).thenReturn(dnd);
		assertEquals(dname,service.getDonorByName(dname).getName());
	}
	
	
//  Test Case For Select query find data by its Blood group (method returns list)
	@Test
	@Order(4)
	public void getUserByBloodGroup() throws DonorNotFoundException
	{
		List<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890809878"));
		myuser.add(new  Donor(2,"Nilam" ,"B+","9890806756"));

		String bgroup = "A+";
		
		when (repository.findBybg(bgroup)).thenReturn(myuser);
		assertEquals(bgroup,service.findByBloodGroup(bgroup).get(0).getBg());
		
	}
	
	
// Test Case for Find by Blood group second way using size method
	@Test
	@Order(5)
	public void getUserByBloodGroup2() throws Exception
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
	
		myuser.add(new  Donor(2,"Madhavi" ,"B+","9890800989"));

		String bgroup = "B+";
		
		when (repository.findBybg(bgroup)).thenReturn(myuser);
		assertEquals(1,service.findByBloodGroup(bgroup).size());
		
	}

//Test Case For Insert Query
	@Test
	@Order(6)
	public void insertUser() throws DonorNotFoundException
	{
		Donor  dn= new Donor(1,"Prisha","B+" ,"9876575698");
		when (repository.save(dn)).thenReturn(dn);
		assertEquals(dn,service.storeDonor(dn));
		
	}
// Test Case For Insert Query which Store multiple records
	@Test
	@Order(7)
	public void inserMultipletUser()
	{
		
		List<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890802312"));
		myuser.add(new  Donor(2,"Prisha" ,"B+","9890805656"));

		
		when (repository.saveAll(myuser)).thenReturn(myuser);
		assertEquals(myuser,service.storeMultipleDonor(myuser));
		
	}

	
	
// Test Case for Update Query
	
	@Test
	@Order(9)
	public void updateUser() throws DonorNotFoundException
	{
		Donor  dn= new Donor(1,"Prisha","B+" ,"9876575656");
		when (repository.findById(1)).thenReturn(dn);
	
		dn.setName("Gia");
		dn.setBg("C+");
		dn.setMobileno("123456789");
//		Donor dn1 =repository.save(dn);
		assertEquals("Gia",repository.findById(1).getName());
		assertEquals("C+",repository.findById(1).getBg());
		assertEquals("123456789",repository.findById(1).getMobileno());
	
		
		
	
		
	}
// Test case for delete Query  by its id

	@Test
	@Order(10)
	public void deleteUser() throws DonorNotFoundException	{
		
		int did= 1;
		
		when (repository.findById(did)).thenReturn(new Donor(1,"Kavya","A+","9078567656"));
		service.deleteDonor(1);
		verify(repository,times(1)).deleteById(1);
	}

	@Test
	@Order(15)
	public void findByIdsecond() throws DonorNotFoundException
	{
	
//		Donor donor = new Donor(1,"Prisha","B+","9898080808");
		int did= 1;
		
		when (repository.findById(did)).thenReturn(new Donor(1,"Kavya","A+","9078567656"));
		assertEquals(did,service.DonorById(did).getId());
		
//		when (repository.findById(did)).thenReturn(myuser).orElse(null);
//		assertEquals(did,service.DonorById(did).getId());
//	
		
	}
	
	
	
	
	
// Test case For delete record by object(Verify methods)
	@Test
	@Order(11)
	public void deleteuserobject()
	{
		Donor donor = new Donor(1,"Payal","B+","9090989080");
		service.deleteDonorByObject(donor);
		verify(repository,times(1)).delete(donor);
	}
	
// Test case For one Combination
	@Test
	@Order(13)
	public void searchcombinationone()
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890890904"));
		myuser.add(new  Donor(2,"Prisha" ,"B+","9890800987"));
		
		
		String query1 = "Madhavi";
		String query2 =  "A+";
		String query3	= "9890890904";
		int query4 = 1;
		String query5= Integer.toString(query4);
		
//		 For Name 
		when (repository.searchCombinationek(query1)).thenReturn(myuser);
		assertEquals(query1,service.searchcombinationone(query1).get(0).getName());
		
//      For Blood Group
		when (repository.searchCombinationek(query2)).thenReturn(myuser);
		assertEquals(query2,service.searchcombinationone(query2).get(0).getBg());

//      For Mobile No
		when (repository.searchCombinationek(query3)).thenReturn(myuser);
		assertEquals(query3,service.searchcombinationone(query3).get(0).getMobileno());
		
//     For Id Number
		when (repository.searchCombinationek(query5)).thenReturn(myuser);
		assertEquals(query4,service.searchcombinationone(query5).get(0).getId());
		

	}
	
//Test case for Native Query for two combination
	
	@Test
	@Order(12)
	public void searchtwocombination()
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890809078"));
		myuser.add(new  Donor(2,"Pishu" ,"B+","9890807676"));
		
		String query1 = "Madhavi";
		String query2 =  "A+";
		String query3	= "9890809078";
		
//		 For Name and BloodGroup
		when (repository.searchCombinationbe(query1,query2)).thenReturn(myuser);
		assertEquals(query1,service.searchcombinatontwo(query1,query2).get(0).getName());
		
		when (repository.searchCombinationbe(query1,query2)).thenReturn(myuser);
		assertEquals(query2,service.searchcombinatontwo(query1,query2).get(0).getBg());

//		For BloodGroup and Mobile No
		
		when (repository.searchCombinationbe(query2,query3)).thenReturn(myuser);
		assertEquals(query2,service.searchcombinatontwo(query2,query3).get(0).getBg());
		
		when (repository.searchCombinationbe(query2,query3)).thenReturn(myuser);
		assertEquals(query3,service.searchcombinatontwo(query2,query3).get(0).getMobileno());
//		For Mobile No and Name 
		
		when (repository.searchCombinationbe(query3,query1)).thenReturn(myuser);
		assertEquals(query3,service.searchcombinatontwo(query3,query1).get(0).getMobileno());
		
		when (repository.searchCombinationbe(query3,query1)).thenReturn(myuser);
		assertEquals(query1,service.searchcombinatontwo(query3,query1).get(0).getName());

	}
// Native Query for Three combination
	@Test
	@Order(13)
	public void searchcombinationthree()
	{
		ArrayList<Donor>  myuser = new ArrayList<Donor>();
		myuser.add(new  Donor(1,"Madhavi" ,"A+","9890809089"));
		myuser.add(new  Donor(2,"Prisha" ,"B+","9890807656"));
		
		String query1 = "Madhavi";
		String query2 =  "A+";
		String query3	= "9890809089";
		
//		 For Name and BloodGroup and Mobile no
		when (repository.searchCombinationthin(query1,query2,query3)).thenReturn(myuser);
		assertEquals(query1,service.searchcombinationthree(query1,query2,query3).get(0).getName());
		
		when (repository.searchCombinationthin(query1,query2,query3)).thenReturn(myuser);
		assertEquals(query2,service.searchcombinationthree(query1,query2,query3).get(0).getBg());

		when (repository.searchCombinationthin(query1,query2,query3)).thenReturn(myuser);
		assertEquals(query3,service.searchcombinationthree(query1,query2,query3).get(0).getMobileno());

		
	

	}


		
		
	
	
	
	
	
	
	

	

}
