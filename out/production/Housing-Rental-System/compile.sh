echo "Compiling sources..."
if [[ -d "target/bin" ]]; then
  rm -r target
fi
mkdir -p target/bin
if javac -d ./target/bin -cp ./src ./src/me/samoa/chess/Main.java ; then
  echo "Compile success!"
else
  echo "Compile failed!"
  exit 1
fi

echo "Packaging binaries..."
cp -r src/resources target/bin/
if jar cfm target/Game.jar manifest.txt -C target/bin . ; then
  echo "Package success!"
else
  echo "Package failed!"
  exit 1
fi

exit 0