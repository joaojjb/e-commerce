package produtos.dto.pedidos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidosRequest {
    @NotEmpty(message = "Pedido deve conter pelo menos um produto")
    @Valid
    private List<ProdutosPedidosRequest> produtos;
}

