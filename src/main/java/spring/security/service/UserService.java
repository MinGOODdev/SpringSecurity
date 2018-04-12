package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.domain.User;
import spring.security.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String loginId, String password) {
        User user = userRepository.findOneByLoginId(loginId);
        if(user == null) return null;
//        String pw = Encryption.encrypt(password, Encryption.MD5);
        if(user.getPassword().equals(password) == false) return null;
        return user;
    }
}
