--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-2.pgdg19.10+1)

-- Started on 2020-03-30 16:00:05 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
select pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 16385)
-- Name: css; Type: SCHEMA; Schema: -; Owner: css-db
--

create SCHEMA css;


alter SCHEMA css OWNER TO "css-db";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 206 (class 1259 OID 32783)
-- Name: groups; Type: TABLE; Schema: css; Owner: css-db
--

create TABLE css.questions (
    id integer NOT NULL,
    question character varying,
    active boolean
);

create sequence css.questions_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

alter table css.questions_id_seq OWNER TO "css-db";
alter sequence css.questions_id_seq OWNED BY css.questions.id;

alter table css.questions OWNER TO "css-db";

create TABLE css.groups (
    id integer NOT NULL,
    title character varying
);


alter table css.groups OWNER TO "css-db";

--
-- TOC entry 205 (class 1259 OID 32781)
-- Name: groups_id_seq; Type: SEQUENCE; Schema: css; Owner: css-db
--

create sequence css.groups_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table css.groups_id_seq OWNER TO "css-db";

--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 205
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: css; Owner: css-db
--

alter sequence css.groups_id_seq OWNED BY css.groups.id;


--
-- TOC entry 210 (class 1259 OID 32815)
-- Name: messages; Type: TABLE; Schema: css; Owner: css-db
--

create TABLE css.messages (
    id integer NOT NULL,
    sent_by integer,
    sent_in_session integer,
    "time" timestamp without time zone,
    content text
);


alter table css.messages OWNER TO "css-db";

--
-- TOC entry 209 (class 1259 OID 32813)
-- Name: messages_id_seq; Type: SEQUENCE; Schema: css; Owner: css-db
--

create sequence css.messages_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table css.messages_id_seq OWNER TO "css-db";

--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 209
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: css; Owner: css-db
--

alter sequence css.messages_id_seq OWNED BY css.messages.id;


--
-- TOC entry 208 (class 1259 OID 32794)
-- Name: sessions; Type: TABLE; Schema: css; Owner: css-db
--

create TABLE css.sessions (
    id integer NOT NULL,
    host integer,
    location character varying,
    "group" integer,
    "time" timestamp without time zone
);


alter table css.sessions OWNER TO "css-db";

--
-- TOC entry 207 (class 1259 OID 32792)
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: css; Owner: css-db
--

create sequence css.sessions_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table css.sessions_id_seq OWNER TO "css-db";

--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 207
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: css; Owner: css-db
--

alter sequence css.sessions_id_seq OWNED BY css.sessions.id;


--
-- TOC entry 204 (class 1259 OID 32772)
-- Name: users; Type: TABLE; Schema: css; Owner: css-db
--

create TABLE css.users (
    id integer NOT NULL,
    name character varying
);


alter table css.users OWNER TO "css-db";

--
-- TOC entry 203 (class 1259 OID 32770)
-- Name: users_id_seq; Type: SEQUENCE; Schema: css; Owner: css-db
--

create sequence css.users_id_seq
    AS integer
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table css.users_id_seq OWNER TO "css-db";

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: css; Owner: css-db
--

alter sequence css.users_id_seq OWNED BY css.users.id;


--
-- TOC entry 211 (class 1259 OID 32834)
-- Name: users_in_groups; Type: TABLE; Schema: css; Owner: css-db
--

create TABLE css.users_in_groups (
    "user" integer NOT NULL,
    "group" integer NOT NULL
);


alter table css.users_in_groups OWNER TO "css-db";

--
-- TOC entry 2805 (class 2604 OID 32786)
-- Name: groups id; Type: DEFAULT; Schema: css; Owner: css-db
--

alter table ONLY css.groups alter COLUMN id SET DEFAULT nextval('css.groups_id_seq'::regclass);


--
-- TOC entry 2807 (class 2604 OID 32818)
-- Name: messages id; Type: DEFAULT; Schema: css; Owner: css-db
--

alter table ONLY css.messages alter COLUMN id SET DEFAULT nextval('css.messages_id_seq'::regclass);


--
-- TOC entry 2806 (class 2604 OID 32797)
-- Name: sessions id; Type: DEFAULT; Schema: css; Owner: css-db
--

alter table ONLY css.sessions alter COLUMN id SET DEFAULT nextval('css.sessions_id_seq'::regclass);


--
-- TOC entry 2804 (class 2604 OID 32775)
-- Name: users id; Type: DEFAULT; Schema: css; Owner: css-db
--

alter table ONLY css.users alter COLUMN id SET DEFAULT nextval('css.users_id_seq'::regclass);


--
-- TOC entry 2953 (class 0 OID 32783)
-- Dependencies: 206
-- Data for Name: groups; Type: TABLE DATA; Schema: css; Owner: css-db
--

