package com.kavya;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//  This is my personal project Repository





@Repository
public interface BloodbankRepository extends JpaRepository<Donor,Integer>
{
	Donor findByName(String name);
	Donor findById(int id);
	Donor findByNameAndBg(String name,String bg);
	java.util.List<Donor> findBybg(String bg);
	
	
	@Query("SELECT p FROM Donor p WHERE " +
			"(p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query, '%')")
	List<Donor> searchCombinationek(String query);
	
	@Query("SELECT p FROM Donor p WHERE " +
			"(p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query1, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query2, '%')")
   List<Donor> searchCombinationbe(String query1,String query2);
	

	@Query("SELECT p FROM Donor p WHERE " +
			"(p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query1, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query2, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query3, '%')"+
			"Or (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query1, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query2, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query3, '%')"+
			"Or (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query1, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query2, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query3, '%')")
	List<Donor> searchCombinationthin(String query1, String query2, String query3);

	
	@Query("SELECT p FROM Donor p WHERE " +
			"(p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query1, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query2, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query3, '%') AND (p.id || p.name || p.bg || p.mobileno) LIKE CONCAT('%', :query4, '%')")
   List<Donor> searchCombinationchar(String query1, String query2, String query3, String query4);
	

	
	
}
