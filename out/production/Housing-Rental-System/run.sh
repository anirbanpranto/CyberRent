if [[ -f "target/Game.jar" ]]; then
  java -jar target/Game.jar
else
  echo "Couldn't find game executable, perhaps you forget to build?"
  exit 1
fi
exit 0