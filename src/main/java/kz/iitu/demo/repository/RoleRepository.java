package kz.iitu.demo.repository;

import kz.iitu.demo.entity.Role;
import kz.iitu.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
