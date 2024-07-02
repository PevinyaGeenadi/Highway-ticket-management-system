package lk.ijse.userservice.dao;

import lk.ijse.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,String> {
        Optional<UserEntity> findByEmail(String email);
}
