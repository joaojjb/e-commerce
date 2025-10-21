package produtos.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import produtos.model.dto.pedidos.PedidosResponse;
import produtos.model.entity.Pedidos;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProdutosPedidosMapper.class})
public interface PedidosMapper {

    PedidosResponse toResponse(final Pedidos pedido);

    List<PedidosResponse> toListResponse(final List<Pedidos> pedidos);
}
