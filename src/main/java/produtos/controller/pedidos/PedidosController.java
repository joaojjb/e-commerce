package produtos.controller.pedidos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import produtos.dto.pedidos.PedidosRequest;
import produtos.dto.pedidos.PedidosResponse;
import produtos.enums.TipoPagamento;
import produtos.service.pedidos.PedidosService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidosController {

    private final PedidosService pedidosService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidosResponse> buscarPorId(@PathVariable final UUID id) {
        final PedidosResponse produto = pedidosService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidosResponse> criar(@Valid @RequestBody final PedidosRequest request) {
        final PedidosResponse pedido = pedidosService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/meus-pedidos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PedidosResponse>> listarMeusPedidos() {
        final List<PedidosResponse> pedidos = pedidosService.listarPedidosDoUsuario();
        return ResponseEntity.ok(pedidos);
    }

    @PatchMapping("/pagar/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidosResponse> pagar(
            @PathVariable final UUID id,
            @RequestParam final TipoPagamento tipoPagamento) {
        final PedidosResponse pedido = pedidosService.pagar(id, tipoPagamento);
        return ResponseEntity.ok(pedido);
    }

}

