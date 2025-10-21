package produtos.controller.relatorios;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import produtos.model.dto.relatorios.RelatorioResponse;
import produtos.service.relatorios.RelatorioService;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    
    private final RelatorioService relatorioService;
    
    @GetMapping("/top-usuarios")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RelatorioResponse> getTop5Usuarios() {
        final RelatorioResponse relatorio = relatorioService.getTop5UsuariosQueMaisCompraram();
        return ResponseEntity.ok(relatorio);
    }
    
    @GetMapping("/ticket-medio")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RelatorioResponse> getTicketMedio() {
        final RelatorioResponse relatorio = relatorioService.getTicketMedioPorUsuario();
        return ResponseEntity.ok(relatorio);
    }
    
    @GetMapping("/faturamento-mensal")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RelatorioResponse> getFaturamentoMensal(
            @RequestParam final Integer ano,
            @RequestParam final Integer mes) {
        final RelatorioResponse relatorio = relatorioService.getFaturamentoMensal(ano, mes);
        return ResponseEntity.ok(relatorio);
    }
}
