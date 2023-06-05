package br.com.fiap.donatedine.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private LoteService loteService;
    private EstoqueService estoqueService;
    private DoacaoService doacaoService;

    public StatusService(
        LoteService loteService, 
        EstoqueService estoqueService, 
        DoacaoService doacaoService
    ) {
        this.loteService = loteService;
        this.estoqueService = estoqueService;
        this.doacaoService = doacaoService;
    }

    public Map<String, Long> getStats() {
        long totalLotes = loteService.count();
        long totalEstoques = estoqueService.count();
        long totalDoacoes = doacaoService.count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalLotes", totalLotes);
        stats.put("totalEstoques", totalEstoques);
        stats.put("totalDoacoes", totalDoacoes);

        return stats;
    }
}
