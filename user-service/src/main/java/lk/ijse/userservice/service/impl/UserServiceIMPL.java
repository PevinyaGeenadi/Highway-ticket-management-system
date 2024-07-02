package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.userservice.dao.UserRepo;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final Mapping mapping;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return mapping.toUserDto(userRepo.save(mapping.toUserEntity(userDTO)));
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return mapping.toUserDto(userRepo.findById(userId).orElse(null));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return mapping.toUserDTOList(userRepo.findAll());
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);

        if (userEntity != null) {
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());
            userRepo.save(userEntity);
        }

    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}
