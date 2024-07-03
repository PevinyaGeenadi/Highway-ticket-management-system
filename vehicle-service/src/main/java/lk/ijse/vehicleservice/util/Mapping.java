package lk.ijse.vehicleservice.util;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class Mapping {
    private final ModelMapper mapper;
    Mapping(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public VehicleDTO toVehicleDTO (VehicleEntity vehicleEntity) {
        return mapper.map(vehicleEntity, VehicleDTO.class);
    }

    public VehicleEntity toVehicleEntity (VehicleDTO vehicleDTO) {
        return mapper.map(vehicleDTO, VehicleEntity.class);
    }

    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntities) {
        return vehicleEntities.stream()
                .map(vehicleEntity -> mapper.map(vehicleEntity, VehicleDTO.class))
                .collect(Collectors.toList());
    }

}
