
create table estado(
	id_estado serial primary key,
	nome varchar(100) not null
);

create table municipio(
	id_municipio bigserial primary key,
	nome varchar(100) not null,
	id_estado int not null,
	foreign key (id_estado) references estado(id_estado)
);


create table fabricante(
	id_fabricante bigserial primary key,
	nome varchar(100) not null
);

create table vacina(
	id_vacina bigserial primary key,
	nome varchar(30) not null,
	id_fabricante bigint not null,
	intervalo_doses integer,
	foreign key (id_fabricante) references fabricante(id_fabricante)
);

create table paciente(
	id_paciente bigserial primary key,
	nome varchar(120) not null,
	data_nascimento date,
	cpf varchar(11) not null,
	rua varchar(200),
	numero int,
	cep varchar(8) not null,
	id_municipio bigint not null,
	ativo boolean DEFAULT true,
	foreign key (id_municipio) references municipio(id_municipio)
);

create table unidade_atendimento(
	id_unidade bigserial primary key,
	nome varchar(30),
	rua varchar(200),
	numero int,
	cep varchar(8),
	id_municipio bigint not null,
	foreign key (id_municipio) references municipio(id_municipio)
);

create type tipo_profissional as ENUM ('CLT', 'PJ');

create table profissional(
	id_profissional bigserial primary key,
	nome varchar(120),
	documento varchar(14), --CPF quando for clt e CNPJ para PJ
	cargo varchar(30),
	tipo tipo_profissional not null,
	id_unidade bigint not null,
	foreign key (id_unidade) references unidade_atendimento(id_unidade)
);

create table registro_vacinacao (
	id_registro bigserial primary key,
	data_vacinacao date not null,
	lote varchar(150) not null,
	data_fabricacao date not null,
	validade date not null,
	id_paciente bigint not null,
	id_unidade bigint not null,
	id_profissional bigint not null,
	id_vacina bigint not null,
	foreign key (id_paciente) references paciente(id_paciente),
	foreign key (id_unidade) references unidade_atendimento(id_unidade),
	foreign key (id_profissional) references profissional(id_profissional),
	foreign key (id_vacina) references vacina(id_vacina)
);

--#######################--
--        INSERT
--#######################--
-- Estado — 3 a 5 registros
-- Município — 5 a 10 registros
-- Fabricante — 3 registros
-- Vacina — 5 registros
-- Unidade_Atendimento — 3 registros
-- Profissional — 3 registros
-- Paciente — 5 a 10 registros
-- Registro_Vacinacao — 10 a 15 registros

--INSERT Estado
insert into estado(nome) values ('Bahia');
insert into estado(nome) values ('São Paulo');
insert into estado(nome) values ('Amazonas');
insert into estado(nome) values ('Ceará');
insert into estado(nome) values ('Minas Gerais');

--INSERT Municipio
insert into municipio(nome,id_estado) values ('Salvador',1);
insert into municipio(nome,id_estado) values ('Camaçari',1);
insert into municipio(nome,id_estado) values ('São Paulo',2);
insert into municipio(nome,id_estado) values ('Guarulhos',2);
insert into municipio(nome,id_estado) values ('Manaus',3);
insert into municipio(nome,id_estado) values ('Autares',3);
insert into municipio(nome,id_estado) values ('Fortaleza',4);
insert into municipio(nome,id_estado) values ('Caucaia',4);
insert into municipio(nome,id_estado) values ('Belo Horizonte',5);
insert into municipio(nome,id_estado) values ('Uberlândia',5);

--INSERT Fabricante
insert into fabricante(nome) values ('PFizer');
insert into fabricante(nome) values ('Butantan');
insert into fabricante(nome) values ('Sanofi');

