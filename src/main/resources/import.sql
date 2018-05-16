insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1000, 'Komputery i oprogramowanie', null);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1001, 'Komputery', 1000);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1002, 'Stacjonarne', 1001);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1003, 'Laptopy', 1001);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1004, 'Inne komputery', 1001);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1005, 'Oprogramowanie', 1000);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1006, 'Systemy operacyjne', 1005);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1007, 'Programy typu office', 1005);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1008, 'Programy specjalistyczne', 1005);
insert into CATEGORY(ID, NAME, PARENT_CATEGORY_ID) values (1009, 'Programy antywirusowe', 1005);

insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2000, 'Dell Optiplex 3010', 1002, 3, 0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2001, 'Dell Optiplex 3020', 1002, 5,0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2002, 'Dell XPS', 1003, 10,0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2003, 'Dell Latitude', 1003, 1,0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2004, 'MS Windows 8', 1006, 2,0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2005, 'MS Windows 10', 1006, 0,0);
insert into PRODUCT(ID, NAME, CATEGORY_ID, STOCK, version) values (2006, 'MS Office 2015', 1007, 4,0);

