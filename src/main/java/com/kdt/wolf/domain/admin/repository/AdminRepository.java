package com.kdt.wolf.domain.admin.repository;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail(String email);

    @Query("SELECT a FROM AdminEntity a WHERE a.id = :userId")
    Optional<AdminEntity> findAdminById(long userId);
}
