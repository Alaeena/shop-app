package app.controller;


import app.model.dto.AddressDto;
import app.model.postgres.UserEntity;
import app.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/address")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public Map<String, List<AddressDto>> getAddress(
            @AuthenticationPrincipal UserEntity user
    ) {
        return Map.of(
                "data",
                addressService.getAddresses(user)
        );
    }

    @PostMapping
    public Map<String, List<AddressDto>> addAddress(
            @Valid @RequestBody AddressDto dto,
            @AuthenticationPrincipal UserEntity user
    ) {
        return Map.of(
                "data",
                addressService.addAddress(dto, user)
        );
    }

    @PutMapping
    public Map<String, List<AddressDto>> updateAddress(
            @Valid @RequestBody AddressDto dto,
            @AuthenticationPrincipal UserEntity user
    ) {
        return Map.of(
                "data",
                addressService.updateAddress(dto, user)
        );
    }

    @DeleteMapping
    public Map<String, List<AddressDto>> deleteAddress(
            @RequestParam("addressId") Long addressId,
            @AuthenticationPrincipal UserEntity user
    ) {
        return Map.of(
                "data",
                addressService.deleteAddress(addressId, user)
        );
    }

}
