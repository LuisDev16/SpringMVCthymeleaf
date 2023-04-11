package com.examplemvc.example_16.repository;

import com.examplemvc.example_16.model.Contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact,Integer>, CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);
    //@Query("SELECT c From Contact c WHERE c.status= :status") //Ejemplo con JPQL
    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true) //Ejemplo con query nativo
    Page<Contact> findByStatus(String status, Pageable pageable);

    //Creando un query para update el status de una notificacion de abierto a cerrado
    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status= ?1 WHERE c.contactId= ?2")
    int updateStatusById(String status, int id);

   //Metodos relacionados al @NameQuery en nuestro entity Contact
    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);

    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}
