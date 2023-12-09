package in.mark.datajpa.repository;

import in.mark.datajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByFirstName(String firstName);

  // Find Users by LastName and Age
  List<User> findByLastNameAndAge(String lastName, int age);

  // Find Users by Age Range
  @Query("SELECT u FROM User u WHERE u.age BETWEEN :minAge AND :maxAge")
  List<User> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
}
