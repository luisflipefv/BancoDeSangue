package luisf.dev.ProjetoBancoDeSangue.Doador;

import luisf.dev.ProjetoBancoDeSangue.Agendamento.AgendamentoRepository;
import luisf.dev.ProjetoBancoDeSangue.Doacao.DoacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoadorService {
    private DoadorRepository doadorRepository;
    private AgendamentoRepository agendamentoRepository;
    private DoacaoRepository doacaoRepository;

    public DoadorService(DoadorRepository doadorRepository, AgendamentoRepository agendamentoRepository, DoacaoRepository doacaoRepository) {
        this.doadorRepository = doadorRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.doacaoRepository = doacaoRepository;
    }

    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public DoadorModel criaDoador(DoadorModel doador){
        if (doadorRepository.existsByCpf(doador.getCpf())){
            throw new IllegalArgumentException("Doador já existente");
        }
        return doadorRepository.save(doador);
    }

    //deleta
    public void deletaDoador(Long id){
        if (!doadorRepository.existsById(id)){
            throw new IllegalArgumentException("Doador inválido");
        }
        if (agendamentoRepository.existsByDoadorId(id) || doacaoRepository.existsByDoadorId(id)){
            throw new IllegalArgumentException("Não é possível excluir um doador com histórico de doações ou agendamento marcado");
        }
        doadorRepository.deleteById(id);
    }
    //altera
    public DoadorModel alteraDoador(Long id, DoadorModel doadorAtualizado){
        return doadorRepository.findById(id).map(doadorExitente -> {
                if (doadorAtualizado.getEmail() != null && !doadorAtualizado.getEmail().isBlank()){
                    doadorExitente.setEmail(doadorAtualizado.getEmail());
                }
                if (doadorAtualizado.getTelefone() != null && !doadorAtualizado.getTelefone().isBlank()){
                    doadorExitente.setTelefone(doadorAtualizado.getTelefone());
                }
            return doadorRepository.save(doadorExitente);
        }).orElseThrow(() -> new IllegalArgumentException("Doador não encontrado"));
    }

    //consultaPorId
    public DoadorModel consultaDoadorPorId(Long id){
        DoadorModel doadorEncontrado = doadorRepository.findById(id).orElse(null);
        if (doadorEncontrado == null){
            throw new IllegalArgumentException("Doador não encontrado");
        }
        return doadorEncontrado;
    }

    //consultaTodos
    public List<DoadorModel> consultaTodosDoadores(){
        return doadorRepository.findAll();
    }
}
