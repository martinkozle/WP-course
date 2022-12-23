package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Service;

public interface AuthService {
    User login(String username, String password);
}
