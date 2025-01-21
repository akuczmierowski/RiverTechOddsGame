package pl.andrzejkuczmierowski.RiverTechOddsGame.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Player;

@Mapper(componentModel = "spring")
public interface DTOMapper {
    DTOMapper MAPPER = Mappers.getMapper(DTOMapper.class);

    PlayerDTO playerToPlayerDTO(Player player);
}
