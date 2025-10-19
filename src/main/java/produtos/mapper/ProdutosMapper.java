package produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.dto.produtos.ProdutosRequest;
import produtos.dto.produtos.ProdutosResponse;
import produtos.entity.Produtos;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "criadoPor", ignore = true)
    @Mapping(target = "atualizadoPor", ignore = true)
    Produtos toEntity(ProdutosRequest request);

    @Mapping(target = "criadoPorId", source = "criadoPor.id")
    @Mapping(target = "criadoPorUsername", source = "criadoPor.username")
    @Mapping(target = "atualizadoPorId", source = "atualizadoPor.id")
    @Mapping(target = "atualizadoPorUsername", source = "atualizadoPor.username")
    ProdutosResponse toResponse(Produtos produto);
}
