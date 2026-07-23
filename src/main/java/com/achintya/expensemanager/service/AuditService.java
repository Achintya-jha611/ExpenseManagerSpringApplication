package com.achintya.expensemanager.service;

import com.achintya.expensemanager.model.AuditLog;
import com.achintya.expensemanager.repository.AuditLogRepository;
import com.achintya.expensemanager.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AuditService {
    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);
    private AuditLogRepository auditLogRepository;
    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAuditLog(String action) {
        AuditLog auditLog = new AuditLog(action,LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }
}
