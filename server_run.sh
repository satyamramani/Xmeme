
./mvnw clean install

java -jar target/xmeme-0.0.1-SNAPSHOT.jar &


while ! netstat -tna | grep 'LISTEN\>' | grep -q ':8080\>'; do
  echo "waiting for spring application to start"
  sleep 2 # time in seconds, tune it as needed
done

# If you have any script to load the data make sure that its part of this bash script.

if systemctl status mongodb.service | grep active > /dev/null; then
    echo "MongoDB is running..."
else
    echo "MongoDB not running; Exiting"
    exit -1
fi
