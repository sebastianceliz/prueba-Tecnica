use prueba-tecnica

db.createUser({
  user: "admin",
  pwd: "sebas",
  roles: [
    { role: "readWrite", db: "prueba-tecnica" },
    { role: "dbAdmin", db: "prueba-tecnica" }
  ]
});