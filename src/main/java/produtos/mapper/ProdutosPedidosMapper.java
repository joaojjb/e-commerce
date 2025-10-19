package produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.dto.pedidos.ProdutosPedidosResponse;
import produtos.entity.ProdutosPedidos;

@Mapper(componentModel = "spring")
public interface ProdutosPedidosMapper {

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "produtoNome", source = "produto.nome")
    @Mapping(target = "criadoPorId", source = "criadoPor.id")
    @Mapping(target = "criadoPorUsername", source = "criadoPor.username")
    @Mapping(target = "atualizadoPorId", source = "atualizadoPor.id")
    @Mapping(target = "atualizadoPorUsername", source = "atualizadoPor.username")
    ProdutosPedidosResponse toResponse(ProdutosPedidos produtosPedidos);
}
