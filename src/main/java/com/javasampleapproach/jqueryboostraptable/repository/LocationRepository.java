package com.javasampleapproach.jqueryboostraptable.repository;



import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javasampleapproach.jqueryboostraptable.model.Location;


@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Integer> {
  
	List<Location> findByname(String name); 
	
}
