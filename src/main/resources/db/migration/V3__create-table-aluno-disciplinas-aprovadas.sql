CREATE TABLE IF NOT EXISTS public.aluno_disciplinas_aprovadas
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    aluno_id bigint,
    disciplina character varying(255) COLLATE pg_catalog."default",

    CONSTRAINT aluno_disciplinas_aprovadas_pkey PRIMARY KEY (id),

    CONSTRAINT aluno_disciplinas_aprovadas_aluno_id_fkey FOREIGN KEY (aluno_id)
    REFERENCES public.alunos (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
);