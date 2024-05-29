db.createUser({
  user: "root",
  pwd: "sebas",
  roles: [
    { role: "readWrite", db: "prueba" },
    { role: "dbAdmin", db: "prueba" }
  ]
});