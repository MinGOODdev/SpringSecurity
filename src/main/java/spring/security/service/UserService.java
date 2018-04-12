package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.security.domain.User;
import spring.security.repository.UserRepository;
import spring.security.utils.Encryption;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public User login(String loginId, String password) {
        User user = userRepository.findOneByLoginId(loginId);
        if(user == null) return null;
        String pw = Encryption.encrypt(password, Encryption.MD5);
        if(user.getPassword().equals(pw) == false) return null;
        return user;
    }
}
