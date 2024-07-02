package lk.ijse.userservice.controller;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/health")
    public String healthTest() {
        return "User Service is up and running";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        logger.info("Save User Details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            UserDTO savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception exception) {
            logger.error("Error saving User: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error: | User saved Unsuccessfully.Please try again later.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        logger.info("Getting all Users");
        try {
            List<UserDTO> userDTOList = userService.getAllUser();
            return ResponseEntity.ok(userDTOList);
        } catch (Exception exception) {
            logger.error("Error Getting all Users: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to get Users.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        logger.info("Getting user with ID: {}", userId);
        try {
            UserDTO userDTO = userService.getSelectedUser(userId);
            return ResponseEntity.ok(userDTO);
        } catch (Exception exception) {
            logger.error("Error getting user by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to get user.\nMore Details\n" + exception);
        }
    }
}
