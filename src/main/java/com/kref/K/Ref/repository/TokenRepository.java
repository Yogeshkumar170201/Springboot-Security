package com.kref.K.Ref.repository;

import com.kref.K.Ref.entity.User;
import com.kref.K.Ref.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
