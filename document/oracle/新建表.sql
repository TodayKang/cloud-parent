-- Create table
create table ORD_USER_ORDER
(
  user_order_id INTEGER not null,
  order_id      NUMBER(20) not null,
  order_type    VARCHAR2(30),
  biz_type      VARCHAR2(20),
  create_time   DATE,
  user_id       NUMBER(11),
  delete_flag   VARCHAR2(1) default 'N'
)
tablespace LVMAMA_SUPER
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ORD_USER_ORDER.user_order_id
  is '主键';
comment on column ORD_USER_ORDER.order_id
  is '订单ID';
comment on column ORD_USER_ORDER.order_type
  is '订单类型';
comment on column ORD_USER_ORDER.biz_type
  is '订单系统';
comment on column ORD_USER_ORDER.create_time
  is '下单时间';
comment on column ORD_USER_ORDER.user_id
  is '下单人';
comment on column ORD_USER_ORDER.delete_flag
  is '删除标记（Y:已删除，N/NULL:未删除）';
-- Create/Recreate indexes 
create index IDX_ORD_USER_OID on ORD_USER_ORDER (ORDER_ID)
  tablespace LVMAMA_SPACE_INX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_ORD_USER_OID_UID on ORD_USER_ORDER (ORDER_ID, USER_ID)
  tablespace LVMAMA_SUPER
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_ORD_USER_OUID on ORD_USER_ORDER (USER_ID)
  tablespace LVMAMA_SPACE_INX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORD_USER_ORDER
  add constraint PK_ORD_USER_ORDER primary key (USER_ORDER_ID)
  using index 
  tablespace LVMAMA_SUPER
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Grant/Revoke object privileges 
grant select on ORD_USER_ORDER to LVMAMA_SUPER_LIMIT;
