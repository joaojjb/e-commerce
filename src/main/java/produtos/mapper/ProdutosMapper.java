package produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.dto.produtos.ProdutosRequest;
import produtos.dto.produtos.ProdutosResponse;
import produtos.entity.Produtos;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "id", ignore = true)
    Produtos fromRequest(ProdutosRequest request);

    @Mapping(target = "criadoPorId", source = "criadoPor.id")
    @Mapping(target = "criadoPorUsername", source = "criadoPor.username")
    @Mapping(target = "atualizadoPorId", source = "atualizadoPor.id")
    @Mapping(target = "atualizadoPorUsername", source = "atualizadoPor.username")
    ProdutosResponse toResponse(Produtos produto);

    List<ProdutosResponse> toListResponse(List<Produtos> produtos);
}
