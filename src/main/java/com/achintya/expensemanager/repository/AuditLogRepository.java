package com.achintya.expensemanager.repository;

import com.achintya.expensemanager.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog,Integer> {

}
