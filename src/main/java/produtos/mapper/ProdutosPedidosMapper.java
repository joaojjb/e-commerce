package produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.model.dto.pedidos.ProdutosPedidosResponse;
import produtos.model.entity.ProdutosPedidos;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosPedidosMapper {

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "produtoNome", source = "produto.nome")
    @Mapping(target = "criadoPorId", source = "criadoPor.id")
    @Mapping(target = "criadoPorUsername", source = "criadoPor.username")
    @Mapping(target = "atualizadoPorId", source = "atualizadoPor.id")
    @Mapping(target = "atualizadoPorUsername", source = "atualizadoPor.username")
    ProdutosPedidosResponse toResponse(ProdutosPedidos produtosPedidos);

    List<ProdutosPedidosResponse> toListResponse(List<ProdutosPedidos> produtosPedidos);
}
