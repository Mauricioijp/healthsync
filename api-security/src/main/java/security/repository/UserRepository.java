package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    @Query(value = "SELECT * FROM users WHERE user = ?1", nativeQuery = true)
    public UserEntity findByUser(String user);
}
