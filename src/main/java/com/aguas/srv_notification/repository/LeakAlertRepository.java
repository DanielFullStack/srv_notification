package com.aguas.srv_notification.repository;

import com.aguas.srv_notification.model.PressureReading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeakAlertRepository extends JpaRepository<PressureReading, Long> {
}