--INSERT Vacina
insert into vacina(nome, id_fabricante, intervalo_doses) values ('COVID-19',1,28);
insert into vacina(nome, id_fabricante, intervalo_doses) values ('Penta',2,60);
insert into vacina(nome, id_fabricante, intervalo_doses) values ('Influenza',2,365);
insert into vacina(nome, id_fabricante, intervalo_doses) values ('HPV',3,180);
insert into vacina(nome, id_fabricante, intervalo_doses) values ('Febre Amarela',3,null);

--INSERT Unidade_Atendimento
insert into unidade_atendimento(nome, rua, numero, cep, id_municipio) values ('UBS Centro Salvador', 'Rua da Ajuda', 10, '40020000', 1);
insert into unidade_atendimento(nome, rua, numero, cep, id_municipio) values ('UBS Camaçari Norte', 'Av. Jorge Amado', 55, '42800000', 2);
insert into unidade_atendimento(nome, rua, numero, cep, id_municipio) values ('UBS Paulista', 'Av. Paulista', 900, '01310000', 3);
insert into unidade_atendimento(nome, rua, numero, cep, id_municipio) values ('UBS Guarulhos Sul', 'Rua das Flores', 32, '07010000', 4);
insert into unidade_atendimento(nome, rua, numero, cep, id_municipio) values ('UBS Manaus Leste', 'Av. Brasil', 120, '69010000', 5);

--INSERT Profissional
insert into profissional(nome, documento, cargo, tipo, id_unidade) values ('Ana Lima', '12345678901', 'Enfermeira', 'CLT', 1);
insert into profissional(nome, documento, cargo, tipo, id_unidade) values ('Carlos Souza', '98765432100', 'Técnico de Enfermagem', 'CLT', 2);
insert into profissional(nome, documento, cargo, tipo, id_unidade) values ('Maria Oliveira', '11222333000181', 'Enfermeira', 'PJ', 3);
insert into profissional(nome, documento, cargo, tipo, id_unidade) values ('João Pedro', '44455566677', 'Técnico de Enfermagem', 'CLT', 4);
insert into profissional(nome, documento, cargo, tipo, id_unidade) values ('Fernanda Costa', '55566677788', 'Enfermeira', 'CLT', 5);

--INSERT Paciente
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Lucas Mendes', '1990-05-10', '11122233344', 'Rua das Acácias', 15, '40010000', 1);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Juliana Santos', '1985-08-22', '22233344455', 'Av. Central', 200, '42810000', 2);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Roberto Alves', '2000-01-15', '33344455566', 'Rua Augusta', 450, '01305000', 3);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Patrícia Gomes', '1978-11-30', '44455566677', 'Rua Nova', 88, '07020000', 4);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Diego Ferreira', '1995-03-05', '55566677788', 'Av. Amazonas', 310, '69020000', 5);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Camila Rocha', '2002-07-19', '66677788899', 'Rua do Sol', 22, '60810000', 7);
insert into paciente(nome, data_nascimento, cpf, rua, numero, cep, id_municipio) values ('Thiago Nunes', '1988-12-01', '77788899900', 'Rua Verde', 5, '30110000', 9);

--INSERT Registro_Vacinacao
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-01-10', 'LOTE001', '2023-06-01', '2025-06-01', 1, 1, 1, 1);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-02-15', 'LOTE002', '2023-07-01', '2025-07-01', 1, 1, 1, 2);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-03-20', 'LOTE003', '2023-08-01', '2025-08-01', 2, 2, 2, 3);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-04-05', 'LOTE004', '2023-09-01', '2025-09-01', 3, 3, 3, 4);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-04-18', 'LOTE005', '2023-10-01', '2025-10-01', 4, 4, 4, 5);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-05-02', 'LOTE006', '2023-11-01', '2025-11-01', 5, 5, 5, 1);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-05-20', 'LOTE007', '2023-12-01', '2025-12-01', 6, 3, 3, 2);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-06-10', 'LOTE008', '2024-01-01', '2026-01-01', 7, 4, 4, 3);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-07-01', 'LOTE009', '2024-02-01', '2026-02-01', 1, 1, 1, 4);
insert into registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina) values ('2024-08-15', 'LOTE010', '2024-03-01', '2026-03-01', 2, 2, 2, 5);

