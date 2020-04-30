package id.dondon.ppmt.service;

import id.dondon.ppmt.domain.User;
import id.dondon.ppmt.exceptions.UsernameAlreadyExistsException;
import id.dondon.ppmt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public User saveUser(User newUser) {
    try {
      newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

      // Make sure that password and confirmPassword match
      // We don't persist or show the confirmPassword
      return userRepository.save(newUser);
    } catch (Exception e) {
      throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
    }
  }

}
