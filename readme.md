docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_DATABASE=springsecuritydemo --name MYSQL_SpringSecurityProject -p 3306:3306 mysql



C:\Windows\system32>docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_DATABASE=springsecuritydemo --name MYSQL_SpringSecurityProject -p 3306:3307 mysql