--#######################--
--    UPDATES
--#######################--

-- 1. Atualizar o cargo de um profissional
UPDATE profissional
SET cargo = 'Enfermeiro Chefe'
WHERE id_profissional = 1;

-- 2. Corrigir o CEP de uma unidade de atendimento
UPDATE unidade_atendimento
SET cep = '40020001'
WHERE id_unidade = 1;

-- 3. Atualizar o CPF de um paciente
UPDATE paciente
SET cpf = '11122233300'
WHERE id_paciente = 1;

-- 4. Atualizar o lote de um registro de vacinação vencido
UPDATE registro_vacinacao
SET lote = 'LOTE001-REV', validade = '2026-06-01'
WHERE id_registro = 1;

--#######################--
--    DELETEs
--#######################--

-- 1. Remover um registro de vacinação inválido
DELETE FROM registro_vacinacao
WHERE id_registro = 10;


--#######################--
-- SELECTs
--#######################--

-- 1. Todas as vacinas aplicadas em cada unidade de atendimento
SELECT una.nome unidade, vac.nome vacina, count(rv.id_registro) total_aplicacoes
    FROM unidade_atendimento una
        INNER JOIN registro_vacinacao rv
        ON rv.id_unidade = una.id_unidade
          INNER JOIN vacina vac
          ON vac.id_vacina = rv.id_vacina
    GROUP BY una.nome, vac.nome
    ORDER BY una.nome, total_aplicacoes DESC;

-- 2. Vacinas com validade vencida e o paciente que recebeu
SELECT pac.nome nome_paciente, vac.nome vacina, rv.lote, rv.validade
    FROM registro_vacinacao rv
        INNER JOIN paciente pac
        ON pac.id_paciente = rv.id_paciente
          INNER JOIN vacina vac
          ON vac.id_vacina = rv.id_vacina
    WHERE rv.validade < CURRENT_DATE
    ORDER BY rv.validade ASC;

-- 3. Fabricantes e suas vacinas aplicadas com total de doses
SELECT fab.nome fabricante, vac.nome vacina, count(rv.id_registro) total_doses
    FROM fabricante fab
        INNER JOIN vacina vac
        ON vac.id_fabricante = fab.id_fabricante
          INNER JOIN registro_vacinacao rv
          ON rv.id_vacina = vac.id_vacina
    GROUP BY fab.nome, vac.nome
    ORDER BY fab.nome, total_doses DESC;
	
--########################--
--        VIEW
--########################--

-- View 1: Histórico de vacinação completo
CREATE OR REPLACE VIEW vw_registro_vacinacao
AS
SELECT vacina.nome as nome_vacina,
	data_vacinacao,
	validade,
	lote,
	paciente.nome nome_paciente,
	profissional.nome nome_colaborador,
	unidade_atendimento.nome Unidade
   FROM registro_vacinacao
	INNER JOIN paciente
	ON paciente.id_paciente = registro_vacinacao.id_paciente
	  INNER JOIN profissional
	  ON profissional.id_profissional = registro_vacinacao.id_profissional
		INNER JOIN vacina
		ON vacina.id_vacina = registro_vacinacao.id_vacina
		  INNER JOIN unidade_atendimento
		  ON unidade_atendimento.id_unidade = registro_vacinacao.id_unidade
	WHERE paciente.ativo = true;

-- View 2: Pacientes atendidos por profissional
CREATE OR REPLACE VIEW vw_atendimentos_por_profissional
AS
SELECT prof.id_profissional, 
	prof.nome nome_colaborador, 
	pac.id_paciente, 
	pac.nome nome_paciente, 
	una.nome unidade, 
	data_vacinacao data_atendimento
   FROM registro_vacinacao rv
      INNER JOIN profissional prof
      ON prof.id_profissional = rv.id_profissional
        INNER JOIN paciente pac
        ON pac.id_paciente = rv.id_paciente
          INNER JOIN unidade_atendimento una
          ON una.id_unidade = rv.id_unidade
		  ORDER BY id_profissional ASC;

