# SpringSecurityDemo
Spring Security Demo using Java 17 and docker 


C:\Windows\system32>docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=springsecuritydemo --name MYSQL_SpringSecurityProject -p 3306:3307 mysql