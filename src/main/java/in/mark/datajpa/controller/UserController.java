package in.mark.datajpa.controller;

import in.mark.datajpa.model.User;
import in.mark.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Create User
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // Read All Users
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // Upsert User
  @PostMapping("/upsert")
  public ResponseEntity<User> upsertUser(@RequestBody User user) {
    User upsertedUser = userService.upsertUser(user);
    return new ResponseEntity<>(upsertedUser, HttpStatus.OK);
  }

  // Read User by ID
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    Optional<User> user = userService.getUserById(userId);
    return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Update User
  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
    user.setId(userId);
    User updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  // Delete User by ID
  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
    userService.deleteUserById(userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete All Users
  @DeleteMapping
  public ResponseEntity<Void> deleteAllUsers() {
    userService.deleteAllUsers();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Custom Query Endpoints
  @GetMapping("/byFirstName/{firstName}")
  public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable String firstName) {
    List<User> users = userService.getUsersByFirstName(firstName);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // Example: Get Users by Last Name and Age
  @GetMapping("/byLastNameAndAge")
  public ResponseEntity<List<User>> getUsersByLastNameAndAge(
      @RequestParam String lastName, @RequestParam int age) {
    List<User> users = userService.getUsersByLastNameAndAge(lastName, age);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  // Example: Get Users by Age Range
  @GetMapping("/byAgeRange")
  public ResponseEntity<List<User>> getUsersByAgeRange(
      @RequestParam int minAge, @RequestParam int maxAge) {
    List<User> users = userService.getUsersByAgeRange(minAge, maxAge);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
