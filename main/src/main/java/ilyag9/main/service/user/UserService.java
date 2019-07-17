package ilyag9.main.service.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

public interface UserService {

    String login(String userName,String password);
    
    Optional<User> getByToken(String token);
}
