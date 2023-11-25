package app.Service;

import app.Model.Address;
import app.Model.Dto.AddressDto;
import app.Model.Mapper.AddressMapper;
import app.Model.UserEntity;
import app.Repository.AddressRepository;
import app.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static app.Model.Enum.AddressType.HOME;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<AddressDto> getAddresses(UserEntity user) {
        return user.getAddresses().stream()
                .map(AddressMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<AddressDto> addAddress(
            AddressDto addressDto,
            UserEntity user
    ) {
        if (addressDto.getAddressType() == null) {
            addressDto.setAddressType(HOME);
        }
        Address address = AddressMapper.mapToObject(addressDto);
        user.addAddress(address);

        addressRepository.save(address);
        return getAddresses(user);
    }

    public List<AddressDto> updateAddress(
            AddressDto addressDto,
            UserEntity user
    ) {
        Address address = addressRepository.findById(addressDto.getId())
                .orElseThrow(() -> new RuntimeException("Ivalid id"));
        if (user.getId() != address.getUser().getId()) {
            throw new RuntimeException("This address does not belong to the user");
        }
        address.setName(addressDto.getName());
        address.setPhone(addressDto.getPhone());
        address.setAddressType(addressDto.getAddressType());
        address.setAddress(addressDto.getAddress());

        addressRepository.save(address);
        return getAddresses(address.getUser());
    }

    public List<AddressDto> deleteAddress(Long addressId, UserEntity user) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Ivalid id"));
        if (user.getId() != address.getUser().getId()) {
            throw new RuntimeException("This address does not belong to the user");
        }

        user.getAddresses().remove(address);
        addressRepository.delete(address);
        return addressRepository.findAllByUserId(user.getId())
                .stream().map(AddressMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
