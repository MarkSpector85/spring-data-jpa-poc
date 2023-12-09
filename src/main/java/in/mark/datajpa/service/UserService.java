package in.mark.datajpa.service;

import in.mark.datajpa.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  // CRUD Operations
  User saveUser(User user);

  List<User> getAllUsers();

  Optional<User> getUserById(Long userId);

  User updateUser(User user);

  void deleteUserById(Long userId);

  void deleteAllUsers();

  // Query Methods
  List<User> getUsersByFirstName(String firstName);

  List<User> getUsersByLastNameAndAge(String lastName, int age);

  List<User> getUsersByAgeRange(int minAge, int maxAge);

  User upsertUser(User user);
}
