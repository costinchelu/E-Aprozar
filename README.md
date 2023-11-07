# Exemplu - proiect Spring + JSP + Spring Security + Vanilla JDBC


> Aplicație cu rol de demonstrare a componentelor unui magazin online.

Aplicatia este scrisă în Java folosind Spring MVC.

* Pentru partea de backend s-a folosit Hibernate.    
* Baza de date utilizată este PosgreSQL. Poate rula dintr-un container Docker folosind : `docker run -d --name postgres1 -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword postgres`. Pentru crearea și popularea bazei de date se pot folosi script-urile din directorul **./sql**   
* Pentru frontend (server-side) s-a folosit tehnologia Java Server Pages.  
* Aplicația rulează folosind un server Tomcat.

Capturi de ecran:

![Inregistrare utilizator](./screenshots/snap00137.png)

![Login. Validări](./screenshots/snap00139.png)

![Info utilizator](./screenshots/snap00140.png)

![Inregistrare utilizator](./screenshots/snap00137.png)

![Lista comenzi efectuate](./screenshots/snap00143.png)

![Vanzare articole. Ecran principal](./screenshots/snap00144.png)

![Lista produse](./screenshots/snap00145.png)

![Coș de cumpărături](./screenshots/snap00146.png)

![Coș de cumpărături actualizat](./screenshots/snap00147.png)

![Checkout](./screenshots/snap00148.png)

![Listă persistentă de comenzi](./screenshots/snap00149.png)

![Gestiune produse din contul de ADMIN](./screenshots/snap00153.png)

![Listă produse cu posibilitatea editării detaliilor](./screenshots/snap00150.png)

![Detalii editabile pentru produs](./screenshots/snap00151.png)



