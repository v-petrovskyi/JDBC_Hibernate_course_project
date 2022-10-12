USE hibernate_course_project;
INSERT INTO hibernate_course_project.user_role(hibernate_course_project.user_role.role_name,
                                               hibernate_course_project.user_role.role_description)
VALUES ('USER', 'standard user'),
       ('ADMIN', 'administrator'),
       ('SUPER_ADMIN', 'main administrator');

INSERT INTO hibernate_course_project.profile(hibernate_course_project.profile.first_name,
                                             hibernate_course_project.profile.last_name,
                                             hibernate_course_project.profile.email,
                                             hibernate_course_project.profile.phone_number,
                                             hibernate_course_project.profile.postal_code)
VALUES ('Maria', 'Anders', 'mar_an@mail.com', '(171) 555-2222', 'EC1 4SD'),
       ('Shelley', 'Burke', 'sh_bu@mail.com', '(100) 555-4822', '70117'),
       ('Regina', 'Murphy', 're_mu@mail.com', '(313) 555-5735', '48104'),
       ('Yoshi', 'Nagase', 'yo_na@mail.com', '(03) 3555-5011', '100'),
       ('Antonio', 'del Valle Saavedra', 'an_sa@mail.com', '33007', '(98) 598 76 54');

INSERT INTO hibernate_course_project.user (hibernate_course_project.user.user_name,
                                           hibernate_course_project.user.password,
                                           hibernate_course_project.user.user_role_id,
                                           hibernate_course_project.user.profile_id)
VALUES ('mar_an', 'mar_an', 1, 1),
       ('sh_bu', 'sh_bu', 1, 2),
       ('re_mu', 're_mu', 1, 3),
       ('yo_na', 'yo_na', 2, 4),
       ('an_sa', 'an_sa', 3, 5),
       ('1','1',3,null),
       ('2','2',1,null);

INSERT INTO hibernate_course_project.service(hibernate_course_project.service.service_name, hibernate_course_project.service.is_active, hibernate_course_project.service.service_month_price, hibernate_course_project.service.customer_id)
VALUES ('Tarif S', 1, 99.99, 6846548),
       ('Tarif M', 1, 149.99, 542),
       ('Tarif L', 1, 199.99, 12545),
       ('Movie', 1, 49.99, 68548),
       ('Serials', 1, 39.99, 152),
       ('Tarif XXXL', 0, 299.9, 15424);

INSERT INTO hibernate_course_project.user_services(hibernate_course_project.user_services.service_id, hibernate_course_project.user_services.user_id)
VALUES (1,1),
       (1,3),
       (4,2),
       (5,2),
       (5,1);

INSERT INTO hibernate_course_project.incident(hibernate_course_project.incident.service_name, hibernate_course_project.incident.is_active, hibernate_course_project.incident.problem_description, hibernate_course_project.incident.user_id)
VALUES ('payment',0,'проблеми із оплатою',1),
       ('payment',1,'проблеми із оплатою',2),
       ('technical issue',1,'несправність на лінії',2),
       ('ремонт антени', 1, 'ремонт антени клієнта',3),
       ('підключення нового пристрою', 0, 'підключення пристрою',3);


