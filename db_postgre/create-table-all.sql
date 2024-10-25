

CREATE TABLE IF NOT EXISTS public.orientadores
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    usuario_ativo boolean,
    CONSTRAINT orientadores_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.usuarios
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying COLLATE pg_catalog."default" NOT NULL,
    nome character varying COLLATE pg_catalog."default",
    senha character varying COLLATE pg_catalog."default",
    role character varying COLLATE pg_catalog."default",
    ativo smallint,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT usuarios_email_key UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.alunos
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    nusp character varying COLLATE pg_catalog."default" NOT NULL,
    data_nascimento date,
    rg character varying COLLATE pg_catalog."default",
    local_nascimento character varying COLLATE pg_catalog."default",
    curso character varying COLLATE pg_catalog."default",
    lattes character varying COLLATE pg_catalog."default",
    data_matricula date,
    data_exame_qualificacao date,
    data_exame_prof_linguas date,
    disciplinas_aprovadas character varying[] COLLATE pg_catalog."default",
    disciplinas_reprovadas character varying[] COLLATE pg_catalog."default",
    CONSTRAINT alunos_pkey PRIMARY KEY (id),
    CONSTRAINT alunos_nusp_key UNIQUE (nusp)
);

CREATE TABLE IF NOT EXISTS public.aluno_disciplinas_aprovadas
(
    id bigint NOT NULL,
    aluno_id bigint,
    disciplina character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT aluno_disciplinas_aprovadas_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.aluno_disciplinas_reprovadas
(
    id bigint NOT NULL,
    aluno_id bigint,
    disciplina character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT aluno_disciplinas_reprovadas_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.relatorios
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    aluno_id integer,
    orientador_id integer,
    ccp_id integer,
    data_vigente date,
    data_limite date,
    relatorio_atv_academicas text COLLATE pg_catalog."default",
    resumo_atv_pesquisa text COLLATE pg_catalog."default",
    observacao_para_ccp text COLLATE pg_catalog."default",
    necessita_apoio boolean,
    situacao integer,
    parecer_orientador text COLLATE pg_catalog."default",
    data_parecer_orientador timestamp without time zone,
    parecer_ccp text COLLATE pg_catalog."default",
    data_parecer_ccp timestamp without time zone,
    CONSTRAINT relatorios_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.ccp
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    usuario_ativo boolean,
    CONSTRAINT ccp_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.orientadores
    ADD CONSTRAINT orientadores_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.alunos
    ADD CONSTRAINT alunos_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.aluno_disciplinas_aprovadas
    ADD CONSTRAINT aluno_disciplinas_aprovadas_aluno_id_fkey FOREIGN KEY (aluno_id)
    REFERENCES public.alunos (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.aluno_disciplinas_reprovadas
    ADD CONSTRAINT aluno_disciplinas_reprovadas_aluno_id_fkey FOREIGN KEY (aluno_id)
    REFERENCES public.alunos (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.relatorios
    ADD CONSTRAINT relatorios_aluno_id_fkey FOREIGN KEY (aluno_id)
    REFERENCES public.alunos (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.relatorios
    ADD CONSTRAINT relatorios_ccp_id_fkey FOREIGN KEY (ccp_id)
    REFERENCES public.ccp (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.relatorios
    ADD CONSTRAINT relatorios_orientador_id_fkey FOREIGN KEY (orientador_id)
    REFERENCES public.orientadores (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.ccp
    ADD CONSTRAINT ccp_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;