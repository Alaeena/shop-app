package app.repository.postgres;

import app.model.ActivationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ActivationRequestRepository extends JpaRepository<ActivationRequest, Long> {

    Optional<ActivationRequest> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ActivationRequest a set a.confirmedAt = ?2 where a.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
