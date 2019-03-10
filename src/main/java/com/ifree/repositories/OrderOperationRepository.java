package com.ifree.repositories;

import com.ifree.repositories.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOperationRepository extends CrudRepository<Order, Long> {

}
