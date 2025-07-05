package luisf.dev.ProjetoBancoDeSangue.Doador;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoadorService {
    private DoadorRepository doadorRepository;

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
