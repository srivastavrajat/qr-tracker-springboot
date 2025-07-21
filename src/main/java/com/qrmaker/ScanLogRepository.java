package com.qrmaker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ScanLogRepository extends JpaRepository<ScanLog, Long>{
    List<ScanLog> findByQrId(UUID qrId);
}
