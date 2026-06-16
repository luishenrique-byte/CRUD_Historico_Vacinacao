package Services.fabricante;

import Models.Fabricante;
import Repositories.fabricante.IFabricanteRepository;

import java.util.List;

public class FabricanteService implements IFabricanteService{

    private final IFabricanteRepository _repository;

    public FabricanteService(IFabricanteRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void adicionarFabricante(String nome) {
        Integer criado = _repository.createFabricante(nome);

        if (criado != null && criado > 0){
            System.out.println("Fabricante adicionado com sucesso.");
        } else {
            System.out.println("Não foi possível criar novo Fabricante");
        }
    }

    @Override
    public void listarTodosFabricante() {
        List<Fabricante> fabricanteList = _repository.getAllFabricante();

        for(Fabricante f : fabricanteList){
            System.out.println("[ " + f.id +" | " + f.nome+"]");
        }
    }

    @Override
    public void mostrarFabricantePorId(long id) {
        Fabricante f = _repository.getFabricanteById(id);

        if (f != null){
            System.out.println("[ " + f.id +" | " + f.nome+"]");
        } else {
            System.out.println("Fabricante não encontrado a partir desse ID.");
        }
    }

    @Override
    public void modificarNomeFabricante(long id, String nome) {
        Integer qtdeLinhas = _repository.updateFabricante(new Fabricante(id,nome));

        if (qtdeLinhas != null && qtdeLinhas>0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}
