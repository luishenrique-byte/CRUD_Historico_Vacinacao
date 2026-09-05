import axios from 'axios'

const BASE_URL = 'http://localhost:8082/v1/paciente'

export function findByCpf(cpf) {
  return axios.get(BASE_URL + '/buscar?cpf=' + cpf)
}
