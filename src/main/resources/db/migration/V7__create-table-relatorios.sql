CREATE TABLE IF NOT EXISTS public.relatorios
(
    id integer GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    aluno_id integer,
    orientador_id integer,
    ccp_id integer,
    data_limite date,
    prazo_maximo_qualificacao date,
    prazo_maximo_dissertacao date,
    num_artigos_escrita integer,
    num_artigos_submetidos integer,
    num_artigos_publicados integer,
    resumo_atividades_academicas text COLLATE pg_catalog."default",
    resumo_atividades_pesquisa text COLLATE pg_catalog."default",
    info_adicional_para_ccp text COLLATE pg_catalog."default",
    nome_relatorio character varying COLLATE pg_catalog."default",
    resultado_ultima_avaliacao character varying COLLATE pg_catalog."default",
    situacao_relatorio character varying COLLATE pg_catalog."default",
    necessita_apoio_ccp boolean,
    parecer_orientador text COLLATE pg_catalog."default",
    data_parecer_orientador timestamp without time zone,
    parecer_ccp text COLLATE pg_catalog."default",
    data_parecer_ccp timestamp without time zone,

    CONSTRAINT relatorios_pkey PRIMARY KEY (id),

    CONSTRAINT relatorios_aluno_id_fkey FOREIGN KEY (aluno_id)
    REFERENCES public.alunos (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,

    CONSTRAINT relatorios_orientador_id_fkey FOREIGN KEY (orientador_id)
    REFERENCES public.orientadores (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,

    CONSTRAINT relatorios_ccp_id_fkey FOREIGN KEY (ccp_id)
    REFERENCES public.ccp (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);