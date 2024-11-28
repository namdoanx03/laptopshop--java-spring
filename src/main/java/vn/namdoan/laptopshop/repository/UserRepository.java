package vn.namdoan.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.namdoan.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User eric);

    void deleteById(long id);

    List<User> findByEmail(String email);

    List<User> findAll();

    // List<User> findById(long id);
    User findById(long id);

    boolean existsByEmail(String email);
}
