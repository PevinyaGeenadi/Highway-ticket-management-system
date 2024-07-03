package lk.ijse.vehicleservice.dao;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String>{
    List<VehicleEntity> findByUserId(String userId);
}
