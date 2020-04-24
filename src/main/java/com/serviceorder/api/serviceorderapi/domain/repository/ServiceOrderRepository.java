package com.serviceorder.api.serviceorderapi.domain.repository;

import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

}
