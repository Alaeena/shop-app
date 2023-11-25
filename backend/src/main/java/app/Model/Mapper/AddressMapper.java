package app.Model.Mapper;

import app.Model.Address;
import app.Model.Dto.AddressDto;

public class AddressMapper {
    public static Address mapToObject(AddressDto dto) {
        return Address.builder()
                .id(dto.getId())
                .addressType(dto.getAddressType())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .name(dto.getName())
                .build();
    }

    public static AddressDto mapToDto(Address object) {
        return AddressDto.builder()
                .id(object.getId())
                .addressType(object.getAddressType())
                .address(object.getAddress())
                .phone(object.getPhone())
                .name(object.getName())
                .build();
    }
}
