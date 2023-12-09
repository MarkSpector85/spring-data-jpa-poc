package in.mark.datajpa.service.impl;

import in.mark.datajpa.model.User;
import in.mark.datajpa.repository.UserRepository;
import in.mark.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Implementation of the UserService interface providing CRUD operations for User entities. */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Saves a new user or updates an existing user.
   *
   * @param user The user entity to be saved or updated.
   * @return The saved or updated user entity.
   */
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Retrieves all users from the database.
   *
   * @return A list of all users.
   */
  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Retrieves a user by their unique identifier (ID).
   *
   * @param userId The ID of the user to retrieve.
   * @return An Optional containing the user, or an empty Optional if not found.
   */
  @Override
  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  /**
   * Updates an existing user.
   *
   * @param user The user entity to be updated.
   * @return The updated user entity.
   */
  @Override
  public User updateUser(User user) {
    return userRepository.saveAndFlush(user);
  }

  /**
   * Deletes a user by their unique identifier (ID).
   *
   * @param userId The ID of the user to be deleted.
   */
  @Override
  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }

  /** Deletes all users from the database. */
  @Override
  public void deleteAllUsers() {
    userRepository.deleteAll();
  }

  /**
   * Retrieves a list of users by their first name.
   *
   * @param firstName The first name of users to retrieve.
   * @return A list of users with the specified first name.
   */
  @Override
  public List<User> getUsersByFirstName(String firstName) {
    return userRepository.findByFirstName(firstName);
  }

  /**
   * Retrieves a list of users by their last name and age.
   *
   * @param lastName The last name of users to retrieve.
   * @param age The age of users to retrieve.
   * @return A list of users with the specified last name and age.
   */
  @Override
  public List<User> getUsersByLastNameAndAge(String lastName, int age) {
    return userRepository.findByLastNameAndAge(lastName, age);
  }

  /**
   * Retrieves a list of users within a specified age range.
   *
   * @param minAge The minimum age of users to retrieve.
   * @param maxAge The maximum age of users to retrieve.
   * @return A list of users within the specified age range.
   */
  @Override
  public List<User> getUsersByAgeRange(int minAge, int maxAge) {
    return userRepository.findByAgeRange(minAge, maxAge);
  }

  /**
   * Upsert a user based on their unique identifier (ID). If the user with the given ID exists,
   * updates the user; otherwise, saves a new user.
   *
   * @param user The user entity to be upsert.
   * @return The upsert user entity.
   */
  @Override
  public User upsertUser(User user) {
    if (user.getId() == null) {
      return null;
    }
    Optional<User> existingUser = userRepository.findById(user.getId());
    if (existingUser.isPresent()) {
      User updatedUser = existingUser.get();
      updatedUser.setFirstName(user.getFirstName());
      updatedUser.setLastName(user.getLastName());
      updatedUser.setAge(user.getAge());
      return userRepository.save(updatedUser);
    } else {
      return userRepository.save(user);
    }
  }
}
