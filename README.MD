Jsp/Servlets
===================

Example of the JSP/Servlet technologies. 

Contains two applications
- Excel parser - application that receives **word** and 
**excel** file, parses file and finds all occurrences of the word inside 
the file.
- Site crawler - application that crawles **CNN** site
**Sports** category, extracts news titles, url, author, date 
from html and shows to the user.

----------
Technologies
-------------

- javaee-api 8.0
- jsoup 1.11.2
- httpclient 4.5.5
- poi 3.17
- Gradle 4.2
- JBoss WildFly 11.0.0

----------
Installation
------------

To load dependencies
> $ gradle build

To package war file
> $ gradle war
