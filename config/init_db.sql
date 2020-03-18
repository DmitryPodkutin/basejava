create table resume (
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT                 NOT NULL
);
create table contact (
  id          serial,
  resume_uuid char(36) not null references resume (uuid) on delete cascade,
  type        text     not null,
  value       text     not null
);

create table section(
  id          serial   not null constraint section_pk primary key,
  type        text     not null,
  description text     not null,
  resume_uuid char(36) not null constraint section_resume_uuid_fk references resume on delete cascade
);
create unique index contact_uuid_type_index
  on contact (resume_uuid, type);

create unique index "section_index"
	on section (resume_uuid, type);

