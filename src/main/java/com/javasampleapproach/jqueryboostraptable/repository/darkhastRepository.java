package com.javasampleapproach.jqueryboostraptable.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.Tajhizat;
import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.model.darkhast;

@Repository
@Transactional
public interface darkhastRepository extends JpaRepository<darkhast, Long> {

	List<darkhast> findByDarkhastdahandeid(String darkhastdahandeid);
	List<darkhast> findByUsers(List<User> user);
	List<darkhast> findByMonghazi(Integer monghazi);
	List<darkhast> findByTajhizats(Optional<Tajhizat> id);
	List<darkhast> findByTajhizatsAndMonghazi(List<Tajhizat> id,Integer monghazi);
}
