# prueba-Tecnica

Saludos , el proyecto está con java 17 y gradle , para levantar las base de datos se debe ingresar a la carpeta donde se encuentra docker-compose que es "PruebaTecnica1"  (ejemplo en consola : cd pruebatecnica1).

El archivo properties esta configurado para conectarse a la bd, en todo caso revisar el docker-compose

Luego ejecutar el comando " docker-compose up -d ", se generará las bases de datos en un contenedor, en caso no se cree la de mongo DB , levantar manualmente mongoDB  y ejecutar el siguiente comando: 

use prueba-tecnica
db.createUser({
  user: "admin",
  pwd: "sebas",
  roles: [
    { role: "readWrite", db: "prueba-tecnica" },
    { role: "dbAdmin", db: "prueba-tecnica" }
  ]
});

Correr la clase main llamada "PruebaTecnica1Application" , una vez levantado el proyecto se setearan clientes y productos automaticamente en la bd de postgres gracias a flyway, para ver esa data ejecutar " http://localhost:8080/getAllProducts " y " http://localhost:8080/getAllClients " ya que se usaran los IDS para crear pedidos

en el controlador PedidosController hay indicaciones para probar el CRUD de pedidos en mongoDB 

para correr los test unitarios ejecutar ./gradlew test

Cualquier duda o bloqueante comunicarse conmigo al 958659659


