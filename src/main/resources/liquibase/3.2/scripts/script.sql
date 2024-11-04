create sequence theories_seq start 1;

alter table theories alter column id set default nextval('theories_seq'::regclass);
