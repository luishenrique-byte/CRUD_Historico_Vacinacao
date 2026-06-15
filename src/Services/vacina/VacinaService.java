package Services.vacina;

import Repositories.vacina.IVacinaRepository;

public class VacinaService implements IVacinaService{

    IVacinaRepository _repository;

    public VacinaService(IVacinaRepository _repository) {
        this._repository = _repository;
    }

    @Override
    public void adicionarVacina(String nome, long id_fabricante, int intervalo_doses) {
        Integer linhaCriada = _repository.createVacina(nome, id_fabricante, intervalo_doses);

        if (linhaCriada != null && linhaCriada > 0){
            System.out.println("Vacina adicionada com sucesso.");
        } else {
            System.out.println("Não foi possível criar a Vacina.");
        }
    }

    @Override
    public void listaTodosVacinas() {

    }

    @Override
    public void mostrarVacinaPorId(long id) {

    }

    @Override
    public void modicarNomeVacina(long id, String nome) {

    }
}
