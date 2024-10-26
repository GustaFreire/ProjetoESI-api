CREATE TABLE IF NOT EXISTS public.alunos
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    usuario_id integer,
    tipo_curso character varying COLLATE pg_catalog."default",
    nome character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    nusp character varying COLLATE pg_catalog."default",
    rg character varying COLLATE pg_catalog."default",
    nome_orientador character varying COLLATE pg_catalog."default",
    local_nascimento character varying COLLATE pg_catalog."default",
    nacionalidade character varying COLLATE pg_catalog."default",
    link_lattes character varying COLLATE pg_catalog."default",
    data_nascimento date,
    data_ultima_att_lattes date,
    data_matricula date,
    data_aprovacao_exame_qualificacao date,
    data_aprovacao_exame_proficiencia date,

    CONSTRAINT alunos_pkey PRIMARY KEY (id),
    CONSTRAINT alunos_nusp_key UNIQUE (nusp),

    CONSTRAINT alunos_usuario_id_fkey FOREIGN KEY (usuario_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);