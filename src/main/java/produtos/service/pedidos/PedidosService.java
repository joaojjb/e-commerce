package produtos.service.pedidos;

import produtos.model.dto.pedidos.PedidosRequest;
import produtos.model.dto.pedidos.PedidosResponse;
import produtos.enums.TipoPagamento;

import java.util.List;
import java.util.UUID;

public interface PedidosService {
    PedidosResponse criar(final PedidosRequest request);

    PedidosResponse pagar(final UUID id, final TipoPagamento tipoPagamento);

    List<PedidosResponse> listarPedidosDoUsuario();

    boolean existsByProdutosPedidos_Produto_Id(final UUID id);
}

