create table channel (
         id uniqueidentifier not null,
         created_at datetime2 default CURRENT_TIMESTAMP not null,
         last_modified datetime2 default CURRENT_TIMESTAMP not null,
         description varchar(255),
         name varchar(255),
         primary key (id)
);

create table service_catalog (
         id uniqueidentifier not null,
         created_at datetime2 default CURRENT_TIMESTAMP not null,
         last_modified datetime2 default CURRENT_TIMESTAMP not null,
         description varchar(255),
         name varchar(255),
         channel_id uniqueidentifier,
         primary key (id)
);

INSERT INTO channel (id, description, name)
                VALUES ('22fff0e4-7b9d-4914-9d0e-d74805c4bb3e', 'CRM Channel, ' +
                            'Manage all customer activities through one portal', 'CRM');

INSERT INTO service_catalog (id, description, name, channel_id)
        VALUES ('c0a8010e-861d-12cd-8186-1e237f2d0001', 'Account Openining, ' +
                    'service to manage new customer account', 'Account Openining', '22fff0e4-7b9d-4914-9d0e-d74805c4bb3e');