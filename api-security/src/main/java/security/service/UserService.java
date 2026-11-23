package security.service;

import java.util.List;

import security.entity.UserEntity;

public interface UserService {
    
    public List<UserEntity> listUsers();
    public UserEntity getUser(String id);
    public UserEntity createUser(UserEntity user);
    public UserEntity updateUser(UserEntity user);
    public UserEntity deleteUser(int id);

}
