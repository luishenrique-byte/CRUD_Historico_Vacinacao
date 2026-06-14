package Repositories.fabricante;

import Models.Fabricante;

import java.util.List;

public interface IFabricanteRepository {
    public Integer createFabricante(String nome); //Integer para saber se ocorreo a criação
    public List<Fabricante> getAllFabricante();
    public Fabricante getFabricanteById(long id);
    public Integer updateFabricante(Fabricante fabricante);
}
