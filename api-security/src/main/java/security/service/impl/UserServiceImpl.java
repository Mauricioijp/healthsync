package security.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import security.entity.UserEntity;
import security.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final security.repository.UserRepository userRepository;

    @Override
    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(String user) {
        return userRepository.findByUser(user);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(int id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setState(0); // Assuming 0 represents the deleted state
            user = userRepository.save(user);
        }
        return user;
    }


}
