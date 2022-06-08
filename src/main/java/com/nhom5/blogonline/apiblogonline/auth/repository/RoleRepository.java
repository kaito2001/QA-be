package com.nhom5.blogonline.apiblogonline.auth.repository;
import java.util.Optional;

import com.nhom5.blogonline.apiblogonline.auth.models.ERole;
import com.nhom5.blogonline.apiblogonline.auth.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
