package vn.namdoan.laptopshop.repository;
<<<<<<< HEAD

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.namdoan.laptopshop.domain.User;

=======

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.namdoan.laptopshop.domain.User;
import java.util.List;

//crud: create, read, update, delete
>>>>>>> temp-branch
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User eric);

    void deleteById(long id);

    List<User> findOneByEmail(String email);

    List<User> findAll();

    User findById(long id); // null

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
