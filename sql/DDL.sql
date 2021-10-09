CREATE SEQUENCE infouser_id_user_seq;
CREATE SEQUENCE product_id_product_seq;
CREATE SEQUENCE order_id_order_seq;
CREATE SEQUENCE order_id_user_seq;
CREATE SEQUENCE lineitem_id_lineitem_seq;
CREATE SEQUENCE lineitem_id_order_seq;
CREATE SEQUENCE lineitem_id_product_seq;

CREATE TABLE public.infouser
(
    id_user bigint NOT NULL DEFAULT nextval('infouser_id_user_seq'::regclass),
    surname character varying COLLATE pg_catalog."default",
    name character varying COLLATE pg_catalog."default",
    details character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    phone character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    enabled boolean,
    authority character varying COLLATE pg_catalog."default",
    CONSTRAINT infouser_pkey PRIMARY KEY (id_user)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.infouser
    OWNER to postgres;


CREATE TABLE public.product
(
    id_product bigint NOT NULL DEFAULT nextval('product_id_product_seq'::regclass),
    department character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default",
    price numeric,
    currency character varying COLLATE pg_catalog."default",
    availability character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    specifications text COLLATE pg_catalog."default",
    CONSTRAINT product_pkey PRIMARY KEY (id_product)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;



CREATE TABLE public.orderuser
(
    id_order bigint NOT NULL DEFAULT nextval('order_id_order_seq'::regclass),
    id_user bigint DEFAULT nextval('order_id_user_seq'::regclass),
    ordered_date timestamp without time zone,
    paid_date timestamp without time zone,
    status character varying COLLATE pg_catalog."default",
    customer_address text COLLATE pg_catalog."default",
    totalprice numeric,
    paid boolean,
    CONSTRAINT order_pkey PRIMARY KEY (id_order),
    CONSTRAINT id_user FOREIGN KEY (id_user)
        REFERENCES public.infouser (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.orderuser
    OWNER to postgres;



CREATE TABLE public.lineitem
(
    id_lineitem bigint NOT NULL DEFAULT nextval('lineitem_id_lineitem_seq'::regclass),
    id_order bigint DEFAULT nextval('lineitem_id_order_seq'::regclass),
    id_product bigint DEFAULT nextval('lineitem_id_product_seq'::regclass),
    quanity integer,
    price numeric,
    amount numeric,
    CONSTRAINT lineitem_pkey PRIMARY KEY (id_lineitem),
    CONSTRAINT id_order FOREIGN KEY (id_order)
        REFERENCES public."orderuser" (id_order) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_product FOREIGN KEY (id_product)
        REFERENCES public.product (id_product) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.lineitem
    OWNER to postgres;