package com.dragon.service;

import com.dragon.entity.Person;
import com.dragon.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;


    public void save(Person person){
        personMapper.save(person);
    }

}
