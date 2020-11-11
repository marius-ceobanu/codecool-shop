DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;

CREATE TABLE public.product_category (
    id serial NOT NULL,
    name character varying(60) NOT NULL,
    description text NOT NULL,
    department character varying(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.supplier (
     id serial NOT NULL,
     name character varying(60) NOT NULL,
     description text,
     PRIMARY KEY (id)
);

CREATE TABLE public.product (
    id serial NOT NULL,
    name character varying(60) NOT NULL,
    description text NOT NULL,
    price numeric(15, 6) NOT NULL,
    currency character varying(3) NOT NULL,
    category_id integer NOT NULL,
    supplier_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
        REFERENCES public.product_category (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (supplier_id)
        REFERENCES public.supplier (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);