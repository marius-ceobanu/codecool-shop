DROP TABLE IF EXISTS public.cart;
DROP TABLE IF EXISTS public.order_products;
DROP TABLE IF EXISTS public.product;
DROP TABLE IF EXISTS public.product_category;
DROP TABLE IF EXISTS public.supplier;
DROP TABLE IF EXISTS public.user_info;
DROP TABLE IF EXISTS public.order_history;
DROP TABLE IF EXISTS public.user_account;

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

CREATE TABLE public.user_account (
    id bigserial NOT NULL,
    name character varying(80) NOT NULL,
    email character varying(80) NOT NULL,
    password character varying(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE public.cart (
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.order_history (
    id bigserial NOT NULL,
    user_id integer NOT NULL,
    date timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    payment character varying(10) NOT NULL,
    billing_address  boolean NOT NULL,
    name character varying(80) NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(80) NOT NULL,
    address text NOT NULL,
    city character varying(40) NOT NULL,
    county character varying(40) NOT NULL,
    zip_code character varying(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.user_info (
    id bigserial NOT NULL,
    user_id integer NOT NULL,
    name character varying(80),
    phone character varying(20),
    email character varying(80),
    address text,
    city character varying(40),
    county character varying(40),
    zip_code character varying(10),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.order_products (
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id)
        REFERENCES public.order_history (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);