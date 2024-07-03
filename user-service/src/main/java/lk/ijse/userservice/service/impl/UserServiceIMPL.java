package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.userservice.dao.UserRepo;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.Mapping;
import lk.ijse.userservice.util.exception.NotFoundException;
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
        long userCount = userRepo.count();
        String userId = String.format("U%04d", userCount + 1);
        userDTO.setId(userId);
        return mapping.toUserDto(userRepo.save(mapping.toUserEntity(userDTO)));
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found" + userId));

        return mapping.toUserDto(userEntity);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return mapping.toUserDTOList(userRepo.findAll());
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found" + userId));

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
        if (userRepo.existsById(userId)){
            userRepo.deleteById(userId);
        } else throw new NotFoundException("User not found" + userId);
    }

    @Override
    public boolean isUserExists(String userId) {
        return userRepo.existsById(userId);
    }
}
