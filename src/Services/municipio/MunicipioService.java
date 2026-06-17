package Services.municipio;

import Models.Municipio;
import Repositories.municipio.MunicipioRepository;

import java.util.List;

public class MunicipioService implements IMunicipioService {

    private final MunicipioRepository _repository;

    public MunicipioService(MunicipioRepository _repository) {
        this._repository = _repository;
    }


    @Override
    public void adicionarMunicipio(String nome, long id_estado) {
        Integer linhaCriada = _repository.createMunicipio(nome, id_estado);

        if (linhaCriada != null && linhaCriada > 0){
            System.out.println("Munícipio adicionado com sucesso.");
        } else {
            System.out.println("Não foi possível criar o Munícipio.");
        }
    }

    @Override
    public void listaTodosMunicipios() {
        List<Municipio> municipioList = _repository.getAllMunicipio();

        System.out.println("Id  |  Nome  |  Estado");
        for (Municipio m : municipioList){
            System.out.println("[ "+m.id_municipio+" | "+m.nome+" | "+m.nome_estado+" ]");
        }
    }

    @Override
    public void mostrarMunicipioPorId(long id) {
        Municipio m = _repository.getMunicipioById(id);

        if (m != null){
            System.out.println("[ "+m.id_municipio+" | "+m.nome+" | "+m.nome_estado+" ]");
        } else {
            System.out.println("Município não encontrado a partir desse ID.");
        }
    }

    @Override
    public void modicarNomeMunicipio(long id, String nome) {
        Municipio m = _repository.getMunicipioById(id);

        if (m == null){
            System.out.println("Município não encontrado para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updateMunicipio(new Municipio(id,nome, m.id_estado, m.nome_estado));

        if (qtdeLinhas != null && qtdeLinhas>0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }

//    @Override
//    public void modificarEstado(long id, long id_estado) {
//        Municipio m = _repository.getMunicipioById(id);
//        Integer qtdeLinhas = _repository.updateMunicipio(new Municipio(id,m.nome,));
//
//    }
}
