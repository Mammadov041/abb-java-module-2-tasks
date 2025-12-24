CREATE TABLE public.cars(
                     id INT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
                     name VARCHAR(20) NOT NULL ,
                     color VARCHAR(15) NOT NULL ,
                     speed INT NOT NULL,
                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)