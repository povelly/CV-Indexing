package com.cvservice.repositories.db;

import com.cvservice.models.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CVRepository extends CrudRepository<Candidate, String> {

    List<Candidate> findByPhone(String phone);

    List<Candidate> findByLastName(String lastName);

    List<Candidate> findBySkillsName(String name);

    List<Candidate> findBySkillsName(List<String> name);

}


