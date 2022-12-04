package com.boursinos.hrplatform.repositories.scheduler;

import com.boursinos.hrplatform.model.scheduler.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}