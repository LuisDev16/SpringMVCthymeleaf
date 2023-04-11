package com.examplemvc.example_16.repository;

import com.examplemvc.example_16.model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass,Integer> {

}
