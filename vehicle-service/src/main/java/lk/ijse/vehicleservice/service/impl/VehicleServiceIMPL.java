package lk.ijse.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.vehicleservice.dao.VehicleRepo;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.Mapping;
import lk.ijse.vehicleservice.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    private final VehicleRepo vehicleRepo;
    private final Mapping mapping;
    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        long vehicleCount = vehicleRepo.count();
        String vehicleId = String.format("V%04d", vehicleCount + 1);
        vehicleDTO.setVehicleId(vehicleId);
        return mapping.toVehicleDTO(vehicleRepo.save(mapping.toVehicleEntity(vehicleDTO)));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        if (vehicleRepo.existsById(vehicleId)){
            vehicleRepo.deleteById(vehicleId);
        } else throw new NotFoundException("Vehicle not found" + vehicleId);
    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleId) {
        VehicleEntity vehicleEntity = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found" + vehicleId));
        return mapping.toVehicleDTO(vehicleEntity);
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return mapping.toVehicleDTOList(vehicleRepo.findAll());
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle Not Found" + vehicleId));
        if (vehicleEntity != null) {
            vehicleEntity.setLicensePlate(vehicleDTO.getLicensePlate());
            vehicleEntity.setBrand(vehicleDTO.getBrand());
            vehicleEntity.setModel(vehicleDTO.getModel());
            vehicleEntity.setUserId(vehicleDTO.getUserId());
            vehicleRepo.save(vehicleEntity);
        }
    }
    @Override
    public List<VehicleDTO> getVehicleByUserId(String userId) {
        return mapping.toVehicleDTOList(vehicleRepo.findByUserId(userId));
    }
}
