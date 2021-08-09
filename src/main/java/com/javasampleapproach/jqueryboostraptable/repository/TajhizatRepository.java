package com.javasampleapproach.jqueryboostraptable.repository;





import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.Tajhizat;



@Repository
@Transactional
public interface TajhizatRepository extends JpaRepository<Tajhizat, Integer> {

	
	@Query(value = "SELECT * FROM Tajhizat t where t.name like %:keyword% "
			+ "or t.amvalid like %:keyword% or t.model like %:keyword%",nativeQuery = true)
	public List<Tajhizat> search(@Param("keyword") String keyword);
	
	
}
