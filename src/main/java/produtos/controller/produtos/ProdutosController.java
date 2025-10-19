package produtos.controller.produtos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import produtos.dto.produtos.ProdutosRequest;
import produtos.dto.produtos.ProdutosResponse;
import produtos.service.produtos.ProdutosService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService produtosService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProdutosResponse> buscarPorId(@PathVariable final UUID id) {
        final ProdutosResponse produto = produtosService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProdutosResponse> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax) {
        return produtosService.buscar(nome, categoria, precoMin, precoMax);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutosResponse> criar(@Valid @RequestBody final ProdutosRequest request) {
        final ProdutosResponse produto = produtosService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutosResponse> atualizar(
            @PathVariable final UUID id,
            @Valid @RequestBody final ProdutosRequest request) {
        final ProdutosResponse produto = produtosService.atualizar(id, request);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable final UUID id) {
        produtosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
