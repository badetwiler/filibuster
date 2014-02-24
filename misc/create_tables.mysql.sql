
create table if not exists acl_sid(
  id int auto_increment not null primary key,
  principal boolean not null,
  sid varchar(100) not null,
  constraint unique_uk_1 unique(sid,principal));

create table if not exists acl_class(
  id int auto_increment not null primary key,
  class varchar(100) not null,
  constraint unique_uk_2 unique(class));

create table if not exists acl_object_identity(
  id int auto_increment primary key,
  object_id_class int not null,
  object_id_identity int not null,
  parent_object int,
  owner_sid int,
  entries_inheriting boolean not null,
  constraint unique_uk_3 unique(object_id_class,object_id_identity),
  constraint foreign_fk_1 foreign key(parent_object) references acl_object_identity(id),
  constraint foreign_fk_2 foreign key(object_id_class) references acl_class(id),
  constraint foreign_fk_3 foreign key(owner_sid) references acl_sid(id));

create table if not exists acl_entry(
  id int auto_increment primary key,
  acl_object_identity int not null,
  ace_order int not null,
  sid int not null,
  mask integer not null,
  granting boolean not null,
  audit_success boolean not null,
  audit_failure boolean not null,
  constraint unique_uk_4 unique(acl_object_identity,ace_order),
  constraint foreign_fk_4 foreign key(acl_object_identity)
      references acl_object_identity(id),
  constraint foreign_fk_5 foreign key(sid) references acl_sid(id));

