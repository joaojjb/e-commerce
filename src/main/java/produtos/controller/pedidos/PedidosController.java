package produtos.controller.pedidos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import produtos.model.dto.pedidos.PedidosRequest;
import produtos.model.dto.pedidos.PedidosResponse;
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
        return ResponseEntity.ok(pedidosService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidosResponse> criar(@Valid @RequestBody final PedidosRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.criar(request));
    }

    @GetMapping("/meus-pedidos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PedidosResponse>> listarMeusPedidos() {
        return ResponseEntity.ok(pedidosService.listarPedidosDoUsuario());
    }

    @PatchMapping("/pagar/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidosResponse> pagar(
            @PathVariable final UUID id,
            @RequestParam final TipoPagamento tipoPagamento) {
        return ResponseEntity.ok(pedidosService.pagar(id, tipoPagamento));
    }

}

