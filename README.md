# hn-library
© Andrei Romanovich

# Training project LIBRARY in IT academy (Java Developer program).<br/>
Project has a three-tier architecture.<br/>
Build-utility - Maven.<br/>
Frameworks - Hibernate, Spring, Apache Tiles.<br/>
DB - MySQL, driver - JDBC.<br/>
Simple JUnit is presented for dao module and Mock is presented for services module.<br/>
View - JSP (JSTL/EL), i18n.<br/>
Security - Spring Security.<br/>
# Structure
You can see the following directories:

    hn-domain/     All entities are involved in the functioning of the library<br/>
    hn-dao/        The classes implement connection and CRUD operation with the database check the validity<br/>
    hn-service/    Services checkes the validity of data before sending to dao and form the extracted data for web application<br/>
    hn-web/        Controllers, Filters and Coontrollers for processing request and response, and generation view through JSP+Til<br/>
