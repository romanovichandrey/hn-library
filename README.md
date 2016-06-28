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
    entities/      All entities are involved in the functioning of the library<br/>
    dao/           The classes implement connection and CRUD operation with the database check the validity<br/>
    services/      Services checkes the validity of data before sending to dao and form the extracted data for web application<br/>
    web/           Controllers, Filters and Coontrollers for processing request and response, and generation view through JSP+Til<br/>
