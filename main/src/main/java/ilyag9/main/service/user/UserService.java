package ilyag9.main.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import ilyag9.main.rest.dto.UserDto;

public interface UserService {

    String login(String userName,String password);
    
    Optional<User> getByToken(String token);
    
    List<UserDto> getAllUsers();
}
