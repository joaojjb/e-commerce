package produtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.dto.pedidos.PedidosResponse;
import produtos.entity.Pedidos;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProdutosPedidosMapper.class})
public interface PedidosMapper {

    @Mapping(target = "tipoPagamento", expression = "java(pedido.getTipoPagamento() != null ? pedido.getTipoPagamento().name() : null)")
    @Mapping(target = "produtos", source = "produtosPedidos")
    @Mapping(target = "criadoPorId", source = "criadoPor.id")
    @Mapping(target = "criadoPorUsername", source = "criadoPor.username")
    @Mapping(target = "atualizadoPorId", source = "atualizadoPor.id")
    @Mapping(target = "atualizadoPorUsername", source = "atualizadoPor.username")
    PedidosResponse toResponse(Pedidos pedido);

    List<PedidosResponse> toListResponse(List<Pedidos> pedidos);
}
