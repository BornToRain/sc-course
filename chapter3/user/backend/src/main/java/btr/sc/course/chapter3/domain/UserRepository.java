package btr.sc.course.chapter3.domain;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>
{
  Option<User> findById(String id);
}
