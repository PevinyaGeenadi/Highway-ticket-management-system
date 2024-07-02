package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.userservice.dao.UserRepo;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return List.of();
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

    }

    @Override
    public void deleteUser(String userId) {

    }
}
