CREATE SEQUENCE public.compras_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;

 CREATE SEQUENCE public.compras_item_id_seq
   INCREMENT 1
   MINVALUE 1
   MAXVALUE 9223372036854775807
   START 3
   CACHE 1;

ALTER TABLE public.compras_id_seq
  OWNER TO postgres;

CREATE TABLE compras (
  id    BIGINT      NOT NULL DEFAULT nextval('compras_id_seq'::regclass),
  descricao    VARCHAR(30)  NOT NULL,
  observacao   VARCHAR(200)     NULL,
  status       SMALLINT     NOT NULL,
  dt_cadastro  TIMESTAMP    NOT NULL,
  dt_alteracao TIMESTAMP    NOT NULL,
  CONSTRAINT PK_COMPRAS PRIMARY KEY (id)
);

CREATE TABLE compras_item (
  id                BIGINT      NOT NULL DEFAULT nextval('compras_item_id_seq'::regclass),
  id_compras        BIGINT      NOT NULL,
  descricao         VARCHAR(80) NOT NULL,
  quantidade        BIGINT      NOT NULL,
  valor             NUMERIC     NOT NULL,
  CONSTRAINT PK_COMPRAS_item PRIMARY KEY (id),
  CONSTRAINT FK_COMPRAS_ITEM_REF_COMPRAS FOREIGN KEY (id_compras) REFERENCES compras (id)
);