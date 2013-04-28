mvn clean install
cp target/Hotel.war /usr/jboss-6.1.0/server/default/deploy
less -F /usr/jboss-6.1.0/server/default/log/server.log
