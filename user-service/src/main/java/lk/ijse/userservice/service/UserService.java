package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);          // Create
    UserDTO getSelectedUser(String userId);     // Read (details of a specific user based on their ID
    List<UserDTO> getAllUser();                 // Read
    void updateUser(String userId, UserDTO userDTO); // Update
    void deleteUser(String userId);             // Delete
    boolean isUserExists(String userId);
}
