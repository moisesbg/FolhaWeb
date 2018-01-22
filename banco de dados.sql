--------------------------------------
-- TABLE CARGOS
--------------------------------------
create table public.cargos (	
    id integer not null, 
	nome character varying(60) not null,
    constraint pk_cargos primary key (id),
	constraint chave_negocio_cargo unique (nome)
);


--------------------------------------
-- TABLE DEPARTAMENTOS
--------------------------------------
create table public.departamentos (	
    id integer not null, 
	nome character varying(60) not null,
    constraint pk_departamentos primary key (id),
	constraint chave_negocio_departamento unique (nome)
);


--------------------------------------
-- TABLE EVENTOS
--------------------------------------
create table public.eventos (	
    id integer not null, 
	nome character varying(60) not null,
    tipo_evento character(1) not null check (tipo_evento in ('P','D')), 
    unidade character varying(20), 
    monta_base_irrf character(1) not null check (monta_base_irrf in ('S','N')),
    monta_base_inss character(1) not null check (monta_base_inss in ('S','N')),
    tipo_formula character(1),
    constraint pk_eventos primary key (id),
	constraint chave_negocio_evento unique (nome)
);  

--------------------------------------
-- TABLE FUNCIONARIOS
--------------------------------------
create table public.funcionarios(
    id integer not null, 
    nome character varying(60) not null, 
    data_admissao date not null, 
    i_departamentos integer not null, 
    i_cargos integer not null, 
    salario numeric(9,2) not null, 
    horas_mensais integer not null, 
    sexo character(1) not null check (sexo in ('F','M')), 
    data_nascimento date not null, 
    qtd_dependentes integer, 
    cpf character(11), 
    ctps character varying(11), 
    uf_ctps character(2), 
    data_ctps date, 
    constraint pk_funcionarios primary key (id), 
    constraint fk_func_departamentos foreign key (i_departamentos) references public.departamentos (id) on update no action on delete no action,
    constraint fk_func_cargos foreign key (i_cargos) references public.cargos (id) on update no action on delete no action,
	constraint chave_negocio_funcionario unique (nome)
);


--------------------------------------
-- TABLE FOLHAS_PAGAMENTOS
--------------------------------------
create table public.folhas_pagamentos(
    id integer not null, 
    i_competencias date not null,
    i_funcionarios integer not null, 
    data_pagamento date not null, 
    constraint pk_folhas_pagamentos  primary key (id),
    constraint fk_folhas_funcionarios foreign key (i_funcionarios) references public.funcionarios (id) on update no action on delete no action ,
	constraint chave_negocio_folhas_pagamentos unique(i_competencias, i_funcionarios)
); 


--------------------------------------
-- TABLE ITENS_FOLHA
--------------------------------------
create table public.itens_folha(
    id integer not null,
    i_folhas_pagamentos integer not null, 
    i_eventos integer not null, 
    vlr_referencia numeric(9,2) not null, 
    vlr_calculado numeric(9,2) not null, 
    constraint pk_itens_folha  primary key (id),
	constraint fk_itens_folha_folha foreign key (i_folhas_pagamentos) references public.folhas_pagamentos (id) on update no action on delete no action,
	constraint fk_itens_folha_eventos foreign key (i_eventos) references public.eventos (id) on update no action on delete no action,
	constraint chave_negocio_itens_folhas unique(i_folhas_pagamentos, i_eventos)
);


--------------------------------------
-- TABLE ERROS_CALCULO
--------------------------------------
create table public.erros_calculo(
    id integer not null, 
    i_competencias date not null,
    i_funcionarios integer not null, 
    erro character varying(1000) not null, 
    constraint pk_erros_calculo  primary key (id),
    constraint fk_erros_calc_funcionarios foreign key (i_funcionarios) references public.funcionarios (id) on update no action on delete no action ,
	constraint chave_negocio_erros_calculo unique(i_competencias, i_funcionarios, erro)
); 


--------------------------------------
-- TABLE VARIAVEIS
--------------------------------------
create table public.variaveis(
    id integer not null, 
    i_competencias date not null, 
    i_funcionarios integer not null, 
    i_eventos integer not null, 
    valor numeric(9,2) not null, 
    constraint pk_variaveis primary key (id), 
    constraint fk_var_funcionarios foreign key (i_funcionarios) references public.funcionarios (id) on update no action on delete no action,
    constraint fk_var_eventos foreign key (i_eventos) references public.eventos (id) on update no action on delete no action
);


alter table public.folhas_pagamentos drop constraint chave_negocio_folhas_pagamentos;

alter table public.folhas_pagamentos rename column i_competencias to competencia;

alter table public.folhas_pagamentos add constraint chave_negocio_folhas_pagamentos unique(competencia, i_funcionarios);

alter table public.funcionarios alter column cpf type character varying(11);

alter table public.funcionarios alter column uf_ctps type character varying(2);

CREATE SEQUENCE public.seq_cargos minvalue 1;

CREATE SEQUENCE public.seq_departamentos minvalue 1;

CREATE SEQUENCE public.seq_eventos minvalue 1;

CREATE SEQUENCE public.seq_funcionarios minvalue 1;

CREATE SEQUENCE public.seq_folhas_pagamentos minvalue 1;

CREATE SEQUENCE public.seq_itens_folha minvalue 1;

CREATE SEQUENCE public.seq_erros_calculo minvalue 1;

/*
alter table public.folhas_pagamentos add column total_proventos numeric(9,2) default 0;

alter table public.folhas_pagamentos add column total_descontos numeric(9,2) default 0;

create or replace function dbf_totaliza_eventos_por_tipo()
returns trigger as $itens_folha$
begin
    update public.folhas_pagamentos 
       set total_proventos = total_proventos + new.vlr_calculado
     where id = new.i_folhas_pagamentos
       and (select tipo_evento from public.eventos 
             where id = new.i_eventos) = 'P';
               
    update public.folhas_pagamentos 
       set total_descontos = total_descontos + new.vlr_calculado
     where id = new.i_folhas_pagamentos
       and (select tipo_evento from public.eventos 
             where id = new.i_eventos) = 'D';
            
      return new;
end;
$itens_folha$ language plpgsql; 

create trigger  "tai_totaliza_eventos_por_tipo" after insert on public.itens_folha 
for each row execute procedure  dbf_totaliza_eventos_por_tipo();
*/