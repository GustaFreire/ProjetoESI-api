CREATE TABLE IF NOT EXISTS public.orientadores
(
    id integer GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    usuario_id integer,
    nome character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",

    CONSTRAINT orientadores_pkey PRIMARY KEY (id),

    CONSTRAINT orientadores_usuario_id_fkey FOREIGN KEY (usuario_id)
    REFERENCES public.usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);