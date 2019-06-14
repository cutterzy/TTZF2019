create table internet_cafe(
                            id varchar(255) primary key not null ,
                            cafe_name varchar(255),
                            cafe_size varchar(20),
                            cafe_unit varchar(2),
                            cafe_manager_id varchar(255),
                            cafe_level varchar(2),
                            cafe_grade varchar(2),
                            cafe_work_time varchar(50),
                            cafe_notice varchar(255),
                            cafe_img varchar(255),
                            cafe_dimension numeric(12,10),
                            cafe_translate numeric(12,10),
                            cafe_create_time varchar(255),
                            cafe_create_operate varchar(255),
                            cafe_state numeric(2),
                            cafe_tag varchar(255),
                            cafe_price numeric(12,2)
);

drop table internet_cafe