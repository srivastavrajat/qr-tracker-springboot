package com.qrmaker;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRDataRepository extends JpaRepository<QRData, UUID>{
}
