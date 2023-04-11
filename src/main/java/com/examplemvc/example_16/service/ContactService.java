package com.examplemvc.example_16.service;

import com.examplemvc.example_16.constants.EazySchoolConstants;
import com.examplemvc.example_16.model.Contact;
import com.examplemvc.example_16.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    //private static Logger log= LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        //int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE,contactId); //usando los metodos @Query
        int rows = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE,contactId);  //usando los metodos @NamedQuery
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
