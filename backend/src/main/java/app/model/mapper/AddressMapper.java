package app.model.mapper;

import app.model.Address;
import app.model.dto.AddressDto;

public class AddressMapper {
    public static Address mapToObject(AddressDto dto) {
        return Address.builder()
                .id(dto.getId())
                .addressType(dto.getAddressType())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .receiver(dto.getReceiver())
                .build();
    }

    public static AddressDto mapToDto(Address object) {
        return AddressDto.builder()
                .id(object.getId())
                .addressType(object.getAddressType())
                .address(object.getAddress())
                .phone(object.getPhone())
                .receiver(object.getReceiver())
                .build();
    }
}
