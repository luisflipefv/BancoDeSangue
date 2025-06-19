package luisf.dev.ProjetoBancoDeSangue.Doacao;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {
    private DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public DoacaoModel criaDoacao(DoacaoModel doacao){
        return doacaoRepository.save(doacao);
    }

    public void deletaDoacao(Long id){
        if (doacaoRepository.existsById(id)){
            doacaoRepository.deleteById(id);
        } else{
            throw new IllegalArgumentException("Doação não encontrada: " + id);
        }
    }

    public DoacaoModel alteraDoacao(Long id, DoacaoModel doacaoAtualizada){
        return doacaoRepository.findById(id).map(doacaoExistente -> {
            if (doacaoAtualizada.getDataDoacao() != null) {
                doacaoExistente.setDataDoacao(doacaoAtualizada.getDataDoacao());
            }
            if (doacaoAtualizada.getDoador() != null){
                doacaoExistente.setDoador(doacaoAtualizada.getDoador());
            }
            if (doacaoAtualizada.getQuantidadeMl() > 10){
                doacaoExistente.setQuantidadeMl(doacaoAtualizada.getQuantidadeMl());
            }
            return doacaoRepository.save(doacaoExistente);
        }).orElseThrow(() -> new RuntimeException("Não foi possível fazer a atualização"));
    }

    public DoacaoModel consultaDoacaoPorId(Long id){
        return doacaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));
    }

    public List<DoacaoModel> consultaTodasDoacoes(){
        return doacaoRepository.findAll();
    }
}
