package produtos.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.model.entity.Produtos;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "id", ignore = true)
    Produtos fromRequest(final ProdutosRequest request);

    ProdutosResponse toResponse(final Produtos produto);

    List<ProdutosResponse> toListResponse(final List<Produtos> produtos);
}
