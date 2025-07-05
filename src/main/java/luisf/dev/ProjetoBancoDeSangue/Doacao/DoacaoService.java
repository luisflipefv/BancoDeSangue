package luisf.dev.ProjetoBancoDeSangue.Doacao;

import luisf.dev.ProjetoBancoDeSangue.Doador.DoadorModel;
import luisf.dev.ProjetoBancoDeSangue.Doador.DoadorRepository;
import luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue.EstoqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoacaoService {
    private DoacaoRepository doacaoRepository;
    private DoadorRepository doadorRepository;
    private EstoqueService estoqueService;

    public DoacaoService(DoacaoRepository doacaoRepository, DoadorRepository doadorRepository, EstoqueService estoqueService) {
        this.doacaoRepository = doacaoRepository;
        this.doadorRepository = doadorRepository;
        this.estoqueService = estoqueService;
    }

    @Transactional
    public DoacaoModel criaDoacao(DoacaoModel doacao){
        DoadorModel doadorEncontrado = doadorRepository.findById(doacao.getDoador().getId())
                .orElseThrow(() -> new IllegalArgumentException("Doador não encontrado/cadastrado"));
        if (doadorEncontrado.getUltimaDoacao() != null && doadorEncontrado.getUltimaDoacao().isAfter(LocalDate.now().minusMonths(3))){
            throw new IllegalStateException("O doador " + doadorEncontrado.getNomeCompleto() + " nao pode realizar essa doação pois sua última doação foi há menos de 3 meses");
        }
        DoacaoModel doacaoNova = new DoacaoModel();
        doacaoNova.setDataDoacao(LocalDateTime.now());
        doacaoNova.setDoador(doadorEncontrado);
        doacaoNova.setQuantidadeMl(doacao.getQuantidadeMl());
        doacaoRepository.save(doacaoNova);

        doadorEncontrado.setUltimaDoacao(LocalDate.now());
        doadorRepository.save(doadorEncontrado);

        if (doacao.getQuantidadeMl() >= 450 && doacao.getQuantidadeMl() <= 500){
            estoqueService.incrementarEstoque(doacao.getDoador().getTipoSanguineo(), 1);
        }
        return doacaoNova;
    }

    public DoacaoModel consultaDoacaoPorId(Long id){
        return doacaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));
    }

    public List<DoacaoModel> consultaTodasDoacoes(){
        return doacaoRepository.findAll();
    }

    public List<DoacaoModel> consultaDoacaoPorDoador(Long idDoador){
        return doacaoRepository.findByDoadorId(idDoador);
    }
}
