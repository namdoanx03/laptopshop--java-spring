package vn.namdoan.laptopshop.repository;
<<<<<<< HEAD
=======


>>>>>>> temp-branch

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.namdoan.laptopshop.domain.Role;
<<<<<<< HEAD
=======

>>>>>>> temp-branch

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