--########################--
--        Funtions
--########################--

-- Funtion 1: Total de Doses por paciente
CREATE OR REPLACE FUNCTION func_qtde_doses(cod_paciente bigint)
    RETURNS TABLE(nome_paciente varchar(120), qtde_doses bigint) 
    LANGUAGE 'sql'
AS 
$BODY$
	select pac.nome nome_paciente, count(id_registro) qtde_doses from registro_vacinacao rv
		inner join paciente pac
		on pac.id_paciente = rv.id_paciente
		  where rv.id_paciente = cod_paciente
		  group by nome_paciente;
$BODY$;

-- Funtion 2: Qtde de doses por vacina
CREATE OR REPLACE FUNCTION func_qtde_doses_x_vacina(
	cod_paciente bigint)
    RETURNS TABLE(vacina varchar, qtde_doses bigint) 
    LANGUAGE 'sql'

AS 
$BODY$
	select vac.nome, count(id_registro) from registro_vacinacao rv
		inner join vacina vac
		on vac.id_vacina = rv.id_vacina
			where rv.id_paciente = cod_paciente
			group by vac.nome;
$BODY$;

-- Function 3: idade paciente
CREATE OR REPLACE FUNCTION func_idade_pac(
	cod_paciente bigint)
    RETURNS interval
	LANGUAGE 'sql'
AS 
$BODY$
	select age(CURRENT_DATE, paciente.data_nascimento) 
	  from paciente 
	    where id_paciente = cod_paciente;
$BODY$;


-- Funtion 4: Proxima dose
CREATE OR REPLACE FUNCTION func_prox_dose(cod_paciente bigint)
	returns table(nome_paciente varchar(120), vacina varchar(50),data_vacinacao_atual date, data_prox_dose date)
	language 'sql'
AS 
$BODY$
	select 
		pac.nome nome_paciente,
		vac.nome vacina,
		rv.data_vacinacao,
		CASE
			WHEN vac.intervalo_doses is not null then (data_vacinacao + vac.intervalo_doses)
			ELSE null
		END as data_prox_dose 
	    from registro_vacinacao rv
	      inner join vacina vac
	      on vac.id_vacina = rv.id_vacina
		    inner join paciente pac
			on pac.id_paciente = rv.id_paciente
	    where rv.id_paciente = cod_paciente;
$BODY$;

--########################--
--       Procedures
--########################--

-- Procedure 1: Registra nova vacinação
CREATE OR REPLACE PROCEDURE proc_nova_vacinacao(
		p_lote varchar(150), 
		p_data_fabricacao date,
		p_validade date,
		p_id_paciente bigint,
		p_id_unidade bigint,
		p_id_profissional bigint,
		p_id_vacina bigint)
	LANGUAGE 'sql'
AS
$BODY$
	INSERT INTO registro_vacinacao(data_vacinacao, lote, data_fabricacao, validade, id_paciente, id_unidade, id_profissional, id_vacina)
	VALUES(CURRENT_DATE, p_lote, p_data_fabricacao, p_validade,p_id_paciente, p_id_unidade, p_id_profissional, p_id_vacina);
$BODY$;

-- Procedure 3: Inativar um paciente
CREATE OR REPLACE PROCEDURE proc_inativar_pac(
		p_id_paciente bigint)
	LANGUAGE 'sql'
AS
$BODY$
	UPDATE paciente
	SET ativo = false
	WHERE id_paciente = p_id_paciente;
$BODY$;

-- Procedure 4: Ativar um paciente
CREATE OR REPLACE PROCEDURE proc_ativar_pac(
		p_id_paciente bigint)
	LANGUAGE 'sql'
AS
$BODY$
	UPDATE paciente
	SET ativo = true
	WHERE id_paciente = p_id_paciente;
$BODY$;