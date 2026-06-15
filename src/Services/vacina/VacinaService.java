package Services.vacina;

import Models.Vacina;
import Repositories.vacina.IVacinaRepository;

import java.util.List;

public class VacinaService implements IVacinaService{

    IVacinaRepository _repository;

    public VacinaService(IVacinaRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void adicionarVacina(String nome, long id_fabricante, Integer intervalo_doses) {
        Integer linhaCriada = _repository.createVacina(nome, id_fabricante, intervalo_doses);

        if (linhaCriada != null && linhaCriada > 0){
            System.out.println("Vacina adicionada com sucesso.");
        } else {
            System.out.println("Não foi possível criar a Vacina.");
        }
    }

    @Override
    public void listaTodosVacinas() {
        List<Vacina> vacinaList = _repository.getAllVacina();

        System.out.println("Id  |  Nome  |  Intervalo_Doses | Fabricante");
        for (Vacina v : vacinaList){
            System.out.println("[ "+v.id+" | "+v.nome+" | "+v.intervalo_doses+" | "+v.nome_fabricante+" ]");
        }
    }

    @Override
    public void mostrarVacinaPorId(long id) {
        Vacina v = _repository.getVacinaById(id);

        System.out.println("Id  |  Nome  |  Intervalo_Doses | Fabricante");
        if (v != null){
            System.out.println("[ "+v.id+" | "+v.nome+" | "+v.intervalo_doses+" | "+v.nome_fabricante+" ]");
        } else {
            System.out.println("Vacina não encontrada a partir desse ID.");
        }

    }

    @Override
    public void modicarNomeVacina(long id, String novoNome) {
        Vacina v = _repository.getVacinaById(id);

        if (v != null){
            System.out.println("Vacina não encontrada para esse ID.");
            return;
        }

        Integer qtdeLinhas = _repository.updateVacina(new Vacina(v.id, novoNome, v.intervalo_doses, v.id_fabricante, v.nome_fabricante));

        if (qtdeLinhas != null && qtdeLinhas>0) {
            System.out.println("Operação realizada com sucessor.");
            System.out.println(qtdeLinhas + " linha afetada.");
        } else {
            System.out.println("Nenhuma linha foi afetada. Reveja o ID.");
        }
    }
}
