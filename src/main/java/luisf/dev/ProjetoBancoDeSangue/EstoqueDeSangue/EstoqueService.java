package luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue;

import luisf.dev.ProjetoBancoDeSangue.Doador.TipoSanguineo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueService {

    private EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    //incrementar estoque
    public void incrementarEstoque(TipoSanguineo id, int quantidade){
        EstoqueModel encontrado = estoqueRepository.findById(id)
                .orElse(new EstoqueModel(id, 0, null));
        encontrado.setQuantidadeBolsas(encontrado.getQuantidadeBolsas() + quantidade);
        encontrado.setUltimaAtualizacao(LocalDateTime.now());

        estoqueRepository.save(encontrado);
    }

    //decrementar estoque
    public void decrementarEstoque(TipoSanguineo id, int quantidade){
        EstoqueModel encontrado = estoqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo sanguíneo não cadastrado"));

        if (encontrado.getQuantidadeBolsas() < quantidade){
            throw new IllegalStateException("Estoque insuficiente");
        }

        encontrado.setQuantidadeBolsas(encontrado.getQuantidadeBolsas() - quantidade);
        encontrado.setUltimaAtualizacao(LocalDateTime.now());

        estoqueRepository.save(encontrado);
    }

    //listar todos
    public List<EstoqueModel> listarTodos(){
        return estoqueRepository.findAll();
    }

    //llistar por id
    public EstoqueModel listarPorTipo(TipoSanguineo tipo){
        return estoqueRepository.findById(tipo)
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado"));
    }
}
