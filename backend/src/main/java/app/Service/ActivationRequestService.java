package app.Service;

import app.Repository.ActivationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivationRequestService {
    private final ActivationRequestRepository repository;

    public void setConfirmedAt(String token) {
        repository.updateConfirmedAt(
                token,
                LocalDateTime.now()
        );
    }
}
