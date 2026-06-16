package Services.estado;

import Models.Estado;
import Repositories.estado.EstadoRepository;

import java.util.LinkedList;
import java.util.List;

public class EstadoService implements IEstadoService{

    private final EstadoRepository _repository;

    public EstadoService(EstadoRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void adicionarEstado(String nome) {
        Integer criado = _repository.createEstado(nome);

        if (criado != null && criado > 0){
            System.out.println("Estado adicionado com sucesso.");
        } else {
            System.out.println("Não foi possível criar.");
        }
    }

    @Override
    public void listarTodosEstados() {
        List<Estado> estadoList = _repository.getAllEstados();

        for(Estado e : estadoList){
            System.out.println("[ " + e.id +" | " + e.nome+"]");
        }
    }

    @Override
    public void mostrarEstadoPorId(long id) {
        Estado e = _repository.getEstadoById(id);

        if (e != null){
            System.out.println("[ " + e.id +" | " + e.nome+"]");
        } else {
            System.out.println("Estado não encontrado a partir desse ID.");
        }
    }

    @Override
    public void modificarNomeEstado(long id, String nome) {
        Integer qtdeLinhas = _repository.updateEstado(new Estado(id, nome));

        if (qtdeLinhas != null && qtdeLinhas>0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}