COPY css.groups (id, title) FROM stdin;
1	Admins
2	Hochschule
3	Capturis
4	CustomerA
5	CustomerB
\.

COPY css.questions (id, question, active) FROM stdin;
1	How would you rate your general experience?	true
2	Rate the support employee!	false
3	Has your problem been solved?	true
4	How professional was the support employee?	true
5	Would you recommend the session share feature to your colleagues?	true
\.

--
-- TOC entry 2957 (class 0 OID 32815)
-- Dependencies: 210
-- Data for Name: messages; Type: TABLE DATA; Schema: css; Owner: css-db
--

COPY css.messages (id, sent_by, sent_in_session, "time", content) FROM stdin;
1	1	1	\N	Ja meddl
\.


--
-- TOC entry 2955 (class 0 OID 32794)
-- Dependencies: 208
-- Data for Name: sessions; Type: TABLE DATA; Schema: css; Owner: css-db
--

COPY css.sessions (id, host, location, "group", "time") FROM stdin;
1	1	rechnung-1	2	\N
\.


--
-- TOC entry 2951 (class 0 OID 32772)
-- Dependencies: 204
-- Data for Name: users; Type: TABLE DATA; Schema: css; Owner: css-db
--

COPY css.users (id, name) FROM stdin;
1	Admin
2	Simon1
3	David
4	Amir
5	Marcus
6	Chris
7	Levi
8	Marcy
9	Amanda
10	Carsten
11	Capturis
12	HSH
13	Simon2
14	Kevin
15	James
16	Bill
17	Sarah
18	Tobias
19	Emma
20	Olivia
21	Mia
22	Isabella
23	Abigail
24	Charlotte
\.


--
-- TOC entry 2958 (class 0 OID 32834)
-- Dependencies: 211
-- Data for Name: users_in_groups; Type: TABLE DATA; Schema: css; Owner: css-db
--

COPY css.users_in_groups ("user", "group") FROM stdin;
1	1
2	1
5	1
13	1
1	2
2	2
4	2
5	2
6	2
10	2
12	2
13	2
1	3
3	3
7	3
9	3
11	3
1	4
14	4
15	4
16	4
17	4
18	4
19	4
1	5
20	5
21	5
23	5
24	5
\.


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 205
-- Name: groups_id_seq; Type: SEQUENCE SET; Schema: css; Owner: css-db
--

select pg_catalog.setval('css.groups_id_seq', 2, true);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 209
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: css; Owner: css-db
--

select pg_catalog.setval('css.messages_id_seq', 1, true);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 207
-- Name: sessions_id_seq; Type: SEQUENCE SET; Schema: css; Owner: css-db
--

select pg_catalog.setval('css.sessions_id_seq', 1, true);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 203
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: css; Owner: css-db
--

select pg_catalog.setval('css.users_id_seq', 4, true);


--
-- TOC entry 2811 (class 2606 OID 32791)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2815 (class 2606 OID 32823)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- TOC entry 2813 (class 2606 OID 32802)
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- TOC entry 2817 (class 2606 OID 32838)
-- Name: users_in_groups users_in_groups_pkey; Type: CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.users_in_groups
    ADD CONSTRAINT users_in_groups_pkey PRIMARY KEY ("user", "group");


--
-- TOC entry 2809 (class 2606 OID 32780)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2823 (class 2606 OID 32844)
-- Name: users_in_groups group_group; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.users_in_groups
    ADD CONSTRAINT group_group FOREIGN KEY ("group") REFERENCES css.groups(id);


--
-- TOC entry 2822 (class 2606 OID 32839)
-- Name: users_in_groups group_user; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.users_in_groups
    ADD CONSTRAINT group_user FOREIGN KEY ("user") REFERENCES css.users(id);


--
-- TOC entry 2820 (class 2606 OID 32824)
-- Name: messages message_sender; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.messages
    ADD CONSTRAINT message_sender FOREIGN KEY (sent_by) REFERENCES css.users(id);


--
-- TOC entry 2821 (class 2606 OID 32829)
-- Name: messages message_session; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.messages
    ADD CONSTRAINT message_session FOREIGN KEY (sent_in_session) REFERENCES css.sessions(id);


--
-- TOC entry 2819 (class 2606 OID 32808)
-- Name: sessions session_group; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.sessions
    ADD CONSTRAINT session_group FOREIGN KEY ("group") REFERENCES css.groups(id);


--
-- TOC entry 2818 (class 2606 OID 32803)
-- Name: sessions session_host; Type: FK CONSTRAINT; Schema: css; Owner: css-db
--

alter table ONLY css.sessions
    ADD CONSTRAINT session_host FOREIGN KEY (host) REFERENCES css.users(id);


-- Completed on 2020-03-30 16:00:05 CEST

--
-- PostgreSQL database dump complete
--

